package com.skilldistillery.audiophile.entities;

import java.time.LocalDateTime;
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
	
	@Column(name="create_date")
	private LocalDateTime createDate;

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
	
	@OneToMany(mappedBy="song")
	private List<SongRating> songRatings;
	
	@ManyToMany
	@JoinTable(
		name="favorite_song",
		joinColumns=@JoinColumn(name="song_id"),
		inverseJoinColumns=@JoinColumn(name="user_id")
	)
	private List<User> favoritedBy;
	
	
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
		get/set Create Date
	---------------------------------------------------------------------------- */
	public LocalDateTime getCreateDate() {
		return createDate;
	}
	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
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
		Get/Set Song Ratings
	---------------------------------------------------------------------------- */
	public List<SongRating> getSongRatings() {
		return songRatings;
	}
	public void setSongRatings(List<SongRating> songRatings) {
		this.songRatings = songRatings;
	}

	public void addSongRating(SongRating songRating) {
		if(songRatings == null) {
			songRatings = new ArrayList<>();
		}
		
		if(!songRatings.contains(songRating)) {
			songRatings.add(songRating);
			if(songRating.getSong() != null) {
				songRating.getSong().getSongRatings().remove(songRating);
				
			}
			
			songRating.setSong(this);
		}
	}
	public void removeSongRating(SongRating songRating) {
		songRating.setSong(null);
		
		if(songRatings != null) {
			songRatings.remove(songRating);
		}
	}
	
	
	/* ----------------------------------------------------------------------------
		FavoritedBy list methods
	---------------------------------------------------------------------------- */
	public List<User> getFavoritedBy() {
		if (favoritedBy == null) {
			favoritedBy = new ArrayList<>();
		}
		
		return favoritedBy;
	}
	public void setFavoritedBy(List<User> favoritedBy) {
		this.favoritedBy = favoritedBy;
	}
	
	public boolean addToFavoritedBy(User user) {
		if (favoritedBy == null) {
			favoritedBy = new ArrayList<>();
		}
		
		boolean addedToList = false;
		if (user != null) {
			if (! favoritedBy.contains(user)) {
				addedToList = favoritedBy.add(user);
			}
			
			if (! user.getFavoriteSongs().contains(this)) {
				user.getFavoriteSongs().add(this);
			}
		}
		
		return addedToList;
	}
	public boolean removeFavoritedBy(User user) {
		boolean removed = false;
		if (favoritedBy != null && favoritedBy.contains(user)) {
			removed = favoritedBy.remove(user);
		}
		
		if (user.getFavoriteSongs().contains(this)) {			
			user.removeFavoriteSong(this);
		}
		
		return removed;
	}

	
	/* ----------------------------------------------------------------------------
	   misc
	---------------------------------------------------------------------------- */
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
		StringBuilder builder = new StringBuilder();
		builder.append("Song [id=").append(id).append(", name=").append(name).append(", durationInSeconds=")
				.append(durationInSeconds).append(", createDate=").append(createDate).append(", user=").append(user)
				.append(", album=").append(album).append("]");
		return builder.toString();
	}

}
