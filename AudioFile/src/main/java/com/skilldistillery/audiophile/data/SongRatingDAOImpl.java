package com.skilldistillery.audiophile.data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.skilldistillery.audiophile.entities.SongRating;
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
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Invalid user id: " + id);
			return null;
		}
	}

	
	@Override
	public SongRating findSongRatingByUserIdSongId(int userId, int songId) {
		String jpql = "SELECT sr FROM SongRating sr WHERE sr.user.id =:uid and sr.song.id =:sid";
		
		SongRating rating = null;
		try {
			rating = em.createQuery(jpql, SongRating.class)
					.setParameter("uid", userId)
					.setParameter("sid", songId)
					.getSingleResult();
		}catch (Exception e) {
			System.out.println("Could not find rating for song: " + songId + " from user id: " + userId);
		}
		
		return rating;
		

	}

	/*
	 * ----------------------------------------------------------------------------
	 * sortedByRating
	 * ----------------------------------------------------------------------------
	 */
	@Override
	public List<SongRating> sortedByRating(int songId, boolean ascendingOrder) {
		String jpql = "SELECT sr FROM SongRating sr WHERE sr.song.id = :id ORDER BY sr.rating";

		if (ascendingOrder) {
			jpql += " ASC";

		} else {
			jpql += " DESC";

		}

		List<SongRating> songRatings = em.createQuery(jpql, SongRating.class).setParameter("id", songId)
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

		List<SongRating> songRatings = em.createQuery(jpql, SongRating.class).setParameter("id", songId)
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
	public List<SongRating> sortSongRatingsByRatingDateAndLimitOutput(boolean ascendingOrder, int numberOfRatingsToShow) {
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

	@Override
	public List<SongRating> sortRatingByCreationDateFindBySongId(int songId, boolean ascendingOrder) {
		String jpql = "SELECT sr FROM SongRating sr WHERE sr.song.id = :id ORDER BY sr.ratingDate";

		if (ascendingOrder) {
			jpql += " ASC";

		} else {
			jpql += " DESC";

		}

		List<SongRating> albumRatings = em.createQuery(jpql, SongRating.class).setParameter("id", songId)
				.getResultList();
		if (albumRatings == null) {
			albumRatings = new ArrayList<>();
		}

		return albumRatings;
	}

	@Override
	public Double getAverageSongRating(int songId) {
		String jpql = "SELECT AVG(sr.rating) FROM SongRating sr WHERE sr.song.id = :id";

		Double avgerageRating = null;
		try {
			avgerageRating = em.createQuery(jpql, Double.class).setParameter("id", songId).getSingleResult();
		} catch (Exception e) {
			System.err.println("Could not get average for album id: " + songId);
			avgerageRating = 0.0;
		}

		if (avgerageRating == null) {
			avgerageRating = 0.0;
		}

		return avgerageRating;
	}

	/* ----------------------------------------------------------------------------
	updateRating
---------------------------------------------------------------------------- */
	@Override
	public boolean updateRating(int id, int newRating) {
		boolean updated = false;
		if (newRating > 0 && newRating <= 10) {
			SongRating managedAlbumRating = em.find(SongRating.class, id);
			managedAlbumRating.setRating(newRating);
			
			updated = true;
		}
			
		return updated;
	}

	/* ----------------------------------------------------------------------------
	updateDescription
---------------------------------------------------------------------------- */
	@Override
	public boolean updateDescription(int id, String newDescription) {
		boolean updated = false;
		if (newDescription != null) {
			SongRating managedSongRating = em.find(SongRating.class, id);
			managedSongRating.setDescription(newDescription);
			
			updated = true;
		}
			
		return updated;
	}
	/* ----------------------------------------------------------------------------
	createSongRating
---------------------------------------------------------------------------- */
	@Override
	public SongRating createSongRating(SongRating songRating) {
		em.persist(songRating);
		em.flush();
		
		return songRating;
	}

	/* ----------------------------------------------------------------------------
	deleteSongRating
---------------------------------------------------------------------------- */
	@Override
	public boolean deleteAlbumRating(int id) {
		SongRating managedSongRating = em.find(SongRating.class, id);
		boolean deleted = false;
		
		if (managedSongRating != null) {
			em.remove(managedSongRating);
			
			managedSongRating = em.find(SongRating.class, id);
			if (managedSongRating == null) {
				deleted = true;
			}
		}
		
		
		return deleted;
	}

}
