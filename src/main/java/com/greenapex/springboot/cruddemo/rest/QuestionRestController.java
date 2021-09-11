package com.greenapex.springboot.cruddemo.rest;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.greenapex.springboot.cruddemo.entity.Question;
import com.greenapex.springboot.cruddemo.service.QuestionService;

@RestController
@RequestMapping("/api")
public class QuestionRestController {
	
private QuestionService questionService;
	
	public QuestionRestController(QuestionService questionService) 
	{
		this.questionService = questionService;
	}
	
	@GetMapping("/questions")
	public List<Question> findAll()
	{
		return questionService.findAll();
	}
	
	@GetMapping("questions/{questionId}")
	public Question getQuestion(@PathVariable int questionId)
	{
		Question theQuestion = questionService.findById(questionId);
		
		if(theQuestion == null)
			throw new RuntimeException("Question Id is not found "+ questionId);
		
		return theQuestion;
	}
	
	@PostMapping("/questions")
	public Question saveQuestion(@RequestBody Question theQuestion)
	{
		theQuestion.setQuestionId(0);
		questionService.save(theQuestion);
		
		return theQuestion;
	}
	
	@PutMapping("/questions")
	public Question updateQuestion(@RequestBody Question theQuestion)
	{
		questionService.save(theQuestion);

		return theQuestion;
	}
	
	@DeleteMapping("/questions/{questionId}")
	public String deleteQuestion(@PathVariable int questionId)
	{
		// get the Employee
		Question theQuestion = questionService.findById(questionId);
		
		// throw exception if Employee not found 
		if(theQuestion == null)
			return "Question not found with ID " +questionId;
		
		questionService.deleteById(questionId);
		
		return "Question is deleted with Id "+ questionId;	
	}

}
