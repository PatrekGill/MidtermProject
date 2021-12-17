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
import com.skilldistillery.audiophile.entities.Song;

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
		assertEquals("Jimmy Buffett", a.getArtist().getName());
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
		List<Album> a = albumDAO.findAlbumsByGenreName("Country");
		assertNotNull(a);
		assertTrue(a.size() > 0);
	}
	
	@Test
	@DisplayName("test find albums by average rating")
	void test7() {
		List<Album> a = albumDAO.sortAlbumsByRating(true);
		assertNotNull(a);
		assertTrue(a.size() > 0);
	}
	
	@Test
	@DisplayName("Test songs from album")
	void test8() {
		List<Song> s = albumDAO.getSongsFromAlbum(albumDAO.findAlbumById(2));
		assertNotNull(s);
		assertTrue(s.size() > 0);
	}
	
	@Test
	@DisplayName("Test sort albums by rating")
	void test9() {
		List<Album> a = albumDAO.sortAlbumsByRating(false);
		assertNotNull(a);
		assertTrue(a.size() > 0);
	}
	@Test
	@DisplayName("Test sort top 3 albums by rating")
	void test12() {
		List<Album> a = albumDAO.sortAlbumsByRating(false);
		assertNotNull(a);
		assertTrue(a.size() > 0);
		assertEquals("Kind of Blue",a.get(0).getTitle());
		assertEquals("2",a.get(1).getTitle());
		assertEquals("21",a.get(2).getTitle());
	}
	
	@Test
	@DisplayName("Test sort albums by creation date")
	void void10(){
		List<Album> a = albumDAO.sortAlbumsByCreateDate(false);
		assertNotNull(a);
		assertTrue(a.size() > 0);
	}
	
	@Test
	@DisplayName("Test getting albums by artist name and sorting them by rating")
	void test11() {
		List<Album> a = albumDAO.findAlbumsByArtistSortByRating(false, "Jimmy Buffett");
		assertNotNull(a);
		assertTrue(a.size() > 0);
	}
	

}
