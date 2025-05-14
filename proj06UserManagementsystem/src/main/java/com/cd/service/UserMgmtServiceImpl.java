package com.cd.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.security.SecureRandom;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.cd.entity.UserMaster;
import com.cd.model.ActivateUser;
import com.cd.model.LoginCredentials;
import com.cd.model.RecoverPassword;
import com.cd.model.UserAccount;
import com.cd.repository.IUserMasterRepository;
import com.cd.utils.EmailUtils;

@Service
public class UserMgmtServiceImpl implements IUserMgmtService {
	
	@Autowired
	private IUserMasterRepository repo;
	@Autowired
	private EmailUtils utils;
	@Autowired
	private Environment env;

	@Override
	public String registerUser(UserAccount user) throws Exception {
		UserMaster entity=new UserMaster();
		BeanUtils.copyProperties(user, entity);
		String tempPassword=generateRandomPassword(6);
		entity.setPassword(tempPassword);
		entity.setActiveSw("InActive");
		UserMaster master=repo.save(entity);
		String subject="User Registration Success";
		String body=readEmailMessageBody(env.getProperty("mailbody.registeruser.location"),user.getName(),tempPassword);
		utils.sendEmailMessage(user.getEmail(), subject, body);
		return master.getUserId()!=null?"user is Registered With Id Value::"+master.getUserId()+"Check Mail For Temp password":"Problem In user registration";
		
	}

	private String readEmailMessageBody(String fileName,String fullName,String pwd)throws Exception{
		String mailBody=null;
		String url="http://localhost:4041/activate";
		try(FileReader reader=new FileReader(fileName);
				BufferedReader br=new BufferedReader(reader)){
			StringBuilder buffer=new StringBuilder();
			String line=null;
			do {
				line=br.readLine();
				if(line!=null)
					buffer.append(line);
			}while(line!=null);
			mailBody=buffer.toString();
			mailBody=mailBody.replace("{FULL-NAME}", fullName);
			mailBody=mailBody.replace("{PWD}", pwd);
			mailBody=mailBody.replace("{URL}", url);
			}
		catch (Exception e) {
			throw e;
		}
		return mailBody;
		}
	
	
	private String generateRandomPassword(int length) {
		 // a list of characters to choose from in form of a string
		 String alphaNumericStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvxyz0123456789";
		 // creating a StringBuffer size of AlphaNumericStr
		 StringBuilder randomWord = new StringBuilder(length);
		 int i;
		 for ( i=0; i<length; i++) {
			   //generating a random number using math.random()  (gives psuedo random number 0.0 to 1.0)
				  SecureRandom random=new SecureRandom();
				  float  randVal=random.nextFloat();
			   int ch = (int)(alphaNumericStr.length() * randVal);
			   //adding Random character one by one at the end of randonword
			   randomWord.append(alphaNumericStr.charAt(ch));
			  }
		  return randomWord.toString();
	}

	@Override
	public String activateUserAccount(ActivateUser user) {
		UserMaster entity=repo.findByEmailAndPassword(user.getEmail(),user.getTempPassword());
		if(entity==null) {
		return "User is Not Registered with given Eamil";
		}
		else {
			entity.setPassword(user.getConfirmPassword());
			entity.setActiveSw("Active");
			repo.save(entity);
			return "User is Activated with the New Password";
		}
	}

	@Override
	public String login(LoginCredentials credentials) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserAccount> listUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserAccount showUserByUserId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserAccount showUserByEmailAndName(String email, String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updateUser(UserAccount user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteUserById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String changeUserStatus(Integer id, String status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String recoverPassword(RecoverPassword recover) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
