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
import com.greenapex.springboot.cruddemo.entity.Answer;
import com.greenapex.springboot.cruddemo.service.AnswerService;

@RestController
@RequestMapping("/api")
public class AnswerRestController {
	private AnswerService answerService;


	public AnswerRestController(AnswerService answerService) 
	{
		this.answerService = answerService;
	}

	@GetMapping("/answers")
	public List<Answer> findAll()
	{
		return answerService.findAll();
	}

	@GetMapping("answers/{answerId}")
	public Answer getAnswer(@PathVariable int answerId)
	{
		Answer theAnswer = answerService.findById(answerId);

		if(theAnswer == null)
			throw new RuntimeException("Answer Id is not found "+ answerId);
		return theAnswer;
	}

	@PostMapping("/answers")
	public Answer saveAnswer(@RequestBody Answer theAnswer)
	{
		theAnswer.setAnswerId(0);
		answerService.save(theAnswer);

		return theAnswer;
	}

	@PutMapping("/answers")
	public Answer updateAnswer(@RequestBody Answer theAnswer)
	{
		answerService.save(theAnswer);

		return theAnswer;
	}

	@DeleteMapping("/answers/{answerId}")
	public String deleteAnswer(@PathVariable int answerId)
	{
		Answer theAnswer = answerService.findById(answerId);

		if(theAnswer == null)
			return "User not found with ID " +answerId;

		answerService.deleteById(answerId);

		return "Answer is deleted with Id "+ answerId;	
	}
}
