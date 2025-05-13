package com.cd.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivateUser {

	private String email;
	private String tempPassword;
	private String newPssword;
	private String confirmPassword;
}
