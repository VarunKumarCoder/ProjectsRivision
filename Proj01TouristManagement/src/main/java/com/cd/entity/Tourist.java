package com.cd.entity;


import org.springframework.lang.NonNull;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name="REST_TOURIST")
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Data
public class Tourist {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name="NAME",length = 50,insertable = true,nullable = false,updatable = true)
	private String name;
	@Column(name="NAME",length = 50,insertable = true,nullable = false,updatable = true)
	private String city;
	@Column(name="NAME",length = 50,insertable = true,nullable = false,updatable = true)
	private String packageType;
	@NonNull
	private Double budget;
}
