package com.skilldistillery.audiophile.data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.skilldistillery.audiophile.entities.Artist;

@Repository
@Transactional
@Service
public class ArtistDAOImpl implements ArtistDAO {
	@PersistenceContext
	private EntityManager em;

	/*
	 * ----------------- Find Artist By ID -----------------
	 */
	@Override
	public Artist findById(int id) {
		return em.find(Artist.class, id);
	}

	/*
	 * ----------------------- Find by Artist Name -----------------------
	 */
	@Override
	public List<Artist> findByArtistsName(String name) {
		String jpql = "SELECT a FROM Artist a where a.name LIKE :artName";
		List<Artist> artists = em.createQuery(jpql, Artist.class)
				.setParameter("artName", "%" + name + "%")
				.getResultList();
		return artists;
	}

	/*
	 * ----------------------- Sort by Create Date -----------------------
	 */
	@Override
	public List<Artist> sortByCreateDate() {
		String jpql = "SELECT a FROM Artist a order by a.createDate";
		List<Artist> artists = em.createQuery(jpql, Artist.class).getResultList();

		return artists;
	}

	/*
	 * ----------------------- Sort by update Date -----------------------
	 */

	@Override
	public List<Artist> sortByUpdateTime() {
		String jpql = "SELECT a FROM Artist a order by a.updatedAt";
		List<Artist> artists = em.createQuery(jpql, Artist.class).getResultList();

		return artists;
	}

	/*
	 * ----------------------- Find By Song Name -----------------------
	 */

	@Override
	public List<Artist> findArtistsBySongName(String songName) {
		String jpql = "SELECT s.artists FROM Song s where s.name LIKE :name";
		
		List<Artist> artists = new ArrayList<>();
		
		List<Object> objs = em.createQuery(jpql, Object.class)
				.setParameter("name", "%" + songName + "%")
				.getResultList();
		
		objs.forEach(obj -> artists.add((Artist) obj));
		
		return artists;
	}

	/*
	 * ----------------------- Find by Song Id -----------------------
	 */

	@Override
	public List<Artist> findArtistsBySongid(int songId) {
		String jpql = "SELECT s.artists FROM Song s where s.id =:id";
		List<Artist> artists = new ArrayList<>();
		List<Object> objs = em.createQuery(jpql, Object.class).setParameter("id", songId).getResultList();
		objs.forEach(obj -> artists.add((Artist) obj));
		return artists;
	}

	/*
	 * ----------------------- Find by Album Name -----------------------
	 */
	@Override
	public Artist findPrimaryArtistByAlbumName(String albumName) {
		String jpql = "SELECT a.artist FROM Album a where a.title LIKE :name";
		
		Artist artist;
		try {
			artist = em.createQuery(jpql, Artist.class)
					.setParameter("name", "%" + albumName + "%")
					.getSingleResult();
		} catch (Exception e) {
			System.err.println("findArtistByAlbumName encountered problemt");
			artist = null;
		}
		
		return artist;

	}

	/*-----------------------------
	 * add Artist
	 * ----------------------------
	 */

	@Override
	public Artist addNewArtist(Artist artist) {
		em.persist(artist);
		em.flush();
		
		return artist;
	}

	/*-----------------------------
	 * update Artist Name
	 * ----------------------------
	 */
	@Override
	public boolean updateArtistName(int id, String newName) {

		boolean updated = false;
		if (newName != null) {
			Artist updateArtistName = em.find(Artist.class, id);
			updateArtistName.setName(newName);
			updated = true;
		}

		return updated;
	}

	/*-----------------------------
	 * update Artist Image
	 * ----------------------------
	 */
	@Override
	public boolean updateArtistImage(int id, String newImage) {
		boolean updated = false;
		if (newImage != null) {
			Artist updateArtistImage = em.find(Artist.class, id);
			updateArtistImage.setImageUrl(newImage);
			updated = true;
		}

		return updated;
	}

	/*-----------------------------
	 * update Artist Description
	 * ----------------------------
	 */

	@Override
	public boolean updateArtistDescription(int id, String newDescription) {
		boolean updated = false;
		if (newDescription != null) {
			Artist updateArtistDesc = em.find(Artist.class, id);
			updateArtistDesc.setDescription(newDescription);
			updated = true;
		}

		return updated;
	}

	/*-----------------------------
	 * update Artist
	 * ----------------------------
	 */
	@Override
	public boolean updateArtist(int id, Artist artist) {
		Artist updateArtist = em.find(Artist.class, id);
		
		boolean updated = false;
		if (updateArtist != null && artist != null) {
			updateArtistName(id, artist.getName());
			updateArtistImage(id, artist.getImageUrl());
			updateArtistDescription(id, artist.getDescription());
			
			updated = true;
		}
		
		return updated;
	}

	/*-----------------------------
	 * delete Artist By ID
	 * ----------------------------
	 */
	@Override
	public boolean deleteArtist(int id) {
		boolean successfullyDeleted = false;
		Artist artist = em.find(Artist.class, id);

		if (artist != null) {
			em.remove(artist);
			successfullyDeleted = !em.contains(artist);
		}
		
		return successfullyDeleted;
	}

	@Override
	public List<Artist> getTopThreeArtist(boolean ascendingOder) {
String jpql = "SELECT art FROM Artist art JOIN art.albums alb JOIN alb.albumRatings ar GROUP BY art ORDER BY AVG(ar.rating)";
		
		if (ascendingOder) {
			jpql += " ASC ";
			
		} else {
			jpql += " DESC ";
			
		}
		
		List<Artist> artists = em.createQuery(jpql, Artist.class).getResultList();
		if(artists == null) {
			artists = new ArrayList<>();
		}
		return artists;
	}



}
