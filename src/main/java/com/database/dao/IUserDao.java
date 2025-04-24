package com.database.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.database.entity.User;

public interface IUserDao extends JpaRepository<User, String> {

}
