package com.cd.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.cd.constants.UserRegisterConstants;
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

	private  Map<String,String> messages;
	
	
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
		return messages.get(UserRegisterConstants.ACTIVE_FAILURE);
		}
		else {
			entity.setPassword(user.getConfirmPassword());
			entity.setActiveSw("Active");
			repo.save(entity);
			return messages.get(UserRegisterConstants.STATUS_CHANGE_SUCCESS);
					}
	}

	@Override
	public String login(LoginCredentials credentials) {
		UserMaster entity=new UserMaster();
		BeanUtils.copyProperties(credentials, entity);
		Example<UserMaster> example=Example.of(entity);
		List<UserMaster> listEntities=repo.findAll(example);
		if(listEntities.isEmpty()) {
			return messages.get(UserRegisterConstants.LOGIN_CREDENTIALS_FAILURE);
		}
		else {
			UserMaster entityOne=listEntities.get(0);
			if(entity.getActiveSw().equalsIgnoreCase("Active")) {
				return messages.get(UserRegisterConstants.LOGIN_CREDENTIALS_SUCCESS);
			}
			else {
				return messages.get(UserRegisterConstants.STATUS_CHANGE_FAILURE);
						}
		}

	}

	@Override
	public List<UserAccount> listUsers() {
		/*return repo.findAll().stream().map(entity->{
			UserAccount user=new UserAccount();
			BeanUtils.copyProperties(entity, user);
			return user;
		}).toList();*/
		
		List<UserMaster> listEntities=repo.findAll();
		List<UserAccount> listUsers=new ArrayList<UserAccount>();
		listEntities.forEach(entity->{
			UserAccount user=new UserAccount();
			BeanUtils.copyProperties(entity, user);
			listUsers.add(user);
		});
		return listUsers;
	}

	@Override
	public UserAccount showUserByUserId(Integer id) {
		Optional<UserMaster> opt=repo.findById(id);
		UserAccount account=null;
		if(opt.isPresent()) {
			account=new UserAccount();
			BeanUtils.copyProperties(opt, account);
		}
		return account;
	}

	@Override
	public UserAccount showUserByEmailAndName(String email, String name) {
		UserMaster master=repo.findByNameAndEmail(name, email);
		UserAccount account=null;
		if(master!=null) {
			account=new UserAccount();
			BeanUtils.copyProperties(master, account);
		}
		return account;
	}

	@Override
	public String updateUser(UserAccount user) {
		Optional<UserMaster> opt=repo.findById(user.getUserId());
		if(opt.isPresent()) {
			UserMaster master=new UserMaster();
			BeanUtils.copyProperties(user, master);
			repo.save(master);
			//return "User Details are Updated";
			return messages.get(UserRegisterConstants.UPDATE_SUCCESS);
		}
		else {
			return messages.get(UserRegisterConstants.UPDATE_FAILURE);
		}
		
	}

	@Override
	public String deleteUserById(Integer id) {
		Optional<UserMaster> opt=repo.findById(id);
		if(opt.isPresent()) {
			repo.deleteById(id);
			return messages.get(UserRegisterConstants.DELETE_SUCCESS);
		}
		else {
			return messages.get(UserRegisterConstants.DELETE_FAILURE);
		}
		
	}

	@Override
	public String changeUserStatus(Integer id, String status) {
		Optional<UserMaster> opt=repo.findById(id);
		if(opt.isPresent()) {
			UserMaster master=opt.get();
			master.setActiveSw(status);
			repo.save(master);
			return messages.get(UserRegisterConstants.STATUS_CHANGE_SUCCESS);
		}
		return messages.get(UserRegisterConstants.STATUS_CHANGE_FAILURE);
	}

	@Override
	public String recoverPassword(RecoverPassword recover) throws Exception {
		UserMaster   master=repo.findByNameAndEmail(recover.getName(), recover.getEmail());
		if(master!=null) {
			String  pwd=master.getPassword();
			 //send the recovered password  to the email account 
			String  subject="  mail for  password recovery";
			String  mailBody=readEmailMessageBody(env.getProperty("mailbody.recoverpwd.location"), recover.getName(), pwd);  //private method
			utils.sendEmailMessage(recover.getEmail(), subject, mailBody);
			return  pwd +messages.get(UserRegisterConstants.RECOVER_PASSWORD_SUCCESS);
		}
		return messages.get(UserRegisterConstants.RECOVER_PASSWORD_FAILURE);
				}

}
