package com.nervlabs.storagely.business.mappers.classes;

import org.springframework.stereotype.Component;

import com.nervlabs.storagely.business.commons.enums.Roles;
import com.nervlabs.storagely.business.mappers.Interfaces.IEmployeeDTOMapper;
import com.nervlabs.storagely.domain.dtos.EmployeeDTO;
import com.nervlabs.storagely.domain.entites.EmployeeEntity;

@Component
public class EmployeeDTOMapper implements IEmployeeDTOMapper {

	@Override
	public EmployeeEntity toEmployeEntity(EmployeeDTO src) {

		return (src == null) ? null
				: EmployeeEntity.builder()
				.username(src.getUsername())
				.password(src.getPassword())
				.name(src.getName())
				.lastname(src.getLastname())
				.email(src.getEmail())
				.rol(Roles.getRoles(src.getRol()))
				.build();
	}

}
