package com.cd.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IndianFestival {

	private Integer id;
	private String fname;
	private String season;
	private String reason;
	private String dressCode;
}
