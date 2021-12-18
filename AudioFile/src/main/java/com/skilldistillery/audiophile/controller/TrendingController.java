package com.skilldistillery.audiophile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.skilldistillery.audiophile.data.AlbumDAOImpl;
import com.skilldistillery.audiophile.data.ArtistDAOImpl;
import com.skilldistillery.audiophile.data.SongDAOImpl;

@Controller
public class TrendingController {

		@Autowired
		private SongDAOImpl songDAO;
		
		@Autowired
		private AlbumDAOImpl albumDAO;
		
		@Autowired
		private ArtistDAOImpl artistDAO;
		
		/*-----------------------------------------------------------------------------
		    GET trending page
		 -----------------------------------------------------------------------------*/
		
		@GetMapping(path="trending")
		public String showTrendingPage(Model model) {
			model.addAttribute("tredningAlbums", albumDAO.sortAlbumsByRating(false));
			return "trending";
		}
	
}
