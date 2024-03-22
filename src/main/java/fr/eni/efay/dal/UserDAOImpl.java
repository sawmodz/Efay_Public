package fr.eni.efay.dal;


import fr.eni.efay.bo.User;
import fr.eni.efay.dal.interfaces.UserDAO;
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
public class UserDAOImpl implements UserDAO {

    final String SELECT = "SELECT * FROM users";
    final String INSERT = "INSERT INTO users(name, surname, username, role, email, sold, password) VALUES(:name, :surname, :username, :role, :email, :sold, :password)";
    final String SELECT_BY_USERNAME = "SELECT * FROM users WHERE username=:username";
    final String SELECT_BY_ID = "SELECT * FROM users WHERE id =:id";
    final String UPDATE = "UPDATE users SET username=:username, email=:email, address=:address WHERE id =:id";
    final String UPDATE_SOLD = "UPDATE users SET sold = :sold WHERE id = :id";
    final String DELETE = "DELETE FROM users WHERE username=:username";
    final String UPDATE_ROLE = "UPDATE users SET role = :role WHERE username = :username";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    RowMapper<User> mapper = (rs, i) -> {
        User user = new User();

        user.setId(rs.getLong("id"));
        user.setName(rs.getString("name"));
        user.setSurname(rs.getString("surname"));
        user.setRole(rs.getString("role"));
        user.setEmail(rs.getString("email"));
        user.setSold(rs.getLong("sold"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));

        return user;
    };

    @Override
    public List<User> getAll() {
        return jdbcTemplate.query(SELECT, mapper);
    }

    @Override
    public void insert(User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        BeanPropertySqlParameterSource namedParameters = new BeanPropertySqlParameterSource(user);
        namedParameterJdbcTemplate.update(INSERT, namedParameters,keyHolder);
        user.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());
    }

    @Override
    public User findByPseudo(String pseudo) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("username", pseudo);
        User user = namedParameterJdbcTemplate.queryForObject(SELECT_BY_USERNAME,namedParameters, mapper);
        return user;
    }

    @Override
    public User findById(long id) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("id", id);
        User user = namedParameterJdbcTemplate.queryForObject(SELECT_BY_ID,namedParameters, mapper);
        return user;
    }

    @Override
    public void update(User user) {
        BeanPropertySqlParameterSource namedParameters = new BeanPropertySqlParameterSource(user);
        namedParameterJdbcTemplate.update(UPDATE, namedParameters);
    }

    @Override
    public void removeMoney(User user, long moneyToRemove) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("sold", user.getSold() - moneyToRemove);
        namedParameters.addValue("id", user.getId());
        namedParameterJdbcTemplate.update(UPDATE_SOLD, namedParameters);
        user.setSold(user.getSold() - moneyToRemove);
    }

    @Override
    public void addMoney(User user, long moneyToRemove) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("sold", user.getSold() + moneyToRemove);
        namedParameters.addValue("id", user.getId());
        namedParameterJdbcTemplate.update(UPDATE_SOLD, namedParameters);
        user.setSold(user.getSold() + moneyToRemove);
    }

    @Override
    public void delete(String username) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("username", username);
        namedParameterJdbcTemplate.update(DELETE, namedParameters);
    }

    @Override
    public void changeRole(String username, String role) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("username", username);
        namedParameters.addValue("role", role);
        namedParameterJdbcTemplate.update(UPDATE_ROLE, namedParameters);
    }
}
