package com.cd.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cd.model.ActorData;
import com.cd.service.IActorInfoMgmt;

@RestController
@RequestMapping("/actorinfo-api")
public class ActorInfoController {
	
	private IActorInfoMgmt service;

	public ActorInfoController(IActorInfoMgmt service) {
		super();
		this.service = service;
	}

	@PostMapping("/registeractor")
	public ResponseEntity<String> saveActor(@RequestBody ActorData data){
		String msg=service.registerActorinfo(data);
		return new ResponseEntity<String>(msg,HttpStatus.OK);
	}
}
