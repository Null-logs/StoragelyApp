package com.nervlabs.storagely.business.mappers.Interfaces;

import org.mapstruct.Mapper;

import com.nervlabs.storagely.domain.dtos.EmployeeDTO;
import com.nervlabs.storagely.domain.entites.EmployeeEntity;

@Mapper(componentModel = "spring")
public interface IEmployeeDTOMapper {
	
	EmployeeEntity toEmployeEntity(EmployeeDTO employeeDTO);
}
