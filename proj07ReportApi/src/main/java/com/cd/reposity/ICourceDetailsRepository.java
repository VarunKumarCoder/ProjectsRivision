package com.cd.reposity;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cd.entity.CourceDetails;

public interface ICourceDetailsRepository extends JpaRepository<CourceDetails, Integer> {

	@Query("select distinct(courceCategory) from CouceDetails")
	public Set<String> getUniqueCourceCategory();
	@Query("select distinct(facultyName) from CourceDetails")
	public Set<String> getUniqueFacultyNames();
	@Query("select distinct(trainingMode) from CourceDetails")
	public Set<String> getUniqueTrainingModes();
}
