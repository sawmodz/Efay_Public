package fr.eni.efay.dal;

import fr.eni.efay.bo.Product;
import fr.eni.efay.bo.State;
import fr.eni.efay.dal.interfaces.CategoryDAO;
import fr.eni.efay.dal.interfaces.ImageDAO;
import fr.eni.efay.dal.interfaces.ProductDAO;
import fr.eni.efay.dal.interfaces.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Objects;

@Repository
public class ProductDAOImpl implements ProductDAO {
    final String SELECT = "SELECT * FROM product";
    final String GET_ALL_BY_CATEGORY = "SELECT * FROM product WHERE category_id=:category_id";
    final String INSERT = "INSERT INTO `product`(`name`, `category_id`, `description`, `price`, `user_id`, `image_id`, `state`) VALUES (:name,:category_id,:description,:price,:user_id,:image_id,:state)";
    final String FIND_BY_ID = "SELECT * FROM product WHERE id=:id";
    final String GET_ALL_BY_SELLER_ID = "SELECT * FROM product WHERE user_id=:user_id";

    final String CHANGE_STATE = "UPDATE product SET state=:state WHERE id=:id";

    final String SEARCH = "SELECT * FROM product WHERE name LIKE CONCAT('%', :search, '%')";



    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    CategoryDAO categoryDAO;

    @Autowired
    UserDAO userDAO;

    @Autowired
    ImageDAO imageDAO;


    RowMapper<Product> mapper = (rs, i) -> {
        Product product = new Product();

        product.setProduct_id(rs.getLong("id"));
        product.setName(rs.getString("name"));
        product.setCategory_id(categoryDAO.findById(rs.getLong("category_id")));
        product.setDescription(rs.getString("description"));
        product.setPrice(rs.getLong("price"));
        product.setUser_id(userDAO.findById(rs.getLong("user_id")));
        product.setState(State.valueOf(rs.getString("state")));
        product.setImage_id(imageDAO.getById(rs.getLong("image_id")));

        return product;
    };

    @Override
    public List<Product> getAll() {
        return jdbcTemplate.query(SELECT, mapper);
    }

    @Override
    public List<Product> getAllByCategory(long category_id) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("category_id", category_id);
        List<Product> product = namedParameterJdbcTemplate.query(GET_ALL_BY_CATEGORY,namedParameters, mapper);
        return product;
    }

    @Override
    public void insert(Product product) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();

        namedParameters.addValue("name", product.getName());
        namedParameters.addValue("category_id", (int)product.getCategory_id().getCategory_id());
        namedParameters.addValue("description", product.getDescription());
        namedParameters.addValue("price", (int)product.getPrice());
        namedParameters.addValue("user_id", (int)product.getUser_id().getId());
        namedParameters.addValue("image_id", (int)product.getImage_id().getImage_id());
        namedParameters.addValue("state", product.getState().toString());

        namedParameterJdbcTemplate.update(INSERT, namedParameters, keyHolder);

        if (keyHolder != null && keyHolder.getKey() != null) {
            product.setProduct_id(keyHolder.getKey().longValue());
        }

    }
    @Override
    public Product findById(long id) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("id", id);
        Product product = namedParameterJdbcTemplate.queryForObject(FIND_BY_ID,namedParameters, mapper);
        return product;
    }

    @Override
    public List<Product> getAllBySellerId(long seller_id) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("user_id", seller_id);
        List<Product> product = namedParameterJdbcTemplate.query(GET_ALL_BY_SELLER_ID,namedParameters, mapper);
        return product;
    }

    @Override
    public void changeState(State state, long id) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("state", state.toString());
        namedParameters.addValue("id", id);
        namedParameterJdbcTemplate.update(CHANGE_STATE, namedParameters);
    }

    @Override
    public List<Product> search(String search) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("search", search);
        return namedParameterJdbcTemplate.query(SEARCH,namedParameters, mapper);
    }

}