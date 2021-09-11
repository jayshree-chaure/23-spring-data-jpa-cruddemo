package com.greenapex.springboot.cruddemo.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.greenapex.springboot.cruddemo.dao.AssessmentRepository;
import com.greenapex.springboot.cruddemo.entity.Assessment;

@Service
public class AssessmentServiceImpl implements AssessmentService {
	private AssessmentRepository assessmentRepository;

	@Autowired
	public AssessmentServiceImpl(AssessmentRepository theassessmentRepository) 
	{
		assessmentRepository = theassessmentRepository;
	}

	public List<Assessment> findAll()
	{
		return assessmentRepository.findAll();
	}

	public Assessment findById(int theId) 
	{
		Optional<Assessment> result = assessmentRepository.findById(theId);

		Assessment theAssessment = null;
		if(result.isPresent())
			theAssessment = result.get();
		else
			throw new RuntimeException("Didn't find Assessment with Id "+theId);

		return theAssessment;
	}

	public Assessment save(Assessment theAssessment) 
	{
		return assessmentRepository.save(theAssessment);
	}

	public void deleteById(int theId)
	{
		assessmentRepository.deleteById(theId);
	}

}
