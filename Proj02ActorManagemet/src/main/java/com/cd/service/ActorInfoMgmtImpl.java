package com.cd.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.cd.entity.ActorInfo;
import com.cd.model.ActorData;
import com.cd.repository.IActorRepo;



@Service
public class ActorInfoMgmtImpl implements IActorInfoMgmt {
	@Value("${user.name}")
	private String osuser;
	private IActorRepo repo;
	
	public ActorInfoMgmtImpl(IActorRepo repo) {
		super();
		this.repo = repo;
	}

	@Override
	public String registerActorinfo(ActorData data) {
		//create Object for destination class
		ActorInfo entity=new ActorInfo();
		//Copy source data to destination data
		BeanUtils.copyProperties(data, entity);
		entity.setCreatedBy(osuser);
		entity.setUpdatedBy(osuser);
		//save the destination data
		int actor=repo.save(entity).getId();
		//return data
		return actor+" Actor Info is saved with IDVal";
	}

	@Override
	public List<ActorData> showAllActors() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ActorData showActorById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updateActorData(ActorData data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updateActorRemuneration(int id, double amount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updateActorStatus(int id, String status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteActorById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
