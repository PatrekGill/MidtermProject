package com.skilldistillery.audiophile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.skilldistillery.audiophile.data.UserDAO;

@Controller
public class HomeController {
	
	@Autowired
	private UserDAO userDAO;
	
	@RequestMapping(path = {"/","home.do"})
	public String home(Model model) {
		model.addAttribute("DEBUG",userDAO.findByUsername("admin"));
		return "home";
	}

}
