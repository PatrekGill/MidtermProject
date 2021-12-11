package com.skilldistillery.audiophile.entities;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="song_rating")
public class SongRating {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column
	private String description;
	
	@Column(name="rating_date")
	private LocalDateTime ratingdate;
	
	@Column
	private int rating;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	@ManyToOne
	@JoinColumn(name="song_id")
	private Song song;
	

	public SongRating() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getRatingdate() {
		return ratingdate;
	}

	public void setRatingdate(LocalDateTime ratingdate) {
		this.ratingdate = ratingdate;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Song getSong() {
		return song;
	}

	public void setSong(Song song) {
		this.song = song;
	}

	@Override
	public int hashCode() {
		return Objects.hash(description, id, rating, ratingdate, song, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SongRating other = (SongRating) obj;
		return Objects.equals(description, other.description) && id == other.id && rating == other.rating
				&& Objects.equals(ratingdate, other.ratingdate) && Objects.equals(song, other.song)
				&& Objects.equals(user, other.user);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SongRating [id=").append(id).append(", ratingdate=").append(ratingdate).append(", rating=")
				.append(rating).append(", user=").append(user).append(", song=").append(song).append("]");
		return builder.toString();
	}

	
	
	
}
