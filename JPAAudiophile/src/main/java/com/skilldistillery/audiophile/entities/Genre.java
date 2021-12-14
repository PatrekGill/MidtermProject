package com.skilldistillery.audiophile.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Genre {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
		
	private String name;
	
	@ManyToMany(mappedBy="genres")
	private List<Album> albums;
	
	/* ----------------------------------------------------------------------------
	Constructors
	---------------------------------------------------------------------------- */
	public Genre() {
		super();
	}

	/* ----------------------------------------------------------------------------
	Get/Set Id
	---------------------------------------------------------------------------- */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/* ----------------------------------------------------------------------------
	Get/Set Name
	---------------------------------------------------------------------------- */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	/* ----------------------------------------------------------------------------
	Get/Set Albums
	---------------------------------------------------------------------------- */

	public List<Album> getAlbums() {
		if (albums == null) {
			albums = new ArrayList<>();
		}
		return albums;
	}
	public void setAlbums(List<Album> albums) {
		this.albums = albums;
	}
	
	public boolean addAlbum(Album album) {
		if (albums == null) {
			albums = new ArrayList<>();
		}
		
		boolean addedToList = false;
		if (album != null) {
			if (! albums.contains(album)) {
				albums.add(album);
			}
			
			if (! album.getGenres().contains(this)) {
				addedToList = album.getGenres().add(this);
			}
		}
		
		return addedToList;
	}
	public boolean removeAlbum(Album album) {
		boolean removed = false;
		if (albums != null && albums.contains(album)) {
			removed = albums.remove(album);
		}
		
		if (album.getGenres().contains(this)) {
			album.removeGenre(this);
		}
		
		return removed;
	}

	/* ----------------------------------------------------------------------------
	    misc.
	---------------------------------------------------------------------------- */
	@Override
	public String toString() {
		return "Genre [id=" + id + ", name=" + name + "]";
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
		Genre other = (Genre) obj;
		return id == other.id;
	}

	
	}

