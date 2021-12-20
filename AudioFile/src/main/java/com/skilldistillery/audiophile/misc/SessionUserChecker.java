package com.skilldistillery.audiophile.misc;

import javax.servlet.http.HttpSession;

import com.skilldistillery.audiophile.entities.User;

public class SessionUserChecker {
	public User getSessionUser(HttpSession session) {
		session.getAttribute("user");
		User user;
		try {
			user = (User)session.getAttribute("user");
		} catch (Exception e) {
			System.out.println("Did not find session user");
			user = null;
		}
		
		return user;
	}
	
	public boolean isSessionUser(HttpSession session, User user) {
		User sessionUser = getSessionUser(session);
		return (user != null && sessionUser != null && sessionUser.equals(user));
	}
}
