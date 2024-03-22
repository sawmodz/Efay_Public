package fr.eni.efay.bll.interfaces;

import fr.eni.efay.bo.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAll();
    Category findById(long id);
}
