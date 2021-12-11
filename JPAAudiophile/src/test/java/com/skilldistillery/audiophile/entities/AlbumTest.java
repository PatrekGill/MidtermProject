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
import org.junit.jupiter.api.Test;

class AlbumTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Album album;
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
		album = em.find(Album.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		album =null;
	}

	@Test
	void test_AlbumTitle_basic_mappings() {
		assertNotNull(album);
		assertEquals("A1A", album.getTitle());
	}
	@Test
	void test_AlbumDescription_basic_mappings() {
		assertNotNull(album);
		assertTrue(album.getDescription().contains("A1A"));
	}
	
	@Test
	void test_AlbumReleaseDate_basic_mappings() {
		assertNotNull(album);
		assertEquals(1974, album.getReleaseDate().getYear());
		assertEquals(12, album.getReleaseDate().getMonthValue());
		assertEquals(01, album.getReleaseDate().getDayOfMonth());
	}
	
	@Test
	void test_AlbumImageURL_basic_mappings() {
		assertNotNull(album);
		assertTrue(album.getImageURL().contains("upload.wikimedia.org"));
	}
	
	@Test
	void test_UserId_basic_mappings() {
		assertNotNull(album);
		assertEquals(1, album.getUserId());
	}
	
	@Test
	void test_favoritedBy_mapping() {
		assertNotNull(album);
		assertNotNull(album.getFavoritedBy());
		
		assertFalse(album.getFavoritedBy().isEmpty());
		assertEquals(1,album.getFavoritedBy().get(0).getId());
		assertEquals("admin",album.getFavoritedBy().get(0).getUsername());
	}
}
