package fr.eni.efay.bll.interfaces;

import fr.eni.efay.bo.History;

import java.util.List;

public interface HistoryService {
    List<History> getAllByUserId(long id);
    void insert(History history);
}
