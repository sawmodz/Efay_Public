package fr.eni.efay.security;

import fr.eni.efay.bo.User;
import fr.eni.efay.dal.interfaces.UserDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UserDAO userDAO;

	@Override
	public UserDetails loadUserByUsername(String pseudo) {

		User user;
		try {
			user = userDAO.findByPseudo(pseudo);
			if (user == null) {
				throw new UsernameNotFoundException(pseudo);
			}
		} catch (Exception e) {
			throw new UsernameNotFoundException(pseudo);
		}
		return new MyUserDetail(user);
	}
}