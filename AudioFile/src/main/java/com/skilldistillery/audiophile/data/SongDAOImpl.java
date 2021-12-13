package com.skilldistillery.audiophile.data;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.skilldistillery.audiophile.entities.Song;
import com.skilldistillery.audiophile.entities.User;

@Repository
@Transactional
public class SongDAOImpl implements SongDAO {
	@PersistenceContext
	private EntityManager em;
//	@Override
//	public  findByUsername(String username) {
//		// TODO Auto-generated method stub
//		String jpql ="SELECT u from User u where u.username =:n";
//		
//		try {
//			return em.createQuery(jpql,User.class).setParameter("n",username).getSingleResult();
//		} catch (Exception e) {s
//			System.err.println("Invalid user name: "+username);
//			return null;
//		}
//	}
	@Override
	public Song findBySongId(int id) {
		String jpql ="SELECT s FROM Song s where s.id = :id";
		try {
			return em.createQuery(jpql,Song.class)
					 .setParameter("id", id)
					 .getSingleResult();
		} catch (Exception e) {
			System.err.println("Invalid Song id: "+id);
			return null;
		}
	}
	@Override
	public List<Song> findBySongName(String name) {

		String jpql ="SELECT s FROM Song s where s.name = :songName";
		List<Song> songs;
		songs = em.createQuery(jpql,Song.class)
				  .setParameter("songName", name)
				  .getResultList();
		return songs;
	}
	@Override
	public List<Song> sortByCreatDate(LocalDateTime songDate) {
		String jpql ="SELECT s FROM Song s order by createDate";
		List<Song> songs;
		songs = em.createQuery(jpql,Song.class)
				  .getResultList();
		return songs;
	}
	@Override
	public List<Song> findByArtistName(String artistName) {
		String jpql = "SELECT a.songs FROM Artist a where a.name = :artName";
		
		return null;
	}
	
	
}
