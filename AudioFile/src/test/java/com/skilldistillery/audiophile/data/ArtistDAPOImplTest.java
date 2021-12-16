package com.skilldistillery.audiophile.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ArtistDAPOImplTest {

	@Autowired
	ArtistDAOImpl DAOImp;

	@Test
	void test_findById() {
		assertNotNull(DAOImp.findById(1));
		assertEquals("jimmy buffett",DAOImp.findById(1).getName());
	}

	@Test
	void test_findByArtistsName() {
		assertNotNull(DAOImp.findByArtistsName("jimmy buffett"));
		assertEquals(DAOImp.findByArtistsName("jimmy buffett").get(0).getName(), "jimmy buffett");
	}

	@Test
	void test_sortByCreateDate() {
		assertNotNull(DAOImp.sortByCreateDate());
		assertFalse(DAOImp.sortByCreateDate().isEmpty());
	}

	@Test
	void test_sort_by_Update_date() {
		assertNotNull(DAOImp.sortByUpdateTime());
		assertFalse(DAOImp.sortByUpdateTime().isEmpty());
	}

	@Test
	void test_findArtistsBySongName() {
		assertNotNull(DAOImp.findArtistsBySongName("Dallas"));
		assertFalse(DAOImp.findArtistsBySongName("Dallas").isEmpty());
	}

	@Test
	void test_findArtistsBySongid() {
		assertNotNull(DAOImp.findArtistsBySongid(2));
		assertFalse(DAOImp.findArtistsBySongid(2).isEmpty());
	}

	@Test
	void test_findPrimaryArtistByAlbumName() {
		assertNotNull(DAOImp.findPrimaryArtistByAlbumName("A1A"));
		assertEquals(DAOImp.findPrimaryArtistByAlbumName("A1A").getName(), "jimmy buffett");
	}
	

}
