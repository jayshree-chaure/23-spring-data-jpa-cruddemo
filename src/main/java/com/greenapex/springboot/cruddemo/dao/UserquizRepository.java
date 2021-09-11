package com.greenapex.springboot.cruddemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greenapex.springboot.cruddemo.entity.Userquiz;

public interface UserquizRepository extends JpaRepository<Userquiz,Integer> {
	// This interface will automatically provide the CRUD operations
}

