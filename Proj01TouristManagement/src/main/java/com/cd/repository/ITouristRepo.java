package com.cd.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cd.entity.Tourist;

public interface ITouristRepo extends JpaRepository<Tourist, Integer> {
	
	@Query("FROM Tourist WHERE city in(:city1,:city2,:city3) order by name asc")
	public List<Tourist> findTouristsByCityName(String city1,String city2, String city3);

}
