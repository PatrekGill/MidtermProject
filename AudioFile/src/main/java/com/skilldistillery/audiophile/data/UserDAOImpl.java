package com.skilldistillery.audiophile.data;

import java.util.ArrayList;
import java.util.List;

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
	
	/* ----------------------------------------------------------------------------
		findByUsername
	---------------------------------------------------------------------------- */
	@Override
	public User findByUsername(String username) {
		String jpql = "SELECT u from User u where u.username = :n";
		
		try {
			return em.createQuery(jpql,User.class).setParameter("n",username).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Invalid user name: " + username);
			return null;
		}
	}
	
	
	/* ----------------------------------------------------------------------------
		findUserById
	---------------------------------------------------------------------------- */
	@Override
	public User findUserById(int id) {
		return (User)em.find(User.class, id);
	}
	

	/* ----------------------------------------------------------------------------
		sortUsersByCreationDate
	---------------------------------------------------------------------------- */
	@Override
	public List<User> sortUsersByCreationDate(boolean ascendingOrder, int numberOfUsersToShow) {
		String jpql = "SELECT u FROM User u ORDER BY u.creationDateTime";
		
		if (ascendingOrder) {
			jpql += " ASC";
			
		} else {
			jpql += " DESC";
			
		}
		
		List<User> users = em.createQuery(jpql, User.class).getResultList();
		if (users != null && numberOfUsersToShow > 0) {
			if (!users.isEmpty()) {
				if (numberOfUsersToShow > users.size()) {
					numberOfUsersToShow = users.size();
				}
				
				users = users.subList(0, numberOfUsersToShow);
			}
			
		} else {
			users = new ArrayList<>();
			
		}
		
		return users;
	}
	public List<User> sortUsersByCreationDate(boolean ascendingOrder) {
		String jpql = "SELECT u FROM User u ORDER BY u.creationDateTime";
		
		if (ascendingOrder) {
			jpql += " ASC";
			
		} else {
			jpql += " DESC";
			
		}
		
		List<User> users = em.createQuery(jpql, User.class).getResultList();
		if (users == null) {
			users = new ArrayList<>();
		}
		
		return users;
	}

}
