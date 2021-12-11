package com.skilldistillery.audiophile.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SongTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Song song;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf=Persistence.createEntityManagerFactory("JPAAudiophile");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf.close();
	}

	@BeforeEach
	void setUp() throws Exception {
		em = emf.createEntityManager();
		song = em.find(Song.class, 2);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		song =null;
	}
	
	
	@Test
	@DisplayName("test song mapping")
	void test1() {
		assertNotNull(song);
		assertEquals("Stories We Could Tell", song.getName());
		assertTrue(song.getLyrics().contains("Talkin' to myself againTalkin' to myself again"));
		assertEquals(198, song.getDurationInSeconds());
		assertEquals(1, song.getUser().getId());
	}
	
	@Test
	@DisplayName("test song to artist mapping")
	void test2 () {
		assertNotNull(song);
		assertNotNull(song.getArtists());
		assertTrue(song.getArtists().size() > 0);
	}
	
	@Test
	@DisplayName("test song to album mapping")
	void test3() {
		assertNotNull(song);
		assertNotNull(song.getAlbum());
		assertEquals("A1A", song.getAlbum().getTitle());
		assertEquals(1974, song.getAlbum().getReleaseDate().getYear());
		assertEquals(12, song.getAlbum().getReleaseDate().getMonthValue());
		assertEquals(01, song.getAlbum().getReleaseDate().getDayOfMonth());
	}
	
	@Test
	@DisplayName("test song to user mapping")
	void test4() {
		assertNotNull(song);
		assertNotNull(song.getUser());
		assertEquals("admin", song.getUser().getUsername());
		assertEquals("admin@gmail.com", song.getUser().getEmail());
	}
	@Test
	@DisplayName("test song to create date ")
	void test5() {
		assertNotNull(song);
		assertNotNull(song.getUser());
		assertEquals("admin", song.getUser().getUsername());
		assertEquals(5, song.getCreateDate().getDayOfMonth());
		assertEquals(2019, song.getCreateDate().getYear());
		assertEquals(6, song.getCreateDate().getMonthValue());
	}

	@Test
	void test_favoritedBy_user_to_song_mapping() {
		assertNotNull(song);
		assertNotNull(song.getFavoritedBy());
		
		assertFalse(song.getFavoritedBy().isEmpty());
		assertEquals(1,song.getFavoritedBy().get(0).getId());
		assertEquals("admin",song.getFavoritedBy().get(0).getUsername());
	}
}
