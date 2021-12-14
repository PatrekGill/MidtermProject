package com.skilldistillery.audiophile.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.skilldistillery.audiophile.entities.AlbumRating;

@SpringBootTest
class AlbumRatingDAOImplTest {
	@Autowired
	private AlbumRatingDAOImpl dao;
	
	@Test
	void test_findByUsername() {
		AlbumRating rating = dao.findById(1);
		assertNotNull(rating);
		assertEquals("nice album", rating.getDescription());
		
	}

}
