package com.database.service;

import java.util.List;

import com.database.entity.User;

public interface IUserService {

	//Save user details, if succeed returns username, otherwise returns null
	public String saveUserDetails(User user);
	
	//Get User details by username, if existed returns details, otherwise null
	public User findUserByUsername(String username);
	
	//Get all user details, if existed returns details, otherwise null
	public List<User> findAllUserDetails();
	
	//Update user details by username, if succeed returns username, otherwise null
	public String updateUserDetailsByUsername(User user);
	
	//Delete user details by username, if succeed returns username, otherwise null
	public String deleteUserByUsername(String username);
}
