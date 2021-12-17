package com.skilldistillery.audiophile.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.skilldistillery.audiophile.data.AlbumDAO;
import com.skilldistillery.audiophile.data.ArtistDAO;
import com.skilldistillery.audiophile.entities.Album;
import com.skilldistillery.audiophile.entities.Artist;

@Controller
public class ArtistController {

	@Autowired
	private ArtistDAO artistDAO;

	@Autowired
	private AlbumDAO albumDAO;

	@GetMapping(path = "artistProfile")
	public String getAccountPage(Model model, @RequestParam("id") int id, RedirectAttributes redir) {
		try {
			Artist artist = artistDAO.findById(id);
			if(artist != null) {
			model.addAttribute("artist", artist);
			List<Album> artistsHighestRatedAlbums = albumDAO.findAlbumsByArtistSortByRating(false, artist.getName());
			if (artistsHighestRatedAlbums.size() > 3) {
				model.addAttribute("artistsHighestRatedAlbums", artistsHighestRatedAlbums.subList(0, 3));
			} else {
				model.addAttribute("artistsHighestRatedAlbums", artistsHighestRatedAlbums);
			}
			return "artist";
			}else {
				throw new Exception("No artist found with id: " + id);
			}
		} catch (Exception e) {
			redir.addFlashAttribute("error", e.getMessage());
			e.printStackTrace();
		}
		return "redirect:home";
	}
}
