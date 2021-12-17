package com.skilldistillery.audiophile.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.skilldistillery.audiophile.data.AlbumDAO;
import com.skilldistillery.audiophile.data.ArtistDAO;
import com.skilldistillery.audiophile.data.SongDAO;
import com.skilldistillery.audiophile.data.SongRatingDAO;
import com.skilldistillery.audiophile.entities.Album;
import com.skilldistillery.audiophile.entities.Artist;
import com.skilldistillery.audiophile.entities.Song;
import com.skilldistillery.audiophile.entities.SongRating;

@Controller
public class SearchController {

	@Autowired
	private SongDAO songDAO;
	@Autowired
	private AlbumDAO albumDAO;
	@Autowired
	private ArtistDAO artistDAO;
	@Autowired
	private SongRatingDAO songRatingDAO;

	/*
	 * --------------------------- Search for Songs ---------------------------
	 */

	@GetMapping(path = "search")

	public String searchAll(@RequestParam("keyword") String keyword, Model model,
			@RequestParam("searchAll") String searchAll, RedirectAttributes redir) {
		if (keyword.trim().length() > 0) {
			boolean notPopulateWith = false;
			if (searchAll.equals("All") || (searchAll.equals("Genre") && keyword == "")) {
				List<Song> songs = songDAO.findBySongName(keyword);
				List<Album> albums = albumDAO.findAlbumsByTitle(keyword);
				List<Artist> artists = artistDAO.findByArtistsName(keyword);
				if (songs.isEmpty() && albums.isEmpty() && artists.isEmpty()) {
					notPopulateWith = true;
				}
				model.addAttribute("Songs", songs);
				model.addAttribute("Albums", albums);
				model.addAttribute("Artists", artists);
			} else if (searchAll.equals("Album")) {
				List<Album> albums = albumDAO.findAlbumsByTitle(keyword);
				model.addAttribute("Albums", albums);
				if (albums.isEmpty()) {
					notPopulateWith = true;
				}
			} else if (searchAll.equals("Artist")) {
				List<Artist> artists = artistDAO.findByArtistsName(keyword);
				model.addAttribute("Artists", artists);
				if (artists.isEmpty()) {
					notPopulateWith = true;
				}

			} else if (searchAll.equals("Song")) {
				List<Song> songs = songDAO.findBySongName(keyword);
				model.addAttribute("Songs", songs);
				if (songs.isEmpty()) {
					notPopulateWith = true;
				}
			} else if (searchAll.equals("Genre") && keyword != "") {
				List<Album> albums = albumDAO.findAlbumsByGenreName(keyword);
				model.addAttribute("Albums", albums);
				if (albums.isEmpty()) {
					notPopulateWith = true;
				}
			}
			model.addAttribute("NotPopulated", notPopulateWith);
			return "redirect:result";
		}else {
			redir.addFlashAttribute("erroe", "Please input something to search by.");
			return "redirect:home";
		}
	}
	/*
	 * --------------------------- Song details page result
	 * ---------------------------
	 */

	@RequestMapping(path = "searchBySongName.do", params = "songName", method = RequestMethod.GET)
	public ModelAndView getBySongName(@RequestParam("songName") String songName) {
		ModelAndView mv = new ModelAndView();
		List<Song> songs = songDAO.findBySongName(songName);
//		Artist artist = artistDAO.findPrimaryArtistByAlbumName(album.getTitle());
		Song song = songs.get(0);
		int songId = song.getId();
		String userName = song.getUser().getUsername();
		int userId = song.getUser().getId();
		LocalDateTime createDate = song.getCreateDate();
		LocalDateTime ratingDate;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String newCreateDate = createDate.format(formatter);
		int durationSeconds = song.getDurationInSeconds();
		String newDurationSeconds;
		long MM = durationSeconds / 60;
		long SS = durationSeconds % 60;
		newDurationSeconds = String.format("%02d:%02d", MM, SS);
		if (song.getSongRatings().size() > 0) {
			for (SongRating songRating : song.getSongRatings()) {
				ratingDate = songRating.getRatingDate();
				String newRatingDate = ratingDate.format(formatter);
				String description = songRating.getDescription();
				int rating = songRating.getRating();
				mv.addObject("RateDate", newRatingDate);
				mv.addObject("Comments", description);
				mv.addObject("Rating", rating);
			}
		} else {
			String noComment = "";
			mv.addObject("RateDate", noComment);
		}
		mv.addObject("Song", song);
		mv.addObject("User", userName);
		mv.addObject("CreateDate", newCreateDate);
		mv.addObject("DurationSeconds", newDurationSeconds);
		mv.setViewName("SongDetails");
		return mv;
	}
	/*
	 * ------------------------------------------------------------ Below not used ,
	 * just in case ------------------------------------------------------------
	 */

	@RequestMapping(path = "getSong.do", params = "songId", method = RequestMethod.GET)
	public ModelAndView getBySongId(@RequestParam("songId") int songId) {
		ModelAndView mv = new ModelAndView();
		Song song = songDAO.findById(songId);
		mv.addObject("Song", song);
		mv.setViewName("result");
		return mv;
	}

	@RequestMapping(path = "searchByAlbumName.do", params = "songAlbumName", method = RequestMethod.GET)
	public ModelAndView getByAlbumName(@RequestParam("songAlbumName") String songAlbumName) {
		ModelAndView mv = new ModelAndView();
		List<Song> songs = songDAO.findByAlbumName(songAlbumName);
		mv.addObject("Songs", songs);
		mv.setViewName("result");
		return mv;
	}

	@RequestMapping(path = "searchByLyricsKeywords.do", params = "LyricsKey", method = RequestMethod.GET)
	public ModelAndView getByLyricsKeyword(@RequestParam("LyricsKey") String LyricsKey) {
		ModelAndView mv = new ModelAndView();
		List<Song> songs = songDAO.findByLyricsKeyword(LyricsKey);
		mv.addObject("Songs", songs);
		mv.setViewName("result");
		return mv;
	}

	@RequestMapping(path = "searchByArtistName.do", params = "SongArtistName", method = RequestMethod.GET)
	public ModelAndView getByArtistName(@RequestParam("SongArtistName") String SongArtistName) {
		ModelAndView mv = new ModelAndView();
		List<Song> songs = songDAO.findByArtistName(SongArtistName);
		mv.addObject("Songs", songs);
		mv.setViewName("result");
		return mv;
	}

	@RequestMapping(path = "searchBySongRating.do", params = "SongRating", method = RequestMethod.GET)
	public ModelAndView getBySongRating(@RequestParam("SongRating") int SongRating) {
		ModelAndView mv = new ModelAndView();
		List<Song> songs = songDAO.findSongsByRating(SongRating);
		mv.addObject("Songs", songs);
		mv.setViewName("result");
		return mv;
	}

	@RequestMapping(path = "sortBySongRating.do", method = RequestMethod.GET)
	public ModelAndView sortBySongRating() {
		ModelAndView mv = new ModelAndView();
		List<Song> songs = songDAO.sortBySongRating(false, 1);
		mv.addObject("Songs", songs);
		mv.setViewName("result");
		return mv;
	}

	@RequestMapping(path = "sortByCreateTime.do", method = RequestMethod.GET)
	public ModelAndView sortByCreateTime() {
		ModelAndView mv = new ModelAndView();
		List<Song> songs = songDAO.sortByCreatDate();
		mv.addObject("Songs", songs);
		mv.setViewName("result");
		return mv;
	}

	/*
	 * ------------------------- Search for Albums -------------------------
	 */
	@RequestMapping(path = "searchByAlbumId.do", params = "AlbumId", method = RequestMethod.GET)
	public ModelAndView getByAlbumId(@RequestParam("AlbumId") int AlbumId) {
		ModelAndView mv = new ModelAndView();
		Album album = albumDAO.findAlbumById(AlbumId);
		mv.addObject("Album", album);
		mv.setViewName("result");
		return mv;
	}

	@RequestMapping(path = "searchAlbumByAlbumName.do", params = "AlbumName", method = RequestMethod.GET)
	public ModelAndView getAlbumByAlbumName(@RequestParam("AlbumName") String AlbumName) {
		ModelAndView mv = new ModelAndView();
		Album album = albumDAO.findAlbumByTitle(AlbumName);
		mv.addObject("Album", album);
		mv.setViewName("result");
		return mv;
	}

	@RequestMapping(path = "searchAlbumBySongName.do", params = "AlbumSongName", method = RequestMethod.GET)
	public ModelAndView getAlbumBySongTitle(@RequestParam("AlbumSongName") String AlbumSongName) {
		ModelAndView mv = new ModelAndView();
//		Album album = albumDAO.findAlbumBySongTitle(AlbumSongName);
//		mv.addObject("Album", album);
		mv.setViewName("result");
		return mv;
	}

	@RequestMapping(path = "searchAlbumByArtistName.do", params = "AlbumArtistName", method = RequestMethod.GET)
	public ModelAndView getAlbumByArtistName(@RequestParam("AlbumArtistName") String AlbumArtistName) {
		ModelAndView mv = new ModelAndView();
		List<Album> albums = albumDAO.findAlbumsByArtistName(AlbumArtistName);
		mv.addObject("Albums", albums);
		mv.setViewName("result");
		return mv;
	}

	@RequestMapping(path = "searchAlbumByCreatedUsername.do", params = "AlbumUserName", method = RequestMethod.GET)
	public ModelAndView getAlbumByCreatedUsername(@RequestParam("AlbumUserName") String AlbumUserName) {
		ModelAndView mv = new ModelAndView();
		List<Album> albums = albumDAO.findAlbumsByCreatedUsername(AlbumUserName);
		mv.addObject("Albums", albums);
		mv.setViewName("result");
		return mv;
	}

	@RequestMapping(path = "searchAlbumByGenre.do", params = "AlbumGenre", method = RequestMethod.GET)
	public ModelAndView getAlbumByGenre(@RequestParam("AlbumGenre") String AlbumGenre) {
		ModelAndView mv = new ModelAndView();
		List<Album> albums = albumDAO.findAlbumsByGenreName(AlbumGenre);
		mv.addObject("Albums", albums);
		mv.setViewName("result");
		return mv;
	}

	@RequestMapping(path = "searchAlbumByCreateTime.do", params = "AlbumCreateTime", method = RequestMethod.GET)
	public ModelAndView getAlbumByCreateTime(@RequestParam("AlbumGenre") LocalDateTime AlbumCreateTime) {
		ModelAndView mv = new ModelAndView();
		List<Album> albums = albumDAO.findAlbumByCreationDate(AlbumCreateTime);
		mv.addObject("Albums", albums);
		mv.setViewName("result");
		return mv;
	}

	@RequestMapping(path = "sorthAlbumByRating.do", params = "ascendingOrder", method = RequestMethod.GET)
	public ModelAndView getAlbumByRating(@RequestParam("ascendingOrder") boolean ascendingOrder) {
		ModelAndView mv = new ModelAndView();
		List<Album> albums = albumDAO.sortAlbumsByRating(ascendingOrder);
		mv.addObject("Albums", albums);
		mv.setViewName("result");
		return mv;
	}

	/*
	 * ------------------------- Search for Artist -------------------------
	 */
	@RequestMapping(path = "searchArtistByArtistId.do", params = "ArtistId", method = RequestMethod.GET)
	public ModelAndView getArtistByArtistID(@RequestParam("ArtistId") int ArtistId) {
		ModelAndView mv = new ModelAndView();
		Artist artist = artistDAO.findById(ArtistId);
		mv.addObject("Artist", artist);
		mv.setViewName("result");
		return mv;
	}

	@RequestMapping(path = "searchArtistByArtistName.do", params = "ArtistName", method = RequestMethod.GET)
	public ModelAndView getArtistByArtistName(@RequestParam("ArtistName") String ArtistName) {
		ModelAndView mv = new ModelAndView();
		List<Artist> artists = artistDAO.findByArtistsName(ArtistName);
		mv.addObject("Artists", artists);
		mv.setViewName("result");
		return mv;
	}

	@RequestMapping(path = "searchArtistBySongName.do", params = "ArtistSongName", method = RequestMethod.GET)
	public ModelAndView getArtistBySongName(@RequestParam("ArtistSongName") String ArtistSongName) {
		ModelAndView mv = new ModelAndView();
		List<Artist> artists = artistDAO.findArtistsBySongName(ArtistSongName);
		mv.addObject("Artists", artists);
		mv.setViewName("result");
		return mv;
	}

	@RequestMapping(path = "searchArtistByAlbumName.do", params = "ArtistAlbumName", method = RequestMethod.GET)
	public ModelAndView getArtistByAlbumName(@RequestParam("ArtistAlbumName") String ArtistAlbumName) {
		ModelAndView mv = new ModelAndView();
		Artist artist = artistDAO.findPrimaryArtistByAlbumName(ArtistAlbumName);
		mv.addObject("Artist", artist);
		mv.setViewName("result");
		return mv;
	}

}
