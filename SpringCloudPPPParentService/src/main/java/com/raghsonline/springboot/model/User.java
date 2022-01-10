package com.raghsonline.springboot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

	private Long id;
	
	private String firstName;
	
	private String lastName;
	
	private String emailAddress;
	
	private String mobileNo;
	
	private String userId;
	
	private String password;
}
