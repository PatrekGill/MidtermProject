package com.skilldistillery.audiophile.data;

import java.util.List;

import com.skilldistillery.audiophile.entities.AlbumRating;
import com.skilldistillery.audiophile.entities.SongRating;

public interface SongRatingDAO {

	List<SongRating> findByUsername (String username);
	public SongRating findSongRatingById(int id);
	public List<SongRating> findSongRatingsByUserId(int id);
	public SongRating findSongRatingByUserIdSongId(int userid ,int songid);
	
	List<SongRating> sortedByRating(int songId, boolean ascendingOrder);
	List<SongRating> sortedByRating(int songId, boolean ascendingOrder, int numberOfEntriesToShow);

	public List<SongRating> sortSongRatingsByRatingDate (boolean ascendingOrder, int numberOfRatingsToShow);
	public List<SongRating> sortSongRatingsByRatingDate (boolean ascendingOrder);
}
