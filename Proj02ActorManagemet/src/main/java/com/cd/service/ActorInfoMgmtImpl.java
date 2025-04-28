package com.cd.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
		//call original method using entity class
		List<ActorInfo> list=repo.findAll();
		//convert entity into model class
		List<ActorData> modeldata=new ArrayList();
		//print using enhanced for loop
		for(ActorInfo entity:list) {
			//create object to store the list of model data
			ActorData adata=new ActorData();
			//finded entity data using predefined class now copy entity into model 
			BeanUtils.copyProperties(entity, adata);
			//after copying add data to object
			modeldata.add(adata);
		}
		return modeldata;
	}

	@Override
	public ActorData showActorById(int id) {
		ActorInfo info=repo.findById(id).orElseThrow(()->new IllegalArgumentException("invalid ID"));
		ActorData data=new ActorData();
		BeanUtils.copyProperties(info, data);
		return data;
	}

	@Override
	public String updateActorData(ActorData data) {
		ActorInfo info=repo.findById(data.getId()).orElseThrow(()->new IllegalArgumentException("invalid ID"));
		BeanUtils.copyProperties(data, info);
		info.setUpdatedBy(osuser);
		int idVal=repo.save(info).getId();
		return idVal+" ActorInfo is updated";
	}

	@Override
	public String updateActorRemuneration(int id, double amount) {
		ActorInfo info=repo.findById(id).orElseThrow(()->new IllegalArgumentException("invalid ID"));
		info.setRemuneration(amount);
		int idVal=repo.save(info).getId();
		return idVal+" is updated";
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
