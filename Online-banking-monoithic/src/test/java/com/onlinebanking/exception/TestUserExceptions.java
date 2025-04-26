package com.onlinebanking.exception;

import static com.onlinebanking.utils.TestUtils.currentTest;
import static com.onlinebanking.utils.TestUtils.exceptionTestFile;
import static com.onlinebanking.utils.TestUtils.testReport;
import static com.onlinebanking.utils.TestUtils.yakshaAssert;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.google.gson.Gson;
import com.onlinebanking.OnlineBankingApplication;
import com.onlinebanking.controller.UserController;
import com.onlinebanking.dto.UserDTO;
import com.onlinebanking.dto.UserDTO;
import com.onlinebanking.service.UserService;
import com.onlinebanking.utils.MasterData;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc
@ContextConfiguration(classes = OnlineBankingApplication.class)
public class TestUserExceptions {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService userService;

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void testCreateUserInvalidDataException() throws Exception {
		UserDTO userDTO = MasterData.getUserDTO();
		userDTO.setAge(null);
		userDTO.setGender(null);
		userDTO.setId(null);
		userDTO.setName(null);
		userDTO.setPassword(null);
		userDTO.setUsername(null);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/users").content(MasterData.asJsonString(userDTO))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String responseBody = result.getResponse().getContentAsString();
		UserDTO userDTOResponse = new Gson().fromJson(responseBody, UserDTO.class);
		yakshaAssert(currentTest(),
				(result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value() ? "true" : "false"),
				exceptionTestFile);
	}

	@Test
	public void testUpdateUserInvalidDataException() throws Exception {
		UserDTO userDTO = MasterData.getUserDTO();
		userDTO.setAge(null);
		userDTO.setGender(null);
		userDTO.setId(null);
		userDTO.setName(null);
		userDTO.setPassword(null);
		userDTO.setUsername(null);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/users/" + userDTO.getId())
				.content(MasterData.asJsonString(userDTO)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(),
				(result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value() ? "true" : "false"),
				exceptionTestFile);
	}

	@Test
	public void testUpdateUserByIdResourceNotFoundException() throws Exception {
		UserDTO userDTO = MasterData.getUserDTO();

		GlobalExceptionHandler.ErrorResponse exResponse = new GlobalExceptionHandler.ErrorResponse(
				HttpStatus.BAD_REQUEST.value(), "User not found with id: " + userDTO.getId());

		when(this.userService.updateUser(eq(userDTO.getId()), any()))
				.thenThrow(new ResourceNotFoundException("User", "id", userDTO.getId()));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/users/" + userDTO.getId())
				.content(MasterData.asJsonString(userDTO)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contains(exResponse.getMessage()) ? "true" : "false"),
				exceptionTestFile);

	}

	@Test
	public void testGetUserByIdResourceNotFoundException() throws Exception {
		UserDTO userDTO = MasterData.getUserDTO();
		userDTO.setId((long) -10);

		GlobalExceptionHandler.ErrorResponse exResponse = new GlobalExceptionHandler.ErrorResponse(
				HttpStatus.BAD_REQUEST.value(), "" + userDTO.getId());

		when(this.userService.getUserById(userDTO.getId()))
				.thenThrow(new ResourceNotFoundException("User", "id", userDTO.getId()));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/" + userDTO.getId())
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contains(exResponse.getMessage()) ? "true" : "false"),
				exceptionTestFile);

	}

	@Test
	public void testDeleteUserByIdResourceNotFoundException() throws Exception {
		UserDTO userDTO = MasterData.getUserDTO();
		userDTO.setId((long) -10);

		GlobalExceptionHandler.ErrorResponse exResponse = new GlobalExceptionHandler.ErrorResponse(
				HttpStatus.BAD_REQUEST.value(), "" + userDTO.getId());

		when(this.userService.deleteUser(userDTO.getId()))
				.thenThrow(new ResourceNotFoundException("User", "id", userDTO.getId()));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/users/" + userDTO.getId())
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contains(exResponse.getMessage()) ? "true" : "false"),
				exceptionTestFile);

	}

}
