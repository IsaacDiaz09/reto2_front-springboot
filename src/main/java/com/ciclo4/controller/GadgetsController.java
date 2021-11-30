package com.ciclo4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ciclo4.service.GadgetServiceImpl;

@Controller
@RequestMapping("/app")
public class GadgetsController {

	@Autowired
	private GadgetServiceImpl service;

	@GetMapping("/gadgets")
	public String usersPage() {
		return "vistas/gadgets";
	}
}
