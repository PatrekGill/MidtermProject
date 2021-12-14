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
	void test_for_find_by_songId() {
		
			assertNotNull(DaoImp.findBySongId(2));
			
		}
	@Test
	void test_for_find_by_songName() {
		
		assertNotNull(DaoImp.findBySongName("Dallass"));
		
	}
	@Test
	void test_for_sort_by_Create_date() {
		
		assertNotNull(DaoImp.sortByCreatDate());
		
	}
	@Test
	void test_for_sort_by_update_date() {
		
		assertNotNull(DaoImp.sortByUpdateTime());
		
	}
	
	@Test
	void test_for_find_by_Artist_Name() {
		
		assertNotNull(DaoImp.findByArtistName("jimmy buffett") );
		assertNotNull(DaoImp.findByArtistName("jimmy buffett").get(1).getAlbum());
		
	}
	@Test
	void test_for_find_by_Album_Name() {
		
		assertNotNull(DaoImp.findByAlbumName("A1A"));
		
	}
	@Test
	void test_for_find_by_Song_Rating() {
		
		assertNotNull(DaoImp.findSongByRating(3));
		
	}
	@Test
	void test_for_sort_by_Song_Rating() {
		
		assertNotNull(DaoImp.sortBySongRating());
		
	}
	@Test
	void test_for_find_by_Song_lyrics_keyword() {
		
		assertNotNull(DaoImp.findByLyricsKeyword("you"));
		
	}
	
	
	
	

	
}


