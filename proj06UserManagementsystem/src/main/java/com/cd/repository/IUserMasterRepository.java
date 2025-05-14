package com.cd.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cd.entity.UserMaster;

public interface IUserMasterRepository extends JpaRepository<UserMaster, Integer> {

	UserMaster findByEmailAndPassword(String email, String tempPassword);
	
	 //    public    UserMaster   findByEmailAndPassword(String mail,String pwd);
	     public   UserMaster   findByNameAndEmail(String name, String  mail);

}
