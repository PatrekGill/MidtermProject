package com.skilldistillery.audiophile.data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.apache.log4j.chainsaw.Main;
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
		String jpql ="SELECT sr FROM SongRating sr WHERE sr.user.username LIKE :n";
		
		try {
			return em.createQuery(jpql,SongRating.class).setParameter("n","%" + username+ "%").getResultList();
		} catch (Exception e) {
			System.err.println("Invalid user name: "+username);
			return null;
		}
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
