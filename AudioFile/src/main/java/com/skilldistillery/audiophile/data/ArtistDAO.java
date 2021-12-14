package com.skilldistillery.audiophile.data;

import java.util.List;

import com.skilldistillery.audiophile.entities.Artist;

public interface ArtistDAO {

	public Artist findArtistById(int id);
	public List<Artist> findByArtistName( String name);
	public List<Artist> findArtistBySongName(String songName);
	public List<Artist> findArtistBySongid(int songId);
	public Artist findArtistByAlbumName(String albumName);
	public List<Artist> sortByCreateDate();
	public List<Artist> sortByUpdateTime();
	
	public Artist addNewArtist(Artist artist);
	public boolean updateArtistName(int id, String newName);
	public boolean updateArtistImage(int id, String newImage);
	public boolean updateArtistDescription(int id, String newDescription);
	public boolean updatedArtist(int id, Artist artist);
	public boolean deleteArtist(int id);
}
