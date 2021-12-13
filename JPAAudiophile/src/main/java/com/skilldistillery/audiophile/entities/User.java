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
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;


@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@CreationTimestamp
	@Column(name="create_date")
	private LocalDateTime creationDateTime;
	
	@Column(name="image_url")
	private String imageURL;
	
	@ManyToMany(mappedBy="favoritedBy")
	List<Album> favoriteAlbums;

	@ManyToMany(mappedBy="favoritedBy")
	List<Song> favoriteSongs;
	
	@OneToMany(mappedBy="user")
	List<AlbumComment> comments;
	
	private boolean enabled;
	private String email;
	private String password;
	private String username;
	
	
//	private String role;
	
	@OneToMany(mappedBy="user")
	private List<Song> createdSongs;
	
	@OneToMany(mappedBy="user")
	private List<Artist> createdArtists;
	
	@OneToMany(mappedBy="user")
	private List<Album> createdAlbums;
	
	@OneToMany(mappedBy="user")
	private List<AlbumRating> albumRatings;
	
	@OneToMany(mappedBy="user")
	private List<SongRating> songRatings;
	
	
	/* ----------------------------------------------------------------------------
		Constructors
	---------------------------------------------------------------------------- */
	public User() {
		super();
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
		get/set Username
	---------------------------------------------------------------------------- */
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	

	/* ----------------------------------------------------------------------------
		get/set FirstName
	---------------------------------------------------------------------------- */
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	/* ----------------------------------------------------------------------------
		get/set LastName
	---------------------------------------------------------------------------- */
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	/* ----------------------------------------------------------------------------
		get/set CreationDateTime
	---------------------------------------------------------------------------- */
	public LocalDateTime getCreationDateTime() {
		return creationDateTime;
	}
	public void setCreationDateTime(LocalDateTime creationDateTime) {
		this.creationDateTime = creationDateTime;
	}

	
	/* ----------------------------------------------------------------------------
		get/set CreationDateTime
	---------------------------------------------------------------------------- */
	public String getImageURL() {
		return imageURL;
	}
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	
	/* ----------------------------------------------------------------------------
		get/set Enabled
	---------------------------------------------------------------------------- */
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	
	/* ----------------------------------------------------------------------------
		get/set Email
	---------------------------------------------------------------------------- */
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	/* ----------------------------------------------------------------------------
		get/set Password
	---------------------------------------------------------------------------- */
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	/* ----------------------------------------------------------------------------
		Album ratings list methods
	---------------------------------------------------------------------------- */
	public List<SongRating> getSongRatings() {
		if (songRatings == null) {
			songRatings = new ArrayList<>();
		}
		
		return songRatings;
	}
	public void setSongRatings(List<SongRating> songRatings) {
		this.songRatings = songRatings;
	}

	public boolean addSongRating(SongRating rating) {
		if (songRatings == null) {
			songRatings = new ArrayList<>();
		}

		boolean addedToList = false;
		if (rating != null) {
			if (!songRatings.contains(rating)) {
				addedToList = songRatings.add(rating);
			}
			
			rating.setUser(this);
		}

		return addedToList;
	}
	public boolean removeAlbumRating(SongRating rating) {
		boolean removed = false;
		if (songRatings != null && songRatings.contains(rating)) {
			removed = songRatings.remove(rating);
		}
		
		rating.setUser(null);
		
		return removed;
	}
	
	
	/* ----------------------------------------------------------------------------
		Song ratings list methods
	---------------------------------------------------------------------------- */
	public List<AlbumRating> getAlbumRatings() {
		if (albumRatings == null) {
			albumRatings = new ArrayList<>();
		}
		return albumRatings;
	}
	public void setAlbumRatings(List<AlbumRating> albumRatings) {
		this.albumRatings = albumRatings;
	}
	
	public boolean addAlbumRating(AlbumRating rating) {
		if (albumRatings == null) {
			albumRatings = new ArrayList<>();
		}
		
		boolean addedToList = false;
		if (rating != null) {
			if (!albumRatings.contains(rating)) {
				addedToList = albumRatings.add(rating);
			}
			
			rating.setUser(this);
		}
		
		return addedToList;
	}
	public boolean removeAlbumRating(AlbumRating rating) {
		boolean removed = false;
		if (albumRatings != null && albumRatings.contains(rating)) {
			removed = albumRatings.remove(rating);
		}
		
		rating.setUser(null);
		
		return removed;
	}
	
	
	/* ----------------------------------------------------------------------------
		Album comments list methods
	---------------------------------------------------------------------------- */
	public List<AlbumComment> getComments() {
		if (comments == null) {
			comments = new ArrayList<>();
		}
		
		return comments;
	}
	public void setComments(List<AlbumComment> comments) {
		this.comments = comments;
	}
	
	public boolean addComment(AlbumComment comment) {
		if (comments == null) {
			comments = new ArrayList<>();
		}

		boolean addedToList = false;
		if (comment != null) {
			if (!comments.contains(comment)) {
				addedToList = comments.add(comment);
			}
			
			comment.setUser(this);
		}

		return addedToList;
	}
	public boolean removeComment(AlbumComment comment) {
		boolean removed = false;
		if (comments != null && comments.contains(comment)) {
			removed = comments.remove(comment);
		}
		
		comment.setUser(null);
		
		return removed;
	}


	/* ----------------------------------------------------------------------------
		Favorite album list methods
	---------------------------------------------------------------------------- */
	public List<Album> getFavoriteAlbums() {
		if (favoriteAlbums == null) {
			favoriteAlbums = new ArrayList<>();
		}
		
		return favoriteAlbums;
	}
	public void setFavoriteAlbums(List<Album> favoritreAlbums) {
		this.favoriteAlbums = favoritreAlbums;
	}
	
	public boolean addFavoriteAlbum(Album album) {
		if (favoriteAlbums == null) {
			favoriteAlbums = new ArrayList<>();
		}
		
		boolean addedToList = false;
		if (album != null) {
			if (! favoriteAlbums.contains(album)) {
				favoriteAlbums.add(album);
			}
			
			if (! album.getFavoritedBy().contains(this)) {
				addedToList = album.getFavoritedBy().add(this);
			}
		}
		
		return addedToList;
	}
	public boolean removeFavoriteAlbum(Album album) {
		boolean removed = false;
		if (favoriteAlbums != null && favoriteAlbums.contains(album)) {
			removed = favoriteAlbums.remove(album);
		}
		
		if (album.getFavoritedBy().contains(this)) {
			album.removeFavoritedBy(this);
		}
		
		return removed;
	}

	
	/* ----------------------------------------------------------------------------
		Favorite songs list methods
	---------------------------------------------------------------------------- */
	public List<Song> getFavoriteSongs() {
		if (favoriteSongs == null) {
			favoriteSongs = new ArrayList<>();
		}
		
		return favoriteSongs;
	}
	public void setFavoriteSongs(List<Song> favoriteSongs) {
		this.favoriteSongs = favoriteSongs;
	}
	
	public boolean addFavoriteSong(Song song) {
		if (favoriteSongs == null) {
			favoriteSongs = new ArrayList<>();
		}
		
		boolean addedToList = false;
		if (song != null) {
			if (! favoriteSongs.contains(song)) {
				favoriteSongs.add(song);
			}
			
			if (! song.getFavoritedBy().contains(this)) {
				addedToList = song.getFavoritedBy().add(this);
			}
		}
		
		return addedToList;
	}
	public boolean removeFavoriteSong(Song song) {
		boolean removed = false;
		if (favoriteSongs != null && favoriteSongs.contains(song)) {
			removed = favoriteSongs.remove(song);
		}
		
		if (song.getFavoritedBy().contains(this)) {
			song.removeFavoritedBy(this);
		}
		
		return removed;
	}
	
	
	/* ----------------------------------------------------------------------------
		get/set Role
	---------------------------------------------------------------------------- */
//	public String getRole() {
//		return role;
//	}
//	public void setRole(String role) {
//		this.role = role;
//	}

	
	/* ----------------------------------------------------------------------------
		get/set songs
	---------------------------------------------------------------------------- */
	public List<Song> getCreatedSongs() {
		return createdSongs;
	}
	public void setCreatedSongs(List<Song> songs) {
		this.createdSongs = songs;
	}

	
	/* ----------------------------------------------------------------------------
		get/set Artist
	---------------------------------------------------------------------------- */
	public List<Artist> getCreatedArtists() {
		return createdArtists;
	}
	public void setCreatedArtists(List<Artist> artists) {
		this.createdArtists = artists;
	}

	
	public List<Album> getCreatedAlbums() {
		return createdAlbums;
	}


	public void setCreatedAlbums(List<Album> createdAlbums) {
		this.createdAlbums = createdAlbums;
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
		User other = (User) obj;
		return id == other.id;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + "]";
	}

}
