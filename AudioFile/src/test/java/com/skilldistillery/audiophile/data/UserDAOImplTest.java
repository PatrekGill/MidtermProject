package com.skilldistillery.audiophile.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserDAOImplTest {
	@Autowired
	private UserDAOImpl userDAO;
	
	@Test
	void test_findByUsername() {
		assertNotNull(userDAO);
		assertNotNull(userDAO.findByUsername("admin"));
		assertEquals(1, userDAO.findByUsername("admin").getId());
		
	}
	
	@Test
	void test_sortUsersByCreationDate_just_order() {
		assertNotNull(userDAO);
		assertNotNull(userDAO.sortUsersByCreationDate(true));
		assertEquals("admin", userDAO.sortUsersByCreationDate(true).get(0).getUsername());
		
	}
	
	@Test
	void test_sortUsersByCreationDate_number_of_users() {
		assertNotNull(userDAO);
		assertNotNull(userDAO.sortUsersByCreationDate(true,1));
		assertEquals("admin", userDAO.sortUsersByCreationDate(true,1).get(0).getUsername());
		assertEquals(1, userDAO.sortUsersByCreationDate(true,1).size());
		
	}
	
	@Test
	void test_sortUsersByCreationDate_out_of_bounds() {
		assertNotNull(userDAO);
		assertNotNull(userDAO.sortUsersByCreationDate(true,0));
		assertNotNull(userDAO.sortUsersByCreationDate(true,20));
		assertEquals("admin", userDAO.sortUsersByCreationDate(true,20).get(0).getUsername());
		
	}

	@Test
	void test_findUserById_expect_admin() {
		assertNotNull(userDAO);
		assertNotNull(userDAO.findUserById(1));
		assertEquals("admin",userDAO.findUserById(1).getUsername());
		
	}
	
	@Test
	void test_findUserById_expect_null() {
		assertNotNull(userDAO);
		assertNull(userDAO.findUserById(0));
	}
	
//	@Test
//	void test_createUser_deleteUser() {
//		User user = new User();
//		user.setUsername("testUsername");
//		user.setPassword("testPassword");
//		user.setFirstName("Hello");
//		user.setLastName("World");
//		user.setImageURL("www.test.com/image.jpg");
//		user.setEmail("testUser@gmail.com");
//		user.setEnabled(true);
//		
//		assertNotNull(userDAO.createUser(user));
//		assertTrue(userDAO.deleteUser(user.getId()));
//	}
}
