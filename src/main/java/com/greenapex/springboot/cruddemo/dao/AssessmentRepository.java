package com.greenapex.springboot.cruddemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greenapex.springboot.cruddemo.entity.Assessment;

@Repository
public interface AssessmentRepository extends JpaRepository<Assessment,Integer>{

}

