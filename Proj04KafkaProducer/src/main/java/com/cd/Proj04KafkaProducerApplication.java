package com.cd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class Proj04KafkaProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(Proj04KafkaProducerApplication.class, args);
	}

}
