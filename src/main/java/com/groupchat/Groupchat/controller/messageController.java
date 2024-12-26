package com.groupchat.Groupchat.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.groupchat.Groupchat.models.Message;

@RestController
public class messageController {
	
	@MessageMapping("/message")
	@SendTo("/topic/return-to")
	 public Message getMessage(@RequestBody Message message) {
		 try {
			 Thread.sleep(1000);
		 }
		 catch(Exception e) {
			 System.out.println(e);
		 }
		return message;
		 
	 }

}
