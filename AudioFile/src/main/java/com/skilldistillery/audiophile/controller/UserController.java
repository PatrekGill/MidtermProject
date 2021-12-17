package com.skilldistillery.audiophile.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.skilldistillery.audiophile.data.AlbumDAO;
import com.skilldistillery.audiophile.data.UserDAO;
import com.skilldistillery.audiophile.entities.Album;
import com.skilldistillery.audiophile.entities.User;

@Controller
public class UserController {

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private AlbumDAO albumDAO;

	@GetMapping(path = "login")
	public String getLogin(HttpSession session) {
		return "profile";
	}

	@PostMapping(path = "login")
	public String login(HttpSession session, @RequestParam("username") String username,
			@RequestParam("password") String password, RedirectAttributes redir) {
		try {
			User user = userDAO.login(username, password);
			if (user != null) {
				session.setAttribute("user", user);
				redir.addFlashAttribute("success", "Successfully logged in! Redirecting you to home page");
				return "redirect:home";
			} else {
				throw new Exception("Incorrect username or password");
			}
		} catch (Exception e) {
			redir.addFlashAttribute("error", e.getMessage());
			e.printStackTrace();
		}
		return "redirect:profile";
	}

	@GetMapping(path = "logout")
	public String logoutUser(HttpSession session) {
		session.removeAttribute("user");
		session.removeAttribute("albumsCreated");
		return "redirect:/";

	}

	@GetMapping(path = "profile")
	public String getAccountPage(HttpSession session, Album album) {
		User user = (User) session.getAttribute("user");
		if (session.getAttribute("update") != null) {
			session.removeAttribute("update");
		}
		if (user == null) {
			return "profile";
		}
		session.setAttribute("albumsCreated", albumDAO.findAlbumsByCreatedUsername(user.getUsername()));
		return "profile";
	}

	@GetMapping(path = "createAccount")
	public String getCreateAccountPage(HttpSession session) {
		return "createAccount";
	}

	@PostMapping(path = "createAccount")
	public String createAccount(User user, RedirectAttributes redir) {
		try {
			if (userDAO.createUser(user) != null) {
				redir.addFlashAttribute("success", "Account successfully created!");
				return "redirect:profile";
			} else {
				throw new Exception("Failed to create account");
			}
		} catch (Exception e) {
			redir.addFlashAttribute("error", e.getMessage() + ": " + user.toString());
			e.printStackTrace();
		}
		return "redirect:profile";
	}

	@PostMapping(path = "deleteAccount")
	public String deleteAccount(RedirectAttributes redir, HttpSession session) {
		try {
			User userToDelete = (User) session.getAttribute("user");
			if (userDAO.deleteUser(userToDelete.getId())) {
				session.removeAttribute("user");
				redir.addFlashAttribute("success", "Account deleted");
				return "redirect:/";
			} else {
				throw new Exception("cannot delete film.");
			}
		} catch (Exception e) {
			redir.addFlashAttribute("error", e.getMessage());
			e.printStackTrace();
		}
		return "redirect:/";
	}

	@GetMapping(path = "updateAccount")
	public String getUpdateAccount(HttpSession session) {
		try {
			User userToUpdate = (User) session.getAttribute("user");
			session.setAttribute("update", userToUpdate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "profile";
	}

	@PostMapping(path = "updateAccount")
	public String postUpdateAccount(User user, HttpSession session, RedirectAttributes redir) {
		try {
			User userToUpdate = (User) session.getAttribute("user");
			if (userDAO.updateUser(userToUpdate.getId(), user)) {
				session.setAttribute("user", userDAO.findUserById(userToUpdate.getId()));
				session.removeAttribute("update");
				redir.addFlashAttribute("success", "Account updated");
				return "redirect:profile";
			} else {
				throw new Exception("cannot delete film.");
			}

		} catch (Exception e) {
			redir.addFlashAttribute("error", e.getMessage());
			e.printStackTrace();
		}
		return "redirect:profile";
	}

	@GetMapping(path = "friendList")
	public String getFriendPage(HttpSession session, Album album) {
		User user1 = userDAO.findUserById(2);
		session.setAttribute("user1", user1);
		if (user1 == null) {
			return "friendPage";
		}
		session.setAttribute("albumsCreated", albumDAO.findAlbumsByCreatedUsername(user1.getUsername()));
		return "friendPage";
	}


}
