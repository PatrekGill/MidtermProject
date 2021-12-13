package com.skilldistillery.audiophile.data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.skilldistillery.audiophile.entities.Song;

@Repository
@Transactional
public class SongDAOImpl implements SongDAO {
	@PersistenceContext
	private EntityManager em;
	
	/*---------------------------
	 * Find Song By Song ID
	 * --------------------------
	 */
	@Override
	public Song findBySongId(int id) {
		return(Song)em.find(Song.class, id);
	}
	
	/*------------------------
	 * Find Song By Song Name
	 * -----------------------
	 */
	
	@Override
	public List<Song> findBySongName(String name) {

		String jpql ="SELECT s FROM Song s where s.name = :songName";
		List<Song> songs;
		songs = em.createQuery(jpql,Song.class)
				  .setParameter("songName", name)
				  .getResultList();
		return songs;
	}
	
	/*---------------------------
	 * Sort Song By Create Date
	 * --------------------------
	 */
	
	@Override
	public List<Song> sortByCreatDate() {
		String jpql ="SELECT s FROM Song s order by s.createDate";
		List<Song> songs;
		songs = em.createQuery(jpql,Song.class)
				  .getResultList();
		return songs;
	}
	
	/*---------------------------
	 * Find Song By Artist Name
	 * --------------------------
	 */
	@Override
	public List<Song> findByArtistName(String artistName) {
		String jpql = "SELECT a.songs FROM Artist a where a.name = :artName";
		List<Song> songs = new ArrayList<>();
		List<Object> objs = em.createQuery(jpql,Object.class)
				              .setParameter("artName", artistName)
				              .getResultList();
		objs.forEach(obj->songs.add((Song)obj));
		return songs;
	}
	
	/*-------------------------
	 * Find Song By Album Name
	 * ------------------------
	 */
	@Override
	public List<Song> findByAlbumName(String albumName) {
		String jpql = "SELECT a.songs FROM Album a where a.title = :albName";
		List<Song> songs = new ArrayList<>();
		List<Object>objs = em.createQuery(jpql,Object.class)
	              			 .setParameter("albName", albumName)
	              			 .getResultList();
		objs.forEach(obj->songs.add((Song)obj));
		return songs;
	}
	
	/*-----------------------------
	 * Create a new Song
	 * ----------------------------
	 */
	
	@Override
	public Song addNewSongs(Song song) {
		em.getTransaction().begin();
		em.persist(song);
		em.getTransaction().commit();
		em.close();
		return song;
	}
	
	@Override
	public boolean deleteNewAddedSong(int id) {
		boolean successfullyDelete = false;
		Song song = em.find(Song.class, id);
		if (song != null) {
			em.getTransaction().begin();
			em.remove(song);
			successfullyDelete = !em.contains(song);
			em.getTransaction().commit();
		}
		em.close();
		return successfullyDelete;
	}

	@Override
	public List<Song> findSongByRating(int rating) {
		
		String jpql = "SELECT s.song FROM SongRating s where s.rating =:rat";
		List<Song> songs = em.createQuery(jpql,Song.class)
							 .setParameter("rat", rating)
							 .getResultList();
		return songs;
	}

	@Override
	public List<Song> sortBySongRating() {
		String jpql = "SELECT s.song FROM SongRating s order by s.rating";
		List<Song> songs = em.createQuery(jpql,Song.class)
							 .getResultList();
		
		return songs;
	}

	
}
