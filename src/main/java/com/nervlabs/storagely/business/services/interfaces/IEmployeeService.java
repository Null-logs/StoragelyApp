package com.nervlabs.storagely.business.services.interfaces;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;

import com.nervlabs.storagely.domain.dtos.EmployeeDto;
import com.nervlabs.storagely.domain.entites.EmployeeEntity;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface IEmployeeService {
	EmployeeEntity register(EmployeeEntity src);
	Authentication authenticatedUser(EmployeeDto unauthenticatedUser);
	SecurityContext createSecurityContextFor(Authentication authenticatedUser);
	void persistAuthenticationWith(SecurityContext createdSecurityContext, HttpServletRequest request, HttpServletResponse response);
}
