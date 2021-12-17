package com.skilldistillery.audiophile.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.skilldistillery.audiophile.data.AlbumDAO;
import com.skilldistillery.audiophile.data.ArtistDAO;
import com.skilldistillery.audiophile.data.UserDAO;
import com.skilldistillery.audiophile.entities.Album;
import com.skilldistillery.audiophile.entities.Artist;

@Controller
public class HomeController {
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private AlbumDAO albumDAO;
	
	@Autowired
	private ArtistDAO artistDAO;
	
	@RequestMapping(path = {"/", "home"})
	public String home(HttpSession session) {
		List<Album> topAlbums = albumDAO.sortAlbumsByRating(false);
		if(topAlbums.size() > 3) {
		session.setAttribute("topAlbums", topAlbums.subList(0, 3));
		}else {
			session.setAttribute("topAlbums", topAlbums);
		}
		List<Artist> topArtists = artistDAO.getTopThreeArtist(false);
		if(topArtists.size() > 3) {
			session.setAttribute("topArtists", topArtists.subList(0, 3));
		}else {
			session.setAttribute("topArtists", topArtists);
		}
		return "home";
	}

}
