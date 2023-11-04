package com.nervlabs.storagely.presentation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nervlabs.storagely.business.commons.classes.EmployeeConstant;
import com.nervlabs.storagely.business.commons.classes.Response;
import com.nervlabs.storagely.business.commons.enums.Roles;
import com.nervlabs.storagely.business.commons.enums.Types;
import com.nervlabs.storagely.business.facades.IEmployeeFacade;
import com.nervlabs.storagely.domain.dtos.EmployeeDTO;

import aj.org.objectweb.asm.Type;

@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {

	@Autowired
	private IEmployeeFacade facadeEmployee;

	@PostMapping(value = "/register")
	public Response register(@RequestBody EmployeeDTO rq) {
		return facadeEmployee.register(rq);
	}

	@GetMapping(value = "/roles")
	public Response getRoles() {
		return Response.builder().data(Roles.getRoles()).build();
	}

	@GetMapping(value = "/view/login")
	@ResponseBody
	public String loginView() {
		return "login";
	}

	@GetMapping(value = "/login/response")
	public Response doLogin() {
		return Response.builder()
				.httpStatus(HttpStatus.OK)
				.message(EmployeeConstant.USER_SUCCESSFULLY_LOGGED_IN)
				.type(Types.SUCCESS)
				.build();
	}

}
