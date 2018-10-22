package com.dreambroker.assignment.authentication;

import com.dreambroker.assignment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class DBAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private UserService userService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String name = authentication.getPrincipal().toString();
		String password = authentication.getCredentials().toString();

		userService.authenticateUser(name, password);

		return new UsernamePasswordAuthenticationToken(name, password, new ArrayList<>());
	}

	@Override
	public boolean supports(Class<?> aClass) {
		return true;
	}
}
