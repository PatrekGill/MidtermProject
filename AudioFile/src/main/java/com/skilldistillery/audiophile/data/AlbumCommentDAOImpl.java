package com.skilldistillery.audiophile.data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.skilldistillery.audiophile.entities.AlbumComment;


@Repository
@Transactional
@Service
public class AlbumCommentDAOImpl implements AlbumCommentDAO {

	@PersistenceContext
	private EntityManager em;
	

	/* ----------------------------------------------------------------------------
		findByUsername
	---------------------------------------------------------------------------- */
	@Override
	public List<AlbumComment> findByUsername(String username) {
		String jpql = "SELECT ac FROM AlbumComment ac WHERE ac.user.username LIKE :n";

		return em.createQuery(jpql, AlbumComment.class)
				.setParameter("n", "%" + username + "%")
				.getResultList();
	}
	
	
	/* ----------------------------------------------------------------------------
		findCommentReplys
	---------------------------------------------------------------------------- */
	@Override
	public List<AlbumComment> findCommentReplys(int originalCommentId) {
		String jpql = "SELECT ac FROM AlbumComment ac WHERE ac.inReplyTo = :n";
		
		return em.createQuery(jpql, AlbumComment.class)
				.setParameter("n",originalCommentId)
				.getResultList();
	}
	

	/* ----------------------------------------------------------------------------
		findAlbumCommentByUserId
	---------------------------------------------------------------------------- */
	@Override
	public AlbumComment findAlbumCommentByUserId(int id) {
		String jpql = "SELECT ac FROM AlbumComment ac WHERE ac.user.id =:n";

		try {
			return em.createQuery(jpql, AlbumComment.class)
					.setParameter("n", id)
					.getSingleResult();
		} catch (Exception e) {
			System.err.println("Invalid user name: " + id);
			return null;
		}
	}
	

	/* ----------------------------------------------------------------------------
		findByComment
	---------------------------------------------------------------------------- */
	@Override
	public List<AlbumComment> findByComment(String comment) {
		String jpql = "SELECT ac FROM AlbumComment ac WHERE ac.comment LIKE :n";

		return em.createQuery(jpql, AlbumComment.class)
				.setParameter("n", "%" + comment + "%")
				.getResultList();
	}

	
	/* ----------------------------------------------------------------------------
		findAlbumCommentById
	---------------------------------------------------------------------------- */
	@Override
	public AlbumComment findAlbumCommentById(int id) {
		return em.find(AlbumComment.class, id);
	}
	

	/* ----------------------------------------------------------------------------
		findByAlbumId
	---------------------------------------------------------------------------- */
	@Override
	public List <AlbumComment> findByAlbumId(int id) {
		String jpql = "SELECT ac FROM AlbumComment ac WHERE ac.album.id =:n";

		return em.createQuery(jpql, AlbumComment.class).setParameter("n", id).getResultList();
	}
	
	
	
	/* ----------------------------------------------------------------------------
		sortAlbumCommentsByCommentDate
	---------------------------------------------------------------------------- */
	@Override
	public List<AlbumComment> sortAlbumCommentsByCommentDate(boolean ascendingOrder, int numberOfCommentsToShow) {
		String jpql = "SELECT ac FROM AlbumComment ac ORDER BY ac.commentDate";

		if (ascendingOrder) {
			jpql += " ASC";

		} else {
			jpql += " DESC";

		}

		List<AlbumComment> albumComments = em.createQuery(jpql, AlbumComment.class).getResultList();
		if (albumComments != null && numberOfCommentsToShow > 0) {
			if (!albumComments.isEmpty()) {
				if (numberOfCommentsToShow > albumComments.size()) {
					numberOfCommentsToShow = albumComments.size();
				}

				albumComments = albumComments.subList(0, numberOfCommentsToShow);
			}

		} else {
			albumComments = new ArrayList<>();

		}

		return albumComments;
	}
	@Override
	public List<AlbumComment> sortAlbumCommentsByCommentDate(int albumId, boolean ascendingOrder, int numberOfEntries) {
		String jpql = "SELECT ac FROM AlbumComment ac WHERE ac.album.id = :id ORDER BY ac.commentDate";
		
		if (ascendingOrder) {
			jpql += " ASC";
			
		} else {
			jpql += " DESC";
			
		}
		
		List<AlbumComment> albumComments = em.createQuery(jpql, AlbumComment.class)
				.setParameter("id", albumId)
				.getResultList();
		if (albumComments != null && numberOfEntries > 0) {
			if (!albumComments.isEmpty()) {
				if (numberOfEntries > albumComments.size()) {
					numberOfEntries = albumComments.size();
				}
				
				albumComments = albumComments.subList(0, numberOfEntries);
			}
			
		} else {
			albumComments = new ArrayList<>();
			
		}
		
		return albumComments;
	}
	
	
	@Override
	public List<AlbumComment> sortAlbumCommentsByCommentDate(boolean ascendingOrder) {
		String jpql = "SELECT ac FROM AlbumComment ac ORDER BY ac.commentDate";

		if (ascendingOrder) {
			jpql += " ASC";

		} else {
			jpql += " DESC";

		}

		List<AlbumComment> albumComments = em.createQuery(jpql, AlbumComment.class).getResultList();
		if (albumComments == null) {
			albumComments = new ArrayList<>();
		}

		return albumComments;
	}
	@Override
	public List<AlbumComment> sortAlbumCommentsByCommentDate(int albumId, boolean ascendingOrder) {
		String jpql = "SELECT ac FROM AlbumComment ac WHERE ac.album.id = :id ORDER BY ac.commentDate";
		
		if (ascendingOrder) {
			jpql += " ASC";
			
		} else {
			jpql += " DESC";
			
		}
		
		List<AlbumComment> albumComments = em.createQuery(jpql, AlbumComment.class)
				.setParameter("id", albumId)
				.getResultList();
		if (albumComments == null) {
			albumComments = new ArrayList<>();
		}
		
		return albumComments;
	}
	
	

	/* ----------------------------------------------------------------------------
		findByUsername
	---------------------------------------------------------------------------- */
	@Override
	public boolean updateComment(int id, String newComment) {
		boolean updated = false;
		
		AlbumComment managedAlbumComment = em.find(AlbumComment.class, id);
		if (managedAlbumComment != null) {
			managedAlbumComment.setComment(newComment);
			updated = true;
		}
		
		return updated;
	}
	

	/* ----------------------------------------------------------------------------
		findByUsername
	---------------------------------------------------------------------------- */
	@Override
	public AlbumComment createAlbumComment(AlbumComment albumComment) {
		em.persist(albumComment);
		em.flush();
		
		return albumComment;
	}

	
	/* ----------------------------------------------------------------------------
		findByUsername
	---------------------------------------------------------------------------- */
	@Override
	public boolean deleteAlbumComment(int id) {
		AlbumComment managedAlbumComment = em.find(AlbumComment.class,id);
		boolean deleted = false;
		
		if (managedAlbumComment != null) {
			em.remove(managedAlbumComment);
			
			managedAlbumComment = em.find(AlbumComment.class,id);
			if (managedAlbumComment == null) {
				deleted = true;
			}
		}
		
		
		return deleted;
	}

}
