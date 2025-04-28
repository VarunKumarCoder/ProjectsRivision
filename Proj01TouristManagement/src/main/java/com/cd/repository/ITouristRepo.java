package com.cd.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cd.entity.Tourist;

import jakarta.transaction.Transactional;

public interface ITouristRepo extends JpaRepository<Tourist, Integer> {
	
	@Query("FROM Tourist WHERE city in(:city1,:city2,:city3) order by name asc")
	public List<Tourist> findTouristsByCityName(String city1,String city2, String city3);
	
	@Query("FROM Tourist WHERE name=:name")
	public List<Tourist> getTouristByName(@Param("name") String name);
	
	
	@Query("DELETE FROM Tourist WHERE budget>=:start and budget<=:end")
	@Modifying
	@Transactional
	public int removeTouristByBudgetRange(double start,double end);

}
