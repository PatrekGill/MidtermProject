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
	
//	@GetMapping(path ="addAlbum")
//	public String getAddAblumpage(HttpSession session) {
//		return"addAlbum";
//	}
//	@PostMapping(path ="addAlbum")
//	public String createAlbum(Album album,RedirectAttributes redir ) {
//		try {
//		if(albumDAO.addAlbum(album) != null) {
//			redir.addFlashAttribute("success", "Account successfully created!");
//			return "redirect:profile";
//		} else {
//			throw new Exception("Failed to create account");
//		}
//		}catch (Exception e) {
//			redir.addFlashAttribute("error", e.getMessage() + ": " + album.toString());
//			e.printStackTrace();
//		}
//		return"redirect:album";
//	
//	}
	@GetMapping(path ="addSong")
	public String getAddSongpage(HttpSession session) {
		return"addAlbum";
	}
	@PostMapping(path ="addSong")
	public String createSong(Song song,RedirectAttributes redir ) {
		try {
			if(songDAO.addNewSong(song) != null) {
				redir.addFlashAttribute("success", "Account successfully created!");
				return "redirect:profile";
			} else {
				throw new Exception("Failed to create account");
			}
		}catch (Exception e) {
			redir.addFlashAttribute("error", e.getMessage() + ": " + song.toString());
			e.printStackTrace();
		}
		return"redirect:song";
		
	}
	@GetMapping(path ="addArtist")
	public String getAddArtistpage(HttpSession session) {
		return"addArtist";
	}
	
	@PostMapping(path ="addArtist")
	public String createArtist(Artist artist,RedirectAttributes redir ) {
		try {
			if(artistDAO.addNewArtist(artist) != null) {
				redir.addFlashAttribute("success", "Account successfully created!");
				return "redirect:profile";
			} else {
				throw new Exception("Failed to create account");
			}
		}catch (Exception e) {
			redir.addFlashAttribute("error", e.getMessage() + ": " + artist.toString());
			e.printStackTrace();
		}
		return"redirect:artist";
		
	}
}
	

