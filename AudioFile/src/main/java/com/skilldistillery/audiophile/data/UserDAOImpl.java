package com.skilldistillery.audiophile.data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.skilldistillery.audiophile.entities.User;

@Repository
@Transactional
@Service
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


	/* ----------------------------------------------------------------------------
		updateUser
	---------------------------------------------------------------------------- */
	@Override
	public boolean updateUsername(int id, String newName) {
		boolean updated = false;
		if (newName != null) {
			User managedUser = em.find(User.class,id);
			managedUser.setUsername(newName);
			
			updated = true;
		}
			
		return updated;
	}
	
	
	/* ----------------------------------------------------------------------------
		updateUsername
	---------------------------------------------------------------------------- */
	@Override
	public boolean updatePassword(int id, String newPassword) {
		boolean updated = false;
		if (newPassword != null) {
			User managedUser = em.find(User.class,id);
			managedUser.setPassword(newPassword);
			
			updated = true;
		}
		
		return updated;
	}

	
	/* ----------------------------------------------------------------------------
		updateFirstName
	---------------------------------------------------------------------------- */
	@Override
	public boolean updateFirstName(int id, String newName) {
		boolean updated = false;
		if (newName != null) {
			User managedUser = em.find(User.class,id);
			managedUser.setFirstName(newName);
			
			updated = true;
		}
		
		return updated;
	}
	
	
	/* ----------------------------------------------------------------------------
		updateLastName
	---------------------------------------------------------------------------- */
	@Override
	public boolean updateLastName(int id, String newName) {
		boolean updated = false;
		if (newName != null) {
			User managedUser = em.find(User.class,id);
			managedUser.setLastName(newName);
			
			updated = true;
		}
		
		return updated;
	}
	
	
	/* ----------------------------------------------------------------------------
		updateEmail
	---------------------------------------------------------------------------- */
	@Override
	public boolean updateEmail(int id, String newEmail) {
		boolean updated = false;
		if (newEmail != null) {
			User managedUser = em.find(User.class,id);
			managedUser.setEmail(newEmail);
			
			updated = true;
		}
		
		return updated;
	}
	
	
	/* ----------------------------------------------------------------------------
		updateUserImage
	---------------------------------------------------------------------------- */
	@Override
	public boolean updateUserImage(int id, String newURL) {
		boolean updated = false;
		if (newURL != null) {
			User managedUser = em.find(User.class,id);
			managedUser.setImageURL(newURL);
			
			updated = true;
		}
		
		return updated;
	}
	
	
	/* ----------------------------------------------------------------------------
		updateEnabled
	---------------------------------------------------------------------------- */
	@Override
	public boolean updateEnabled(int id, boolean newSetting) {
		User managedUser = em.find(User.class,id);
		boolean currentSetting = managedUser.isEnabled();
		
		boolean updated = false;
		if (currentSetting != newSetting) {
			managedUser.setEnabled(newSetting);
			
			updated = true;
		}
		
		
		return updated;
	}
	
	
	/* ----------------------------------------------------------------------------
		updateUser
	---------------------------------------------------------------------------- */
	@Override
	public boolean updateUser(int id, User user) {
		User managedUser = em.find(User.class, id);
		
		boolean updated = false;
		if (managedUser != null && user != null) {
			updateUsername(id, user.getUsername());
			updatePassword(id, user.getPassword());
			updateFirstName(id, user.getFirstName());
			updateLastName(id, user.getLastName());
			updateEmail(id, user.getEmail());
			updateUserImage(id, user.getImageURL());
			updateEnabled(id, user.isEnabled());
			
			updated = true;
		}
		
		
		return updated;
	}

	
	/* ----------------------------------------------------------------------------
		deleteUser
	---------------------------------------------------------------------------- */
	@Override
	public boolean deleteUser(int id) {
		User managedUser = em.find(User.class,id);
		boolean deleted = false;
		
		if (managedUser != null) {
			em.remove(managedUser);
			
			managedUser = em.find(User.class,id);
			if (managedUser == null) {
				deleted = true;
			}
		}
		
		
		return deleted;
	}
	
	
	/* ----------------------------------------------------------------------------
		createUser
	---------------------------------------------------------------------------- */
	@Override
	public User createUser(User user) {
		em.persist(user);
		em.flush();
		
		return user;
	}
}
