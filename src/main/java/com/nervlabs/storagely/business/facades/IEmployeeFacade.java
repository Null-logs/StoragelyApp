package com.nervlabs.storagely.business.facades;

import com.nervlabs.storagely.business.commons.classes.Response;
import com.nervlabs.storagely.domain.dtos.EmployeeDto;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface IEmployeeFacade {
	Response register(EmployeeDto rq);
	Response login(EmployeeDto unauthenticatedUser, HttpServletRequest request, HttpServletResponse response);
	Response getRoles();
}
