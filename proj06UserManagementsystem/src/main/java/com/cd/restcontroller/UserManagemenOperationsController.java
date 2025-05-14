package com.cd.restcontroller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cd.exception.PasswordNotFoundException;
import com.cd.model.ActivateUser;
import com.cd.model.LoginCredentials;
import com.cd.model.RecoverPassword;
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
	public  ResponseEntity<String>  saveUser(@RequestBody  UserAccount  account) throws Exception{
		//user  service	
			String  resultMsg=userService.registerUser(account);
			return  new ResponseEntity<>(resultMsg,HttpStatus.CREATED);	
	}
	
	@PostMapping("/activate")
	public  ResponseEntity<String>    activateUser(@RequestBody ActivateUser  user){	
			String resultMsg=userService.activateUserAccount(user);
			return  new ResponseEntity<>(resultMsg,HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> performLogin(@RequestBody LoginCredentials credentials){	
	String resultMsg=userService.login(credentials);
		return new ResponseEntity<String>(resultMsg,HttpStatus.OK);
	}
	
	@GetMapping("/report")
	public   ResponseEntity<Object>   showUsers(){	
			List<UserAccount>  list=userService.listUsers();
			return  new ResponseEntity<>(list,HttpStatus.OK);	
	}
	
	@GetMapping("/find/{id}")
	public  ResponseEntity<Object>  showUserById(@PathVariable Integer id){
			UserAccount  account=userService.showUserByUserId(id);
			return  new ResponseEntity<>(account,HttpStatus.OK);
	}
	
	@GetMapping("/find/{email}/{name}")
	public  ResponseEntity<Object>  showUserByEmailAndName(@PathVariable String  email,@PathVariable String  name){
			UserAccount  account=userService.showUserByEmailAndName(email, name);
			return  new ResponseEntity<>(account,HttpStatus.OK);		
	}
	
	@PutMapping("/update")
	public  ResponseEntity<String>  updateUserDetails(@RequestBody UserAccount account){
			String resultMsg=userService.updateUser(account);
			return new ResponseEntity<>(resultMsg,HttpStatus.OK);	
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String>   deleteUserById(@PathVariable Integer id){		
			String resultMsg=userService.deleteUserById(id);
			return new ResponseEntity<>(resultMsg,HttpStatus.OK);		
	}
	
	@PatchMapping("/changeStatus/{id}/{status}")
	public  ResponseEntity<String>   changeStatus(@PathVariable Integer id , @PathVariable String status){
			String resultMsg=userService.changeUserStatus(id, status);
			return new ResponseEntity<>(resultMsg,HttpStatus.OK);
	}
	
	@PostMapping("/recoverPassword")
	public   ResponseEntity<String>   recoverPassword(@RequestBody  RecoverPassword  recover) throws PasswordNotFoundException{
		try {	
			String  resultMsg=userService.recoverPassword(recover);
			return new ResponseEntity<>(resultMsg,HttpStatus.OK);
		}
		catch (Exception e) {
			throw new PasswordNotFoundException();
		}
	}//method
	
}
