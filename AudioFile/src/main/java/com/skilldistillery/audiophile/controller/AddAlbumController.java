package com.skilldistillery.audiophile.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.skilldistillery.audiophile.data.AlbumDAO;
import com.skilldistillery.audiophile.data.ArtistDAO;
import com.skilldistillery.audiophile.data.SongDAO;
import com.skilldistillery.audiophile.entities.Album;
import com.skilldistillery.audiophile.entities.Artist;
import com.skilldistillery.audiophile.entities.Song;

@Controller
public class AddAlbumController {
	
	@Autowired
	private AlbumDAO albumDAO;
	@Autowired
	private SongDAO songDAO;
	@Autowired
	private ArtistDAO artistDAO;
	
//not needed !!!!
//	
}
	

