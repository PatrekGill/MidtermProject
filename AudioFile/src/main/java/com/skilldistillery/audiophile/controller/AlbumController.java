package com.skilldistillery.audiophile.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.skilldistillery.audiophile.data.AlbumCommentDAOImpl;
import com.skilldistillery.audiophile.data.AlbumDAOImpl;
import com.skilldistillery.audiophile.data.AlbumRatingDAOImpl;
import com.skilldistillery.audiophile.data.ArtistDAOImpl;
import com.skilldistillery.audiophile.data.GenreDAOImpl;
import com.skilldistillery.audiophile.data.SongDAOImpl;
import com.skilldistillery.audiophile.data.UserDAOImpl;
import com.skilldistillery.audiophile.entities.Album;
import com.skilldistillery.audiophile.entities.AlbumComment;
import com.skilldistillery.audiophile.entities.AlbumRating;
import com.skilldistillery.audiophile.entities.Artist;
import com.skilldistillery.audiophile.entities.Genre;
import com.skilldistillery.audiophile.entities.Song;
import com.skilldistillery.audiophile.entities.User;
import com.skilldistillery.audiophile.misc.SessionUserChecker;

@Controller
public class AlbumController {
	@Autowired
	private UserDAOImpl userDAO;
	@Autowired
	private AlbumDAOImpl albumDAO;
	@Autowired
	private ArtistDAOImpl artistDAO;
	@Autowired
	private SongDAOImpl songDAO;
	@Autowired
	private AlbumRatingDAOImpl albumRatingDAO;
	@Autowired
	private AlbumCommentDAOImpl albumCommentDAO;
	@Autowired
	private GenreDAOImpl genreDAO;
	
	private SessionUserChecker checker = new SessionUserChecker();
	

	/* ----------------------------------------------------------------------------
		album.do (GET)
	---------------------------------------------------------------------------- */
	@GetMapping(path="album.do")
	public String showAlbumPage(
			Integer albumId, 
			HttpSession session, 
			Model model,
			RedirectAttributes redir
		) {
		
		if (albumId != null) {
			Album album = albumDAO.findAlbumById(albumId);
			if (album != null) {
				
				model.addAttribute("averageRating",albumRatingDAO.getAverageAlbumRating(albumId));
				model.addAttribute("album", album);
				
				List<AlbumComment> comments = albumCommentDAO.sortAlbumCommentsByCommentDate(albumId, false, 10);
				if (!comments.isEmpty()) {
					model.addAttribute("albumComments",comments);
				}
				
				List<AlbumRating> ratings = albumRatingDAO.sortedByCreationDate(albumId, false, 10);
				if (!ratings.isEmpty()) {
					model.addAttribute("albumRatings",ratings);
				}
				
				return "album/pages/album";
			}
		}
		
		redir.addFlashAttribute("error","This album does not seem to exist");
		return "redirect:/";
	}
	
	
	
	/* ----------------------------------------------------------------------------
		albumRatings.do (GET)
	---------------------------------------------------------------------------- */
	@GetMapping(path="albumRatings.do")
	public String showRatingsPage(
			Integer albumId, 
			HttpSession session, 
			Model model,
			RedirectAttributes redir
		) {
		
		if (albumId != null) {
			
			Album album = albumDAO.findAlbumById(albumId);
			if (album != null) {
				model.addAttribute("album", album);
				
				User user = checker.getSessionUser(session);
				boolean userHasRating = false;
				if (user != null) {
					AlbumRating usersRating = albumRatingDAO.findByAlbumAndUserId(albumId, user.getId());
					if (usersRating != null) {
						model.addAttribute("usersRating",usersRating);
						userHasRating = true;
					}
					
				}
				
				model.addAttribute("userHasRating",userHasRating);
				
				List<AlbumRating> ratings = albumRatingDAO.sortedByCreatationDate(albumId, false);
				model.addAttribute("albumRatings",ratings);
				model.addAttribute("averageRating",albumRatingDAO.getAverageAlbumRating(albumId));

				return "album/pages/albumRatings";
			}
		}
		
		redir.addFlashAttribute("error","This album does not seem to exist");
		return "redirect:/";
	}
	
	/* ----------------------------------------------------------------------------
		albumRatings.do (POST)
	---------------------------------------------------------------------------- */
	@PostMapping(path="albumRatings.do")
	public String postRating(
			Integer albumId, 
			String ratingText, 
			Integer ratingNumber, 
			HttpSession session, 
			Model model,
			RedirectAttributes redir
		) {
		
		
		if (albumId != null && ratingNumber != null) {
			
			Album album = albumDAO.findAlbumById(albumId);
			if (album != null) {
				model.addAttribute("album", album);
				 
				User user = checker.getSessionUser(session);
				if (user != null) {
					int userId = user.getId();
					AlbumRating usersRating = albumRatingDAO.findByAlbumAndUserId(albumId, userId);
					
					if (usersRating != null) {
						// update rating
						int ratingId = usersRating.getId();
						albumRatingDAO.updateDescription(ratingId, ratingText);
						albumRatingDAO.updateRating(ratingId, ratingNumber);
						
					} else {
						// create rating
						usersRating = new AlbumRating();
						usersRating.setAlbum(album);
						usersRating.setDescription(ratingText);
						usersRating.setRating(ratingNumber);
						usersRating.setUser(userDAO.findUserById(userId));
						albumRatingDAO.createAlbumRating(usersRating);
						
					}
					
					model.addAttribute("usersRating",usersRating);
					model.addAttribute("userHasRating",true);
					
				}
				
				List<AlbumRating> ratings = albumRatingDAO.sortedByCreatationDate(albumId, false);
				model.addAttribute("albumRatings",ratings);
				model.addAttribute("averageRating",albumRatingDAO.getAverageAlbumRating(albumId));
				
				redir.addFlashAttribute("success","Posted Your Rating!");
				redir.addAttribute("albumId",albumId);
				
				return "redirect:albumRatings.do";
			}
		}
		
		redir.addFlashAttribute("error","Could not post this rating");
		return "redirect:/";
	}
	
	
	/* ----------------------------------------------------------------------------
		deleteRating.do (POST)
	---------------------------------------------------------------------------- */
	@PostMapping(path="deleteRating.do")
	public String deleteRating(
			Integer albumId, 
			HttpSession session, 
			Model model,
			RedirectAttributes redir
		) {
		
		if (albumId != null) {
			
			Album album = albumDAO.findAlbumById(albumId);
			if (album != null) {
				model.addAttribute("album", album);
				 
				User user = checker.getSessionUser(session);
				boolean deleted = false;
				if (user != null) {
					AlbumRating usersRating = albumRatingDAO.findByAlbumAndUserId(albumId, user.getId());
					if (usersRating != null) {
						// delete here
						deleted = albumRatingDAO.deleteAlbumRating(usersRating.getId());
						if (deleted) {
							model.addAttribute("userHasRating",false);
							redir.addFlashAttribute("success","Deleted your rating");
						}
					}
				}
				
				List<AlbumRating> ratings = albumRatingDAO.sortedByCreatationDate(albumId, false);
				model.addAttribute("albumRatings",ratings);
				model.addAttribute("averageRating",albumRatingDAO.getAverageAlbumRating(albumId));
				
				if (!deleted) {
					redir.addFlashAttribute("error","Could not delete that rating");
				}
				
				redir.addAttribute("albumId",albumId);
				return "redirect:albumRatings.do";
			}
		}
		
		redir.addFlashAttribute("error","Could not delete this rating");
		return "redirect:/";
	}
	
	
	
	/* ----------------------------------------------------------------------------
		editAlbum (GET)
	---------------------------------------------------------------------------- */
	@GetMapping(path="editAlbum")
	public String getEditAlbumPage(
			Integer albumId,
			HttpSession session,
			Model model,
			RedirectAttributes redir
		) {
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "profile";
		}
		
		
		if (albumId != null) {
			Album album = albumDAO.findAlbumById(albumId);
			if (album != null) {
				if (album.getUser().equals(user)) {
					model.addAttribute("album", album);				
					model.addAttribute("editing",true);
					
				} else {
					redir.addFlashAttribute("warning", "Only the creating user can edit the details of this item");
					redir.addAttribute("albumId",albumId);
					return "redirect:album.do";
				}
			}
		}
		
		List<Artist> allArtists = artistDAO.sortArtistsAlphabetically();
		model.addAttribute("artists",allArtists);
		
		List<Song> allSongs = songDAO.sortByName(true);
		model.addAttribute("songs",allSongs);
		
		List<Genre> allGenres = genreDAO.sortByName(true);
		model.addAttribute("genres",allGenres);
		
		return "editAlbum";
	}
	
	
	/* ----------------------------------------------------------------------------
		editAlbum (POST)
	---------------------------------------------------------------------------- */
	@PostMapping(path="editAlbum")
	public String editAlbum(
			Integer albumId, String title, String description,
			Integer artistId, String imageURL, String releaseDate,
			Integer[] songIds, Integer[] genreIds,
			HttpSession session,
			Model model,
			RedirectAttributes redir
		) {
		
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "profile";
		}
		
		
		Album album = new Album();
		try {
			album.setTitle(title);
			album.setDescription(description);
			album.setArtist(artistDAO.findById(artistId));
			album.setImageURL(imageURL);
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate localDate = LocalDate.parse(releaseDate, formatter);
			LocalDateTime localDateTime = LocalDateTime.of(localDate, LocalDateTime.now().toLocalTime());
			album.setReleaseDate(localDateTime);
			
			for (Integer id : songIds) {
				album.addSong(songDAO.findById(id));
			}
			
			for (Integer id : genreIds) {
				album.addGenre(genreDAO.findGenreById(id));
			}
			
			
			boolean succeeded = false;
			boolean updating = albumId != null;
			String message;
			if (updating) {
				succeeded = albumDAO.updateAlbum(albumId, album);
				message = "Album successfully updated!";
				
			} else {
				succeeded = albumDAO.addAlbum(album) != null;
				message = "Album successfully created!";
				albumId = album.getId();
				
			}
			
			
			if (succeeded) {
				redir.addFlashAttribute("success", message);
				redir.addAttribute("albumId", albumId);
				return "redirect:album.do";
				
			} else {
				if (updating) {
					message = "Failed to update album: " + albumId;
				} else {
					message = "Failed to create new album";
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
