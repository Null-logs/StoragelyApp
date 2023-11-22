package com.nervlabs.storagely.presentation.controller.privateControllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/storer/view")
public class ViewStorerController {
	
	@RequestMapping(value = "/oneByOne.html")
	public String home() {	
	    return "storerOneByOne";
	}
	
	@RequestMapping(value = "/massive.html")
	public String massive() {	
	    return "storerMassive";
	}
	
	@RequestMapping(value = "/productos.html")
	public String all() {
		return "allItems";
	}
}
