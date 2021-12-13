package com.skilldistillery.audiophile.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.skilldistillery.audiophile.entities.Album;

@SpringBootTest
class AlbumDAOImplTest {

	@Autowired
	private AlbumDAOImpl albumDAO;
	
	@Test
	@DisplayName("Test find albums by created user")
	void test1() {
		assertNotNull(albumDAO);
		List<Album> a = albumDAO.findAlbumsByCreatedUsername("admin");
		assertNotNull(a);
		assertTrue(a.size() > 0);
		
	}
	
	@Test
	@DisplayName("test find album by id")
	void test2() {
		assertNotNull(albumDAO);
		Album a = albumDAO.findAlbumById(1);
		assertNotNull(a);
		assertEquals("A1A", a.getTitle());
		
	}
	
	@Test
	@DisplayName("test find album by title")
	void test3() {
		assertNotNull(albumDAO);
		Album a = albumDAO.findAlbumByTitle("A1A");
		assertNotNull(a);
		assertEquals("jimmy buffett", a.getArtist().getName());
	}
	
	@Test
	@DisplayName("test find album by song title")
	void test4() {
		assertNotNull(albumDAO);
		Album a = albumDAO.findAlbumBySongTitle("Door Number Three");
		assertNotNull(a);
		assertEquals("A1A", a.getTitle());
		
	}

	@Test
	@DisplayName("test find albums by artist")
	void test5() {
		assertNotNull(albumDAO);
		List<Album> a = albumDAO.findAlbumsByArtistName("Jimmy");
		assertNotNull(a);
		assertTrue(a.size() > 0);
	}
	
	@Test
	@DisplayName("test find albums by genre")
	void test6() {
		List<Album> a = albumDAO.findAlbumsByGenre("Country");
		assertNotNull(a);
		assertTrue(a.size() > 0);
	}
	
	@Test
	@DisplayName("test find albums by average rating")
	void test7() {
		List<Album> a = albumDAO.sortAlbumsByRating();
		assertNotNull(a);
		assertTrue(a.size() > 0);
	}
	

}
