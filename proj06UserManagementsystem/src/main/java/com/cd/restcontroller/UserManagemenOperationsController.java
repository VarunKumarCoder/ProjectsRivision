package com.cd.restcontroller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cd.model.ActivateUser;
import com.cd.model.UserAccount;
import com.cd.service.IUserMgmtService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/user-api")
@Slf4j
public class UserManagemenOperationsController {
	@Autowired
	private IUserMgmtService  userService;
	
	@PostMapping("/save")
	public  ResponseEntity<String>  saveUser(@RequestBody  UserAccount  account){
		//user  service
		try {
			String  resultMsg=userService.registerUser(account);
			return  new ResponseEntity<>(resultMsg,HttpStatus.CREATED);
		}
		catch(Exception e) {
			log.error(e.getMessage());
			return  new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@PostMapping("/activate")
	public  ResponseEntity<String>    activateUser(@RequestBody ActivateUser  user){
		try {
			//use service
			String resultMsg=userService.activateUserAccount(user);
			return  new ResponseEntity<>(resultMsg,HttpStatus.CREATED);
		}
		catch(Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
