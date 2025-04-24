package com.database.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.database.dao.IUserDao;
import com.database.entity.User;
import com.database.exception.DuplicateResourceException;
import com.database.exception.ResourceNotFoundException;

/*
 * This class provides all the services of User database
 */

@Service
public class UserServiceImple implements IUserService {

	
	@Autowired
	private IUserDao dao;

	@Override
	public String saveUserDetails(User user) {
		User use = null;
		String status = null;
		
		if(user != null) {
			dao.findById(user.getUsername().toLowerCase()).ifPresent(
						a->{throw new DuplicateResourceException("RESOURCE_AVAILABLE_WITH_USERNAME "+user.getUsername());
						});
			
			use = dao.save(user);
		}
		if(use != null) {
			status = use.getUsername();
		}
		else
			throw new RuntimeException("SOMETHING_WENT_WRONG_TRY_AGAIN...!");
		return status;
	}

	@Override
	public User findUserByUsername(String username) {
		User use = dao.findById(username.toLowerCase()).orElseThrow(
					()-> new ResourceNotFoundException("RESOURCE_NOT_EXISTED_WITH_USERNAME "+username)
				);
		return use;
	}

	@Override
	public List<User> findAllUserDetails() {
		return dao.findAll();
	}

	@Override
	public String updateUserDetailsByUsername(User user) {
		String status = null;
		User use = this.updateNullValues(user);
		if(use != null) {
			user = dao.save(use);
			status = user.getUsername();
		}
		else
			throw new RuntimeException("SOMETHING_WENT_WRONG_TRY_AGAIN...!");
		
		return status;
	}

	@Override
	public String deleteUserByUsername(String username) {
		String status = null;
		
		if(username != null){
			User user = dao.findById(username.toLowerCase()).orElseThrow(
						()-> new ResourceNotFoundException("RESOURCE_NOT_EXISTED_WITH_USERNAME "+username)
					);
			if(user != null) {
				dao.delete(user);
				status = username;
			}
			else
				throw new RuntimeException("SOMETHING_WENT_WRONG_TRY_AGAIN...!");
		}
		return status;
	}
	
	private User updateNullValues(User user) {
		User use = dao.findById(user.getUsername().toLowerCase()).orElseThrow(
				()-> new ResourceNotFoundException("RESOURCE_NOT_EXISTED_WITH_USERNAME "+user.getUsername())
			);
		if(use != null) {
			if(user.getEmail() == null)
				user.setEmail(use.getEmail());
			if(user.getFullName() == null)
				user.setFullName(use.getFullName());
			if(user.getPhone() == null)
				user.setPhone(use.getPhone());
			if(user.getUsername() == null)
				user.setUsername(use.getUsername().toLowerCase());
		}
		return user;
	}

}
