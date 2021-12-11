package com.skilldistillery.audiophile.entities;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Artist {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="name")
	private String name;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	@Column(name="image_url")
	private String imageUrl;
	
	@Column(name="description")
	private String description;
	
	@Column(name="create_date")
	private LocalDateTime createDate;
	
	@ManyToMany(cascade= CascadeType.PERSIST)
	@JoinTable(name="song_artist",
	  joinColumns=@JoinColumn(name="artist_id"),
	  inverseJoinColumns=@JoinColumn(name="song_id")
	)
	private List<Song> songs;
	
	/* ----------------------------------------------------------------------------
	    Constructors
---------------------------------------------------------------------------- */
	
	public Artist() {}
	
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
	get/set User
---------------------------------------------------------------------------- */

	public User getUser() {
		return user;
	}

	public void setUserId(User user) {
		this.user = user;
	}
	
	/* ----------------------------------------------------------------------------
	get/set ImageUrl
---------------------------------------------------------------------------- */

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	/* ----------------------------------------------------------------------------
	get/set Description
---------------------------------------------------------------------------- */

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	/* ----------------------------------------------------------------------------
	get/set Songs
---------------------------------------------------------------------------- */

	public List<Song> getSongs() {
		return songs;
	}

	public void setSongs(List<Song> songs) {
		this.songs = songs;
	}
	
	/* ----------------------------------------------------------------------------
	    misc
---------------------------------------------------------------------------- */

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public void setUser(User user) {
		this.user = user;
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
		Artist other = (Artist) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Artist [id=").append(id).append(", name=").append(name).append(", user=").append(user)
				.append(", createDate=").append(createDate).append("]");
		return builder.toString();
	}
	
	

}
