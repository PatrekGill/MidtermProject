package com.skilldistillery.audiophile.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.skilldistillery.audiophile.data.AlbumDAOImpl;
import com.skilldistillery.audiophile.data.ArtistDAOImpl;
import com.skilldistillery.audiophile.data.SongDAOImpl;
import com.skilldistillery.audiophile.entities.Album;
import com.skilldistillery.audiophile.entities.Artist;
import com.skilldistillery.audiophile.entities.Song;
import com.skilldistillery.audiophile.entities.User;

@Controller
public class SongController {

	@Autowired
	private AlbumDAOImpl albumDAO;

	@Autowired
	private SongDAOImpl songDAO;

	@Autowired
	private ArtistDAOImpl artistDAO;
	
	
	@GetMapping(path = "editSong")
	public String getEditSongPage(
			Integer songId,
			HttpSession session,
			Model model,
			RedirectAttributes redir
		) {
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "profile";
		}
		
		
		// if editing song (not creating a new one) save and id to identify what artists are currently selected for that song
		if (songId != null) {
			Song song = songDAO.findById(songId);
			if (song != null) {
				if (song.getUser().equals(user)) {
					model.addAttribute(song);
					model.addAttribute("editing",true);
					
				} else {
					redir.addFlashAttribute("warning", "Only the creating user can edit the details of this item");
					redir.addAttribute("songName",song.getName());
					return "redirect:searchBySongName.do";
				}
			}
		}
		
		List<Artist> allArtists = artistDAO.sortArtistsAlphabetically();
		model.addAttribute("artists",allArtists);
		
		List<Album> allAlbums = albumDAO.sortAlbumsByTitle(true);
		model.addAttribute("albums",allAlbums);
		
		return "editSong";
	}

	
	
	@PostMapping(path = "editSong")
	public String editSong(
			Integer songId, Integer[] artistIds, Integer[] albumIds, 
			String name, String lyrics,	int durationInSeconds, 
			RedirectAttributes redir, HttpSession session
		) {
		
		Song song = new Song();
		User user = (User) session.getAttribute("user");
		try {
			song.setName(name);
			song.setLyrics(lyrics);
			song.setDurationInSeconds(durationInSeconds);
			song.setUser(user);
			
			for (Integer artistId : artistIds) {
				song.addArtist(artistDAO.findById(artistId));
			}
			
			for (Integer albumId : albumIds) {
				song.addAlbum(albumDAO.findAlbumById(albumId));
			}
			
			boolean succeeded = false;
			boolean updating = songId != null;
			String message;
			if (updating) {
				succeeded = songDAO.updateSong(songId,song);
				message = "Song successfully updated!";
				
			} else {
				succeeded = songDAO.addNewSong(song) != null;
				message = "Song successfully created!";
				songId = song.getId();
			}
			

			if (succeeded) {
				redir.addFlashAttribute("success", message);
				redir.addAttribute("songName",song.getName());
//				redir.addAttribute("songName",songId);
				return "redirect:searchBySongName.do";
				
			} else {
				if (updating) {
					message = "Failed to update song: " + songId;
				} else {
					message = "Failed to create new song";
				}
				
				throw new Exception(message);
			}
			
		} catch (Exception e) {
			redir.addFlashAttribute("error", e.getMessage() + ": " + song.toString());
			e.printStackTrace();
		}
		
		
		return "redirect:/";

	}
}
