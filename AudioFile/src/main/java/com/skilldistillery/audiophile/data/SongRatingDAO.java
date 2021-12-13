package com.skilldistillery.audiophile.data;

import java.util.List;

import com.skilldistillery.audiophile.entities.SongRating;

public interface SongRatingDAO {

	List<SongRating> findByUsername (String username);
	public SongRating findSongRatingById(int id);
	public SongRating findSongRatingByUserId(int id);
	public List<SongRating> sortByRating(int songRating, boolean ascendingOrder);
	public List<SongRating> sortSongRatingsByRatingDate (boolean ascendingOrder, int numberOfRatingsToShow);
	public List<SongRating> sortSongRatingsByRatingDate (boolean ascendingOrder);
}
