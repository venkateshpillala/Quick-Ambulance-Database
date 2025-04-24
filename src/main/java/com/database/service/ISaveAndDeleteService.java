package com.database.service;

import com.database.dto.DriverDTO;
import com.database.dto.UserDTO;

public interface ISaveAndDeleteService {
	
	public String saveUser(UserDTO user);
	public String deleteUser(String username);
	public String saveDriver(DriverDTO driver);
	public String deleteDriver(String username);

}
