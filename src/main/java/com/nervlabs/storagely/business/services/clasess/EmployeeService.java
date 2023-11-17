package com.nervlabs.storagely.business.services.clasess;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Service;

import com.nervlabs.storagely.business.services.interfaces.IEmployeeService;
import com.nervlabs.storagely.domain.dtos.EmployeeDto;
import com.nervlabs.storagely.domain.entites.EmployeeEntity;
import com.nervlabs.storagely.persistence.IEmployeeRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class EmployeeService implements IEmployeeService {

	@Autowired
	private IEmployeeRepository repository;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	SecurityContextRepository securityContextRepository;

	@Override
	public EmployeeEntity register(EmployeeEntity src) {
		return repository.save(src);
	}

	@Override
	public Authentication authenticatedUser(EmployeeDto unauthenticatedUser) {
		Authentication authenticationRequest = UsernamePasswordAuthenticationToken
				.unauthenticated(unauthenticatedUser.getUsername(), unauthenticatedUser.getPassword());
		Authentication authenticationResponse = authenticationManager.authenticate(authenticationRequest);

		return authenticationResponse;
	}

	@Override
	public SecurityContext createSecurityContextFor(Authentication authenticatedUser) {
		SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
		securityContext.setAuthentication(authenticatedUser);

		return securityContext;
	}

	@Override
	public void persistAuthenticationWith(SecurityContext createdSecurityContext, HttpServletRequest request,
			HttpServletResponse response) {
		securityContextRepository.saveContext(createdSecurityContext, request, response);
		
	}
}
