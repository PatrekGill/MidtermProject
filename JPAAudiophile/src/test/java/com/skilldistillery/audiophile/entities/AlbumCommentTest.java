package com.skilldistillery.audiophile.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AlbumCommentTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private AlbumComment albumcomment;
	
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
		albumcomment = em.find(AlbumComment.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		albumcomment =null;
	}

	@Test
	void test_AlbumCUserId_basic_mappings() {
		assertNotNull(albumcomment);
		assertEquals(1, albumcomment.getUserId());
	}
	@Test
	void test_AlbumAlbumId_basic_mappings() {
		assertNotNull(albumcomment);
		assertEquals(1, albumcomment.getAlbumId());
	}
	
	@Test
	void test_AlbumCComment_basic_mappings() {
		assertNotNull(albumcomment);
		assertEquals("I love it", albumcomment.getComment());
}	
	@Test
	void test_AlbumCtCommentDate_basic_mappings() {
		assertNotNull(albumcomment);
		assertEquals(2018, albumcomment.getCommentDate().getYear());
		assertEquals(01, albumcomment.getCommentDate().getMonthValue());
		assertEquals(01, albumcomment.getCommentDate().getDayOfMonth());
}
	//Final test not implemented due to null value within DB
//	@Test
//	void test_AlbumCInReplyTobasic_mappings() {
//		assertNotNull(albumcomment);
//		assertEquals("null", albumcomment.getInReplyTo());
//}
}
