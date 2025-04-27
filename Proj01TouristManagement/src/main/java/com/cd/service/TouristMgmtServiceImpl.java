package com.cd.service;

import org.springframework.stereotype.Service;

import com.cd.entity.Tourist;
import com.cd.repository.ITouristRepo;

@Service("touristService")
public class TouristMgmtServiceImpl implements ITouristMgmtService {

	private ITouristRepo repo;
	
	public TouristMgmtServiceImpl(ITouristRepo repo) {
		super();
		this.repo = repo;
	}
	@Override
	public String registerTourost(Tourist tourist) {
		int idVal=repo.save(tourist).getId();
		return "Tourist is Registered having the IDValue :: "+idVal;
	}

}
