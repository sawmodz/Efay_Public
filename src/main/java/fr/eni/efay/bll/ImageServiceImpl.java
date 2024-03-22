package fr.eni.efay.bll;

import fr.eni.efay.bll.interfaces.ImageService;
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
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    ImageDAO imageDAO;

    @Override
    public void insert(Image image) {
        imageDAO.insert(image);
    }

    @Override
    public Image getById(long id) {
        return imageDAO.getById(id);
    }

    @Override
    public Image uploadImage(MultipartFile file) { return imageDAO.uploadImage(file); }
}
