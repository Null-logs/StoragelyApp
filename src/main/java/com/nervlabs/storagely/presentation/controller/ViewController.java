package com.nervlabs.storagely.presentation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {
	
	@RequestMapping(value = "/login.html")
	public String Login() {
		return "login";
	}
	
}