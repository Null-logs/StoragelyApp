package com.nervlabs.storagely.business.facades.classes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nervlabs.storagely.business.commons.classes.EmployeeConstant;
import com.nervlabs.storagely.business.commons.classes.Response;
import com.nervlabs.storagely.business.commons.enums.Roles;
import com.nervlabs.storagely.business.commons.enums.Types;
import com.nervlabs.storagely.business.facades.IEmployeeFacade;
import com.nervlabs.storagely.business.mappers.Interfaces.IEmployeeDtoMapper;
import com.nervlabs.storagely.business.services.interfaces.IEmployeeService;
import com.nervlabs.storagely.domain.dtos.EmployeeDto;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Service
public class EmployeeFacade implements IEmployeeFacade {
	
	@Autowired
	private IEmployeeDtoMapper mapper;
	
	@Autowired
	private IEmployeeService service;
	
	@Autowired
	private PasswordEncoder encoder;

	@Override
	public Response register(EmployeeDto rq) {
		
		rq.setPassword(encoder.encode(rq.getPassword()));
		var employeeToRegister = mapper.toEmployeEntity(rq);
		var employeeCreated = service.register(employeeToRegister);
		
		return Response.builder()
				.httpStatus(HttpStatus.OK)
				.message(EmployeeConstant.USER_SUCCESSFULLY_REGISTER)
				.data(employeeCreated)
				.build();
	}

	@Override
	public Response login(EmployeeDto unauthenticatedUser, HttpServletRequest request, HttpServletResponse response) {
		//TODO: VALIDAR PRIMERO EL USUARIO, LA CONTRASEÑA Y EL ROL PARA PODER AUTENTICARLO
		var authenticatedUser = service.authenticatedUser(unauthenticatedUser);
		var newSegurityContext = service.createSecurityContextFor(authenticatedUser);
		service.persistAuthenticationWith(newSegurityContext, request, response);
		
		return Response.builder().data(null).httpStatus(HttpStatus.OK).message(EmployeeConstant.USER_SUCCESSFULLY_AUTHENTICATED).type(Types.SUCCESS).build();
	}

	@Override
	public Response getRoles() {
		return Response.builder().data(Roles.getRoles()).type(Types.SUCCESS).build();
	}


}
