package com.greenapex.springboot.cruddemo.rest;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.greenapex.springboot.cruddemo.entity.Answer;
import com.greenapex.springboot.cruddemo.entity.Question;
import com.greenapex.springboot.cruddemo.entity.Quiz;
import com.greenapex.springboot.cruddemo.entity.User;
import com.greenapex.springboot.cruddemo.service.QuizService;

@TestMethodOrder(OrderAnnotation.class)
@ComponentScan(basePackages ="com.greenapex.springboot.cruddemo")
@AutoConfigureMockMvc
@ContextConfiguration
@SpringBootTest(classes = {QuizRestControllerTest.class})
public class QuizRestControllerTest {
	
	@Autowired
	MockMvc mockmvc;
	
	@Mock 
	QuizService quizService;
	
	@InjectMocks
	QuizRestController quizRestController;
	@BeforeEach
	public void setUp() {
		mockmvc=MockMvcBuilders.standaloneSetup(quizRestController).build();
	}
	
	@Test
	@Order(1)
	public void  findAllQuizTest() throws Exception {
		ArrayList<Question> que =new ArrayList<Question>();
		Question question = new Question("why",new Answer("anwer"));
     	que.add(question);
		List<Quiz> quiz = new ArrayList<Quiz>();
		quiz.add(new Quiz("quiz1",que));
		
		when(quizService.findAll()).thenReturn(quiz);
		when(quizService.findAll()).thenReturn(quiz);
		this.mockmvc.perform(get("/api/quizes"))
		.andExpect(status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(quiz.size()))
		.andDo(print());



	}

	@Test
	@Order(2)
	public void  getQuizTest() {
		ArrayList<Question> que =new ArrayList<Question>();
		Question question = new Question("Which of the following is not a Java features? a. Dynamic b. Architecture Neutral c. Use of pointers d. Object-oriented\r\n"
				+ "",new Answer("b"));
		que.add(question);
		Quiz quiz = new Quiz("Java", que);
		quiz.setQuizId(1);


		when(quizService.findById(1)).thenReturn(quiz);

		Quiz actual = quizRestController.getQuiz(1);
		Quiz expected =  quiz;

		verify(quizService).findById(1);
		
		assertEquals(expected,actual);

	}

	@Test
	@Order(3)
	public void saveQuizTest() throws Exception {
		ArrayList<Question> que =new ArrayList<Question>();
		Question question = new Question("Which of the following is not a Java features? a. Dynamic b. Architecture Neutral c. Use of pointers d. Object-oriented\r\n"
				+ "",new Answer("c"));
     	que.add(question);
		Quiz quiz = new Quiz("Java", que);
		
		quiz.setQuizId(0);
        
		when(quizService.save(quiz)).thenReturn(quiz);
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonbody=mapper.writeValueAsString(quiz);
		System.out.println(jsonbody);
		this.mockmvc.perform(post("/api/quizes")
				.content(jsonbody)
				.contentType(MediaType.APPLICATION_JSON)
				)
		.andExpect(status().isOk())
		.andDo(print());

}
	
	@Test
	@Order(4)
	public void updateQuizTest() {
		ArrayList<Question> que =new ArrayList<Question>();
		Question question = new Question("Which of the following is not a Java features? a. Dynamic b. Architecture Neutral c. Use of pointers d. Object-oriented\r\n"
				+ "",new Answer("c"));
     	que.add(question);
		Quiz quiz = new Quiz("Java", que);
		
		quiz.setQuizId(1);

		when(quizService.save(quiz)).thenReturn(quiz);
		
		Quiz actual = quizRestController.updateQuiz(quiz);
		Quiz expected = quiz;
		
		verify(quizService).save(quiz);
		
		assertEquals(expected,actual);

}
	@Test
	@Order(5)
	public void deleteQuizTest() {
		ArrayList<Question> que =new ArrayList<Question>();
		Question question = new Question("Which of the following is not a Java features? a. Dynamic b. Architecture Neutral c. Use of pointers d. Object-oriented\r\n"
				+ "",new Answer("c"));
     	que.add(question);
		Quiz quiz = new Quiz("Java", que);
		
		quiz.setQuizId(1);

		
		when(quizService.findById(1)).thenReturn(quiz);

		quizRestController.deleteQuiz(1);
		
		verify(quizService).findById(1);
		verify(quizService).deleteById(1);
	}
	

}
