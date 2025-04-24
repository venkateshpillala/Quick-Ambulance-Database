package com.database.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.database.entity.Roles;
import com.database.service.IRolesService;

@RestController
@RequestMapping("/roles")
public class RolesController {

	@Autowired
	private IRolesService roleService;
	
	public ResponseEntity<String> updatePassword(Roles role){
		String status = roleService.updatePasswordByUsername(role);
		return new ResponseEntity<String>(status, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<Roles> getRolesByUsername(@RequestParam String username){
		Roles role = roleService.findRolesByUsername(username);
		return new ResponseEntity<Roles>(role, HttpStatus.OK);
	}
	
}
