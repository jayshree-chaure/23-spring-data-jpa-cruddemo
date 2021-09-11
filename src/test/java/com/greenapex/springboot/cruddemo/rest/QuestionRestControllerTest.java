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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.greenapex.springboot.cruddemo.entity.Answer;
import com.greenapex.springboot.cruddemo.entity.Question;
import com.greenapex.springboot.cruddemo.entity.User;
import com.greenapex.springboot.cruddemo.service.QuestionService;

@TestMethodOrder(OrderAnnotation.class)
@ComponentScan(basePackages ="com.greenapex.springboot.cruddemo")
@AutoConfigureMockMvc
@ContextConfiguration
@SpringBootTest(classes = {QuestionRestControllerTest.class})
public class QuestionRestControllerTest {
	
	@Autowired
	MockMvc mockmvc;
	
	@Mock
	QuestionService questionService;
	
	@InjectMocks
	QuestionRestController questionRestController;
	
	@BeforeEach
	public void setUp() {
		mockmvc=MockMvcBuilders.standaloneSetup(questionRestController).build();
	}
	
	@Test
	@Order(1)
	public void  findAllTest() throws Exception {
		List<Question> questions = new ArrayList<Question>(); 
		Question question = new Question("Which of the following is not a Java features? a. Dynamic b. Architecture Neutral c. Use of pointers d. Object-oriented\r\n"
				,new Answer("c"));

		questions.add(question);

		when(questionService.findAll()).thenReturn(questions);
		this.mockmvc.perform(get("/api/questions"))
		.andExpect(status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(questions.size()))
		.andDo(print());

	}

	@Test
	@Order(2)
	public void  getQuestionTest() {
		Question question =
		        new Question("Which of the following is not a Java features? a. Dynamic b. Architecture Neutral c. Use of pointers d. Object-oriented\r\n"
				, new Answer ("c"));
		question.setQuestionId(1);

		when(questionService.findById(1)).thenReturn(question);

		Question actual = questionRestController.getQuestion(1);
		Question expected = question;

		verify(questionService).findById(1);
		
		assertEquals(expected,actual);

	}

	@Test
	@Order(3)
	public void saveQuestionTest() throws Exception {
		Question question = new Question("Which of the following is not a Java features? a. Dynamic b. Architecture Neutral c. Use of pointers d. Object-oriented\r\n"
				+ "",new Answer ("c"));
		question.setQuestionId(0);
        
		when(questionService.save(question)).thenReturn(question);
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonbody=mapper.writeValueAsString(question);
		System.out.println(jsonbody);
		this.mockmvc.perform(post("/api/questions")
				.content(jsonbody)
				.contentType(MediaType.APPLICATION_JSON)
				)
		.andExpect(status().isOk())
		.andDo(print());

}
	
	@Test
	@Order(4)
	public void updateQuestionTest() {
		Question question = new Question("Which of the following is not Java features? a. Dynamic b. Architecture Neutral c. Use of pointers d. Object-oriented\r\n"
				+ "",new Answer ("c"));

		when(questionService.save(question)).thenReturn(question);
		
		Question actual = questionRestController.updateQuestion(question);
		Question expected = question;
		
		verify(questionService).save(question);
		
		assertEquals(expected,actual);

}
	@Test
	@Order(5)
	public void deleteQuestionTest() {
		Question question = new Question("Which of the following is not Java features? a. Dynamic b. Architecture Neutral c. Use of pointers d. Object-oriented\r\n"
				+ "",new Answer ("c"));
		question.setQuestionId(1);
		
		when(questionService.findById(1)).thenReturn(question);

		questionRestController.deleteQuestion(1);
		
		verify(questionService).findById(1);
		verify(questionService).deleteById(1);
	}

}
