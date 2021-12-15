package com.skilldistillery.audiophile.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.skilldistillery.audiophile.entities.AlbumRating;

@SpringBootTest
class AlbumRatingDAOImplTest {
	@Autowired
	private AlbumRatingDAOImpl dao;
	
	@Test
	void test_findById() {
		AlbumRating rating = dao.findById(1);
		assertNotNull(rating);
		assertEquals("nice album", rating.getDescription());
	}
	
	@Test
	void test_sortedByRating_just_order() {
		assertNotNull(dao.sortedByRating(1,true));
		assertEquals("nice album", dao.sortedByRating(1,true).get(0).getDescription());
	}

	@Test
	void test_sortedByRating_number_entities() {
		assertNotNull(dao.sortedByRating(1,true,1));
		assertEquals("nice album", dao.sortedByRating(1,true).get(0).getDescription());
		assertEquals(1, dao.sortedByRating(1,true).size());
	}
	
	@Test
	void test_sortedByRating_number_entities_out_of_bounds() {
		assertNotNull(dao.sortedByRating(1,true,0));
		assertNotNull(dao.sortedByRating(1,true,20));
		assertEquals("nice album", dao.sortedByRating(1,true).get(0).getDescription());
	}

	@Test
	void test_sortedByCreatationDate_just_order() {
		assertNotNull(dao.sortedByCreatationDate(1,true));
		assertEquals("nice album", dao.sortedByCreatationDate(1,true).get(0).getDescription());
	}
	
	@Test
	void test_sortedByCreatationDate_number_entities() {
		assertNotNull(dao.sortedByCreatationDate(1,true,1));
		assertEquals("nice album", dao.sortedByCreatationDate(1,true).get(0).getDescription());
		assertEquals(1, dao.sortedByCreatationDate(1,true).size());
	}
	
	@Test
	void test_sortedByCreatationDate_number_entities_out_of_bounds() {
		assertNotNull(dao.sortedByCreatationDate(1,true,0));
		assertNotNull(dao.sortedByCreatationDate(1,true,20));
		assertEquals("nice album", dao.sortedByCreatationDate(1,true).get(0).getDescription());
	}
	
	@Test
	void test_updateRating() {
		AlbumRating albumRating = dao.findById(1);
		assertNotNull(albumRating);
		
		
		int originalRating = albumRating.getRating();
		assertTrue(
			dao.updateRating(albumRating.getId(), 10)
		);
		assertTrue(
			dao.updateRating(albumRating.getId(), originalRating)
		);
	}
	
	@Test
	void test_updateRating_out_of_range() {
		AlbumRating albumRating = dao.findById(1);
		assertNotNull(albumRating);
		
		assertFalse(
			dao.updateRating(albumRating.getId(), -1)
		);
		assertFalse(
			dao.updateRating(albumRating.getId(), 11)
		);
	}
	
	@Test
	void test_updateDescription() {
		AlbumRating albumRating = dao.findById(1);
		assertNotNull(albumRating);
		
		String newDescription = "Hello World";
		String originalDescription = albumRating.getDescription();
		assertTrue( 
			dao.updateDescription(albumRating.getId(), newDescription) 
		);
		
		assertTrue( 
			dao.updateDescription(albumRating.getId(), originalDescription) 
		);
	}
	
	@Test
	void test_getAverageAlbumRating() {
		Double average = dao.getAverageAlbumRating(1);
		assertNotNull(average);
		assertEquals(average,3.0,1);
		
	}
	
//	@Test
//	void test_createAlbumRating_deleteAlbumRating() {
//		AlbumRating rating = new AlbumRating();
//		rating.setAlbum(dao.findById(1).getAlbum());
//		rating.setDescription("test description");
//		rating.setRating(5);
//		rating.setUser(dao.findById(1).getUser());
//		
//		assertNotNull(dao.createAlbumRating(rating));
//		assertTrue(dao.deleteAlbumRating(rating.getId()));
//		
//	}

}
