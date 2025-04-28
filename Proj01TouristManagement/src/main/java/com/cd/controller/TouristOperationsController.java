package com.cd.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	
	@GetMapping("/list")
	public ResponseEntity<List<Tourist>> getAllTourist(){
		try {
			List<Tourist> list=service.fetchAllTourists();
			return new ResponseEntity<List<Tourist>>(list,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<Tourist>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
				
	}
	
	
	@GetMapping("/listofcitynames/{city1}/{city2}/{city3}")
	public ResponseEntity<List<Tourist>> getTouristNameByCity(@PathVariable String city1,
																@PathVariable String city2,
																@PathVariable String city3){
		try {
			List<Tourist> list=service.getTouristNameByCity(city1, city2, city3);
			return new ResponseEntity<List<Tourist>>(list,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<Tourist>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
				
	}
	
	
	
	@GetMapping("/touristname/{id}")
	public ResponseEntity<Optional<Tourist>> getTouristNameByCity(@PathVariable Integer id){
		try {
			Optional<Tourist> list=service.fetchToouristById(id);
			return new ResponseEntity<Optional<Tourist>>(list,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Optional<Tourist>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
				
	}
	
	@GetMapping("/listoftourists")
	public ResponseEntity<Iterable<Tourist>> getTouristNameByCity(@RequestParam List<Integer> ids){
		try {
			Iterable<Tourist> list=service.fetchAllByIds(ids);
			return new ResponseEntity<Iterable<Tourist>>(list,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Iterable<Tourist>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
				
	}
}
