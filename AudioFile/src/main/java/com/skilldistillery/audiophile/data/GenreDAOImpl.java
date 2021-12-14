package com.skilldistillery.audiophile.data;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.skilldistillery.audiophile.entities.Album;
import com.skilldistillery.audiophile.entities.Genre;

@Repository
@Transactional
public class GenreDAOImpl implements GenreDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Genre findGenreById(int id) {
		String jpql = "SELECT g FROM Genre g WHERE g.id =:id";

		try {
			return em.createQuery(jpql, Genre.class).setParameter("id", id).getSingleResult();
		} catch (Exception e) {
			System.err.println("Invalid genre id: " + id);
			return null;
		}
	}

	@Override
	public Genre findGenreByName(String genreName) {
		String jpql = "SELECT g FROM Genre g WHERE g.name LIKE :name";

		try {
			return em.createQuery(jpql, Genre.class).setParameter("name", "%" + genreName + "%").getSingleResult();
		} catch (Exception e) {
			System.err.println("No genre found from: " + genreName);
			return null;
		}
	}

}
