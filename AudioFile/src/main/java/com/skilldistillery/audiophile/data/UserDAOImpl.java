package com.skilldistillery.audiophile.data;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.skilldistillery.audiophile.entities.User;

@Repository
@Transactional
public class UserDAOImpl implements UserDAO {
	
	@PersistenceContext
	private EntityManager em;
	@Override
	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		String jpql ="SELECT u from User u where u.username =:n";
		
		try {
			return em.createQuery(jpql,User.class).setParameter("n",username).getSingleResult();
		} catch (Exception e) {
			System.err.println("Invalid user name: "+username);
			return null;
		}
	}

}
