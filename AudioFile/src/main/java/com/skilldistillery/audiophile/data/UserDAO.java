package com.skilldistillery.audiophile.data;

import com.skilldistillery.audiophile.entities.User;

public interface UserDAO {

	User findByUsername (String username);
}
