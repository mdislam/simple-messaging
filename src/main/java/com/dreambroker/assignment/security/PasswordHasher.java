package com.dreambroker.assignment.security;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class PasswordHasher {

	public String hashPassword(String password) {
		return BCrypt.hashpw(password, BCrypt.gensalt());
	}

	public boolean passwordMatches(String candidate, String hash) {
		return BCrypt.checkpw(candidate, hash);
	}
}
