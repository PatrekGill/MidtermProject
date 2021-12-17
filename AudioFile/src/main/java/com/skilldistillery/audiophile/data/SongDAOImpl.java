package com.skilldistillery.audiophile.data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.skilldistillery.audiophile.entities.Song;

@Repository
@Transactional
@Service
public class SongDAOImpl implements SongDAO {
	@PersistenceContext
	private EntityManager em;

	/*---------------------------
	 * Find Song By Song ID
	 * --------------------------
	 */
	@Override
	public Song findById(int id) {
		return em.find(Song.class, id);
	}

	/*------------------------
	 * Find Song By Song Name
	 * -----------------------
	 */
	@Override
	public List<Song> findBySongName(String name) {
		String jpql = "SELECT s FROM Song s where s.name LIKE :songName";
		List<Song> songs = em.createQuery(jpql, Song.class)
				.setParameter("songName", "%" + name + "%")
				.getResultList();
		return songs;
	}

	/*---------------------------
	 * Sort Song By Create Date
	 * --------------------------
	 */
	@Override
	public List<Song> sortByCreatDate() {
		String jpql = "SELECT s FROM Song s order by s.createDate";
		List<Song> songs;
		songs = em.createQuery(jpql, Song.class).getResultList();
		return songs;
	}

	/*---------------------------
	 * Find Song By Artist Name
	 * --------------------------
	 */
	@Override
	public List<Song> findByArtistName(String artistName) {
		String jpql = "SELECT a.songs FROM Artist a where a.name LIKE :artName";
		List<Song> songs = new ArrayList<>();
		List<Object> objs = em.createQuery(jpql, Object.class)
				.setParameter("artName", "%" + artistName + "%")
				.getResultList();
		objs.forEach(obj -> songs.add((Song) obj));
		return songs;
	}

	/*-------------------------
	 * Find Song By Album Name
	 * ------------------------
	 */
	@Override
	public List<Song> findByAlbumName(String albumName) {
		String jpql = "SELECT a.songs FROM Album a where a.title LIKE :albName";
		List<Song> songs = new ArrayList<>();
		List<Object> objs = em.createQuery(jpql, Object.class)
				.setParameter("albName", "%" + albumName + "%")
				.getResultList();
		objs.forEach(obj -> songs.add((Song) obj));
		return songs;
	}

	/*-----------------------------
	 * Create a new Song
	 * ----------------------------
	 */
	@Override
	public Song addNewSong(Song song) {
		em.persist(song);
		em.flush();
		
		return song;
	}

	/*-----------------------------
	 * Update Song name
	 * ----------------------------
	 */
	@Override
	public boolean updateSongName(int id, String newName) {
		
		boolean updated = false;
		if (newName != null) {
			Song updateSongName = em.find(Song.class, id);
			updateSongName.setName(newName);
			updated = true;
		}
		
		return updated;
	}

	/*-----------------------------
	 * update Song Lyrics
	 * ----------------------------
	 */
	@Override
	public boolean updateSongLyrics(int id, String newLyrics) {
		
		boolean updated = false;
		if (newLyrics != null) {
			Song updateSongLyrics = em.find(Song.class, id);
			updateSongLyrics.setLyrics(newLyrics);
			updated = true;
		}
		
		return updated;
	}

	/*-----------------------------
	 * update Song Duration Seconds
	 * ----------------------------
	 */
	@Override
	public boolean updateSongDurationSeconds(int id, int newDurationSeconds) {
		boolean updated = false;
		if (newDurationSeconds != 0) {
			Song updateSongDuration = em.find(Song.class, id);
			updateSongDuration.setDurationInSeconds(newDurationSeconds);
			updated = true;
		}
		return updated;
	}

	/*-----------------------------
	 * update  Song
	 * ----------------------------
	 */
	@Override
	public boolean updateSong(int id, Song song) {
		Song updateSong = em.find(Song.class, id);

		boolean updated = false;
		if (updateSong != null && song != null) {
			updateSongName(id, song.getName());
			updateSongLyrics(id, song.getLyrics());
			updateSongDurationSeconds(id, song.getDurationInSeconds());
			updated = true;
		}
		return updated;
	}

	/*-----------------------------
	 * delete Song By ID
	 * ----------------------------
	 */
	@Override
	public boolean deleteNewAddedSong(int id) {
		boolean successfullyDelete = false;
		
		Song song = em.find(Song.class, id);
		if (song != null) {
			em.remove(song);
			successfullyDelete = !em.contains(song);
		}
		
		return successfullyDelete;
	}

	/*-----------------------------
	 * Find  Song By Rating
	 * ----------------------------
	 */
	@Override
	public List<Song> findSongsByRating(int rating) {

		String jpql = "SELECT s.song FROM SongRating s where s.rating =:rat";
		List<Song> songs = em.createQuery(jpql, Song.class).setParameter("rat", rating).getResultList();
		return songs;
	}

	/*-----------------------------
	 * Sort  Song By Rating
	 * ----------------------------
	 */
	@Override
	public List<Song> sortBySongRating(boolean ascendingOder) {
		String jpql = "SELECT s.song FROM SongRating s order by s.rating ";
		if (ascendingOder) {
			jpql += " ASC ";
			
		} else {
			jpql += " DESC ";
			
		}
		List<Song> songs = em.createQuery(jpql, Song.class).getResultList();
		if(songs == null) {
			songs = new ArrayList<>();
		}
		return songs;

	}

	/*-----------------------------
	 * Sort  Song By update time
	 * ----------------------------
	 */
	@Override
	public List<Song> sortByUpdateTime() {
		
		String jpql = "SELECT s FROM Song s order by s.updatedAt";
		List<Song> songs = em.createQuery(jpql, Song.class).getResultList();

		return songs;
	}
	
	/*-----------------------------
	 * Find  Song By Lyrics Keyword
	 * ----------------------------
	 */
	@Override
	public List<Song> findByLyricsKeyword(String keyword) {
		// TODO Auto-generated method stub
		String jpql = "SELECT s FROM Song s where s.lyrics like :keywords";
		List<Song> songs = em.createQuery(jpql, Song.class).setParameter("keywords", "%" + keyword + "%")
				.getResultList();
		return songs;
	}

}
