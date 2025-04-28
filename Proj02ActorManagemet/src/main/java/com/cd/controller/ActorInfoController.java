package com.cd.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	
	@GetMapping("/findallactors")
	public ResponseEntity<List<ActorData>> findAllActors(){
		List<ActorData> data=service.showAllActors();
		return new ResponseEntity<List<ActorData>>(data,HttpStatus.OK);
	}
	
	
	@GetMapping("/find/{id}")
	public ResponseEntity<ActorData> findById(@PathVariable Integer id){
		ActorData data=service.showActorById(id);
		return new ResponseEntity<ActorData>(data,HttpStatus.OK);
	}
	
	@PostMapping("/update")
	public ResponseEntity<String> updateInfo(@RequestBody ActorData data){
		String msg=service.updateActorData(data);
		return new ResponseEntity<String>(msg,HttpStatus.OK);
	}
	
	@PatchMapping("/rupdate/{id}/{amount}")
	public ResponseEntity<String> updateActorRemuneration(@PathVariable("id") Integer id,@PathVariable("amount") double amount) {
		String msg=service.updateActorRemuneration(id, amount);
		return new ResponseEntity<String>(msg,HttpStatus.OK);
	}
	
	
}
