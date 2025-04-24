package com.database.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.database.dto.UserDTO;
import com.database.entity.User;
import com.database.service.ISaveAndDeleteService;
import com.database.service.IUserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private ISaveAndDeleteService sadUserService;
	
	@Autowired
	private IUserService userService;
	
	@PostMapping
	public ResponseEntity<String> saveUser(@RequestBody UserDTO userDto){
		String status = sadUserService.saveUser(userDto);
		return new ResponseEntity<String>(status, HttpStatus.OK);
	}
	
	@DeleteMapping
	public ResponseEntity<String> deleteUser(@RequestParam String username){
		String status = sadUserService.deleteUser(username);
		return new ResponseEntity<String>(status, HttpStatus.OK);
	}
	
	@GetMapping("/find-all")
	public ResponseEntity<List<User>> findAllUsers(){
		List<User> list = userService.findAllUserDetails();
		return new ResponseEntity<List<User>>(list, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<User> findByUsername(@RequestParam String username){
		User user = userService.findUserByUsername(username);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@PatchMapping
	public ResponseEntity<String> updateUserDetails(@RequestBody User user){
		String status = userService.updateUserDetailsByUsername(user);
		return new ResponseEntity<String>(status, HttpStatus.OK);
	}
	
}
