package com.nervlabs.storagely.business.facades;

import com.nervlabs.storagely.business.commons.classes.Response;
import com.nervlabs.storagely.domain.dtos.EmployeeDTO;

public interface IEmployeeFacade {
	Response register(EmployeeDTO rq);
}
