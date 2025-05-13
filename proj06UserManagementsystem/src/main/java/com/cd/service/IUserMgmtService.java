package com.cd.service;

import java.util.List;

import com.cd.model.ActivateUser;
import com.cd.model.LoginCredentials;
import com.cd.model.RecoverPassword;
import com.cd.model.UserAccount;

public interface IUserMgmtService {

	//save the registerPage data screen-1
	public  String  registerUser(UserAccount user)throws Exception;
	//save user with new password
	public   String   activateUserAccount(ActivateUser user);
	//save login credentials
	public  String   login(LoginCredentials  credentials);
	//To get the list of users
	public   List<UserAccount>  listUsers();
	//get user data using user ID
	public   UserAccount   showUserByUserId(Integer id);
	//show user data using name and email for recover password
	public    UserAccount   showUserByEmailAndName(String  email,String name);
	//for edit operations ..partial or fully
	public   String  updateUser(UserAccount  user);
	//to delete the user based on iD
	public    String   deleteUserById(Integer  id);
	//to active/inactive the user
	public    String   changeUserStatus(Integer id,String  status);
	//save the recover password data using new password
	public   String   recoverPassword(RecoverPassword  recover)throws Exception;
}
