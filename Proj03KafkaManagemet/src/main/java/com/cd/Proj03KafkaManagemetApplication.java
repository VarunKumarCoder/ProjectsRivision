package com.cd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class Proj03KafkaManagemetApplication {

	public static void main(String[] args) {
		SpringApplication.run(Proj03KafkaManagemetApplication.class, args);
	}

}
