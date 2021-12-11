package com.skilldistillery.audiophile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.skilldistillery.audiophile.data.UserDAO;

@Controller
public class HomeController {
	
	@Autowired
	private UserDAO userDAO;

}
