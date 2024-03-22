package fr.eni.efay.dal;

import fr.eni.efay.bo.Category;
import fr.eni.efay.dal.interfaces.CategoryDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryDAOImpl implements CategoryDAO {
    final String GET_ALL = "SELECT * FROM category";
    final String FIND_BY_ID = "SELECT * FROM category WHERE id=:id";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    RowMapper<Category> mapper = (rs, i) -> {
        Category category = new Category();

        category.setCategory_id(rs.getLong("id"));
        category.setName(rs.getString("name"));
        category.setIcon(rs.getString("icon"));

        return category;
    };

    @Override
    public List<Category> getAll() {
        return jdbcTemplate.query(GET_ALL, mapper);
    }

    @Override
    public Category findById(long id) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("id", id);
        Category category = namedParameterJdbcTemplate.queryForObject(FIND_BY_ID,namedParameters, mapper);
        return category;
    }
}
