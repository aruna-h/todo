package com.bridgelabz.todoapp;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.bridgelabz.todoapp.exceptionservice.ToDoException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * @author bridgelabz
 * @since 20/7/2018 <br>
 *        <p>
 *        Entity to test todoApplication using Mockito
 *        </p>
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TodoApplication.class)
@SpringBootTest
public class TodoMockitoTest {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext wac;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

	}

	/**
	 * method to test register of user
	 * 
	 * // * @throws Exception
	 */
	@Test
	public void registerTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/user/register").contentType(MediaType.APPLICATION_JSON).content(
				"{ \"email\" : \"arunakush6@gmail.com\",\"firstName\": \"aruna\",\"id\": \"1\", \"lastName\": \"h\",\"mobNumber\":\"9535536188\",\"password\" : \"Aruna@1?\",\"userName\":\"aruna1\"}")
				.accept(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$.message").value("Registered successfully"))
				.andExpect(jsonPath("$.status").value(2));
	}

	/**
	 * method tom test login of user
	 * 
	 * @throws Exception
	 */
	@Test
	public void loginTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/user/login").contentType(MediaType.APPLICATION_JSON)
				.content("{ \"email\" :\"arunakush6@gmail.com\",\"password\":\"Aruna@1?\"}")
				.accept(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$.message").value("Login successfully"))
				.andExpect(jsonPath("$.status").value(3));
	}

	/**
	 * method to test activation of account
	 * 
	 * @throws Exception
	 */
	@Test
	public void activateTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/user/activation").param("token",
				"eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwiaWF0IjoxNTMyMzQ3Nzc1LCJleHAiOjE1MzI0MzQxNzV9._y_iJewAUcE0h5WEZBBzWXg_e870wauqm09eVPK0-f0\n"
						+ "\n" + "")
				.accept(MediaType.TEXT_PLAIN_VALUE))
				.andExpect(jsonPath("$.message").value("User activated successfully"));
		// .andExpect(jsonPath("$.status").value(201));
	}

	/**
	 * method to test forgot password
	 * 
	 * @throws Exception
	 */
	@Test
	public void forgetPasswordTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/user/forgotPassword").param("email", "arunakush6@gmail.com")
				.content(MediaType.TEXT_PLAIN_VALUE))
				.andExpect(jsonPath("$.message").value("See Password Reset Link Sent"))
				.andExpect(jsonPath("$.status").value(202));
	}

	/**
	 * method to test reset password
	 * 
	 * @throws Exception
	 */
	@Test
	public void resetPasswordTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.put("/resetPassword").contentType(MediaType.APPLICATION_JSON).param(
				"token",
				"eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwiaWF0IjoxNTMyMzQ3Nzc1LCJleHAiOjE1MzI0MzQxNzV9._y_iJewAUcE0h5WEZBBzWXg_e870wauqm09eVPK0-f0c")
				.content("{ \"password\" :\"Aruna@1/\",\"confirmpassword\":\"Aruna@1/\"}"))
				.andExpect(jsonPath("$.message").value("Reset Password successfull"))
				.andExpect(jsonPath("$.status").value(201));
	}
}
