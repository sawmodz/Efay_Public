package fr.eni.efay.dal;

import fr.eni.efay.bo.Feedback;
import fr.eni.efay.dal.interfaces.FeedbackDAO;
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
public class FeedbackDAOImpl implements FeedbackDAO {

    final String GET_ALL = "SELECT * FROM feedback";
    final String GET_ALL_BY_SELLER_ID = "SELECT * FROM feedback WHERE seller_id=:seller_id";
    final String GET_ALL_BY_BUYER_ID = "SELECT * FROM feedback WHERE buyer_id=:buyer_id";
    final String INSERT = "INSERT INTO feedback(comment, note, buyer_id, seller_id) VALUES(:comment, :note, :buyer_id, :seller_id)";
    final String SELECT_BY_ID = "SELECT * FROM feedback WHERE id =:id";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    UserDAO userDAO;


    RowMapper<Feedback> mapper = (rs, i) -> {
        Feedback feedback = new Feedback();

        feedback.setFeedback_id(rs.getLong("id"));
        feedback.setComment(rs.getString("comment"));
        feedback.setNote(rs.getInt("note"));
        feedback.setBuyer_id(userDAO.findById(rs.getLong("buyer_id")));
        feedback.setSeller_id(userDAO.findById(rs.getLong("seller_id")));

        return feedback;
    };

    @Override
    public List<Feedback> getAll() {
        return jdbcTemplate.query(GET_ALL, mapper);
    }

    @Override
    public List<Feedback> getAllBySellerId(long seller_id) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("seller_id", seller_id);
        List<Feedback> feedback = namedParameterJdbcTemplate.query(GET_ALL_BY_SELLER_ID,namedParameters, mapper);
        return feedback;
    }

    @Override
    public List<Feedback> getAllByBuyerId(long buyer_id) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("buyer_id", buyer_id);
        List<Feedback> feedback = namedParameterJdbcTemplate.query(GET_ALL_BY_BUYER_ID,namedParameters, mapper);
        return feedback;
    }

    @Override
    public void insert(Feedback feedback) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();

        namedParameters.addValue("comment", feedback.getComment());
        namedParameters.addValue("note", feedback.getNote());
        namedParameters.addValue("buyer_id", (int)feedback.getBuyer_id().getId());
        namedParameters.addValue("seller_id", (int)feedback.getSeller_id().getId());

        namedParameterJdbcTemplate.update(INSERT, namedParameters, keyHolder);

        if (keyHolder != null && keyHolder.getKey() != null) {
            feedback.setFeedback_id(keyHolder.getKey().longValue());
        }
    }

    @Override
    public Feedback findById(long id) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("id", id);
        Feedback feedback = namedParameterJdbcTemplate.queryForObject(SELECT_BY_ID,namedParameters, mapper);
        return feedback;
    }
}
