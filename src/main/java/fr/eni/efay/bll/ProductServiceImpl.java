package fr.eni.efay.bll;

import fr.eni.efay.bll.interfaces.ProductService;
import fr.eni.efay.bo.Product;
import fr.eni.efay.bo.State;
import fr.eni.efay.dal.interfaces.ProductDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductDAO productDAO;

    @Override
    public List<Product> getAll() {
        return productDAO.getAll();
    }

    @Override
    public List<Product> getAllByCategory(long category_id) {
        return productDAO.getAllByCategory(category_id);
    }

    @Override
    @Transactional
    public void insert(Product product) {
        productDAO.insert(product);
    }

    @Override
    public Product findById(long id) {
        return productDAO.findById(id);
    }

    @Override
    public List<Product> getAllBySellerId(long seller_id) {
        return productDAO.getAllBySellerId(seller_id);
    }

    @Override
    public void changeState(State state, long id) { productDAO.changeState(state, id); }

    @Override
    public List<Product> search(String search) {return productDAO.search(search);}
}
