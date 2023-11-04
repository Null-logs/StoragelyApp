package com.nervlabs.storagely.business.facades.classes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nervlabs.storagely.business.commons.classes.EmployeeConstant;
import com.nervlabs.storagely.business.commons.classes.Response;
import com.nervlabs.storagely.business.facades.IEmployeeFacade;
import com.nervlabs.storagely.business.mappers.Interfaces.IEmployeeDTOMapper;
import com.nervlabs.storagely.business.services.interfaces.IEmployeeService;
import com.nervlabs.storagely.domain.dtos.EmployeeDTO;


@Service
public class EmployeeFacade implements IEmployeeFacade {
	
	@Autowired
	private IEmployeeDTOMapper mapper;
	
	@Autowired
	private IEmployeeService service;
	
	@Autowired
	private PasswordEncoder encoder;

	@Override
	public Response register(EmployeeDTO rq) {
		
		rq.setPassword(encoder.encode(rq.getPassword()));
		
		var employeeToRegister = mapper.toEmployeEntity(rq);
		
		var employeeCreated = service.register(employeeToRegister);
		
		return Response.builder()
				.httpStatus(HttpStatus.OK)
				.message(EmployeeConstant.USER_SUCCESSFULLY_REGISTER)
				.data(employeeCreated)
				.build();
	
	}

}
