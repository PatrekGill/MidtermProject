package com.skilldistillery.audiophile.data;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ArtistDAPOImplTest {
	
	@Autowired
	ArtistDAOImpl DAOImp;

	@Test
	void test_find_by_Artist_id() {
		
		assertNotNull(DAOImp.findArtistById(1));
	}
	@Test
	void test_find_by_Artist_name() {
		
		assertNotNull(DAOImp.findByArtistName("jimmy buffett"));
	}
	@Test
	void test_sort_by_Createt_date() {
		
		assertNotNull(DAOImp.sortByCreateDate());
	}
	@Test
	void test_find_by_Song_Name() {
		
		assertNotNull(DAOImp.findArtistBySongName("Dallas"));
	}
	@Test
	void test_find_by_songID() {
		
		assertNotNull(DAOImp.findArtistById(2));
	}

}
