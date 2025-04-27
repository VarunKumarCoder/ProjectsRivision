package com.cd.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cd.entity.Tourist;

public interface ITouristRepo extends JpaRepository<Tourist, Integer> {

}
