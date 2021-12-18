package com.skilldistillery.audiophile.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.skilldistillery.audiophile.entities.Artist;

@SpringBootTest
class ArtistDAOImplTest {

	@Autowired
	ArtistDAOImpl DAOImp;

	@Test
	void test_findById() {
		assertNotNull(DAOImp.findById(1));
		assertEquals("Jimmy Buffett",DAOImp.findById(1).getName());
	}

	@Test
	void test_findByArtistsName() {
		assertNotNull(DAOImp.findByArtistsName("Jimmy Buffett"));
		assertEquals(DAOImp.findByArtistsName("Jimmy Buffett").get(0).getName(), "Jimmy Buffett");
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
	void test_get_topThree_artist_() {
		List<Artist> a = DAOImp.getTopThreeArtist(false);
		assertNotNull(a);
		assertTrue(a.size()>0);
		assertEquals("Miles Davis",a.get(0).getName());
		assertEquals("Mac DeMarco",a.get(1).getName());
		assertEquals("Adele",a.get(2).getName());
		assertEquals("Jimmy Buffett",a.get(3).getName());
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
		assertEquals(DAOImp.findPrimaryArtistByAlbumName("A1A").getName(), "Jimmy Buffett");
	}
	
	@Test
	void test_sortArtistsAlphabetically() {
		List<Artist> a = DAOImp.sortArtistsAlphabetically();
		assertNotNull(a);
		assertTrue(a.size() > 0);
	}

}
