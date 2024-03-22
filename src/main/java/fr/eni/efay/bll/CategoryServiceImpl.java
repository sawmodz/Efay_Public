package fr.eni.efay.bll;

import fr.eni.efay.bll.interfaces.CategoryService;
import fr.eni.efay.bo.Category;
import fr.eni.efay.dal.interfaces.CategoryDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryDAO categoryDAO;

    @Override
    public List<Category> getAll() {
        return categoryDAO.getAll();
    }

    @Override
    public Category findById(long id) {
        return categoryDAO.findById(id);
    }
}
