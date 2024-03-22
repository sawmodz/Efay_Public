package fr.eni.efay.bll;

import fr.eni.efay.bll.interfaces.UserService;
import fr.eni.efay.bo.User;
import fr.eni.efay.dal.interfaces.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDAO userDAO;

    @Autowired
    PasswordEncoder encodeur;

    public List<User> getAll() {

        return userDAO.getAll();
    }

    @Transactional
    public void insert(User user){
        // encodage du mot de passe
        user.setPassword(encodeur.encode(user.getPassword()));

        // insertion en base
        userDAO.insert(user);
    }

    @Override
    public User findByPseudo(String pseudo) {
        return userDAO.findByPseudo(pseudo);
    }

    @Override
    public User findById(Long id) {
        return userDAO.findById(id);
    }

    @Override
    public void update(User user) {
        user.setPassword(encodeur.encode(user.getPassword()));
        userDAO.update(user);
    }

    @Override
    public void removeMoney(User user, long moneyToRemove) {userDAO.removeMoney(user, moneyToRemove);}

    @Override
    public void addMoney(User user, long moneyToAdd) {userDAO.addMoney(user, moneyToAdd); }

    @Override
    public void delete(String username) {userDAO.delete(username);}

    @Override
    public void changeRole(String username, String role) {userDAO.changeRole(username, role);}
}