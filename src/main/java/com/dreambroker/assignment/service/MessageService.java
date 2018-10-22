package com.dreambroker.assignment.service;

import com.dreambroker.assignment.mapper.MessageMapper;
import com.dreambroker.assignment.model.ClientMessage;
import com.dreambroker.assignment.model.Message;
import com.dreambroker.assignment.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MessageService {

	@Autowired
	private UserService userService;

	@Autowired
	private MessageMapper mapper;

	public Message createMessageFromClientMessage(ClientMessage message) {

		User user = userService.getAuthenticatedUser().orElseThrow(
				() -> new IllegalStateException("Must not be invoked without user authentication"));

		return new Message(user.getName(), message.getMessage(), new Date());
	}

	public void saveMessage(Message message) {
		User user = userService.findUserByName(message.getSender()).orElseThrow(
				() -> new IllegalStateException("No matching sender was found"));
		mapper.createMessage(user.getId(), message.getMessage(), message.getSent());
	}

	public List<Message> getMessages() {
		return mapper.getMessages();
	}

}
