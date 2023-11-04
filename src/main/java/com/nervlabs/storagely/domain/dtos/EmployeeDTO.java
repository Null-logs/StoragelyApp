package com.nervlabs.storagely.domain.dtos;


import java.util.UUID;

import com.nervlabs.storagely.business.commons.enums.Roles;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmployeeDTO {
	
	private UUID id;
	private String username;
	private String password;
	private String name;
	private String lastname;
	private String email;
	private String rol;
	
}
