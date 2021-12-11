package com.skilldistillery.audiophile.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Song {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "lyrics")
	private String lyrics;

	@Column(name = "duration_seconds")
	private int durationInSeconds;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToMany(mappedBy = "songs")
	private List<Artist> artists;

	@ManyToOne
	@JoinTable(name = "album_song", 
	         joinColumns = { @JoinColumn(name = "song_id") }, inverseJoinColumns = {
			@JoinColumn(name = "album_id") })
	private Album album;
	
	@OneToMany(mappedBy ="song")
	private List<SongRating> songRatings;
	
	/* ----------------------------------------------------------------------------
	Constructors
---------------------------------------------------------------------------- */

	public Song() {
	}
	
	/* ----------------------------------------------------------------------------
	get/set Id
---------------------------------------------------------------------------- */

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	/* ----------------------------------------------------------------------------
	get/set Name
---------------------------------------------------------------------------- */

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	/* ----------------------------------------------------------------------------
	get/set Lyrics
---------------------------------------------------------------------------- */

	public String getLyrics() {
		return lyrics;
	}

	public void setLyrics(String lyrics) {
		this.lyrics = lyrics;
	}
	
	/* ----------------------------------------------------------------------------
	get/set Duration in Seconds
---------------------------------------------------------------------------- */

	public int getDurationInSeconds() {
		return durationInSeconds;
	}

	public void setDurationInSeconds(int durationInSeconds) {
		this.durationInSeconds = durationInSeconds;
	}
	
	/* ----------------------------------------------------------------------------
	get/set User
---------------------------------------------------------------------------- */

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	/* ----------------------------------------------------------------------------
	get/set Artists
---------------------------------------------------------------------------- */

	public List<Artist> getArtists() {
		return artists;
	}

	public void setArtists(List<Artist> artists) {
		this.artists = artists;
	}
	
	/* ----------------------------------------------------------------------------
	get/set Album
---------------------------------------------------------------------------- */

	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}
	
	/* ----------------------------------------------------------------------------
	   misc
---------------------------------------------------------------------------- */


	
	public List<SongRating> getSongRating() {
		return songRatings;
	}

	public void setSongRating(List<SongRating> songRatings) {
		this.songRatings = songRatings;
	}

	public void addSongRating(SongRating songRating) {
		if(songRatings == null) songRatings = new ArrayList<>();
		if(!songRatings.contains(songRating)) {
			songRatings.add(songRating);
			if(songRating.getSong() != null) {
				songRating.getSong().getSongRating().remove(songRating);
				
			}
			songRating.setSong(this);
		}
	}
	public void removeAlbumRating(SongRating songRating) {
		songRating.setSong(null);
		if(songRatings != null) {
			songRatings.remove(songRating);
		}
	}


	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Song other = (Song) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Song [id=" + id + ", name=" + name + ", lyrics=" + lyrics + ", durationInSeconds=" + durationInSeconds
				+ ", userId=" + user + "]";
	}

}
