package com.skilldistillery.audiophile.entities;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AlbumRatingTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private AlbumRating albumrating;

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
		albumrating = em.find(AlbumRating.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		albumrating =null;
	}

	@Test
	void test() {
		assertNotNull(albumrating);
		assertEquals("nice album", albumrating.getDescription());
		assertEquals(2018, albumrating.getRatingdate().getYear());
		assertEquals(3, albumrating.getRating());
		assertEquals(2021, albumrating.getUser().getCreationDateTime().getYear());
		assertEquals("A1A", albumrating.getAlbum().getTitle());
	}

}
