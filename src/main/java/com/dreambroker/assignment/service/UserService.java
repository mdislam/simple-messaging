package com.dreambroker.assignment.service;

import com.dreambroker.assignment.mapper.UserMapper;
import com.dreambroker.assignment.model.RegistrationUser;
import com.dreambroker.assignment.model.User;
import com.dreambroker.assignment.security.PasswordHasher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

	@Autowired
	UserMapper mapper;

	@Autowired
	PasswordHasher hasher;

	public void createUser(RegistrationUser user) {
		createUser(user.getName(), user.getPassword());
	}

	public void createUser(String userName, String password) {

		if (userName == null || userName.trim().isEmpty() || password == null || password.trim().isEmpty()) {
			throw new IllegalArgumentException("Username or password is empty or null");
		}

		User user = mapper.findUserByName(userName);
		if (user != null) {
			throw new UserNameTakenException("Username is already taken");
		}

		String hashedPassword = hasher.hashPassword(password);

		mapper.createUser(userName, hashedPassword);
	}

	public Optional<User> findUserByName(String name) {
		User user = mapper.findUserByName(name);

		if (user == null) {
			return Optional.empty();
		}

		return Optional.of(user);
	}

	public User authenticateUser(String username, String password) throws AuthenticationException {
		User user = mapper.findUserByName(username);

		if (user == null) {
			throw new UsernameNotFoundException("No such user");
		}

		if (!hasher.passwordMatches(password, user.getPassword())) {
			throw new BadCredentialsException("Invalid password");
		}

		return user;
	}

	public Optional<User> getAuthenticatedUser() {
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		return findUserByName(name);
	}
}
