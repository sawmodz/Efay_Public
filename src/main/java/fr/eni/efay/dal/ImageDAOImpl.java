package fr.eni.efay.dal;

import fr.eni.efay.bo.Image;
import fr.eni.efay.dal.interfaces.ImageDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@Repository
public class ImageDAOImpl implements ImageDAO {

    final String INSERT = "INSERT INTO image() VALUES()";
    final String GET_BY_ID = "SELECT * FROM image WHERE id=:id";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    RowMapper<Image> mapper = (rs, i) -> {
        Image image = new Image();

        image.setImage_id(rs.getLong("id"));

        return image;
    };

    @Override
    public void insert(Image image) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        BeanPropertySqlParameterSource namedParameters = new BeanPropertySqlParameterSource(image);
        namedParameterJdbcTemplate.update(INSERT, namedParameters,keyHolder);
        image.setImage_id(Objects.requireNonNull(keyHolder.getKey()).longValue());
    }

    @Override
    public Image getById(long id) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("id", id);
        return namedParameterJdbcTemplate.queryForObject(GET_BY_ID, namedParameters, mapper);
    }

    @Override
    public Image uploadImage(MultipartFile file){
        try {
            Path path = Paths.get("/opt/tomcat/latest/webapps/ROOT/WEB-INF/classes/static/img/upload");

            byte[] imageContent = file.getBytes();
            Files.createDirectories(path);

            Image image = new Image();
            this.insert(image);

            Path filepath = Paths.get(path.toString(), image.getImage_id() + ".jpg");

            OutputStream os = Files.newOutputStream(filepath);
            os.write(imageContent);
            return image;
        } catch (IOException e) {
            return new Image();
        }
    }
}
