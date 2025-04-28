package com.cd.service;

import java.util.List;
import java.util.Optional;

import com.cd.entity.Tourist;

public interface ITouristMgmtService {

	public String registerTourost(Tourist tourist);
	public List<Tourist> fetchAllTourists();
	public List<Tourist> getTouristNameByCity(String city1, String city2, String city3);
	public Optional<Tourist> fetchToouristById(Integer id);
	public Iterable<Tourist> fetchAllByIds(Iterable<Integer> ids);
	//public Optional<Tourist> fetchToouristById(List<Integer> ids);
	
	public String updateTouristDetails(Tourist tourist) throws TouristNotFoundException;
}
