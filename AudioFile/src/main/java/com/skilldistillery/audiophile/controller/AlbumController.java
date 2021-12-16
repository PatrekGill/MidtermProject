package com.skilldistillery.audiophile.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.skilldistillery.audiophile.data.AlbumDAOImpl;
import com.skilldistillery.audiophile.data.AlbumRatingDAOImpl;
import com.skilldistillery.audiophile.data.UserDAOImpl;
import com.skilldistillery.audiophile.entities.Album;
import com.skilldistillery.audiophile.entities.AlbumComment;

@Controller
public class AlbumController {
	@Autowired
	private UserDAOImpl userDAO;
	@Autowired
	private AlbumDAOImpl albumDAO;
	@Autowired
	private AlbumRatingDAOImpl albumRatingDAO;
	
	@GetMapping(path="album.do")
	public String showAlbumPage(Integer albumId, HttpSession session, Model model) {
		if (albumId != null) {
			Album album = albumDAO.findAlbumById(albumId);
			if (album != null) {
				
				model.addAttribute("averageRating",albumRatingDAO.getAverageAlbumRating(albumId));
				model.addAttribute("album", album);
				
				List<AlbumComment> comments = getLatestAlbumComments(albumId);
				if (!comments.isEmpty()) {
					model.addAttribute("albumComments",comments);
				}
			}
			
		}
		
		return "album";
	}
	
	private List<AlbumComment> getLatestAlbumComments(Integer albumId) {
		
		List<AlbumComment> comments = new ArrayList<AlbumComment>();
		if (albumId != null) {
			
			Album album = albumDAO.findAlbumById(albumId);
			if (album != null) {
				comments = new ArrayList<>(album.getAlbumComments());
				
				if (!comments.isEmpty()) {
					comments.sort(
						(comment1, comment2) -> {
							return comment1.getCommentDate().compareTo(comment2.getCommentDate());
						}
					);
					
				}
			}
			
		}
		
		return comments;
	}
	
	@GetMapping(path="albumComments.do")
	public String showAlbumComments(Integer albumId, HttpSession session, Model model) {
		
		if (albumId != null) {
			
			Album album = albumDAO.findAlbumById(albumId);
			if (album != null) {
				List<AlbumComment> comments = getLatestAlbumComments(albumId);
				model.addAttribute("album", album);
				model.addAttribute("albumComments",comments);
			}
			
		}
		
		return "albumComments";
	}
	
}
