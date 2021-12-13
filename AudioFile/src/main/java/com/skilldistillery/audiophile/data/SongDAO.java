package com.skilldistillery.audiophile.data;

import java.time.LocalDateTime;
import java.util.List;

import com.skilldistillery.audiophile.entities.Song;

public interface SongDAO {

	Song findBySongId(int id);
	List<Song> findBySongName (String name);
	List<Song> sortByCreatDate(LocalDateTime songDate);
	List<Song> findByArtistName(String artistName);
}
