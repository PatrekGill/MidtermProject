package com.skilldistillery.audiophile.data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.skilldistillery.audiophile.entities.Album;
import com.skilldistillery.audiophile.entities.Song;
@Repository
@Transactional
@Service
public class AlbumDAOImpl implements AlbumDAO{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Album findAlbumById(int id) {
		return em.find(Album.class, id);
	}

	@Override
	public Album findAlbumByTitle(String albumTitle) {
		String jpql = "SELECT a FROM Album a WHERE a.title LIKE :title";
		
		try {
			return em.createQuery(jpql, Album.class)
					.setParameter("title", "%" + albumTitle + "%")
					.getSingleResult();
		}catch(Exception e) {
			System.err.println("No album found from: " + albumTitle);
			return null;
		}
	}

	@Override
	public Album findAlbumBySongTitle(String songName) {
		String jpql ="SELECT a FROM Album a JOIN Song s ON a = s.album WHERE s.name LIKE :songName";
		
		try {
			return em.createQuery(jpql, Album.class)
					.setParameter("songName", "%" + songName + "%")
					.getSingleResult();
		}catch(Exception e) {
			System.err.println("No album found from: " + songName);
			return null;
		}
	}

	
	@Override
	public List<Album> findAlbumsByArtistName(String artistName) {
		String jpql = "SELECT a FROM Album a WHERE a.artist.name LIKE :artistName";
		
		return em.createQuery(jpql, Album.class)
			.setParameter("artistName", "%" + artistName + "%")
			.getResultList();
	}

	@Override
	public List<Album> findAlbumByCreationDate(LocalDateTime creationDate) {
		String jpql = "SELECT a FROM Album a WHERE a.creationDateTime =:date";
		
		return em.createQuery(jpql, Album.class)
				.setParameter("date", creationDate)
				.getResultList();
	}

	@Override
	public List<Album> findAlbumsByGenre(String genre) {
		String jpql = "SELECT a FROM Album a WHERE a.genre.name LIKE :genre";
		
		return em.createQuery(jpql, Album.class)
				.setParameter("genre", "%" + genre + "%")
				.getResultList();
	}

	@Override
	public List<Album> findAlbumsByCreatedUsername(String username) {
		String jpql = "SELECT a FROM Album a WHERE a.user.username LIKE :username";
		
		return em.createQuery(jpql, Album.class)
				.setParameter("username", "%" + username + "%")
				.getResultList();
	}
	
	@Override
	public boolean addAlbum(Album album) {
		boolean creationSuccess = false;
		em.persist(album);
		em.flush();
		creationSuccess = em.contains(album);
		return creationSuccess;
	}

	@Override
	public boolean updateAlbum(int id, Album album) {
		boolean updateSuccess = false;
		
		Album albumToBeUpdated = em.find(Album.class, id);
		if (albumToBeUpdated != null && album != null) {
			albumToBeUpdated.setTitle(album.getTitle());
			albumToBeUpdated.setDescription(album.getDescription());
			albumToBeUpdated.setReleaseDate(album.getReleaseDate());
			albumToBeUpdated.setImageURL(album.getImageURL());
			updateSuccess = true;
		}
		
		return updateSuccess;
	}

	@Override
	public boolean deleteAlbum(Album album) {
		boolean deleteSuccess = false;
		em.remove(album);
		deleteSuccess = !em.contains(album);
		return deleteSuccess;
	}

	@Override
	public List<Song> getSongsFromAlbum(Album album) {
		String jpql = "SELECT s FROM Song s WHERE s.album =:a";
		
		return em.createQuery(jpql, Song.class)
				.setParameter("a", album)
				.getResultList();
	}
	
	@Override
	public List<Album> sortAlbumsByRating(boolean ascendingOrder){
		String jpql = "SELECT a FROM Album a LEFT JOIN a.albumRatings ar GROUP BY a ORDER BY AVG(ar.rating)";
		
		if (ascendingOrder) {
			jpql += " ASC";
			
		} else {
			jpql += " DESC";
			
		}
		
		List<Album> albums = em.createQuery(jpql, Album.class).getResultList();
		if(albums == null) {
			albums = new ArrayList<>();
		}
		return albums;
	}

	@Override
	public List<Album> sortAlbumsByCreateDate(boolean ascendingOrder) {
		String jpql ="SELECT a FROM Album a ORDER BY a.creationDateTime";
		
		if (ascendingOrder) {
			jpql += " ASC";
			
		} else {
			jpql += " DESC";
			
		}
		
		List<Album> albums = em.createQuery(jpql, Album.class).getResultList();
		if(albums == null) {
			albums = new ArrayList<>();
		}
		return albums;
	}

}
