package com.skilldistillery.audiophile.data;

import java.util.List;

import com.skilldistillery.audiophile.entities.User;

public interface UserDAO {
	User findByUsername(String username);
	User findUserById(int id);
	List<User> sortUsersByCreationDate(boolean ascendingOrder);
	List<User> sortUsersByCreationDate(boolean ascendingOrder, int numberOfUsersToShow);
	
	boolean updateUsername(int id, String newName);
	boolean updatePassword(int id, String newPassword);
	boolean updateFirstName(int id, String newName);
	boolean updateLastName(int id, String newName);
	boolean updateEmail(int id, String newName);
	boolean updateUserImage(int id, String newURL);
	boolean updateEnabled(int id, boolean newSetting);
	boolean updateUser(int id, User user);
	boolean deleteUser(int id);
	User createUser(User user);
	User login(String username, String password);
}
