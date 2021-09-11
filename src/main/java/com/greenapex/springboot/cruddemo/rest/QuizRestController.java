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

import com.greenapex.springboot.cruddemo.entity.Quiz;
import com.greenapex.springboot.cruddemo.entity.User;
import com.greenapex.springboot.cruddemo.service.QuizService;
import com.greenapex.springboot.cruddemo.service.QuizServiceImpl;
import com.greenapex.springboot.cruddemo.service.UserService;

@RestController
@RequestMapping("/api")
public class QuizRestController {

	private QuizService quizService;

	public QuizRestController(QuizService quizService) 
	{
		this.quizService = quizService;
	}
	
	
	@GetMapping("/quizes")
	public List<Quiz> findAllQuiz()
	{
		return quizService.findAll();
	}
	
	@GetMapping("quizes/{quizId}")
	public Quiz getQuiz(@PathVariable int quizId)
	{
		Quiz theQuiz = quizService.findById(quizId);
		
		if(theQuiz == null)
			throw new RuntimeException("Quiz Id is not found "+ quizId);
		
		return theQuiz;
	}

	@PostMapping("/quizes")
	public Quiz saveQuiz(@RequestBody Quiz theQuiz)
	{
		theQuiz.setQuizId(0);
		quizService.save(theQuiz);

		return theQuiz;
	}

	@PutMapping("/quizes")
	public Quiz updateQuiz(@RequestBody Quiz theQuiz)
	{
		quizService.save(theQuiz);

		return theQuiz;
	}
	
	@DeleteMapping("/quizes/{quizId}")
	public String deleteQuiz(@PathVariable int quizId)
	{
		Quiz theQuiz = quizService.findById(quizId);
		
		if(theQuiz == null)
			return "User not found with ID " +quizId;
		
		quizService.deleteById(quizId);
		
		return "Quiz is deleted with Id "+ quizId;	
	}
}