package com.nervlabs.storagely.presentation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nervlabs.storagely.business.commons.classes.Response;
import com.nervlabs.storagely.business.facades.IEmployeeFacade;
import com.nervlabs.storagely.domain.dtos.EmployeeDto;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/access")
public class AccessController {

	@Autowired
	private IEmployeeFacade facadeEmployee;

	@PostMapping(value = "/login")
	public Response login(@RequestBody EmployeeDto unauthenticatedUser, HttpServletRequest request,
			HttpServletResponse response) {
		return facadeEmployee.login(unauthenticatedUser, request, response);
	}
	
	@GetMapping(value = "/roles")
	public Response getRoles() {
		return facadeEmployee.getRoles();
	}

}
