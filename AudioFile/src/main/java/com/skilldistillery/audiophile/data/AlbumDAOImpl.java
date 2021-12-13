package com.skilldistillery.audiophile.data;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.skilldistillery.audiophile.entities.Album;
import com.skilldistillery.audiophile.entities.Artist;
import com.skilldistillery.audiophile.entities.Genre;
import com.skilldistillery.audiophile.entities.Song;
import com.skilldistillery.audiophile.entities.User;
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

	
	//Not finished need to add artist id to album table in db
	@Override
	public List<Album> findAlbumsByArtistName(String artistName) {

		return null;
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
	public List<Album> findAlbumsByRating(int rating){
		String jpql = "SELECT a FROM Album a WHERE AVG(a.albumRating) BETWEEN :startRating AND :endRating";
		try {
			return em.createQuery(jpql, Album.class).setParameter("startRating", rating).setParameter("endRating", (rating + 1)).getResultList();
		}catch(Exception e) {
			System.err.println("No album found from: " + rating);
			return null;
		}
	}

	@Override
	public boolean addAlbum(Album album) {

		em.getTransaction().begin();
		em.persist(album);
		em.flush();
		em.getTransaction().commit();
		return false;
	}

	@Override
	public boolean updateAlbum(Album album) {
		// TODO Auto-generated method stub
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

}
