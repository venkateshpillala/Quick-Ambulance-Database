package com.database.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.database.dto.DriverDTO;
import com.database.dto.UserDTO;
import com.database.entity.Driver;
import com.database.entity.Roles;
import com.database.entity.User;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class SaveAndDeleteServiceImple implements ISaveAndDeleteService {

	@Autowired
	private IUserService userService;

	@Autowired
	private IDriverService driverService;

	@Autowired
	private IRolesService roleService;

	private String status;

	private Driver driver;
	private User user;
	private Roles role;

	@Override
	@Transactional
	public String saveUser(UserDTO userDto) {

		user = new User();
		role = new Roles();

		String use = null;
		String rol = null;

		if (userDto != null) {
			user.setEmail(userDto.getEmail());
			user.setFullName(userDto.getFullName());
			user.setPhone(userDto.getPhone());
			user.setUsername(userDto.getUsername().toLowerCase());

			role.setPassword(userDto.getPassword());
			role.setUsername(userDto.getUsername().toLowerCase());
			role.setRole("USER");

			use = userService.saveUserDetails(user);
			rol = roleService.saveRoles(role);
		}

		if ((use != null && rol != null) && use.equals(rol))
			status = use;

		return status;
	}

	@Override
	@Transactional
	public String saveDriver(DriverDTO driverDto) {
		driver = new Driver();
		role = new Roles();
		String driv = null;
		String rol = null;

		if (driverDto != null) {
			driver.setDob(driverDto.getDob());
			driver.setDriverName(driverDto.getDriverName());
			driver.setEmail(driverDto.getEmail());
			driver.setPhone(driverDto.getPhone());
			driver.setStatus(false);
			driver.setUsername(driverDto.getUsername().toLowerCase());
			driver.setLicense(this.convertIntoBytesMultipartFile(driverDto.getLicense()));
			
			role.setPassword(driverDto.getPassword());
			role.setUsername(driverDto.getUsername().toLowerCase());
			role.setRole("DRIVER");
			
			driv = driverService.saveDriverDetails(driver);
			rol = roleService.saveRoles(role);
		}
		
		if((rol != null && driv != null) && rol.equals(driv))
			status = rol;

		return status;
	}

	@Override
	@Transactional
	public String deleteUser(String username) {
		String use = userService.deleteUserByUsername(username.toLowerCase());
		String rol = roleService.deleteRoleByUsername(username.toLowerCase());
		if ((use != null && rol != null) && (use.equals(rol)))
			status = use;
		return status;
	}

	@Override
	@Transactional
	public String deleteDriver(String username) {
		String driv = driverService.deleteDriverByUsername(username.toLowerCase());
		String rol = roleService.deleteRoleByUsername(username.toLowerCase());
		if ((driv != null && rol != null) && (driv.equals(rol)))
			status = driv;
		return status;
	}

	private byte[] convertIntoBytesMultipartFile(MultipartFile file) {
		byte[] binary = null;
		try {
			binary = file.getBytes();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return binary;
	}

}
