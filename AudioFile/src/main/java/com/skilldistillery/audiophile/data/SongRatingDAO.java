package com.skilldistillery.audiophile.data;

import java.util.List;

import com.skilldistillery.audiophile.entities.AlbumRating;
import com.skilldistillery.audiophile.entities.SongRating;

public interface SongRatingDAO {

	List<SongRating> findByUsername (String username);
	public SongRating findSongRatingById(int id);
	public List<SongRating> findSongRatingsByUserId(int id);
	public SongRating findSongRatingByUserIdSongId(int userId ,int songId);
	
	boolean updateRating(int id, int newRating);
	boolean updateDescription(int id, String newRating);
	SongRating createSongRating(SongRating songRating);
	boolean deleteAlbumRating(int id);
	
	List<SongRating> sortedByRating(int songId, boolean ascendingOrder);
	List<SongRating> sortedByRating(int songId, boolean ascendingOrder, int numberOfEntriesToShow);

	public List<SongRating> sortSongRatingsByRatingDateAndLimitOutput (boolean ascendingOrder, int numberOfRatingsToShow);
	public List<SongRating> sortSongRatingsByRatingDate (boolean ascendingOrder);
	public List<SongRating> sortRatingByCreationDateFindBySongId(int songId, boolean acendingOrder);
	
	public Double getAverageSongRating(int songId);
}
