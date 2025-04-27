package com.cd.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cd.entity.Tourist;
import com.cd.service.ITouristMgmtService;

@RestController
public class TouristOperationsController {

	private ITouristMgmtService service;

	public TouristOperationsController(ITouristMgmtService service) {
		super();
		this.service = service;
	}
	
	@PostMapping("/register")
	public ResponseEntity<String> enrollTourist(@RequestBody Tourist tourist){
		try {
			String resultMsg=service.registerTourost(tourist);
			return new ResponseEntity<String>(resultMsg,HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<String>("Tourist is Not registed due to Technical Issues",HttpStatus.INTERNAL_SERVER_ERROR);
		}
				
	}
}
