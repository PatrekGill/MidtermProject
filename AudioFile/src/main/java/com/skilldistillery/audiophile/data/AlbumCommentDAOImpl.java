package com.skilldistillery.audiophile.data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.skilldistillery.audiophile.entities.AlbumComment;


@Repository
@Transactional
public class AlbumCommentDAOImpl implements AlbumCommentDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<AlbumComment> findByUsername(String username) {
		String jpql = "SELECT ac FROM AlbumComment ac WHERE ac.user.username LIKE :n";

		try {
			return em.createQuery(jpql, AlbumComment.class).setParameter("n", "%" + username + "%").getResultList();
		} catch (Exception e) {
			System.err.println("Invalid user name: " + username);
			return null;
		}
	}

	@Override
	public AlbumComment findAlbumCommentByUserId(int id) {
		String jpql = "SELECT ac FROM AlbumComment ac WHERE ac.user.id =:n";

		try {
			return em.createQuery(jpql, AlbumComment.class).setParameter("n", id).getSingleResult();
		} catch (Exception e) {
			System.err.println("Invalid user name: " + id);
			return null;
		}
	}

	@Override
	public List<AlbumComment> findByComment(String albumComment) {
		String jpql = "SELECT ac FROM AlbumComment ac WHERE ac.comment LIKE :n";

		try {
			return em.createQuery(jpql, AlbumComment.class).setParameter("n", "%" + albumComment + "%").getResultList();
		} catch (Exception e) {
			System.err.println("Invalid user name: " + albumComment);
			return null;
		}
	}

	@Override
	public AlbumComment findAlbumCommentById(int id) {
		return em.find(AlbumComment.class, id);
	}

	@Override
	public List <AlbumComment> findByAlbumId(int id) {
		String jpql = "SELECT ac FROM AlbumComment ac WHERE ac.album.id =:n";

		try {
			return em.createQuery(jpql, AlbumComment.class).setParameter("n", id).getResultList();
		} catch (Exception e) {
			System.err.println("Invalid album id: " + id);
			return null;
		}
	}

	@Override
	public List<AlbumComment> sortAlbumCommentByCommentDate(boolean ascendingOrder, int numberOfCommentsToShow) {
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

	public List<AlbumComment> sortAlbumCommentByCommentDate(boolean ascendingOrder) {
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

}
