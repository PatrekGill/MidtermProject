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

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="create_date")
	private LocalDateTime creationDateTime;
	
	@Column(name="image_url")
	private String imageURL;
	
	@ManyToMany(mappedBy="favoritedBy")
	List<Album> favoritreAlbums;
	
	
	private boolean enabled;
	private String email;
	private String password;
	private String username;
//	private String role;
	
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
		Favorite album list methods
	---------------------------------------------------------------------------- */
	public List<Album> getFavoritreAlbums() {
		if (favoritreAlbums == null) {
			favoritreAlbums = new ArrayList<>();
		}
		
		return favoritreAlbums;
	}
	public void setFavoritreAlbums(List<Album> favoritreAlbums) {
		this.favoritreAlbums = favoritreAlbums;
	}
	
	public boolean addFavoriteAlbum(Album album) {
		if (favoritreAlbums == null) {
			favoritreAlbums = new ArrayList<>();
		}
		
		boolean addedToList = false;
		if (album != null) {
			if (! favoritreAlbums.contains(album)) {
				favoritreAlbums.add(album);
			}
			
			if (! album.getFavoritedBy().contains(this)) {
				addedToList = album.getFavoritedBy().add(this);
			}
		}
		
		return addedToList;
	}
	public boolean removeFavoriteAlbum(Album album) {
		boolean removed = false;
		if (favoritreAlbums != null && favoritreAlbums.contains(album)) {
			removed = favoritreAlbums.remove(album);
		}
		
		if (album.getFavoritedBy().contains(this)) {
			album.removeFavoritedBy(this);
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
