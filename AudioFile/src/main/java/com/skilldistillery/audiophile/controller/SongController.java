package com.skilldistillery.audiophile.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.skilldistillery.audiophile.data.AlbumDAOImpl;
import com.skilldistillery.audiophile.data.ArtistDAOImpl;
import com.skilldistillery.audiophile.data.SongDAOImpl;
import com.skilldistillery.audiophile.data.SongRatingDAOImpl;
import com.skilldistillery.audiophile.data.UserDAOImpl;
import com.skilldistillery.audiophile.entities.Song;
import com.skilldistillery.audiophile.entities.SongRating;
import com.skilldistillery.audiophile.entities.User;

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

	/*
	 * --------------------------- 
	 * get song detail page
	 * ---------------------------
	 */

	@GetMapping(path = "getSongId.do")
	public String getBySongName(@RequestParam("songId") int songId, Model model) {
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
					SongRating usersRating = songRatingDAO.findSongRatingByUserIdSongId(songId, user.getId());
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
		return "song/songDetails";
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

				User user = getSessionUser(session);
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

	private User getSessionUser(HttpSession session) {
		session.getAttribute("user");
		User user;
		try {
			user = (User) session.getAttribute("user");
		} catch (Exception e) {
			System.out.println("Did not find session user");
			user = null;
		}

		return user;
	}

	private boolean isSessionUser(HttpSession session, User user) {
		User sessionUser = getSessionUser(session);
		return (user != null && sessionUser != null && sessionUser.equals(user));
	}
}
