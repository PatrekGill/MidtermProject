package com.skilldistillery.audiophile.entities;

import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Song {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="lyrics")
	private String lyrics;
	
	@Column(name="duration_seconds")
	private int durationInSeconds;
	
	@Column(name="user_id")
	private int userId;
	
	@ManyToMany(mappedBy="songs")
	private List<Artist> artists;

	
//	*commented need to get album entity information*
//	@ManyToOne
//	@JoinColumn(name="album_id")
//	private Album album;
	
	public Song() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLyrics() {
		return lyrics;
	}

	public void setLyrics(String lyrics) {
		this.lyrics = lyrics;
	}

	public int getDurationInSeconds() {
		return durationInSeconds;
	}

	public void setDurationInSeconds(int durationInSeconds) {
		this.durationInSeconds = durationInSeconds;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public List<Artist> getArtists() {
		return artists;
	}

	public void setArtists(List<Artist> artists) {
		this.artists = artists;
	}

//	*commented need to get album entity information*
//	public Album getAlbum() {
//		return album;
//	}
//
//	public void setAlbum(Album album) {
//		this.album = album;
//	}

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
				+ ", userId=" + userId + "]";
	}
	
	
	
	

}
