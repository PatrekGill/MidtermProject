package com.skilldistillery.audiophile.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.skilldistillery.audiophile.data.AlbumDAO;
import com.skilldistillery.audiophile.data.ArtistDAO;
import com.skilldistillery.audiophile.data.SongDAO;
import com.skilldistillery.audiophile.entities.Album;
import com.skilldistillery.audiophile.entities.Artist;
import com.skilldistillery.audiophile.entities.Song;
import com.skilldistillery.audiophile.entities.SongRating;

@Controller
public class SearchController {

	@Autowired
	private SongDAO songDAO;
	@Autowired
	private AlbumDAO albumDAO;
	@Autowired
	private ArtistDAO artistDAO;

	/*
	 * --------------------------- Search for Songs ---------------------------
	 */

	@GetMapping(path = "search")
	public String getSearchResults(String keyword, String searchType, Model model,
			RedirectAttributes redir) {
		boolean isAllSearch = searchType.equalsIgnoreCase("All");
		List<Song> songs = new ArrayList<>();
		List<Album> albums = new ArrayList<>();
		List<Artist> artists = new ArrayList<>();
		
		try {
			if (keyword.trim().length() > 0) {
				
				if (!isAllSearch && searchType.equalsIgnoreCase("Genre")) {
					albums = albumDAO.findAlbumsByGenreName(keyword);
					model.addAttribute("Albums", albums);
				}
				

				if (isAllSearch || searchType.equalsIgnoreCase("Album")) {
					albums = albumDAO.findAlbumsByTitle(keyword);
					model.addAttribute("Albums", albums);
				}
				if (isAllSearch || searchType.equalsIgnoreCase("Artist")) {
					artists = artistDAO.findByArtistsName(keyword);
					model.addAttribute("Artists", artists);
				}
				if (isAllSearch || searchType.equalsIgnoreCase("Song")) {
					songs = songDAO.findBySongName(keyword);
					model.addAttribute("Songs", songs);
				}
				
				boolean noResults = false;
				if (albums.isEmpty() && artists.isEmpty() && songs.isEmpty()) {
					noResults = true;
				}
				model.addAttribute("NotPopulated", noResults);
				
				
				return "result";
				
			} else {
				throw new Exception("valid entry required in search field");
				
			}
			
			
			
		} catch (Exception e) {
			redir.addFlashAttribute("error", e.getMessage());
			e.printStackTrace();
		}
		return "redirect:home";
	}
	
}
