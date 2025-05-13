package com.cd.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cd.entity.UserMaster;

public interface IUserMasterRepository extends JpaRepository<UserMaster, Integer> {
	
	 //    public    UserMaster   findByEmailAndPassword(String mail,String pwd);
	  //   public   UserMaster   findByNameAndEmail(String name, String  mail);

}
