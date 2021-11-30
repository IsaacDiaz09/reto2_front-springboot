package com.ciclo4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ciclo4.service.UserServiceImpl;

@Controller
public class IndexController {
	
	@Autowired
	private UserServiceImpl service;
	
	@GetMapping({ "/", "/login" })
	public String index() {
		return "vistas/login";
	}
	
	@GetMapping("/register" )
	public String register(Model model) {
		model.addAttribute("roles", service.getUserRoles());
		return "vistas/register";
	}
	
	@GetMapping("/app")
	public String loggedIn() {
		return "vistas/login_success";
	}
}
