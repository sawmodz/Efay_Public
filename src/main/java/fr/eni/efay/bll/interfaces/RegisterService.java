package fr.eni.efay.bll.interfaces;

import fr.eni.efay.bo.User;

import java.util.List;

public interface RegisterService {

    void insert(User user);

    List<User> getAll();

    User findById(Long id);
}
