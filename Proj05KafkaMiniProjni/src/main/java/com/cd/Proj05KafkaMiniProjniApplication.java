package com.cd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka	
public class Proj05KafkaMiniProjniApplication {

	public static void main(String[] args) {
		SpringApplication.run(Proj05KafkaMiniProjniApplication.class, args);
	}

}
