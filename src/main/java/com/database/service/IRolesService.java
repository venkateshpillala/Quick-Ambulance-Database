package com.database.service;

import java.util.List;

import com.database.entity.Roles;

public interface IRolesService {

	
	//Save roles details, if success returns username, otherwise null
	public String saveRoles(Roles role);
	
	//Get role details by username, if exists returns details otherwise null
	public Roles findRolesByUsername(String username);
	
	//Get all the Roles details, if exists returns details, otherwise null
	public List<Roles> findAllRolesDetails();
	
	//Update password, if succeed returns username, otherwise null
	public String updatePasswordByUsername(Roles role);
	
	//Delete a Role by username, if succeed returns username, otherwise returns null
	public String deleteRoleByUsername(String username);
	
}
