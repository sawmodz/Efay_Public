package fr.eni.efay.dal.interfaces;

import fr.eni.efay.bo.Product;
import fr.eni.efay.bo.State;

import java.util.List;

public interface ProductDAO {
    List<Product> getAll();
    List<Product> getAllByCategory(long category_id);
    public void insert(Product product);
    Product findById(long id);
    List<Product> getAllBySellerId(long seller_id);
    void changeState(State state, long id);
    List<Product> search(String search);
}
