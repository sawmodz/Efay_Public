package fr.eni.efay.dal.interfaces;

import fr.eni.efay.bo.History;

import java.util.List;

public interface HistoryDAO {
    List<History> getAllByUserId(long id);
    void insert(History history);
}
