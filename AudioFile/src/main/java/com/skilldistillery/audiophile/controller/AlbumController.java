package com.skilldistillery.audiophile.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.skilldistillery.audiophile.data.AlbumCommentDAOImpl;
import com.skilldistillery.audiophile.data.AlbumDAOImpl;
import com.skilldistillery.audiophile.data.AlbumRatingDAOImpl;
import com.skilldistillery.audiophile.data.UserDAOImpl;
import com.skilldistillery.audiophile.entities.Album;
import com.skilldistillery.audiophile.entities.AlbumComment;
import com.skilldistillery.audiophile.entities.AlbumRating;
import com.skilldistillery.audiophile.entities.User;

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
	
	@GetMapping(path="album.do")
	public String showAlbumPage(Integer albumId, HttpSession session, Model model) {
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
			}
			
		}
		
		return "album";
	}
	
	@GetMapping(path="albumComments.do")
	public String showAlbumComments(Integer albumId, HttpSession session, Model model) {
		
		if (albumId != null) {
			
			Album album = albumDAO.findAlbumById(albumId);
			if (album != null) {
				model.addAttribute("album", album);
				
				List<AlbumComment> comments = albumCommentDAO.sortAlbumCommentsByCommentDate(albumId, false);
				model.addAttribute("albumComments",comments);
				
				model.addAttribute("averageRating",albumRatingDAO.getAverageAlbumRating(albumId));
			}
			
		}
		
		return "albumComments";
	}
	
	@PostMapping(path="albumComments.do")
	public String postComment(Integer albumId, String commentText, HttpSession session, Model model) {
		int userId = 1;
		
		if (albumId != null) {
			
			User user = userDAO.findUserById(userId);
			Album album = albumDAO.findAlbumById(albumId);
			if (album != null && user != null) {
				AlbumComment comment = new AlbumComment();
				comment.setAlbum(album);
				comment.setComment(commentText);
				comment.setUser(user);
				
				albumCommentDAO.createAlbumComment(comment);
				model.addAttribute("album", album);
				
				List<AlbumComment> comments = albumCommentDAO.sortAlbumCommentsByCommentDate(albumId, false);
				model.addAttribute("albumComments",comments);
				
				model.addAttribute("averageRating",albumRatingDAO.getAverageAlbumRating(albumId));
			}
			
		}
		
		return "redirect:albumComments";
	}
	
	@GetMapping(path="albumRatings.do")
	public String showRatingsPage(Integer albumId, HttpSession session, Model model) {
		
		
		if (albumId != null) {
			
			Album album = albumDAO.findAlbumById(albumId);
			if (album != null) {
				model.addAttribute("album", album);
				
				int userId = 1;
				AlbumRating usersRating = albumRatingDAO.findByAlbumAndUserId(albumId, userId);
				boolean userHasRating = false;
				if (usersRating != null) {
					model.addAttribute("usersRating",usersRating);
					userHasRating = true;
				}
				model.addAttribute("userHasRating",userHasRating);
				
				List<AlbumRating> ratings = albumRatingDAO.sortedByCreatationDate(albumId, false);
				model.addAttribute("albumRatings",ratings);
				model.addAttribute("averageRating",albumRatingDAO.getAverageAlbumRating(albumId));
			}
		}
		
	
		return "albumRatings";
	}
	@PostMapping(path="albumRatings.do")
	public String deleteRating(Integer albumId, String ratingText, Integer ratingNumber, HttpSession session, Model model) {
		return "albumRatings";
	}

	
	@PostMapping(path="deleteRating.do")
	public String deleteRating(Integer albumId, HttpSession session, Model model) {
		return "albumRatings";
	}
}
