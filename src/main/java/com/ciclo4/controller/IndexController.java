package com.ciclo4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ciclo4.model.User;
import com.ciclo4.service.UserServiceImpl;

@Controller
public class IndexController {
	
	@Autowired
	private UserServiceImpl service;
	
	@GetMapping({ "/", "/login" })
	public String index() {
		/**
		 * Verifica que el usuario no haya inicado sesion para que le envie al
		 * login,sino, directamente dentro a la aplicacion
		 */
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
			return "vistas/login";
		}
		return "redirect:/app";
	}
	
	@GetMapping("/register" )
	public String register(Model model) {
		model.addAttribute("user",new User());

		model.addAttribute("text_h3","Crea tu cuenta");
		model.addAttribute("roles", service.getUserRoles());
		return "vistas/register";
	}
	
	@GetMapping("/app")
	public String loggedIn() {
		return "vistas/login_success";
	}
}
