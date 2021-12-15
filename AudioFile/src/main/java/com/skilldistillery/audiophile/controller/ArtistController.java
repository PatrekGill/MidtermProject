package com.skilldistillery.audiophile.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.skilldistillery.audiophile.data.AlbumDAO;
import com.skilldistillery.audiophile.data.ArtistDAO;
import com.skilldistillery.audiophile.entities.Artist;
import com.skilldistillery.audiophile.entities.User;

@Controller
public class ArtistController {
	
	@Autowired
	private ArtistDAO artistDAO;
	
	@Autowired
	private AlbumDAO albumDAO;

	@GetMapping(path = "artistProfile")
	public String getAccoutnPage(HttpSession session, Artist artist) {
		session.setAttribute("artist", artist);
		session.setAttribute("albumsByArtist", albumDAO.findAlbumsByArtistName(artist.getName()));
		return "artist";
	}
}
