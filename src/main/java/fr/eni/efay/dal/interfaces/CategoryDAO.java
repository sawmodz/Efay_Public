package fr.eni.efay.dal.interfaces;

import fr.eni.efay.bo.Category;

import java.util.List;

public interface CategoryDAO {
    List<Category> getAll();
    Category findById(long id);
}
