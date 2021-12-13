package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.skilldistillery.audiophile.data.SongDAOImpl;

@SpringBootTest
class TempSongTest {
	@Autowired
	SongDAOImpl DaoImp;

	@Test
	void test() {
		  DaoImp = new SongDAOImpl();
		assertNotNull(DaoImp.findBySongName("Dallas"));
		
	}

}
