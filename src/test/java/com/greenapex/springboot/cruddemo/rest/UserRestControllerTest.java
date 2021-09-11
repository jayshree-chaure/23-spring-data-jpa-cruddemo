package   com.greenapex.springboot.cruddemo.rest;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
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
import com.greenapex.springboot.cruddemo.entity.User;
import com.greenapex.springboot.cruddemo.service.UserService;

@TestMethodOrder(OrderAnnotation.class)
@ComponentScan(basePackages ="com.greenapex.springboot.cruddemo")
@AutoConfigureMockMvc
@ContextConfiguration
@SpringBootTest(classes = {UserRestControllerTest.class})
public class UserRestControllerTest {

	@Autowired
	MockMvc mockmvc;

	@Mock
	UserService userService;

	@InjectMocks
	UserRestController userRestController;

	@BeforeEach
	public void setUp() {
		mockmvc=MockMvcBuilders.standaloneSetup(userRestController).build();
	}

	@Test
	@Order(1)
	public void findAllUserTest() throws Exception 
	{
		List<User> users = new ArrayList<User>();
		User user1 = new User("jayshree","jayshree@gmail.com","jayshree12");
		user1.setUserId(1);

		User user2 = new User("sheetal","sheetal@gmail.com","sheetal12");
		user2.setUserId(2);

		users.add(user1);
		users.add(user2);

		when(userService.findAllUser()).thenReturn(users);

		this.mockmvc.perform(get("/api/users"))
		.andExpect(status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(users.size()))
		.andDo(print());
	}

	@Test
	@Order(2)
	public void getUserTest() throws Exception {
		User user = new User("riya","riya@gmail.com","riya12");
		user.setUserId(1);
		when(userService.findById(1)).thenReturn(user);
		this.mockmvc.perform(get("/api/users/1",1))
		.andExpect(status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.userId").value(1))
		.andExpect(MockMvcResultMatchers.jsonPath("$.username").value("riya"))
		.andExpect(MockMvcResultMatchers.jsonPath("$.email").value("riya@gmail.com"))	
		.andExpect(MockMvcResultMatchers.jsonPath("$.password").value("riya12"))	
		.andDo(print());

	}

	@Test
	@Order(3)
	public void updateUserTest() throws Exception {
		User user = new User();
		user.setUserId(1);
		user.setUsername("riya");
		user.setEmail("riya@green.com");
		user.setPassword("riya3");

		when(userService.saveUser(user)).thenReturn(user);

		ObjectMapper mapper = new ObjectMapper();

		String jsonbody=mapper.writeValueAsString(user);

		this.mockmvc.perform(put("/api/users").content(jsonbody)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$.username").value("riya"))
		.andExpect(MockMvcResultMatchers.jsonPath("$.email").value("riya@green.com"))	
		.andExpect(MockMvcResultMatchers.jsonPath("$.password").value("riya3"))	
		.andDo(print());
	}

	@Test 
	@Order(4)
	public void saveUserTest() throws Exception {
		User user = new User("riya","riya@gmail.com","riya12");
		user.setUserId(0);

		when(userService.saveUser(user)).thenReturn(user);

		ObjectMapper mapper = new ObjectMapper();

		String jsonbody=mapper.writeValueAsString(user);
		System.out.println(jsonbody);

		this.mockmvc.perform(post("/api/users")
				.content(jsonbody)
				.contentType(MediaType.APPLICATION_JSON)
				)
		.andExpect(status().isOk())
		.andDo(print());

	}
	@Test
	@Order(5)
	public void deleteUserTest() throws Exception {
		User user = new User("riya","riya@gmail.com","riya12");
		user.setUserId(1);
		when(userService.findById(1)).thenReturn(user);

		userRestController.deleteUser(1);
		this.mockmvc.perform(delete("/api/users/1",1))
		.andExpect(status().isOk());


	}
}
