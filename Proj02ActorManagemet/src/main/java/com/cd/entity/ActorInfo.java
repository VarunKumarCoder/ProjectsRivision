package com.cd.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.lang.NonNull;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name="ACTOR_INFO")
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Builder
public class ActorInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID", updatable = false, nullable = false)
	private Integer id;
	@NonNull
	@Column(name="ACTOR_NAME",length = 60)
	private String aname;
	@NonNull
	@Column(name="ACTOR_ADDRESS",length=150)
	private String addrs;
	@NonNull()
	@Column(name="ACTIVE_SWITCH",length=10)
	private String active_sw;
	@Column(name="REMUNERATION",length=10)
	private Double remuneration;
	//META DATA
	@Column(length=60)
	private String createdBy;
	@Column(length=60)
	private String updatedBy;
	@Column(updatable=false,insertable=true)
	@CreationTimestamp
	private LocalDate creationDate;
	@UpdateTimestamp
	@Column(insertable=false,updatable=true)
	private LocalDate updationDate;
}
