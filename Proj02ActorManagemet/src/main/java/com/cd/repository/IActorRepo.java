package com.cd.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cd.entity.ActorInfo;

public interface IActorRepo extends JpaRepository<ActorInfo, Integer> {

}
