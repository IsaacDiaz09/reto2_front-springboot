package com.ciclo4.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ciclo4.model.dto.UserDTO;
import com.ciclo4.service.UserServiceImpl;

@Controller
@RequestMapping("/app")
public class UserController {

	@Autowired
	private UserServiceImpl service;

	@GetMapping("/users")
	public String usersPage(Model model) {
		List<UserDTO> users = service.getAll();
		
		model.addAttribute("users",users);
		return "vistas/users";
	}
}