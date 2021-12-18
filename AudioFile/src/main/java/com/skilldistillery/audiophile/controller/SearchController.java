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
				
				
				if (isAllSearch || searchType.equalsIgnoreCase("Album")) {
					albums = albumDAO.findAlbumsByTitle(keyword);
					model.addAttribute("Albums", albums);
				}
				
				if (isAllSearch || searchType.equalsIgnoreCase("Artist")) {
					artists = artistDAO.findByArtistsName(keyword);
					model.addAttribute("Artists", artists);
				}
				
				if (!isAllSearch && searchType.equalsIgnoreCase("Genre")) {
					albums = albumDAO.findAlbumsByGenreName(keyword);
					model.addAttribute("Albums", albums);
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
	
	
	
	/*
	 * --------------------------- Song details page result
	 * ---------------------------
	 */

	@RequestMapping(path = "searchBySongName.do", params = "songName", method = RequestMethod.GET)
	public ModelAndView getBySongName(@RequestParam("songName") String songName, RedirectAttributes redir) {
		ModelAndView mv = new ModelAndView();
		try {
		List<Song> songs = songDAO.findBySongName(songName);
		if(songs != null) {
//		Artist artist = artistDAO.findPrimaryArtistByAlbumName(album.getTitle());
		Song song = songs.get(0);
		int songId = song.getId();
		String userName = song.getUser().getUsername();
		int userId = song.getUser().getId();
		LocalDateTime createDate = song.getCreateDate();
		LocalDateTime ratingDate;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String newCreateDate = createDate.format(formatter);
		int durationSeconds = song.getDurationInSeconds();
		String newDurationSeconds;
		long MM = durationSeconds / 60;
		long SS = durationSeconds % 60;
		newDurationSeconds = String.format("%02d:%02d", MM, SS);
		if (song.getSongRatings().size() > 0) {
			for (SongRating songRating : song.getSongRatings()) {
				ratingDate = songRating.getRatingDate();
				String newRatingDate = ratingDate.format(formatter);
				String description = songRating.getDescription();
				int rating = songRating.getRating();
				mv.addObject("RateDate", newRatingDate);
				mv.addObject("Comments", description);
				mv.addObject("Rating", rating);
			}
		} else {
			String noComment = "";
			mv.addObject("RateDate", noComment);
		}
		mv.addObject("Song", song);
		mv.addObject("User", userName);
		mv.addObject("CreateDate", newCreateDate);
		mv.addObject("DurationSeconds", newDurationSeconds);
		mv.setViewName("SongDetails");
		return mv;
		}else {
			throw new Exception("No song found");
		}
		}catch (Exception e) {
			redir.addFlashAttribute("error", e.getMessage());
			e.printStackTrace();
		}
		mv.setViewName("redirect:home");
		return mv;
		
	}

}
