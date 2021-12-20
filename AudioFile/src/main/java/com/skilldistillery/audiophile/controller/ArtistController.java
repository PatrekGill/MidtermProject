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
public class ArtistController {

	@Autowired
	private ArtistDAOImpl artistDAO;

	@Autowired
	private AlbumDAOImpl albumDAO;
	@Autowired
	private SongDAOImpl songDAO;

	@GetMapping(path = "artistProfile")
	public String getAccountPage(Model model, int id, RedirectAttributes redir) {
		try {
			Artist artist = artistDAO.findById(id);
			if (artist != null) {
				model.addAttribute("artist", artist);
				List<Album> artistsHighestRatedAlbums = albumDAO.findAlbumsByArtistSortByRating(false,
						artist.getName());
				if (artistsHighestRatedAlbums.size() > 3) {
					model.addAttribute("artistsHighestRatedAlbums", artistsHighestRatedAlbums.subList(0, 3));
				} else {
					model.addAttribute("artistsHighestRatedAlbums", artistsHighestRatedAlbums);
				}
				return "artist";
			} else {
				throw new Exception("No artist found with id: " + id);
			}
		} catch (Exception e) {
			redir.addFlashAttribute("error", e.getMessage());
			e.printStackTrace();
		}
		return "redirect:home";
	}
	
	
	/* ----------------------------------------------------------------------------
		editArtist (GET)
	---------------------------------------------------------------------------- */
	@GetMapping(path="editArtist")
	public String getEditArtistPage(
			Integer artistId,
			HttpSession session,
			Model model,
			RedirectAttributes redir
		) {
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "profile";
		}
		
		boolean editing = false;
		if (artistId != null) {
			Artist artist = artistDAO.findById(artistId);
			if (artist != null) {
				if (artist.getUser().equals(user)) {
					model.addAttribute("artist", artist);				
					editing = true;
					
				} else {
					redir.addFlashAttribute("warning", "Only the creating user can edit the details of this item");
					redir.addAttribute("id",artistId);
					return "redirect:artistProfile";
					
				}
			}
			
			model.addAttribute("editing",editing);
			
			List<Album> allAlbums = albumDAO.sortAlbumsByTitle(true);
			model.addAttribute("albums",allAlbums);
			
			List<Song> allSongs = songDAO.sortByName(true);
			model.addAttribute("songs",allSongs);

			return "editArtist";
		}
		
		redir.addFlashAttribute("error", "Could not locate your artist");
		return "redirect:/";
	}
	
	
	/* ----------------------------------------------------------------------------
		editAlbum (POST)
	---------------------------------------------------------------------------- */
	@PostMapping(path="editArtist")
	public String editArtist(
			Integer artistId, String name, 
			String description,	String imageURL,
			Integer[] albumIds, Integer[] songIds,
			HttpSession session,
			Model model,
			RedirectAttributes redir
		) {
		
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "profile";
		}
		
		
		Artist artist = new Artist();
		try {
			artist.setName(name);
			artist.setDescription(description);
			artist.setImageUrl(imageURL);
			
			for (Integer id : songIds) {
				artist.addSong(songDAO.findById(id));
			}
			
			for (Integer id : albumIds) {
				artist.addAlbum(albumDAO.findAlbumById(id));
			}
			
			
			boolean succeeded = false;
			boolean updating = artistId != null;
			String message;
			if (updating) {
				succeeded = artistDAO.updateArtist(artistId, artist);
				message = "Artist successfully updated!";
				
			} else {
				succeeded = artistDAO.addNewArtist(artist) != null;
				message = "Artist successfully created!";
				artistId = artist.getId();
				
			}
			
			
			if (succeeded) {
				redir.addFlashAttribute("success", message);
				redir.addAttribute("id", artistId);
				return "redirect:artistProfile";
				
			} else {
				
				if (updating) {
					message = "Failed to update artist: " + artistId;
				} else {
					message = "Failed to create new artist";
				}
				
				throw new Exception(message);
			}
			
		} catch (Exception e) {
			redir.addFlashAttribute("error", e.getMessage());
			e.printStackTrace();
		}
	
		
		return "redirect:/";
	}
	
	
}
