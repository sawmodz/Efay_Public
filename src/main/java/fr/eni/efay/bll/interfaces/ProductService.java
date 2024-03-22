package fr.eni.efay.bll.interfaces;

import fr.eni.efay.bo.Product;
import fr.eni.efay.bo.State;

import java.util.List;

public interface ProductService {
    List<Product> getAll();
    List<Product> getAllByCategory(long category_id);
    void insert(Product product);
    Product findById(long id);
    List<Product> getAllBySellerId(long seller_id);

    List<Product> search(String search);
    void changeState(State state, long id);
}
