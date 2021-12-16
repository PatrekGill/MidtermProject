package com.skilldistillery.audiophile.data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.skilldistillery.audiophile.entities.AlbumRating;

@Repository
@Transactional
@Service
public class AlbumRatingDAOImpl implements AlbumRatingDAO {
	
	@PersistenceContext
	private EntityManager em;

	/* ----------------------------------------------------------------------------
		findByUsername
	---------------------------------------------------------------------------- */
	@Override
	public AlbumRating findById(int id) {
		return em.find(AlbumRating.class,id);
	}

	
	/* ----------------------------------------------------------------------------
		sortedByRating
	---------------------------------------------------------------------------- */
	@Override
	public List<AlbumRating> sortedByRating(int albumId, boolean ascendingOrder) {
		String jpql = "SELECT ar FROM AlbumRating ar WHERE ar.album.id = :id ORDER BY ar.rating";
		
		if (ascendingOrder) {
			jpql += " ASC";
			
		} else {
			jpql += " DESC";
			
		}
		
		List<AlbumRating> albumRatings = em.createQuery(jpql, AlbumRating.class)
				.setParameter("id",albumId)
				.getResultList();
		if (albumRatings == null) {
			albumRatings = new ArrayList<>();
		}
		
		return albumRatings;
	}

	@Override
	public Double getAverageAlbumRating(int albumId) {
		String jpql = "SELECT AVG(ar.rating) FROM AlbumRating ar WHERE ar.album.id = :id";
		
		Double avgerageRating = null;
		try {
			avgerageRating = em.createQuery(jpql, Double.class)
					.setParameter("id", albumId)
					.getSingleResult();
		} catch (Exception e) {
			System.err.println("Could not get average for album id: " + albumId);
			avgerageRating = 0.0;
		}
		
		if (avgerageRating == null) {
			avgerageRating = 0.0;
		}
		
		return avgerageRating;
	}
	
	@Override
	public List<AlbumRating> sortedByRating(int albumId, boolean ascendingOrder, int numberOfEntriesToShow) {
		String jpql = "SELECT ar FROM AlbumRating ar WHERE ar.album.id = :id ORDER BY ar.rating";
		
		if (ascendingOrder) {
			jpql += " ASC";
			
		} else {
			jpql += " DESC";
			
		}
		
		List<AlbumRating> albumRatings = em.createQuery(jpql, AlbumRating.class)
				.setParameter("id",albumId)
				.getResultList();
		if (albumRatings != null && numberOfEntriesToShow > 0) {
			if (!albumRatings.isEmpty()) {
				if (numberOfEntriesToShow > albumRatings.size()) {
					numberOfEntriesToShow = albumRatings.size();
				}
				
				albumRatings = albumRatings.subList(0, numberOfEntriesToShow);
			}
			
		} else {
			albumRatings = new ArrayList<>();
			
		}
		
		return albumRatings;
	}


	/* ----------------------------------------------------------------------------
		sortedByRating
	---------------------------------------------------------------------------- */
	@Override
	public List<AlbumRating> sortedByCreatationDate(int albumId, boolean ascendingOrder) {
		String jpql = "SELECT ar FROM AlbumRating ar WHERE ar.album.id = :id ORDER BY ar.ratingDate";
		
		if (ascendingOrder) {
			jpql += " ASC";
			
		} else {
			jpql += " DESC";
			
		}
		
		List<AlbumRating> albumRatings = em.createQuery(jpql, AlbumRating.class)
				.setParameter("id",albumId)
				.getResultList();
		if (albumRatings == null) {
			albumRatings = new ArrayList<>();
		}
		
		return albumRatings;
	}

	@Override
	public List<AlbumRating> sortedByCreatationDate(int albumId, boolean ascendingOrder, int numberOfEntriesToShow) {
		String jpql = "SELECT ar FROM AlbumRating ar WHERE ar.album.id = :id ORDER BY ar.ratingDate";
		
		if (ascendingOrder) {
			jpql += " ASC";
			
		} else {
			jpql += " DESC";
			
		}
		
		List<AlbumRating> albumRatings = em.createQuery(jpql, AlbumRating.class)
				.setParameter("id",albumId)
				.getResultList();
		if (albumRatings != null && numberOfEntriesToShow > 0) {
			if (!albumRatings.isEmpty()) {
				if (numberOfEntriesToShow > albumRatings.size()) {
					numberOfEntriesToShow = albumRatings.size();
				}
				
				albumRatings = albumRatings.subList(0, numberOfEntriesToShow);
			}
			
		} else {
			albumRatings = new ArrayList<>();
			
		}
		
		return albumRatings;
	}


	/* ----------------------------------------------------------------------------
		updateRating
	---------------------------------------------------------------------------- */
	@Override
	public boolean updateRating(int id, int newRating) {
		boolean updated = false;
		if (newRating > 0 && newRating <= 10) {
			AlbumRating managedAlbumRating = em.find(AlbumRating.class, id);
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
			AlbumRating managedAlbumRating = em.find(AlbumRating.class, id);
			managedAlbumRating.setDescription(newDescription);
			
			updated = true;
		}
			
		return updated;
	}


	/* ----------------------------------------------------------------------------
		createAlbumRating
	---------------------------------------------------------------------------- */
	@Override
	public AlbumRating createAlbumRating(AlbumRating albumRating) {
		em.persist(albumRating);
		em.flush();
		
		return albumRating;
	}
	
	
	/* ----------------------------------------------------------------------------
		deleteAlbumRating
	---------------------------------------------------------------------------- */
	@Override
	public boolean deleteAlbumRating(int id) {
		AlbumRating managedAlbumRating = em.find(AlbumRating.class, id);
		boolean deleted = false;
		
		if (managedAlbumRating != null) {
			em.remove(managedAlbumRating);
			
			managedAlbumRating = em.find(AlbumRating.class, id);
			if (managedAlbumRating == null) {
				deleted = true;
			}
		}
		
		
		return deleted;
	}
}
