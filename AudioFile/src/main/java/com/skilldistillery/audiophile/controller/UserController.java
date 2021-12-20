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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.skilldistillery.audiophile.data.AlbumDAO;
import com.skilldistillery.audiophile.data.ArtistDAO;
import com.skilldistillery.audiophile.data.SongDAO;
import com.skilldistillery.audiophile.data.UserDAO;
import com.skilldistillery.audiophile.entities.Album;
import com.skilldistillery.audiophile.entities.Artist;
import com.skilldistillery.audiophile.entities.Song;
import com.skilldistillery.audiophile.entities.User;

@Controller
public class UserController {

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private AlbumDAO albumDAO;

	@Autowired
	private SongDAO songDAO;

	@Autowired
	private ArtistDAO artistDAO;

	@GetMapping(path = {"login", "/profile"})
	public String getLogin(HttpSession session) {
		return "profile";
	}

	@PostMapping(path = "login")
	public String login(HttpSession session, @RequestParam("username") String username,
			@RequestParam("password") String password, RedirectAttributes redir) {
		try {
			User user = userDAO.login(username, password);
			if (user != null) {
				session.setAttribute("user", user);
				redir.addFlashAttribute("success", "Successfully logged in! Redirecting you to home page");
				return "redirect:home";
			} else {
				throw new Exception("Incorrect username or password");
			}
		} catch (Exception e) {
			redir.addFlashAttribute("error", e.getMessage());
			e.printStackTrace();
		}
		return "redirect:profile";
	}

	@GetMapping(path = "logout")
	public String logoutUser(HttpSession session, RedirectAttributes redir) {
		session.removeAttribute("user");
		session.removeAttribute("albumsCreated");
		redir.addFlashAttribute("warning", "Logged out");
		return "redirect:/";

	}

	@GetMapping(path = "myProfile")
	public String getAccountPage(HttpSession session, Album album) {

		User user = null;
		user = (User) session.getAttribute("user");
		if (user != null) {
			if (session.getAttribute("update") != null) {
				session.removeAttribute("update");
			}
			session.setAttribute("albumsCreated", albumDAO.findAlbumsByCreatedUsername(user.getUsername()));
			return "profile";
		}
		return "profile";
	}

	@GetMapping(path = "createAccount")
	public String getCreateAccountPage(HttpSession session) {
		return "createAccount";
	}

	@PostMapping(path = "createAccount")
	public String createAccount(User user, RedirectAttributes redir) {
		try {
			User createdUser = userDAO.createUser(user);
			if (createdUser != null) {
				redir.addFlashAttribute("success", "Account successfully created!");
				return "redirect:profile";
			} else {
				throw new Exception("Failed to create account");
			}
		} catch (Exception e) {
			redir.addFlashAttribute("error", e.getMessage() + ": " + user.toString());
			e.printStackTrace();
		}
		return "redirect:profile";
	}

	@PostMapping(path = "deleteAccount")
	public String deleteAccount(RedirectAttributes redir, HttpSession session) {
		try {
			User userToDelete = (User) session.getAttribute("user");
			if (userDAO.deleteUser(userToDelete.getId())) {
				session.removeAttribute("user");
				redir.addFlashAttribute("success", "Account deleted");
				return "redirect:/";
			} else {
				throw new Exception("cannot delete film.");
			}
		} catch (Exception e) {
			redir.addFlashAttribute("error", e.getMessage());
			e.printStackTrace();
		}
		return "redirect:/";
	}

	@GetMapping(path = "updateAccount")
	public String getUpdateAccount(HttpSession session) {
		try {
			User userToUpdate = (User) session.getAttribute("user");
			session.setAttribute("update", userToUpdate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "profile";
	}

	@PostMapping(path = "updateAccount")
	public String postUpdateAccount(User user, HttpSession session, RedirectAttributes redir) {
		try {
			User userToUpdate = (User) session.getAttribute("user");
			if (userDAO.updateUser(userToUpdate.getId(), user)) {
				session.setAttribute("user", userDAO.findUserById(userToUpdate.getId()));
				session.removeAttribute("update");
				redir.addFlashAttribute("success", "Account updated");
				return "redirect:profile";
			} else {
				throw new Exception("cannot delete film.");
			}

		} catch (Exception e) {
			redir.addFlashAttribute("error", e.getMessage());
			e.printStackTrace();
		}
		return "redirect:profile";
	}

	@GetMapping(path = "friendList")
	public String getFriendPage(Model model, Album album) {
		User user = userDAO.findUserById(2);
		model.addAttribute("user1", user);

		model.addAttribute("albumsCreated", albumDAO.findAlbumsByCreatedUsername(user.getUsername()));
		return "friendPage";
	}

	/*
	 * --------------------------
	 * Create Album&Artist&Song
	 * --------------------------
	 */
	@GetMapping(path = "addAlbum")
	public String getAddAblumpage(HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (user == (null)) {

			return "profile";
		}
		return "addAlbum";
	}

	@PostMapping(path = "addAlbum")
	public String createAlbum(String title, String description, String releaseDate, String imageURL, String names,
			RedirectAttributes redir, HttpSession session) {
		Album album = new Album();
		User user = (User) session.getAttribute("user");
		try {
			album.setTitle(title);
			album.setDescription(description);
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate ld = LocalDate.parse(releaseDate, formatter);
			LocalDateTime ldt = LocalDateTime.of(ld, LocalDateTime.now().toLocalTime());
			album.setReleaseDate(ldt);
			album.setImageURL(imageURL);
			List<Artist> artist = artistDAO.findByArtistsName(names);
			for (Artist artist2 : artist) {
			
				album.setArtist(artist2);
			}
			album.setUser(user);
			albumDAO.addAlbum(album);
			if (albumDAO.addAlbum(album) != null) {
				redir.addFlashAttribute("success", "Album successfully created!");
				return "redirect:album.do?albumId="+album.getId();
			} else {
				throw new Exception("Failed to create album");
			}
		} catch (Exception e) {
			redir.addFlashAttribute("error", e.getMessage() + ": " + album.toString());
			e.printStackTrace();
		}
		return "redirect:/";

	}

	@GetMapping(path = "addSong")
	public String getAddSongpage(HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (user == (null)) {

			return "profile";
		}
		return "addSong";
	}

	@PostMapping(path = "addSong")
	public String createSong(String name, String lyrics, int durationInSeconds, RedirectAttributes redir,
			HttpSession session) {
		Song song = new Song();
		User user = (User) session.getAttribute("user");
		try {
			song.setName(name);
			song.setLyrics(lyrics);
			song.setDurationInSeconds(durationInSeconds);
			song.setUser(user);
			songDAO.addNewSong(song);

			if (songDAO.addNewSong(song) != null) {
				redir.addFlashAttribute("success", "Song successfully created!");
				return "redirect:searchBySongName.do?songName="+song.getName();
			} else {
				throw new Exception("Failed to create song");
			}
		} catch (Exception e) {
			redir.addFlashAttribute("error", e.getMessage() + ": " + song.toString());
			e.printStackTrace();
		}
		return "redirect:/";
	}
	@PostMapping(path = "updateSong")
	public String postUpdateSong(User user, HttpSession session, RedirectAttributes redir, Song song) {
		try {
			Song songToUpdate = (Song) session.getAttribute("song");
			if (songDAO.updateSong(songToUpdate.getId(), song)) {
				session.setAttribute("song", songDAO.findById(songToUpdate.getId()));
				session.removeAttribute("update");
				redir.addFlashAttribute("success", "Account updated");
				return "redirect:profile";
			} else {
				throw new Exception("cannot delete film.");
			}

		} catch (Exception e) {
			redir.addFlashAttribute("error", e.getMessage());
			e.printStackTrace();
		}
		return "redirect:profile";
	}
	

	@GetMapping(path = "addArtist")
	public String getAddArtistpage(HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (user == (null)) {

			return "profile";
		}
		return "addArtist";
	}

	@PostMapping(path = "addArtist")
	public String createArtist(Artist artist, RedirectAttributes redir,HttpSession session) {
		User user = (User) session.getAttribute("user");
		try {
			if (artistDAO.addNewArtist(artist) != null) {
				redir.addFlashAttribute("success", "Account successfully created!");
				return "redirect:artistProfile?id="+artist.getId();
			} else {
				throw new Exception("Failed to create account");
			}
		} catch (Exception e) {
			redir.addFlashAttribute("error", e.getMessage() + ": " + artist.toString());
			e.printStackTrace();
		}
		return "redirect:/";
	}
	@GetMapping(path = "updateArtist")
	public String getUpdateArtist(HttpSession session) {
		try {
			Artist artistToUpdate = (Artist) session.getAttribute("artist");
			session.setAttribute("update", artistToUpdate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "addArtist";
	}
	
	@PostMapping(path = "updateArtist")
	public String postUpdateArtist(HttpSession session, RedirectAttributes redir, Artist artist) {
		try {
			Artist artistToUpdate = (Artist) session.getAttribute("artist");
			if (artistDAO.updateArtist(artistToUpdate.getId(), artist)) {
				session.setAttribute("artist", artistDAO.findById(artistToUpdate.getId()));
//				session.removeAttribute("update");
				redir.addFlashAttribute("success", "Artist updated");
				return "redirect:profile";
			} else {
				throw new Exception("cannot update Artist.");
			}

		} catch (Exception e) {
			redir.addFlashAttribute("error", e.getMessage());
			e.printStackTrace();
		}
		return "redirect:trending";
	}
	@PostMapping(path = "deleteArtist")
	public String deleteArtist(RedirectAttributes redir, HttpSession session,Artist artist) {
		try {
			Artist artistTodelete = (Artist) session.getAttribute("artist");
			if (artistDAO.deleteArtist(artistTodelete.getId())) {
				session.removeAttribute("artist");
				redir.addFlashAttribute("success", "Artist deleted");
				return "redirect:/";
			} else {
				throw new Exception("cannot delete film.");
			}
		} catch (Exception e) {
			redir.addFlashAttribute("error", e.getMessage());
			e.printStackTrace();
		}
		return "redirect:/";
	}
	
	@GetMapping(path = "otherUsersProfile")
	public String getOtherUsersPage(@RequestParam("id") int id, Model model, Album album) {
		User user = userDAO.findUserById(id);
		model.addAttribute("otherUsersProfile", user);
		model.addAttribute("albumsCreatedByOtherUser", albumDAO.findAlbumsByCreatedUsername(user.getUsername()));
		return "profile";
	}

}
	
