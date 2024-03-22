package fr.eni.efay.bll;

import fr.eni.efay.bll.interfaces.HistoryService;
import fr.eni.efay.bo.History;
import fr.eni.efay.dal.interfaces.HistoryDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryServiceImpl implements HistoryService {
    @Autowired
    HistoryDAO historyDAO;


    @Override
    public List<History> getAllByUserId(long id) {
        return historyDAO.getAllByUserId(id);
    }

    @Override
    public void insert(History history) {
        historyDAO.insert(history);
    }
}
