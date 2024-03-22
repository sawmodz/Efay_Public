package fr.eni.efay.bll;

import fr.eni.efay.bll.interfaces.RegisterService;
import fr.eni.efay.bo.User;
import fr.eni.efay.dal.interfaces.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private UserDAO userDAO;

    @Transactional
    public void insert(User user) {
        userDAO.insert(user);
    }

    @Override
    public List<User> getAll() {
        return userDAO.getAll();
    }

    @Override
    public User findById(Long id) {
        return userDAO.findById(id);
    }
}
