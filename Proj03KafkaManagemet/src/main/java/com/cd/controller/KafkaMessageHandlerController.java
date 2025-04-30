package com.cd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cd.consumer.MessageStore;
import com.cd.producer.MessageProducer;

@RestController
public class KafkaMessageHandlerController {

	@Autowired
	private MessageProducer producer;
	@Autowired
	private MessageStore store;
	
	@GetMapping("/send")
	public String sendMessage(@RequestParam("message")String message) {
		String status=producer.sendMessage(message);
		return status;
	}
	
	@GetMapping("/readAll")
	public String fetchAllMessages() {
		return store.getAllMessages();
	}
}
