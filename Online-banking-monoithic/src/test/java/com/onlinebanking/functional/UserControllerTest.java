package com.onlinebanking.functional;

import static com.onlinebanking.utils.TestUtils.businessTestFile;
import static com.onlinebanking.utils.TestUtils.currentTest;
import static com.onlinebanking.utils.TestUtils.yakshaAssert;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.onlinebanking.OnlineBankingApplication;
import com.onlinebanking.controller.UserController;
import com.onlinebanking.dto.UserDTO;
import com.onlinebanking.entity.User;
import com.onlinebanking.service.UserService;
import com.onlinebanking.utils.MasterData;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc
@ContextConfiguration(classes = OnlineBankingApplication.class)
public class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService userService;

	@Test
	public void testCreateUser() throws Exception {
		UserDTO savedUserDTO = MasterData.getUserDTO();
		User User = MasterData.convertUserDTOToUser(savedUserDTO);
		when(userService.createUser(any())).thenReturn(User);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/users")
				.content(MasterData.asJsonString(savedUserDTO)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(savedUserDTO)) ? "true"
						: "false"),
				businessTestFile);

	}

	@Test
	public void testCreateUserIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		UserDTO savedUserDTO = MasterData.getUserDTO();
		User User = MasterData.convertUserDTOToUser(savedUserDTO);
		when(this.userService.createUser(any())).then(new Answer<User>() {

			@Override
			public User answer(InvocationOnMock invocation) throws Throwable {
				count[0]++;
				return User;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/users")
				.content(MasterData.asJsonString(savedUserDTO)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);
	}

	@Test
	public void testGetUserById() throws Exception {
		UserDTO UserDTO = MasterData.getUserDTO();
		User User = MasterData.convertUserDTOToUser(UserDTO);
		when(this.userService.getUserById(UserDTO.getId())).thenReturn(User);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/" + UserDTO.getId())
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(UserDTO)) ? "true"
						: "false"),
				businessTestFile);

	}

	@Test
	public void testGetUserByIdIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		UserDTO UserDTO = MasterData.getUserDTO();
		User User = MasterData.convertUserDTOToUser(UserDTO);
		when(this.userService.getUserById(UserDTO.getId())).then(new Answer<User>() {

			@Override
			public User answer(InvocationOnMock invocation) throws Throwable {
				count[0]++;
				return User;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/" + UserDTO.getId())
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}

	@Test
	public void testGetAllUsers() throws Exception {
		List<UserDTO> UserDTOS = MasterData.getUsersDTOList();
		List<User> Users = MasterData.convertUserDTOListToUserList(UserDTOS);
		when(this.userService.getAllUsers()).thenReturn(Users);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(UserDTOS)) ? "true"
						: "false"),
				businessTestFile);

	}

	@Test
	public void testGetAllUsersIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		List<UserDTO> UserDTOS = MasterData.getUsersDTOList();
		List<User> Users = MasterData.convertUserDTOListToUserList(UserDTOS);
		when(this.userService.getAllUsers()).then(new Answer<List<User>>() {

			@Override
			public List<User> answer(InvocationOnMock invocation) throws Throwable {
				count[0]++;
				return Users;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}

	@Test
	public void testUpdateUser() throws Exception {
		UserDTO updateUserDTO = MasterData.getUserDTO();
		User User = MasterData.convertUserDTOToUser(updateUserDTO);
		when(this.userService.updateUser(eq(updateUserDTO.getId()), any())).thenReturn(User);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/users/" + updateUserDTO.getId())
				.content(MasterData.asJsonString(updateUserDTO)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(updateUserDTO))
						? "true"
						: "false"),
				businessTestFile);

	}

	@Test
	public void testUpdateUserIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		UserDTO updateUserDTO = MasterData.getUserDTO();
		User User = MasterData.convertUserDTOToUser(updateUserDTO);
		when(this.userService.updateUser(eq(updateUserDTO.getId()), any())).then(new Answer<User>() {

			@Override
			public User answer(InvocationOnMock invocation) throws Throwable {
				count[0]++;
				return User;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/users/" + updateUserDTO.getId())
				.content(MasterData.asJsonString(updateUserDTO)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}

	@Test
	public void testDeleteUser() throws Exception {
		UserDTO updateUserDTO = MasterData.getUserDTO();
		User User = MasterData.convertUserDTOToUser(updateUserDTO);
		when(this.userService.deleteUser(updateUserDTO.getId())).thenReturn(true);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/users/" + updateUserDTO.getId())
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(), (result.getResponse().getContentAsString().contentEquals("") ? "true" : "false"),
				businessTestFile);

	}

	@Test
	public void testDeleteUserIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		UserDTO updateUserDTO = MasterData.getUserDTO();
		User User = MasterData.convertUserDTOToUser(updateUserDTO);
		when(this.userService.deleteUser(updateUserDTO.getId())).then(new Answer<Boolean>() {

			@Override
			public Boolean answer(InvocationOnMock invocation) throws Throwable {
				count[0]++;
				return true;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/users/" + updateUserDTO.getId())
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}

	@Test
	public void testSearchUserByName() throws Exception {
		UserDTO updateUserDTO = MasterData.getUserDTO();
		User User = MasterData.convertUserDTOToUser(updateUserDTO);
		List<User> Users = MasterData.convertUserDTOListToUserList(MasterData.getUsersDTOList());
		when(this.userService.searchUsersByName(updateUserDTO.getName())).thenReturn(Users);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/search?name=" + updateUserDTO.getName())
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(Users)) ? "true"
						: "false"),
				businessTestFile);
	}

	@Test
	public void testSearchUserByNameIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		UserDTO updateUserDTO = MasterData.getUserDTO();
		User User = MasterData.convertUserDTOToUser(updateUserDTO);
		List<User> Users = MasterData.convertUserDTOListToUserList(MasterData.getUsersDTOList());

		when(this.userService.searchUsersByName(updateUserDTO.getName())).then(new Answer<List<User>>() {

			@Override
			public List<User> answer(InvocationOnMock invocation) throws Throwable {
				count[0]++;
				return Users;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/search?name=" + updateUserDTO.getName())
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);
	}

}
