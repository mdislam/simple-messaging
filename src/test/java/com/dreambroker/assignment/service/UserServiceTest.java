package com.dreambroker.assignment.service;


import com.dreambroker.assignment.mapper.UserMapper;
import com.dreambroker.assignment.model.User;
import com.dreambroker.assignment.security.PasswordHasher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;

import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

	// mocking the DataSource prevents the annoying (but harmless) stacktrace, as the MySQL database is likely not
	// running when running unit tests
	@MockBean
	private DataSource dataSource;

	@Mock
	UserMapper mapper;

	@Mock
	PasswordHasher hasher;

	@InjectMocks
	UserService userService = new UserService();

	@Test(expected = UserNameTakenException.class)
	public void shouldThrowUserNameTakenExceptionIsUserAlreadyExists() {
		String name = "testuser";
		String password = "password";

		when(mapper.findUserByName(name)).thenReturn(mock(User.class));
		userService.createUser(name, password);
	}

	@Test(expected = UsernameNotFoundException.class)
	public void shouldThrowExceptionIfAuthenticatingWithUserThatDoesNotExist() {
		String name = "testuser";
		String password = "password";

		when(mapper.findUserByName(name)).thenReturn(null);
		userService.authenticateUser(name, password);
	}

	@Test(expected = BadCredentialsException.class)
	public void shouldThrowExceptionIfPasswordDoesNotMatch() {
		String name = "testuser";
		String password = "password";
		String correctHash = "thisisthecorrecthash";

		User user = mock(User.class);
		when(user.getPassword()).thenReturn(correctHash);
		when(mapper.findUserByName(name)).thenReturn(user);

		when(hasher.passwordMatches(password, correctHash)).thenReturn(false);
		userService.authenticateUser(name, password);
	}

	@Test
	public void shouldReturnTheUserWhenAuthenticationSucceeds() {
		String name = "testuser";
		String password = "password";
		String correctHash = "thisisthecorrecthash";

		User user = mock(User.class);
		when(user.getPassword()).thenReturn(correctHash);
		when(mapper.findUserByName(name)).thenReturn(user);

		when(hasher.passwordMatches(password, correctHash)).thenReturn(true);
		User authenticatedUser = userService.authenticateUser(name, password);

		// rare case of reference equality actually being used
		assertTrue(authenticatedUser == user);
	}
}
