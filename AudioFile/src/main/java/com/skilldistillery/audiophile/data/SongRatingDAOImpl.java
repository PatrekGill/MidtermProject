package com.skilldistillery.audiophile.data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.skilldistillery.audiophile.entities.AlbumRating;
import com.skilldistillery.audiophile.entities.SongRating;

@Repository
@Transactional
public class SongRatingDAOImpl implements SongRatingDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<SongRating> findByUsername(String username) {
		String jpql = "SELECT sr FROM SongRating sr WHERE sr.user.username LIKE :n";

		return em.createQuery(jpql, SongRating.class).setParameter("n", "%" + username + "%").getResultList();
	}

	@Override
	public List<SongRating> findSongRatingsByUserId(int id) {
		String jpql = "SELECT sr FROM SongRating sr WHERE sr.user.id =:n";

		try {
			return em.createQuery(jpql, SongRating.class).setParameter("n", id).getResultList();
		} catch (Exception e) { e.printStackTrace();
			System.err.println("Invalid user id: " + id);
			return null;
		}
	}

	@Override
	public SongRating findSongRatingByUserIdSongId(int userid, int songid) {
		String jpql = "SELECT sr FROM SongRating sr WHERE sr.user.id =:uid and sr.song.id =:sid";

		return em.createQuery(jpql, SongRating.class).setParameter("uid",userid ).setParameter("sid", songid).getSingleResult();
		 
	}
	/* ----------------------------------------------------------------------------
		sortedByRating
	---------------------------------------------------------------------------- */
	@Override
	public List<SongRating> sortedByRating(int songId, boolean ascendingOrder) {
		String jpql = "SELECT sr FROM SongRating sr WHERE sr.song.id = :id ORDER BY sr.rating";
		
		if (ascendingOrder) {
			jpql += " ASC";
			
		} else {
			jpql += " DESC";
			
		}
		
		List<SongRating> songRatings = em.createQuery(jpql, SongRating.class)
				.setParameter("id",songId)
				.getResultList();
		if (songRatings == null) {
			songRatings = new ArrayList<>();
		}
		
		return songRatings;
	}
	
	@Override
	public List<SongRating> sortedByRating(int songId, boolean ascendingOrder, int numberOfEntriesToShow) {
		String jpql = "SELECT sr FROM SongRating sr WHERE sr.song.id = :id ORDER BY sr.rating";
		
		if (ascendingOrder) {
			jpql += " ASC";
			
		} else {
			jpql += " DESC";
			
		}
		
		List<SongRating> songRatings = em.createQuery(jpql, SongRating.class)
				.setParameter("id",songId)
				.getResultList();
		if (songRatings != null && numberOfEntriesToShow > 0) {
			if (!songRatings.isEmpty()) {
				if (numberOfEntriesToShow > songRatings.size()) {
					numberOfEntriesToShow = songRatings.size();
				}
				
				songRatings = songRatings.subList(0, numberOfEntriesToShow);
			}
			
		} else {
			songRatings = new ArrayList<>();
			
		}
		
		
		return songRatings;
	}

	@Override
	public SongRating findSongRatingById(int id) {
		return em.find(SongRating.class, id);
	}

	@Override
	public List<SongRating> sortSongRatingsByRatingDate(boolean ascendingOrder, int numberOfRatingsToShow) {
		String jpql = "SELECT sr FROM SongRating sr ORDER BY sr.ratingDate";

		if (ascendingOrder) {
			jpql += " ASC";

		} else {
			jpql += " DESC";

		}

		List<SongRating> ratings = em.createQuery(jpql, SongRating.class).getResultList();
		if (ratings != null && numberOfRatingsToShow > 0) {
			if (!ratings.isEmpty()) {
				if (numberOfRatingsToShow > ratings.size()) {
					numberOfRatingsToShow = ratings.size();
				}

				ratings = ratings.subList(0, numberOfRatingsToShow);
			}

		} else {
			ratings = new ArrayList<>();

		}

		return ratings;
	}

	public List<SongRating> sortSongRatingsByRatingDate(boolean ascendingOrder) {
		String jpql = "SELECT sr FROM SongRating sr ORDER BY sr.ratingDate";

		if (ascendingOrder) {
			jpql += " ASC";

		} else {
			jpql += " DESC";

		}

		List<SongRating> ratings = em.createQuery(jpql, SongRating.class).getResultList();
		if (ratings == null) {
			ratings = new ArrayList<>();
		}

		return ratings;
	}


}
