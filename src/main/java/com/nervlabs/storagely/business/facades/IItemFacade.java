package com.nervlabs.storagely.business.facades;

import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.nervlabs.storagely.business.commons.classes.DataTableResponse;
import com.nervlabs.storagely.business.commons.classes.Response;
import com.nervlabs.storagely.domain.dtos.UnregisteredItemDto;

@Service
public interface IItemFacade {
	Response register(Set<UnregisteredItemDto> unregisteredItems);
	DataTableResponse get(Map<String, String> dataTableParams);
}
