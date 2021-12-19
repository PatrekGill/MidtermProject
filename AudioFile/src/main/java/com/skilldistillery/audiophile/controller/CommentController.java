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
import com.skilldistillery.audiophile.entities.Album;
import com.skilldistillery.audiophile.entities.AlbumComment;
import com.skilldistillery.audiophile.entities.User;
import com.skilldistillery.audiophile.misc.SessionUserChecker;

@Controller
public class CommentController {
	@Autowired
	private AlbumDAOImpl albumDAO;
	@Autowired
	private AlbumRatingDAOImpl albumRatingDAO;
	@Autowired
	private AlbumCommentDAOImpl albumCommentDAO;
	private SessionUserChecker checker;
	
	/* ----------------------------------------------------------------------------
		albumComments.do (GET)
	---------------------------------------------------------------------------- */
	@GetMapping(path="albumComments.do")
	public String showAlbumComments(
			Integer albumId, 
			HttpSession session, 
			Model model, 
			RedirectAttributes redir
		) {
		
		if (albumId != null) {
			
			Album album = albumDAO.findAlbumById(albumId);
			if (album != null) {
				model.addAttribute("album", album);
				
				List<AlbumComment> comments = albumCommentDAO.sortAlbumCommentsByCommentDate(albumId, false);
				model.addAttribute("albumComments",comments);
				model.addAttribute("averageRating",albumRatingDAO.getAverageAlbumRating(albumId));
				
				return "album/pages/albumComments";
			}
			
		}
		
		redir.addFlashAttribute("error","This album does not seem to exist");
		return "redirect:/";
		
	}
	/* ----------------------------------------------------------------------------
		albumComments.do (POST)
	---------------------------------------------------------------------------- */
	@PostMapping(path="albumComments.do")
	public String postComment(
			Integer albumId, 
			String commentText, 
			HttpSession session, 
			Model model,
			RedirectAttributes redir
		) {
		
		if (albumId != null) {
			
			User user = checker.getSessionUser(session);
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
				
				redir.addFlashAttribute("success","Posted Your Comment");
				redir.addAttribute("albumId", albumId);
				return "redirect:albumComments.do";
			}
			
		}
		
		redir.addFlashAttribute("error","Could not post your comment");
		return "redirect:/";
	}
	
	
	/* ----------------------------------------------------------------------------
		albumComments.do (POST Editing Comment)
	---------------------------------------------------------------------------- */	
	@PostMapping(
		path="albumComments.do",
		params = {"editCommentId","commentText"}
	)
	public String editComment(
			Integer editCommentId,
			String commentText,
			HttpSession session,
			Model model,
			RedirectAttributes redir
		) {
		
		User user = checker.getSessionUser(session);
		
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
				
				redir.addFlashAttribute("success","Updated Your Comment");
				redir.addAttribute("commentId", editCommentId);
				return "redirect:commentThread.do";
			}
	
		}
		
		redir.addFlashAttribute("error","Could not update that comment");
		return "redirect:/";
	}
	
	
	

	/* ----------------------------------------------------------------------------
		commentThread.do (GET)
	---------------------------------------------------------------------------- */
	@GetMapping(path="commentThread.do")
	public String showCommentThread(
			Integer commentId,
			HttpSession session,
			Model model,
			RedirectAttributes redir
		) {
		
		if (commentId != null) {

			AlbumComment comment = albumCommentDAO.findAlbumCommentById(commentId);
			if (comment != null) {
				model.addAttribute("originalComment",comment);
				model.addAttribute("album", comment.getAlbum());
				model.addAttribute("replyingComments",comment.getReplies());
				model.addAttribute("averageRating",albumRatingDAO.getAverageAlbumRating(comment.getAlbum().getId()));
				model.addAttribute("userOwnsComment",checker.isSessionUser(session,comment.getUser()));
				
				return "album/pages/albumComments";
			}
			
		}
		
		redir.addFlashAttribute("error","Could not find that comment thread");
		return "redirect:/";
	}
	
	/* ----------------------------------------------------------------------------
		commentThread.do (POST)
	---------------------------------------------------------------------------- */
	@PostMapping(path="commentThread.do")
	public String postReply(
			Integer replyToId,
			String commentText,
			HttpSession session,
			Model model,
			RedirectAttributes redir
		) {
		
		if (replyToId != null) {
			
			AlbumComment originalComment = albumCommentDAO.findAlbumCommentById(replyToId);
			User user = checker.getSessionUser(session);
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
				model.addAttribute("userOwnsComment",checker.isSessionUser(session,originalComment.getUser()));
				model.addAttribute("album", album);		
				model.addAttribute("averageRating",albumRatingDAO.getAverageAlbumRating(album.getId()));
				
				redir.addAttribute("commentId", replyToId);
				redir.addFlashAttribute("success","Reply Posted!");
				return "redirect:commentThread.do";
			}
		}
		redir.addFlashAttribute("error","Could not post reply");
		return "redirect:/";
		
	}
}
