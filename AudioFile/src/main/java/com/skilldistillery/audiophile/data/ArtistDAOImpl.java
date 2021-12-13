package com.skilldistillery.audiophile.data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.skilldistillery.audiophile.entities.Artist;
import com.skilldistillery.audiophile.entities.Song;

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

	@Override
	public List<Artist> sortByCreateDate() {
		String jpql = "SELECT a FROM Artist a order by a.createDate";
		List<Artist> artists = em.createQuery(jpql,Artist.class)
								 .getResultList();

		return artists;
	}

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
	
	
	

}
