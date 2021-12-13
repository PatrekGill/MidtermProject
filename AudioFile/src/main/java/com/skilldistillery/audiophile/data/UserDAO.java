package com.skilldistillery.audiophile.data;

import java.util.List;

import com.skilldistillery.audiophile.entities.User;

public interface UserDAO {

	User findByUsername(String username);
	User findUserById(int id);
	List<User> sortUsersByCreationDate(boolean ascendingOrder);
	List<User> sortUsersByCreationDate(boolean ascendingOrder, int numberOfUsersToShow);
}
