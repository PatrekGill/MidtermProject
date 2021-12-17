package com.skilldistillery.audiophile.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.skilldistillery.audiophile.data.AlbumDAO;
import com.skilldistillery.audiophile.data.UserDAO;
import com.skilldistillery.audiophile.entities.Album;

@Controller
public class HomeController {
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private AlbumDAO albumDAO;
	
	@RequestMapping(path = {"/", "home"})
	public String home(HttpSession session) {
		List<Album> topAlbums = albumDAO.sortAlbumsByRating(false);
		if(topAlbums.size() > 3) {
		session.setAttribute("topAlbums", topAlbums.subList(0, 3));
		}else {
			session.setAttribute("topAlbums", topAlbums);
		}
		return "home";
	}

}
