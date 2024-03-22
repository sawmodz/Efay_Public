package fr.eni.efay.bll;

import fr.eni.efay.bll.interfaces.FeedbackService;
import fr.eni.efay.bo.Feedback;
import fr.eni.efay.dal.interfaces.FeedbackDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    FeedbackDAO feedbackDAO;
    @Override
    public List<Feedback> getAll() {
        return feedbackDAO.getAll();
    }

    @Override
    public List<Feedback> getAllBySellerId(long seller_id) {
        return feedbackDAO.getAllBySellerId(seller_id);
    }

    @Override
    public List<Feedback> getAllByBuyerId(long buyer_id) {
        return feedbackDAO.getAllByBuyerId(buyer_id);
    }

    @Override
    public void insert(Feedback feedback) {
        feedbackDAO.insert(feedback);
    }

    @Override
    public Feedback findById(long id) {
        return feedbackDAO.findById(id);
    }
}
