package com.skilldistillery.audiophile.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.skilldistillery.audiophile.entities.SongRating;

@SpringBootTest
class SongRatingDAOImplTest {
	
	@Autowired
	SongRatingDAO srDAO;
	
	@Autowired
	SongDAOImpl DaoImp;
	@Test
	void SongRating_FindByUsername_Test() {
		List<SongRating> songRatingList = srDAO.findByUsername("admin");
		assertNotNull(songRatingList);
		assertTrue(songRatingList.size() > 0);
	}

	@Test
	void test() {
		  DaoImp = new SongDAOImpl();
		assertNotNull(DaoImp.findBySongId(2));
		
	}
}
