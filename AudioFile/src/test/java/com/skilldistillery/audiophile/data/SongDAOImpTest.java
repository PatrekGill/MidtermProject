package com.skilldistillery.audiophile.data;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SongDAOImpTest {

	@Autowired
	SongDAOImpl DaoImp;

	@Test
	void test_findById() {
		assertNotNull(DaoImp.findById(2));
		assertEquals(DaoImp.findById(2).getName(), "Door Number Three");

	}

	@Test
	void test_findBySongName() {
		assertNotNull(DaoImp.findBySongName("Dallas"));
		assertEquals(DaoImp.findBySongName("Dallas").get(0).getName(), "Dallas");

	}

	@Test
	void test_sortByCreatDate() {
		assertNotNull(DaoImp.sortByCreatDate());
		assertFalse(DaoImp.sortByCreatDate().isEmpty());
	}

	@Test
	void test_sortByUpdateTime() {
		assertNotNull(DaoImp.sortByUpdateTime());
		assertFalse(DaoImp.sortByUpdateTime().isEmpty());
	}

	@Test
	void test_findByArtistName() {
		assertNotNull(DaoImp.findByArtistName("jimmy buffett"));
		assertNotNull(DaoImp.findByArtistName("jimmy buffett").get(1).getAlbum());

	}

	@Test
	void test_findByAlbumName() {
		assertNotNull(DaoImp.findByAlbumName("A1A"));
		assertEquals(DaoImp.findByAlbumName("A1A").get(0).getName(), "Making Music for Money");

	}

	@Test
	void test_findSongByRating() {
		assertNotNull(DaoImp.findSongsByRating(3));
		assertFalse(DaoImp.findSongsByRating(3).isEmpty());
	}

	@Test
	void test_sortBySongRating() {
		assertNotNull(DaoImp.sortBySongRating());
		assertFalse(DaoImp.sortBySongRating().isEmpty());
	}

	@Test
	void test_findByLyricsKeyword() {
		assertNotNull(DaoImp.findByLyricsKeyword("you"));
		assertFalse(DaoImp.findByLyricsKeyword("you").isEmpty());

	}

}
