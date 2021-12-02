package com.ciclo4.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ciclo4.model.Gadget;
import com.ciclo4.service.GadgetServiceImpl;

@Controller
@RequestMapping("/app/gadgets")
public class GadgetsController {

	@Autowired
	private GadgetServiceImpl service;

	@GetMapping
	public String gadgetsPage(Model model) {
		List<Gadget> gadgets = service.getAll();
		model.addAttribute("gadgets", gadgets);
		return "vistas/gadgets";
	}

	@GetMapping("/update")
	public String editar(Gadget gadget,Model model) {
		model.addAttribute("text_h3", "Editar gadget");

		return "vistas/gadgets";
	}
	
	@GetMapping("/add")
	public String agregar(Gadget gadget, Model model) {
		model.addAttribute("text_h3", "Registrar producto");

		return "vistas/new-gadget";
	}
}
