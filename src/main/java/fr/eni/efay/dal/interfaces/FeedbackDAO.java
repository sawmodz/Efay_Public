package fr.eni.efay.dal.interfaces;


import fr.eni.efay.bo.Feedback;


import java.util.List;

public interface FeedbackDAO {
    List<Feedback> getAll();

    List<Feedback> getAllBySellerId(long seller_id);
    List<Feedback> getAllByBuyerId(long buyer_id);

    public void insert(Feedback feedback);

    Feedback findById(long id);


}
