package com.graodireto.quemtemboca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.graodireto.quemtemboca.entity.User;
import com.graodireto.quemtemboca.repository.user.IUserRepository;

@Service
public class UserService extends AbstractService<User> {

	@Autowired
	private IUserRepository userRepository;
	
	@Override
	public IUserRepository getRepository() {
		return userRepository;
	}
	
	public User findByEmailAndPassword(String email, String password) {
		User user = getRepository().findByEmail(email);
		if (user != null) {
			Boolean senhaCorreta = BCrypt.checkpw(password, user.getPassword());
			if (senhaCorreta == true) {
				return user;
			}
		}
		return null;
	}

	public User findByEmail(String email) {
		return getRepository().findByEmail(email);
	}
}
