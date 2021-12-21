package com.skilldistillery.audiophile.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.skilldistillery.audiophile.data.AlbumDAOImpl;
import com.skilldistillery.audiophile.data.ArtistDAOImpl;
import com.skilldistillery.audiophile.data.SongDAOImpl;
import com.skilldistillery.audiophile.data.SongRatingDAOImpl;
import com.skilldistillery.audiophile.data.UserDAOImpl;
import com.skilldistillery.audiophile.entities.Album;
import com.skilldistillery.audiophile.entities.Artist;
import com.skilldistillery.audiophile.entities.Song;
import com.skilldistillery.audiophile.entities.SongRating;
import com.skilldistillery.audiophile.entities.User;
import com.skilldistillery.audiophile.misc.SessionUserChecker;

@Controller
public class SongController {

	@Autowired
	private UserDAOImpl userDAO;
	@Autowired
	private SongDAOImpl songDAO;
	@Autowired
	private SongRatingDAOImpl songRatingDAO;
	@Autowired
	private AlbumDAOImpl albumDAO;
	@Autowired
	private ArtistDAOImpl artistDAO;
	private SessionUserChecker checker = new SessionUserChecker();

	/*
	 * --------------------------- 
	 * get song detail page
	 * ---------------------------
	 */

	@GetMapping(path = "getSongId.do")
	public String getBySongId(@RequestParam("songId") int songId, Model model) {
		Song song = null;
		try {
			song = songDAO.findById(songId);
			 long durationSeconds = song.getDurationInSeconds();
				String newDurationSeconds;
				long MM = durationSeconds / 60;
				long SS = durationSeconds % 60;
				newDurationSeconds = String.format("%02d:%02d", MM, SS);
			model.addAttribute("Song", song);
			model.addAttribute("DurationSeconds",newDurationSeconds);
			model.addAttribute("albums", albumDAO.findAlbumsBySongTitle(song.getName()));
			model.addAttribute("artists", artistDAO.findArtistsBySongName(song.getName()));
			System.out.println(songRatingDAO.getAverageSongRating(songId));
			model.addAttribute("averageRating", songRatingDAO.getAverageSongRating(songId));
			List<SongRating> ratings = songRatingDAO.sortRatingByCreationDateFindBySongId(songId, false);
			model.addAttribute("songRatings", ratings);
		} catch (Exception e) {
			System.out.println("Did not find session user");
			song = null;
		}
		return "song/SongDetails";
	}

	/*
	 * ----------------------------------------------------------------------------
	 * songRatings.do (GET)
	 * ----------------------------------------------------------------------------
	 */
	@GetMapping(path = "songRatings.do")
	public String showRatingsPage(Integer songId, HttpSession session, Model model) {
		if (songId != null) {

			Song song = songDAO.findById(songId);
			if (song != null) {
				model.addAttribute("song", song);

				User user = (User) session.getAttribute("user");
				boolean userHasRating = false;
				if (user != null) {
					SongRating usersRating = songRatingDAO.findSongRatingByUserIdSongId(user.getId(), songId);
					if (usersRating != null) {
						model.addAttribute("usersRating", usersRating);
						userHasRating = true;
					}

				}

				model.addAttribute("userHasRating", userHasRating);

				List<SongRating> ratings = songRatingDAO.sortRatingByCreationDateFindBySongId(songId, false);
				model.addAttribute("songRatings", ratings);
				model.addAttribute("averageRating", songRatingDAO.getAverageSongRating(songId));
			}
		}
		return "song/songRatings";
	}

	/*
	 * ----------------------------------------------------------------------------
	 * songRatings.do (POST)
	 * ----------------------------------------------------------------------------
	 */
	@PostMapping(path = "songRatings.do")
	public String postRating(Integer songId, String ratingText, Integer ratingNumber, HttpSession session,
			Model model) {

		if (songId != null && ratingNumber != null) {

			Song song = songDAO.findById(songId);
			if (song != null) {
				model.addAttribute("song", song);

				User user = checker.getSessionUser(session);
				if (user != null) {
					int userId = user.getId();
					SongRating usersRating = songRatingDAO.findSongRatingByUserIdSongId(userId, songId);

					if (usersRating != null) {
						// update rating
						int ratingId = usersRating.getId();
						songRatingDAO.updateDescription(ratingId, ratingText);
						songRatingDAO.updateRating(ratingId, ratingNumber);

					} else {
						// create rating
						usersRating = new SongRating();
						usersRating.setSong(song);
						usersRating.setDescription(ratingText);
						usersRating.setRating(ratingNumber);
						usersRating.setUser(userDAO.findUserById(userId));
						songRatingDAO.createSongRating(usersRating);

					}

					model.addAttribute("usersRating", usersRating);
					model.addAttribute("userHasRating", true);

				}

				List<SongRating> ratings = songRatingDAO.sortRatingByCreationDateFindBySongId(songId, false);
				model.addAttribute("songRatings", ratings);
				model.addAttribute("averageRating", songRatingDAO.getAverageSongRating(songId));
			}
		}

		return "song/songRatings";
	}
	
	
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
		boolean editing = false;
		if (songId != null) {
			Song song = songDAO.findById(songId);
			
			if (song != null) {
				if (song.getUser().equals(user)) {
					model.addAttribute(song);
					editing = true;
					
				} else {
					redir.addFlashAttribute("warning", "Only the creating user can edit the details of this item");
					redir.addAttribute("songId",songId);
					return "redirect:getSongId.do";
				}
				
			}

		}
		
		model.addAttribute("editing",editing);
		
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
		
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "profile";
		}
		
		Song song;
		if (songId != null) {
			song = songDAO.findById(songId);
			
		} else {
			song = new Song();
			
		}
		
		try {
			song.setUser(user);
			song.setName(name);
			song.setLyrics(lyrics);
			song.setDurationInSeconds(durationInSeconds);
			song.setUser(user);
			
			// if editing song
			if (artistIds != null) {
				List<Artist> artists = song.getArtists();
				if (!artists.isEmpty()) {
					List<Integer> artistIdsList = Arrays.asList(artistIds);
					
					for (int i = 0; i < artists.size(); i++) {
						Artist artist = artists.get(i);
						if (!artistIdsList.contains(artist.getId())) {
							artist.removeSong(song);
						}
					}
				}
				for (Integer artistId : artistIds) {
					song.addArtist(artistDAO.findById(artistId));
				}
				
			}
			
			if (albumIds != null) {
				List<Album> albums = song.getAlbums();
				if (!albums.isEmpty()) {
					List<Integer> albumIdsList = Arrays.asList(albumIds);
					
					for (int i = 0; i < albums.size(); i++) {
						Album album = albums.get(i);
						if (!albumIdsList.contains(album.getId())) {
							album.removeSong(song);
						}
					}
				}
				for (Integer albumId : albumIds) {
					song.addAlbum(albumDAO.findAlbumById(albumId));
				}
				
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
				redir.addAttribute("songId",songId);
				return "redirect:getSongId.do";
				
			} else {
				if (updating) {
					message = "Failed to update song: " + songId;
				} else {
					message = "Failed to create new song";
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
