package com.skilldistillery.audiophile.data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.apache.log4j.chainsaw.Main;
import org.springframework.stereotype.Repository;

import com.skilldistillery.audiophile.entities.SongRating;
import com.skilldistillery.audiophile.entities.User;

@Repository
@Transactional
public class SongRatingDAOImpl implements SongRatingDAO {
	
	@PersistenceContext
	private EntityManager em;
	static UserDAOImpl userDAOImpl = new UserDAOImpl();
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
}
