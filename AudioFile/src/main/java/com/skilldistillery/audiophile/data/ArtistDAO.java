package com.skilldistillery.audiophile.data;

import java.util.List;

import com.skilldistillery.audiophile.entities.Artist;

public interface ArtistDAO {

	public Artist findArtistById(int id);
	public List<Artist> findByArtistName( String name);
	public List<Artist> sortByCreateDate();
	public List<Artist> findArtistBySongName(String songName);
	public List<Artist> findArtistBySongid(int songId);
	public List<Artist> findArtistByAlbumName(String albumName);
}
