package com.skilldistillery.audiophile.controller;

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
import com.skilldistillery.audiophile.data.UserDAOImpl;
import com.skilldistillery.audiophile.entities.Album;
import com.skilldistillery.audiophile.entities.AlbumComment;
import com.skilldistillery.audiophile.entities.AlbumRating;
import com.skilldistillery.audiophile.entities.User;
import com.skilldistillery.audiophile.misc.SessionUserChecker;

@Controller
public class AlbumController {
	@Autowired
	private UserDAOImpl userDAO;
	@Autowired
	private AlbumDAOImpl albumDAO;
	@Autowired
	private AlbumRatingDAOImpl albumRatingDAO;
	@Autowired
	private AlbumCommentDAOImpl albumCommentDAO;
	
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
	

	
}
