package com.cd.model;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAccount {
	private Integer userId;
	private String name;
	private String email;
	private Long contactNo;
	private String gender="Female";
	private Date dob;
	private Long aadharNo;
}
