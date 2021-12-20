package com.skilldistillery.audiophile.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.skilldistillery.audiophile.entities.Genre;

@SpringBootTest
class GenreDAOImplTest {
	@Autowired
	private GenreDAOImpl genreDAO;
	
	@Test
	@DisplayName("Test find genre by id")
	void test1() {
		assertNotNull(genreDAO);
		Genre g = genreDAO.findGenreById(1);
		assertNotNull(g);
		assertEquals(g.getName(), "country");
		
	}
	@Test
	@DisplayName("Test find genre by name")
	void test2() {
		assertNotNull(genreDAO);
		Genre g = genreDAO.findGenreByName("country");
		assertNotNull(g);
		assertEquals(g.getId(), 1);
	}
	

	@Test
	void test_sortByName() {
		List<Genre> genres = genreDAO.sortByName(true);
		assertNotNull(genres);
		assertTrue(genres.size() > 0);
//		assertEquals(genres.get(0).getName(),"country");
	}
}
