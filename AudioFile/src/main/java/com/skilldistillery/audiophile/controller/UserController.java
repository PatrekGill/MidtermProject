package com.skilldistillery.audiophile.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.skilldistillery.audiophile.data.UserDAO;

@Controller
public class UserController {
	
	@Autowired
	private UserDAO user;

	@GetMapping(path = "login.do")
	public String getLogin(HttpSession session) {
		if(session.getAttribute("user") != null) {
			return"index";
		}
		return "profile";
	}
}
