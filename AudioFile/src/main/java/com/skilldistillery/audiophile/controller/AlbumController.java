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
	

	/* ----------------------------------------------------------------------------
		album.do (GET)
	---------------------------------------------------------------------------- */
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
		
		return "album/pages/album";
	}
	
	
	/* ----------------------------------------------------------------------------
		albumComments.do (GET)
	---------------------------------------------------------------------------- */
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
		
		return "album/pages/albumComments";
	}
	/* ----------------------------------------------------------------------------
		albumComments.do (POST)
	---------------------------------------------------------------------------- */
	@PostMapping(path="albumComments.do")
	public String postComment(Integer albumId, String commentText, HttpSession session, Model model) {
		
		if (albumId != null) {
			
			User user = getSessionUser(session);
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
		
		return "album/pages/albumComments";
	}
	
	@PostMapping(
		path="albumComments.do",
		params = {"editCommentId","commentText"}
	)
	public String editComment(
			Integer editCommentId,
			String commentText,
			HttpSession session,
			Model model
		) {
		User user = getSessionUser(session);
		
		if (editCommentId != null) {
			AlbumComment comment = albumCommentDAO.findAlbumCommentById(editCommentId);
			if (comment != null && comment.getUser().equals(user)) {
				Album album = comment.getAlbum();
				comment.setComment(commentText);
				
				model.addAttribute("originalComment",comment);
				model.addAttribute("album", album);
				model.addAttribute("replyingComments",albumCommentDAO.findCommentReplys(editCommentId));
				model.addAttribute("userOwnsComment",comment.getUser().equals(user));
				model.addAttribute("album", album);		
				model.addAttribute("averageRating",albumRatingDAO.getAverageAlbumRating(album.getId()));
			}

		}
		
		return "album/pages/commentThread";
	}
	
	
	/* ----------------------------------------------------------------------------
		albumRatings.do (GET)
	---------------------------------------------------------------------------- */
	@GetMapping(path="albumRatings.do")
	public String showRatingsPage(Integer albumId, HttpSession session, Model model) {
		if (albumId != null) {
			
			Album album = albumDAO.findAlbumById(albumId);
			if (album != null) {
				model.addAttribute("album", album);
				
				User user = getSessionUser(session);
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
			}
		}
		
	
		return "album/pages/albumRatings";
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
			Model model
		) {
		
		
		if (albumId != null && ratingNumber != null) {
			
			Album album = albumDAO.findAlbumById(albumId);
			if (album != null) {
				model.addAttribute("album", album);
				 
				User user = getSessionUser(session);
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
			}
		}
		
		return "album/pages/albumRatings";
	}
	
	
	/* ----------------------------------------------------------------------------
		deleteRating.do (POST)
	---------------------------------------------------------------------------- */
	@PostMapping(path="deleteRating.do")
	public String deleteRating(Integer albumId, HttpSession session, Model model) {
		if (albumId != null) {
			
			Album album = albumDAO.findAlbumById(albumId);
			if (album != null) {
				model.addAttribute("album", album);
				 
				User user = getSessionUser(session);
				if (user != null) {
					AlbumRating usersRating = albumRatingDAO.findByAlbumAndUserId(albumId, user.getId());
					if (usersRating != null) {
						if (albumRatingDAO.deleteAlbumRating(usersRating.getId())) {
							model.addAttribute("userHasRating",false);
						}
					}
					
				}
				
				List<AlbumRating> ratings = albumRatingDAO.sortedByCreatationDate(albumId, false);
				model.addAttribute("albumRatings",ratings);
				model.addAttribute("averageRating",albumRatingDAO.getAverageAlbumRating(albumId));
			}
		}
		
		return "album/pages/albumRatings";
	}
	

	/* ----------------------------------------------------------------------------
		commentThread.do (GET)
	---------------------------------------------------------------------------- */
	@GetMapping(path="commentThread.do")
	public String showCommentThread(Integer commentId, HttpSession session, Model model) {
		
		if (commentId != null) {

			AlbumComment comment = albumCommentDAO.findAlbumCommentById(commentId);
			if (comment != null) {
				model.addAttribute("originalComment",comment);
				model.addAttribute("album", comment.getAlbum());
				model.addAttribute("replyingComments",comment.getReplies());

				model.addAttribute("userOwnsComment",isSessionUser(session,comment.getUser()));
			}
			
		}
		
		return "album/pages/commentThread";
	}
	
	@PostMapping(path="commentThread.do")
	public String postReply(
			Integer replyToId,
			String commentText,
			HttpSession session,
			Model model
		) {
		
		if (replyToId != null) {
			
			AlbumComment originalComment = albumCommentDAO.findAlbumCommentById(replyToId);
			User user = getSessionUser(session);
			if (originalComment != null && user != null) {
				Album album = originalComment.getAlbum();
				
				if (commentText != null & !commentText.equals("")) {
					AlbumComment comment = new AlbumComment();
					comment.setAlbum(album);
					comment.setComment(commentText);
					comment.setUser(user);
					comment.setInReplyTo(replyToId);
					albumCommentDAO.createAlbumComment(comment);
				}

				model.addAttribute("originalComment",originalComment);
				model.addAttribute("album", originalComment.getAlbum());
				model.addAttribute("replyingComments",albumCommentDAO.findCommentReplys(replyToId));
				model.addAttribute("userOwnsComment",isSessionUser(session,originalComment.getUser()));
				model.addAttribute("album", album);		
				model.addAttribute("averageRating",albumRatingDAO.getAverageAlbumRating(album.getId()));
			}
		}
		
		return "album/pages/commentThread";
	}
	
	private User getSessionUser(HttpSession session) {
		session.getAttribute("user");
		User user;
		try {
			user = (User)session.getAttribute("user");
		} catch (Exception e) {
			System.out.println("Did not find session user");
			user = null;
		}
		
		return user;
	}
	
	private boolean isSessionUser(HttpSession session, User user) {
		User sessionUser = getSessionUser(session);
		return (user != null && sessionUser.equals(user));
	}
}
