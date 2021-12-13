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
	
	
	@Test
	void SongRating_FindByUsername_Test() {
		List<SongRating> songRatingList = srDAO.findByUsername("admin");
		assertNotNull(songRatingList);
		assertTrue(songRatingList.size() > 0);
	}

	
	
	@Test
	void findSongRatingByUserId_Test() {
		assertNotNull(srDAO);
		assertNotNull(srDAO.findSongRatingByUserId(1));
		assertEquals(1, srDAO.findSongRatingByUserId(1).getId());
	}
	
	@Test
	void FindBySongRatingById_Test() {
		assertNotNull(srDAO);
		assertNotNull(srDAO.findSongRatingById(1));
		assertEquals(1, srDAO.findSongRatingById(1).getId());
	}
	
	@Test
	void test_sortSongRatingsByCreationDate_just_order() {
		assertNotNull(srDAO);
		assertNotNull(srDAO.sortSongRatingsByRatingDate(true));
		assertEquals(3, srDAO.sortSongRatingsByRatingDate(true).get(0).getRating());
		
	}
	
	@Test
	void test_sortSongRatingsByCreationDate_number_of_users() {
		assertNotNull(srDAO);
		assertNotNull(srDAO.sortSongRatingsByRatingDate(true,1));
		assertEquals(3, srDAO.sortSongRatingsByRatingDate(true,1).get(0).getRating());
		assertEquals(1, srDAO.sortSongRatingsByRatingDate(true,1).size());
		
	}
	
	@Test
	void test_sortSongRatingsByCreationDate_out_of_bounds() {
		assertNotNull(srDAO);
		assertNotNull(srDAO.sortSongRatingsByRatingDate(true,0));
		assertNotNull(srDAO.sortSongRatingsByRatingDate(true,20));
		assertEquals(3, srDAO.sortSongRatingsByRatingDate(true,20).get(0).getRating());
		
	}
	
	@Test
	void test_sortByRating_Mappings() {
		assertNotNull(srDAO);
		assertNotNull(srDAO.sortByRating(0, true).size() > 0);
	}
	

//	@Test
//	void test_findUserById_expect_admin() {
//		assertNotNull(srDAO);
//		assertNotNull(srDAO.sortSongRatingsByRatingDate(1));
//		assertEquals("admin",srDAO.sortSongRatingsByRatingDate(1).getRating());
//		
//	}

//	@Test
//	void test() {
//		  DaoImp = new SongDAOImpl();
//		assertNotNull(DaoImp.findBySongId(2));
//		
//	}
}
