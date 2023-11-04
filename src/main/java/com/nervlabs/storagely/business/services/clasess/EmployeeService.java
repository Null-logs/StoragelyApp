package com.nervlabs.storagely.business.services.clasess;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nervlabs.storagely.business.services.interfaces.IEmployeeService;
import com.nervlabs.storagely.domain.entites.EmployeeEntity;
import com.nervlabs.storagely.persistence.IEmployeeRepository;

@Service
public class EmployeeService implements IEmployeeService {

	@Autowired
	private IEmployeeRepository repository;
	
	@Override
	public EmployeeEntity register(EmployeeEntity src) {
		
		return repository.save(src);
	}

}
