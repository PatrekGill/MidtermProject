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
import javax.persistence.OneToMany;


@Entity
public class Album {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String title;
	private String description;

	@Column(name = "release_date")
	private LocalDateTime releaseDate;

	@Column(name = "image_url")
	private String imageURL;

	@Column(name = "user_id")
	private int userId;
	
	@ManyToMany
	@JoinTable(name="favortie_album",
		joinColumns=@JoinColumn(name="album_id"),
		inverseJoinColumns=@JoinColumn(name="user_id")
	)
	private List<User> favoritedBy;
	
	@OneToMany(mappedBy ="album")
	private List<AlbumRating> albumRatings;

	
	public Album() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(LocalDateTime releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	
	public List<User> getFavoritedBy() {
		if (favoritedBy == null) {
			favoritedBy = new ArrayList<>();
		}
		
		return favoritedBy;
	}
	public void setFavoritedBy(List<User> favoritedBy) {
		this.favoritedBy = favoritedBy;
	}
	
	public List<AlbumRating> getAlbumRatings() {
		return albumRatings;
	}

	public void setAlbumRatings(List<AlbumRating> albumRatings) {
		this.albumRatings = albumRatings;
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
			
			if (! user.getFavoritreAlbums().contains(this)) {
				user.getFavoritreAlbums().add(this);
			}
		}
		
		return addedToList;
	}
	public boolean removeFavoritedBy(User user) {
		boolean removed = false;
		if (favoritedBy != null && favoritedBy.contains(user)) {
			removed = favoritedBy.remove(user);
		}
		
		if (user.getFavoritreAlbums().contains(this)) {			
			user.removeFavoriteAlbum(this);
		}
		
		return removed;
	}
	//Larry addAlbum through the AlbumRating
//	public void addFilm(Film film) {
//		if(films == null) films = new ArrayList<>();
//		if(!films.contains(film)) {
//			films.add(film);
//			if(film.getLanguage() != null) {
//				film.getLanguage().getFilms().remove(film);
//				
//			}
//			film.setLanguage(this);
//		}
//	}
//	public void removeFilm(Film film) {
//		film.setLanguage(null);
//		if(films != null) {
//			films.remove(film);
//		}
//	}

	
	@Override
	public String toString() {
		return "Album [id=" + id + ", title=" + title + ", releaseDate=" + releaseDate + "]";
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
		Album other = (Album) obj;
		return id == other.id;
	}

}