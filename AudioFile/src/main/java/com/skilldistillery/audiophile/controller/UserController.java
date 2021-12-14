package com.skilldistillery.audiophile.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.skilldistillery.audiophile.data.UserDAO;
import com.skilldistillery.audiophile.entities.User;

@Controller
public class UserController {
	
	@Autowired
	private UserDAO userDAO;

	@GetMapping(path = "login")
	public String getLogin(HttpSession session) {
		if(session.getAttribute("user") != null) {
			return"index";
		}
		return "profile";
	}
	
	@PostMapping(path = "login")
	public String login(HttpSession session, @RequestParam("username") String username, @RequestParam("password") String password) {
		User user = userDAO.login(username, password);
		if(user == (null)) {
			return "login";
		}
		session.setAttribute("user", user);
		
		return "redirect:/";
	}
	
	@GetMapping(path = "logout")
	public String logoutUser(HttpSession session) {
		session.removeAttribute("user");
		return "redirect:/";
		
	}
	
	@GetMapping(path = "account")
	public String getAccoutnPage(HttpSession session) {
		session.getAttribute("user");
		return "profile";
	}
	
	
}
