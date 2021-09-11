package com.greenapex.springboot.cruddemo.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
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
import com.greenapex.springboot.cruddemo.service.AnswerService;

@TestMethodOrder(OrderAnnotation.class)
@ComponentScan(basePackages ="com.greenapex.springboot.cruddemo")
@AutoConfigureMockMvc
@ContextConfiguration
@SpringBootTest(classes = {AnswerRestControllerTest.class})
public class AnswerRestControllerTest {

	@Autowired
	MockMvc mockmvc;

	@Mock
	AnswerService answerService;

	@InjectMocks
	AnswerRestController answerRestController;

	@BeforeEach
	public void setUp() {
		mockmvc=MockMvcBuilders.standaloneSetup(answerRestController).build();
	}

	@Test
	@Order(1)
	public void findAllTest() throws Exception {
		List<Answer> answers = new ArrayList<Answer>(); 
		Answer answer1 = new Answer("b");
		answer1.setAnswerId(1);
		Answer answer2 = new Answer("c");
		answer2.setAnswerId(2);

		answers.add(answer1);
		answers.add(answer2);

		when(answerService.findAll()).thenReturn(answers);

		this.mockmvc.perform(get("/api/answers"))
		.andExpect(status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(answers.size()))
		.andDo(print());

	}
	@Test
	@Order(2)
	public void getAnswerTest() throws Exception {
		Answer answer = new Answer("b");
		answer.setAnswerId(1);
		when(answerService.findById(1)).thenReturn(answer);
		this.mockmvc.perform(get("/api/answers/1",1))
		.andExpect(status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.answerId").value(1))
		.andExpect(MockMvcResultMatchers.jsonPath("$.answer").value("b"))	
		.andDo(print());
	}

	@Test
	@Order(3)
	public void saveAnswerTest() throws Exception {
		Answer answer = new Answer("b");
		answer.setAnswerId(0);
		when(answerService.save(answer)).thenReturn(answer);
		ObjectMapper mapper = new ObjectMapper();
		String jsonbody=mapper.writeValueAsString(answer);
		System.out.println(jsonbody);
		this.mockmvc.perform(post("/api/answers")
				.content(jsonbody)
				.contentType(MediaType.APPLICATION_JSON)
				)
		.andExpect(status().isOk())
		.andDo(print());
	}

	@Test
	@Order(4)
	public void updateAnswerTest() throws Exception {
		Answer answer = new Answer("b");
		answer.setAnswerId(1);

		when(answerService.save(answer)).thenReturn(answer);

		ObjectMapper mapper = new ObjectMapper();

		String jsonbody=mapper.writeValueAsString(answer);

		this.mockmvc.perform(put("/api/answers").content(jsonbody)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$.answerId").value(1))
		.andExpect(MockMvcResultMatchers.jsonPath("$.answer").value("b"))	
		.andDo(print());
	}

	@Test
	@Order(5)
	public void deleteAnswerTest() throws Exception {
		Answer answer = new Answer("b");
		answer.setAnswerId(1);
		when(answerService.findById(1)).thenReturn(answer);

		answerRestController.deleteAnswer(1);
		this.mockmvc.perform(delete("/api/answers/1",1))
		.andExpect(status().isOk());


	}




}
