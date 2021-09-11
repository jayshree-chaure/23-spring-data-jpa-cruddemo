package com.greenapex.springboot.cruddemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greenapex.springboot.cruddemo.entity.User;

public interface UserRepository extends JpaRepository<User,Integer> {
	// This interface will automatically provide the CRUD operations
}
