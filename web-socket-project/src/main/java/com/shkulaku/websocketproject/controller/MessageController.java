package com.shkulaku.websocketproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

import com.shkulaku.websocketproject.model.MessageModel;
import com.shkulaku.websocketproject.sorage.UserStorage;

@RestController
public class MessageController {
	
	@Autowired
	private SimpMessagingTemplate template;
	
	@MessageMapping("chat/{to}")
	public void sendMessage(@DestinationVariable String to, MessageModel mesageMessageModel){
		System.out.println("handling send message: "+ mesageMessageModel+ " to: "+to);
		boolean isExists = UserStorage.getInstance().getUsers().contains(to);
		if(isExists) {
			template.convertAndSend("/topic/messages/" + to, mesageMessageModel);
		}
	} {
		
	}

}
