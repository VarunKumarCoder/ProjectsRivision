package com.cd.rest;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cd.entity.IndianFestival;
import com.cd.publlisher.KafkaMessageSender;
import com.nt.subscriber.KafkaMessageSubscriber;

@RestController
@RequestMapping("/message-api")
public class KafkaMessageOperationsController {

	@Autowired
	private KafkaMessageSender sender;
	@Autowired
	private KafkaMessageSubscriber subscriber;
	
	@PostMapping("/send")
	public ResponseEntity<String> pushMessage(@RequestBody IndianFestival festival){
		String msg=sender.sendMessage(festival);
		return new ResponseEntity<String>(msg,HttpStatusCode.valueOf(200));
	}
	
	public ResponseEntity<IndianFestival> readMessage(){
		IndianFestival festival=subscriber.getCurrentMessage();
		return new ResponseEntity<IndianFestival>(festival,HttpStatusCode.valueOf(200));
	}
}
