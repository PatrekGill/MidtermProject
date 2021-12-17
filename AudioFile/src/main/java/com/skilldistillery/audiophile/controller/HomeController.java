package com.skilldistillery.audiophile.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.skilldistillery.audiophile.data.AlbumDAO;
import com.skilldistillery.audiophile.data.ArtistDAO;
import com.skilldistillery.audiophile.data.SongDAO;
import com.skilldistillery.audiophile.data.UserDAO;
import com.skilldistillery.audiophile.entities.Album;
import com.skilldistillery.audiophile.entities.Artist;
import com.skilldistillery.audiophile.entities.Song;

@Controller
public class HomeController {

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private AlbumDAO albumDAO;

	@Autowired
	private ArtistDAO artistDAO;

	@Autowired
	private SongDAO songDAO;

	@RequestMapping(path = { "/", "home" })
	public String home(HttpSession session) {
		List<Album> topAlbums = albumDAO.sortAlbumsByRating(false);
		if (topAlbums.size() > 3) {
			session.setAttribute("topAlbumsSideBar", topAlbums.subList(0, 3));
		} else {
			session.setAttribute("topAlbumsSideBar", topAlbums);
		}
		List<Artist> topArtists = artistDAO.getTopThreeArtist(false);
		if (topArtists.size() > 3) {
			session.setAttribute("topArtistsSideBar", topArtists.subList(0, 3));
		} else {
			session.setAttribute("topArtistsSideBar", topArtists);
		}
		List<Song> topSongs = songDAO.sortBySongRating(false, 3);
		session.setAttribute("topSongsSideBar", topSongs);
		return "home";
	}

}
