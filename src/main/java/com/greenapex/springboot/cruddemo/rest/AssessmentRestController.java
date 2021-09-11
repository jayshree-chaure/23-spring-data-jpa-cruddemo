package com.greenapex.springboot.cruddemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greenapex.springboot.cruddemo.entity.Assessment;
import com.greenapex.springboot.cruddemo.service.AnswerService;
import com.greenapex.springboot.cruddemo.service.AssessmentService;
import com.greenapex.springboot.cruddemo.service.QuestionService;
import com.greenapex.springboot.cruddemo.service.QuizService;
import com.greenapex.springboot.cruddemo.service.UserService;

@RestController
@RequestMapping("/api")
public class AssessmentRestController {

	private AssessmentService assessmentService;
	private UserService userService;
	private QuizService quizService;
	private QuestionService questionService;
	private static AnswerService answerService;

	@Autowired
	public AssessmentRestController(AssessmentService assessmentService, UserService userService, QuizService quizService,
			QuestionService questionService, AnswerService answerService) 
	{
		this.assessmentService = assessmentService;
		this.userService = userService;
		this.quizService = quizService;
		this.questionService = questionService;
		this.answerService = answerService;
	}

	@GetMapping("/assessments")
	public List<Assessment> findAll()
	{
		List<Assessment> assessments = assessmentService.findAll();
		for(Assessment assessment : assessments)
		{
			assessment.setCorrect(getResult(assessment));
		}
		
		return assessments;
	}
	
	@GetMapping("/assessmentsQuiz")
	public List<Assessment> findQuiz()
	{
		List<Assessment> assessments = assessmentService.findAll();
		for(Assessment assessment : assessments)
		{
			assessment.setCorrect(getResult(assessment));
		}
		
		return assessments;
	}

	@GetMapping("assessments/{assessmentId}")
	public Assessment findId(@PathVariable int assessmentId)
	{
		Assessment theAssessment = assessmentService.findById(assessmentId);

		if(theAssessment == null)
			throw new RuntimeException("Assessment Id is not found "+ assessmentId);
		
		theAssessment.setCorrect(getResult(theAssessment));

		return theAssessment;
	}
	
	@GetMapping("assessmentsQuiz/{assessmentId}")
	public Assessment findQuizes(@PathVariable int assessmentId)
	{
		Assessment theAssessment = assessmentService.findById(assessmentId);

		if(theAssessment == null)
			throw new RuntimeException("Assessment Id is not found "+ assessmentId);
		
		theAssessment.setCorrect(getResult(theAssessment));

		return theAssessment;
	}

	@PostMapping("/assessments")
	public Assessment saveAssessment(@RequestBody Assessment theAssessment)
	{
		theAssessment.setId(0);
		theAssessment.setCorrect(getResult(theAssessment));
		assessmentService.save(theAssessment);

		return theAssessment;
	}

	@PutMapping("/assessments")
	public Assessment updateAssessment(@RequestBody Assessment theAssessment)
	{
		theAssessment.setCorrect(getResult(theAssessment));
		assessmentService.save(theAssessment);

		return theAssessment;
	}

	@DeleteMapping("/assessments/{assessmentId}")
	public String deleteAssessment(@PathVariable int assessmentId)
	{
		Assessment theAssessment = assessmentService.findById(assessmentId);

		if(theAssessment == null)
			return "Assessment not found with ID " +assessmentId;

		assessmentService.deleteById(assessmentId);

		return "Assessment is deleted with Id "+ assessmentId;	
	}

	public static boolean getResult(Assessment assessment)
	{
		return answerService.findById(assessment.getQuestionId()).getAnswer().equalsIgnoreCase(assessment.getAnswer()) ? true : false;
	}
}
