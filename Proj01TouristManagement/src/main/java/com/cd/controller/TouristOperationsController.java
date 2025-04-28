package com.cd.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cd.entity.Tourist;
import com.cd.service.ITouristMgmtService;
import com.cd.service.TouristNotFoundException;

@RestController

public class TouristOperationsController {

	private ITouristMgmtService service;

	public TouristOperationsController(ITouristMgmtService service) {
		super();
		this.service = service;
	}

	@PostMapping("/register")
	public ResponseEntity<String> enrollTourist(@RequestBody Tourist tourist) {
		try {
			String resultMsg = service.registerTourost(tourist);
			return new ResponseEntity<String>(resultMsg, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<String>("Tourist is Not registed due to Technical Issues",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/list")
	public ResponseEntity<List<Tourist>> getAllTourist() {
		try {
			List<Tourist> list = service.fetchAllTourists();
			return new ResponseEntity<List<Tourist>>(list, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<Tourist>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/listofcitynames/{city1}/{city2}/{city3}")
	public ResponseEntity<List<Tourist>> getTouristNameByCity(@PathVariable String city1, @PathVariable String city2,
			@PathVariable String city3) {
		try {
			List<Tourist> list = service.getTouristNameByCity(city1, city2, city3);
			return new ResponseEntity<List<Tourist>>(list, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<Tourist>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/touristname/{id}")
	public ResponseEntity<Optional<Tourist>> getTouristNameByCity(@PathVariable Integer id) {
		try {
			Optional<Tourist> list = service.fetchToouristById(id);
			return new ResponseEntity<Optional<Tourist>>(list, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Optional<Tourist>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/listoftourists")
	public ResponseEntity<Iterable<Tourist>> getTouristNameByCity(@RequestParam List<Integer> ids) {
		try {
			Iterable<Tourist> list = service.fetchAllByIds(ids);
			return new ResponseEntity<Iterable<Tourist>>(list, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Iterable<Tourist>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PutMapping("/updatetourist")
	public ResponseEntity<String> updateTourist(@RequestBody Tourist tourist) {
		try {
			String list = service.updateTouristDetails(tourist);
			return new ResponseEntity<String>(list, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/getbyname/{name}")
	public ResponseEntity<List<Tourist>> getTouristByname(@PathVariable("name") String name) {
		try {
			List<Tourist> list = service.getTouritByName(name);
			return new ResponseEntity<List<Tourist>>(list, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<Tourist>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PatchMapping("/updatebudget/{id}/{percentage}")
	public ResponseEntity<String> updateTouristBudget(@PathVariable Integer id,@PathVariable("percentage") double percentage)
	 {
		
		try {
			String resultMsg = service.updateTouristBudgetById(id, percentage);
			return new ResponseEntity<String>(resultMsg,HttpStatus.OK);
		} catch (TouristNotFoundException e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	} 
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteByid(@PathVariable("id") Integer id) throws TouristNotFoundException{
		String resultMsg;
		try {
			resultMsg = service.removeTouristById(id);
			return new ResponseEntity<String>(resultMsg,HttpStatus.OK);
		} catch (TouristNotFoundException e) {
			throw new TouristNotFoundException(id+" Number is not Found");
		}
	}
	
	@DeleteMapping("deleterange/{start}/{end}")
	public ResponseEntity<String> deleteTouristByBudgetRange(@PathVariable("start") double start,
																@PathVariable("end") double end){
		String resultMsg=service.removeTouristByBudgetRange(start, end);
		return new ResponseEntity<String>(resultMsg,HttpStatus.OK);
	}
}
	


