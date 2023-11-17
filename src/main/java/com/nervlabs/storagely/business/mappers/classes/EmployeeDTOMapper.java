package com.nervlabs.storagely.business.mappers.classes;

import org.springframework.stereotype.Component;

import com.nervlabs.storagely.business.commons.enums.Roles;
import com.nervlabs.storagely.business.mappers.Interfaces.IEmployeeDtoMapper;
import com.nervlabs.storagely.domain.dtos.EmployeeDto;
import com.nervlabs.storagely.domain.entites.EmployeeEntity;

@Component
public class EmployeeDtoMapper implements IEmployeeDtoMapper {

	@Override
	public EmployeeEntity toEmployeEntity(EmployeeDto src) {

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
