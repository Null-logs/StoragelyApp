package com.nervlabs.storagely.presentation.controller.privateControllers;

import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nervlabs.storagely.business.commons.classes.Response;
import com.nervlabs.storagely.business.facades.IItemFacade;
import com.nervlabs.storagely.domain.dtos.UnregisteredItemDto;

@RestController
@RequestMapping(value = "item")
public class ItemController {
	
	@Autowired
	private IItemFacade facade;
	
	@PostMapping(value = "register")
	Response register(@RequestBody Set<UnregisteredItemDto> unregisteredItems) {
		return facade.register(unregisteredItems);
	}
	
	@GetMapping(value = "test")
	String test () {
		return "Hola";
	}
}
