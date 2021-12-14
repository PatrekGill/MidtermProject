package com.skilldistillery.audiophile.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.skilldistillery.audiophile.entities.AlbumComment;

@SpringBootTest
class AlbumCommentDAOImplTest {
	
	@Autowired
	AlbumCommentDAO acDAO;
	
	@Autowired
	AlbumCommentDAOImpl DaoImp;
	@Test
	void AlbumComment_FindByUsername_Test() {
		List<AlbumComment> albumCommentList = acDAO.findByUsername("admin");
		assertNotNull(albumCommentList);
		assertTrue(albumCommentList.size() > 0);
	}
	
	@Test
	void findAlbumCommentByUserId_Test() {
		assertNotNull(acDAO);
		assertNotNull(acDAO.findAlbumCommentByUserId(1));
		assertEquals(1, acDAO.findAlbumCommentByUserId(1).getId());
	}
	
	@Test
	void FindAlbumCommentById_Test() {
		assertNotNull(acDAO);
		assertNotNull(acDAO.findAlbumCommentById(1));
		assertEquals(1, acDAO.findAlbumCommentById(1).getId());
	}
	
	@Test
	void test_sortAlbumCommentsByCreationDate_just_order() {
		assertNotNull(acDAO);
		assertNotNull(acDAO.sortAlbumCommentByCommentDate(true));
		assertEquals("I love it", acDAO.sortAlbumCommentByCommentDate(true).get(0).getComment());
		
	}
	
	@Test
	void test_sortAlbumCommentsByCreationDate_number_of_users() {
		assertNotNull(acDAO);
		assertNotNull(acDAO.sortAlbumCommentByCommentDate(true,1));
		assertEquals("I love it", acDAO.sortAlbumCommentByCommentDate(true,1).get(0).getComment());
		assertEquals(1, acDAO.sortAlbumCommentByCommentDate(true,1).size());
		
	}
	
	@Test
	void test_sortAlbumCommentsByCreationDate_out_of_bounds() {
		assertNotNull(acDAO);
		assertNotNull(acDAO.sortAlbumCommentByCommentDate(true,0));
		assertNotNull(acDAO.sortAlbumCommentByCommentDate(true,20));
		assertEquals("I love it", acDAO.sortAlbumCommentByCommentDate(true,20).get(0).getComment());
		
	}
	
	@Test
	void test_findByAlbumComment_Mappings() {
		assertNotNull(acDAO);
		assertNotNull(acDAO.findByComment(null).size() > 0);
	}

}
