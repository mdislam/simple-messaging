package com.dreambroker.assignment.controller;

import com.dreambroker.assignment.model.Message;
import com.dreambroker.assignment.service.MessageService;
import com.dreambroker.assignment.model.ClientMessage;
import com.dreambroker.assignment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
public class ChatController {

	@Autowired
	MessageService messageService;

	@GetMapping("/")
	public ModelAndView getUser() {
		return new ModelAndView("chat");
	}

	@GetMapping("/oldmessages")
	@ResponseBody
	public List<Message> existingMessages() {
		return messageService.getMessages();
	}


	@MessageMapping("/chat")
	@SendTo("/subscribe/messages")
	public Message sendMessage(ClientMessage message) {
		Message serverMessage = messageService.createMessageFromClientMessage(message);
		messageService.saveMessage(serverMessage);
		return serverMessage;
	}
}
