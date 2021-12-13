package com.skilldistillery.audiophile.data;

import java.util.List;

import com.skilldistillery.audiophile.entities.SongRating;

public interface SongRatingDAO {

	List<SongRating> findByUsername (String username);
}
