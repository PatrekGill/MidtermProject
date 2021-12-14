package com.skilldistillery.audiophile.data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.skilldistillery.audiophile.entities.Artist;

@Repository
@Transactional
public class ArtistDAOImpl implements ArtistDAO {
	@PersistenceContext
	private EntityManager em;

	
	/*
	 * -----------------
	 * Find Artist By ID
	 * -----------------
	 */
	@Override
	public Artist findArtistById(int id) {
		// TODO Auto-generated method stub
		return (Artist)em.find(Artist.class, id);
	}

	/*
	 * -----------------------
	 * Find by Artist Name
	 * -----------------------
	 */
	@Override
	public List<Artist> findByArtistName(String name) {
		String jpql = "SELECT a FROM Artist a where a.name =:artName";
		List<Artist> artists = em.createQuery(jpql,Artist.class)
								 .setParameter("artName", name)
								 .getResultList();
		return artists;
	}

	/*
	 * -----------------------
	 * Sort by Create Date
	 * -----------------------
	 */
	@Override
	public List<Artist> sortByCreateDate() {
		String jpql = "SELECT a FROM Artist a order by a.createDate";
		List<Artist> artists = em.createQuery(jpql,Artist.class)
								 .getResultList();

		return artists;
	}
	
	/*
	 * -----------------------
	 * Sort by update Date
	 * -----------------------
	 */
	
	@Override
	public List<Artist> sortByUpdateTime() {
		String jpql = "SELECT a FROM Artist a order by a.updatedAt";
		List<Artist> artists = em.createQuery(jpql,Artist.class)
								 .getResultList();

		return artists;
	}
	
	/*
	 * -----------------------
	 * Find By Song Name
	 * -----------------------
	 */

	@Override
	public List<Artist> findArtistBySongName(String songName) {
		String jpql = "SELECT s.artists FROM Song s where s.name =:name";
		List<Artist> artists = new ArrayList<>();
		List<Object> objs = em.createQuery(jpql,Object.class)
							  .setParameter("name", songName)
							  .getResultList();
		objs.forEach(obj->artists.add((Artist)obj));
		return artists;
	}
	
	/*
	 * -----------------------
	 * Find by Song Id
	 * -----------------------
	 */

	@Override
	public List<Artist> findArtistBySongid(int songId) {
		String jpql = "SELECT s.artists FROM Song s where s.id =:id";
		List<Artist> artists = new ArrayList<>();
		List<Object> objs = em.createQuery(jpql,Object.class)
							  .setParameter("id", songId)
							  .getResultList();
		objs.forEach(obj->artists.add((Artist)obj));
		return artists;
	}

	/*
	 * -----------------------
	 * Find by Album Name
	 * -----------------------
	 */
	@Override
	public Artist findArtistByAlbumName(String albumName) {
		String jpql = "SELECT a.artist FROM Album a where a.title =:name";
		Artist artists = em.createQuery(jpql,Artist.class)
				 .setParameter("name", albumName)
				 .getSingleResult();
		return artists;
		
	}
	
	/*-----------------------------
	 * add Artist
	 * ----------------------------
	 */

	@Override
	public Artist addNewArtist(Artist artist) {
		em.getTransaction().begin();
		em.persist( artist);
		em.getTransaction().commit();
		em.close();
		return artist ;
	}

	/*-----------------------------
	 * update Artist Name
	 * ----------------------------
	 */
	@Override
	public boolean updateArtistName(int id, String newName) {

		boolean updated = false;
		if(newName != null) {
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
		if(newImage != null) {
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
		if(newDescription != null) {
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
	public boolean updatedArtist(int id, Artist artist) {
		Artist updateArtist = em.find(Artist.class, id);
		boolean updated = false;
		if(updateArtist != null && artist !=null) {
			updateArtistName(id, artist.getName());
			updateArtistImage(id, artist.getImageUrl());
			updateArtistDescription(id, artist.getDescription());
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
			em.getTransaction().begin();
			em.remove(artist);
			successfullyDeleted = !em.contains(artist);
			em.getTransaction().commit();
		}
		em.close();
		return successfullyDeleted;
	}


	
	
	

}
