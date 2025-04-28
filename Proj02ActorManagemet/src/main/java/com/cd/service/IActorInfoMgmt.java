package com.cd.service;

import java.util.List;

import com.cd.model.ActorData;

public interface IActorInfoMgmt {

	public String registerActorinfo(ActorData data);
	public List<ActorData> showAllActors();
	public ActorData showActorById(int id);
	public String updateActorData(ActorData data);
	public String updateActorRemuneration(int id,double amount);
	public String updateActorStatus(int id, String status);
	public String deleteActorById(int id);
}
