package com.dreambroker.assignment.service;

import com.dreambroker.assignment.mapper.MessageMapper;
import com.dreambroker.assignment.model.ClientMessage;
import com.dreambroker.assignment.model.Message;
import com.dreambroker.assignment.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MessageServiceTest {

	// mocking the DataSource prevents the annoying (but harmless) stacktrace, as the MySQL database is likely not
	// running when running unit tests
	@MockBean
	private DataSource dataSource;

	@Mock
	private MessageMapper mapper;

	@Mock
	private UserService userService;

	@InjectMocks
	private MessageService messageService = new MessageService();


	@Test
	public void shouldReturnAListOfAllMessages() {
		String sender = "sender";
		String message = "message";
		Date date = new Date();

		String sender2 = "sender2";
		String message2 = "message2";
		Date date2 = new Date();

		List<Message> list = Arrays.asList(new Message[] {
						new Message(sender, message, date),
						new Message(sender2, message2, date2)});

		when(mapper.getMessages()).thenReturn(list);

		List<Message> returnList = messageService.getMessages();

		assertEquals(sender, returnList.get(0).getSender());
		assertEquals(message, returnList.get(0).getMessage());
		assertEquals(date, returnList.get(0).getSent());

		assertEquals(sender2, returnList.get(1).getSender());
		assertEquals(message2, returnList.get(1).getMessage());
		assertEquals(date2, returnList.get(1).getSent());
	}

	@Test
	public void shouldCreateMessageWhenUserIsAuthenticated() {
		Date date = new Date();

		String name = "user";
		String message = "hello, world";
		ClientMessage clientMessage = new ClientMessage();
		clientMessage.setMessage(message);

		User user = mock(User.class);
		when(user.getName()).thenReturn(name);

		when(userService.getAuthenticatedUser()).thenReturn(Optional.of(user));
		Message retMsg = messageService.createMessageFromClientMessage(clientMessage);

		assertEquals(retMsg.getSender(), name);
		assertEquals(retMsg.getMessage(), message);
		assertFalse(retMsg.getSent().before(date));
	}

	@Test
	public void shouldSaveTheMessageWhenDetailsAreCorrect() {
		String name = "name";
		long id = 123456789;
		String message = "message";
		Date date = new Date();

		User user = mock(User.class);
		when(user.getId()).thenReturn(id);

		when(userService.findUserByName(name)).thenReturn(Optional.of(user));
		Message m =	new Message(name, message, date);
		messageService.saveMessage(m);
		verify(mapper, times(1)).createMessage(id, message, date);
	}
}


