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
	void test() {
		
			assertNotNull(DaoImp.findBySongId(2));
			
		}
	}


