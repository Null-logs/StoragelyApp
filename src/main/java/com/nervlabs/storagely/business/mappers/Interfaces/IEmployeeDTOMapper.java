package com.nervlabs.storagely.business.mappers.Interfaces;

import org.mapstruct.Mapper;

import com.nervlabs.storagely.domain.dtos.EmployeeDto;
import com.nervlabs.storagely.domain.entites.EmployeeEntity;

@Mapper(componentModel = "spring")
public interface IEmployeeDtoMapper {
	EmployeeEntity toEmployeEntity(EmployeeDto employeeDTO);
}
