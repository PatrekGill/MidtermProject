package com.skilldistillery.audiophile.data;

import java.util.List;

import com.skilldistillery.audiophile.entities.Genre;

public interface GenreDAO {

	/*
	 * ----------------------------------------------------------------------------
	 * Search Functions
	 * ----------------------------------------------------------------------------
	 */
	
	public Genre findGenreById(int id);
	public Genre findGenreByName(String genreName);
	public List<Genre> sortByName(boolean ascendingOrder);
}
