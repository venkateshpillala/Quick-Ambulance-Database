package com.database.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.database.dao.IRolesDao;
import com.database.entity.Roles;
import com.database.exception.DuplicateResourceException;
import com.database.exception.ResourceNotFoundException;

@Service
public class RolesServiceImple implements IRolesService {

	@Autowired
	private IRolesDao dao;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public String saveRoles(Roles role) {
		Roles rol = null;
		String status = null;
		
		if (role != null) {
			dao.findById(role.getUsername().toLowerCase()).ifPresent(a-> new DuplicateResourceException("RESOURCE_ALREADY_AVAILABLE_WITH_USERNAME "+role.getUsername()));;
			
			role.setPassword(passwordEncoder.encode(role.getPassword()));
			rol = dao.save(role);
		}
		if(rol != null)
			status = rol.getUsername();
		else
			throw new RuntimeException("SOMETHING_WENT_WRONG_TRY_AGAIN...!");
		return status;
	}

	@Override
	public Roles findRolesByUsername(String username) {
		Roles rol = dao.findById(username.toLowerCase()).orElseThrow(
					()-> new ResourceNotFoundException("RESOURCE_NOT_FOUND_WITH_USERNAME "+username)
				);
		return rol;
	}

	@Override
	public List<Roles> findAllRolesDetails() {
		return dao.findAll();
	}

	@Override
	public String updatePasswordByUsername(Roles role) {
		Roles rol = null;
		String status = null;
		
		if (role != null) {
			rol = dao.findById(role.getUsername().toLowerCase()).orElseThrow(
						()-> new ResourceNotFoundException("RESOURCE_NOT_FOUND_WITH_USERNAME"+role.getUsername())
					);
			if (rol != null) {
				role.setPassword(passwordEncoder.encode(role.getPassword()));
				role.setRole(rol.getRole());
				rol = dao.save(role);
				status = rol.getUsername();
			}
			else
				throw new RuntimeException("SOMETHING_WENT_WRONG_TRY_AGAIN...!");
		}
		return status;
	}

	@Override
	public String deleteRoleByUsername(String username) {
		String status = null;
		if (username != null) {
			Roles rol = dao.findById(username.toLowerCase()).orElseThrow(
					()-> new ResourceNotFoundException("RESOURCE_NOT_FOUND_WITH_USERNAME "+username)
					);
			if (rol != null) {
				dao.delete(rol);
				status = username;
			}
			else 
				throw new RuntimeException("SOMETHING_WENT_WRONG_TRY_AGAIN...!");
		}
		return status;
	}

}
