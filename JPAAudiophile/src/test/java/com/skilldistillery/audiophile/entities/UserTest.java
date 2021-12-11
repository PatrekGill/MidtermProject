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

class UserTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private User user;
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
		user = em.find(User.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		user = null;
	}

	@Test
	void test_User_username_mapping() {
		assertNotNull(user);
		assertEquals("admin", user.getUsername());
	}
	
	@Test
	void test_User_password_mapping() {
		assertNotNull(user);
		assertEquals("admin", user.getPassword());
	}
	
	@Test
	void test_User_firstName_mapping() {
		assertNotNull(user);
		assertEquals("Kings", user.getFirstName());
	}
	
	@Test
	void test_User_lastName_mapping() {
		assertNotNull(user);
		assertEquals("Jam", user.getLastName());
	}

	@Test
	void test_User_ImageUrl_mapping() {
		assertNotNull(user);
		assertEquals("Look at this awesome pose!", user.getImageURL());
	}
	
	@Test
	void test_User_Email_mapping() {
		assertNotNull(user);
		assertEquals("admin@gmail.com", user.getEmail());
	}
	
	@Test
	void test_User_Enabled_mapping() {
		assertNotNull(user);
		assertTrue(user.isEnabled());
	}
	
//	@Test
//	void test_User_Role_mapping() {
//		assertNotNull(user);
//		assertEquals("", user.getRole());
//	}
	
	@Test
	void test_User_CreationDateTime_mapping() {
		assertNotNull(user);
		assertNotNull(user.getCreationDateTime());
		
		assertEquals(2021, user.getCreationDateTime().getYear());
		assertEquals(9, user.getCreationDateTime().getMonthValue());
		assertEquals(21, user.getCreationDateTime().getDayOfMonth());
	}
	
	@Test
	void test_favoritedAlbums_mapping() {
		assertNotNull(user);
		assertNotNull(user.getFavoriteAlbums());
		
		assertFalse(user.getFavoriteAlbums().isEmpty());
		assertEquals(1,user.getFavoriteAlbums().get(0).getId());
		assertEquals("A1A",user.getFavoriteAlbums().get(0).getTitle());
	}
	
	@Test
	void test_favoritedSongs_mapping() {
		assertNotNull(user);
		assertNotNull(user.getFavoriteSongs());
		
		assertFalse(user.getFavoriteSongs().isEmpty());
		assertEquals(2,user.getFavoriteSongs().get(0).getId());
		assertEquals("Door Number Three",user.getFavoriteSongs().get(0).getName());
	}

}
