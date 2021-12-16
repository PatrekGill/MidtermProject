package com.skilldistillery.audiophile.data;

import java.util.List;

import com.skilldistillery.audiophile.entities.Artist;

public interface ArtistDAO {

	public Artist findById(int id);

	public List<Artist> findByArtistsName(String name);

	public List<Artist> findArtistsBySongName(String songName);

	public List<Artist> findArtistsBySongid(int songId);

	public Artist findPrimaryArtistByAlbumName(String albumName);

	public List<Artist> sortByCreateDate();

	public List<Artist> sortByUpdateTime();
	
	public Artist addNewArtist(Artist artist);

	public boolean updateArtistName(int id, String newName);

	public boolean updateArtistImage(int id, String newImage);

	public boolean updateArtistDescription(int id, String newDescription);

	public boolean updateArtist(int id, Artist artist);

	public boolean deleteArtist(int id);
}
