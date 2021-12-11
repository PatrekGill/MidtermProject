package com.skilldistillery.audiophile.entities;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="album_rating")
public class AlbumRating {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column
	private String description;
	
	@Column(name="rating_date")
	private LocalDateTime ratingdate;
	
	@Column
	private int rating;

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

	@Override
	public int hashCode() {
		return Objects.hash(description, id, rating, ratingdate);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AlbumRating other = (AlbumRating) obj;
		return Objects.equals(description, other.description) && id == other.id && rating == other.rating
				&& Objects.equals(ratingdate, other.ratingdate);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AlbumRating [id=").append(id).append(", description=").append(description)
				.append(", ratingdate=").append(ratingdate).append(", rating=").append(rating).append("]");
		return builder.toString();
	}
	

}
