package com.database.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.database.entity.Roles;

public interface IRolesDao extends JpaRepository<Roles, String> {

}
