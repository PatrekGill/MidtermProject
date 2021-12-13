package com.skilldistillery.audiophile.data;

import java.time.LocalDateTime;
import java.util.List;

import com.skilldistillery.audiophile.entities.Song;

public interface SongDAO {

	public Song findBySongId(int id);
	public List<Song> findSongByRating(int rating);
	public List<Song> findBySongName (String name);
	public List<Song> findByArtistName(String artistName);
	public List<Song> findByAlbumName(String albumName);
	public List<Song> findByLyricsKeyword(String keyword);
	public List<Song> sortByCreatDate();
	public List<Song> sortBySongRating();
	public Song addNewSongs(Song song);
	public boolean deleteNewAddedSong(int id);
}
