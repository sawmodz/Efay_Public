package fr.eni.efay.dal;

import fr.eni.efay.bll.interfaces.ProductService;
import fr.eni.efay.bll.interfaces.UserService;
import fr.eni.efay.bo.Feedback;
import fr.eni.efay.bo.History;
import fr.eni.efay.dal.interfaces.HistoryDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public class HistoryDAOImpl implements HistoryDAO {

    JdbcTemplate jdbcTemplate;
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    ProductService productService;
    UserService userService;

    RowMapper<History> mapper = (rs, i) -> {
        History history = new History();

        history.setId(rs.getLong("id"));
        history.setProduct(productService.findById(rs.getLong("product_id")));
        history.setUser(userService.findById(rs.getLong("user_id")));
        history.setDate(rs.getDate("date").toLocalDate());

        return history;
    };

    String GET_ALL_BY_USER_ID = "SELECT * FROM history WHERE user_id = :user_id";
    String INSERT = "INSERT INTO history (product_id, user_id, date) VALUES (:product_id, :user_id, :date)";

    public HistoryDAOImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate, ProductService productService, UserService userService) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.productService = productService;
        this.userService = userService;
    }

    @Override
    public List<History> getAllByUserId(long id) {
        return namedParameterJdbcTemplate.query(GET_ALL_BY_USER_ID, new MapSqlParameterSource("user_id", id), mapper);
    }

    @Override
    public void insert(History history) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("product_id", history.getProduct().getProduct_id());
        namedParameters.addValue("user_id", history.getUser().getId());
        namedParameters.addValue("date", history.getDate());
        namedParameterJdbcTemplate.update(INSERT, namedParameters, keyHolder);
        history.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());
    }
}
