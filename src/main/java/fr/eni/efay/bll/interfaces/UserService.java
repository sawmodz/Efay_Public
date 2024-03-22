package fr.eni.efay.bll.interfaces;

import fr.eni.efay.bo.User;

import java.util.List;

public interface UserService {
    List<User> getAll();
    public void insert(User user);
    User findByPseudo(String pseudo);
    User findById(Long id);
    public void update(User user);
    void removeMoney(User user, long moneyToRemove);
    void addMoney(User user, long moneyToAdd);
    void delete(String username);
    void changeRole(String username, String role);
}
