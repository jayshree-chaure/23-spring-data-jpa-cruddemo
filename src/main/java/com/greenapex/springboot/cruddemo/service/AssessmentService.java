package com.greenapex.springboot.cruddemo.service;

import java.util.List;

import com.greenapex.springboot.cruddemo.entity.Assessment;

public interface AssessmentService {
	
	public Assessment findById(int userId);

	public void deleteById(int userId);

	public List<Assessment> findAll();

	public Assessment save(Assessment theAssessment);

}
