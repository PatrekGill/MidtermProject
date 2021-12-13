package com.skilldistillery.audiophile.data;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.skilldistillery.audiophile.entities.Album;
import com.skilldistillery.audiophile.entities.Song;
@Repository
@Transactional
public class AlbumDAOImpl implements AlbumDAO{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Album findAlbumById(int id) {
		String jpql = "SELECT a FROM Album a WHERE a.id =:id";
		
		try {
			return em.createQuery(jpql, Album.class).setParameter("id", id).getSingleResult();
		}catch (Exception e) {
			System.err.println("Invalid album id: " + id);
			return null;
		}
	}

	@Override
	public Album findAlbumByTitle(String albumTitle) {
		String jpql = "SELECT a FROM Album a WHERE a.title LIKE :title";
		
		try {
			return em.createQuery(jpql, Album.class).setParameter("title", "%" + albumTitle + "%").getSingleResult();
		}catch(Exception e) {
			System.err.println("No album found from: " + albumTitle);
			return null;
		}
	}

	@Override
	public Album findAlbumBySongTitle(String songName) {
		String jpql ="SELECT a FROM Album a JOIN Song s ON a = s.album WHERE s.name LIKE :songName";
		
		try {
			return em.createQuery(jpql, Album.class).setParameter("songName", "%" + songName + "%").getSingleResult();
		}catch(Exception e) {
			System.err.println("No album found from: " + songName);
			return null;
		}
	}

	
	@Override
	public List<Album> findAlbumsByArtistName(String artistName) {
		String jpql = "SELECT a FROM Album a WHERE a.artist.name LIKE :artistName";
		
		try {
			return em.createQuery(jpql, Album.class).setParameter("artistName", "%" + artistName + "%").getResultList();
		}catch(Exception e) {
			System.err.println("No album found from: " + artistName);
			return null;
		}
	}

	@Override
	public List<Album> findAlbumByCreationDate(LocalDateTime creationDate) {
		String jpql = "SELECT a FROM Album a WHERE a.creationDateTime =:date";
		
		try {
			return em.createQuery(jpql, Album.class).setParameter("date", creationDate).getResultList();
		}catch(Exception e) {
			System.err.println("No album found from: " + creationDate);
			return null;
		}
	}

	@Override
	public List<Album> findAlbumsByGenre(String genre) {
		String jpql = "SELECT a FROM Album a WHERE a.genre.name LIKE :genre";
		
		try {
			return em.createQuery(jpql, Album.class).setParameter("genre", "%" + genre + "%").getResultList();
		}catch(Exception e) {
			System.err.println("No album found from: " + genre);
			return null;
		}
	}

	@Override
	public List<Album> findAlbumsByCreatedUsername(String username) {
		String jpql = "SELECT a FROM Album a WHERE a.user.username LIKE :username";
		
		try {
			return em.createQuery(jpql, Album.class).setParameter("username", "%" + username + "%").getResultList();
		}catch(Exception e) {
			System.err.println("No album found from: " + username);
			return null;
		}
	}
	
	@Override
	public List<Album> sortAlbumsByRating(){
		String jpql = "SELECT a FROM Album a LEFT JOIN a.albumRatings ar GROUP BY a ORDER BY AVG(ar.rating)";
		try {
			return em.createQuery(jpql, Album.class).getResultList();
		}catch(Exception e) {
			System.err.println("No albums found");
			return null;
		}
	}

	@Override
	public boolean addAlbum(Album album) {
		boolean creationSuccess = false;
		em.getTransaction().begin();
		em.persist(album);
		em.flush();
		creationSuccess = em.contains(album);
		em.getTransaction().commit();
		return creationSuccess;
	}

	@Override
	public boolean updateAlbum(int id, Album album) {
		boolean updateSuccess = false;
		Album albumToBeUpdated = em.find(Album.class, id);
		albumToBeUpdated.setTitle(album.getTitle());
		albumToBeUpdated.setDescription(album.getDescription());
		albumToBeUpdated.setReleaseDate(album.getReleaseDate());
		albumToBeUpdated.setImageURL(album.getImageURL());
		return false;
	}

	@Override
	public boolean deleteAlbum(Album album) {
		boolean deleteSuccess = false;
		em.getTransaction().begin();
		em.remove(album);
		deleteSuccess = !em.contains(album);
		em.getTransaction().commit();
		return deleteSuccess;
	}

	@Override
	public List<Song> getSongsFromAlbum(Album album) {
		String jpql = "SELECT s FROM Song s WHERE s.album =:a";
		try {
			return em.createQuery(jpql, Song.class).setParameter("a", album).getResultList();
		}catch(Exception e) {
			System.err.println("No songs found from: " + album);
			return null;
		}
	}

	@Override
	public List<Album> sortAlbumsByCreateDate() {
			String jpql ="SELECT a FROM Album a ORDER BY a.creationDateTime";
		return null;
	}

}
