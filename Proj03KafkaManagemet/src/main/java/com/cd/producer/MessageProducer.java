package com.cd.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;


@Component("msgProducer")
public class MessageProducer {

	@Autowired
	private KafkaTemplate<String, String> template;
	@Value("${app.topic.name}")
	private String topicName;
	
	public String sendMessage(String message) {
		template.send(topicName,message);
		return "Message Delivered";
	}
}
