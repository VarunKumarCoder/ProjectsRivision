package com.cd.subscriber;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.cd.entity.IndianFestival;

@Component
public class KafkaMessageSubscriber {

	private IndianFestival festival;
	
	@KafkaListener(topics="${app.tpc.name}",groupId="grp1")
	public void readMessage(IndianFestival festival) {
		this.festival=festival;
		System.out.println("Message is:: "+festival);
	}
	
	public IndianFestival getCurrentMessage() {
		return festival;
	}
}
