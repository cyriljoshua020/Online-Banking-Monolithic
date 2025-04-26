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
import com.onlinebanking.controller.AccountController;
import com.onlinebanking.dto.AccountDTO;
import com.onlinebanking.service.AccountService;
import com.onlinebanking.utils.MasterData;

@WebMvcTest(AccountController.class)
@AutoConfigureMockMvc
@ContextConfiguration(classes = OnlineBankingApplication.class)
public class TestAccountExceptions {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private AccountService accountService;

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void testCreateAccountInvalidDataException() throws Exception {
		AccountDTO accountDTO = MasterData.getAccountDTO();
		accountDTO.setAccountType(null);
		accountDTO.setBalance(null);
		accountDTO.setId(null);
		accountDTO.setNumber(null);
		accountDTO.setUser(null);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/accounts")
				.content(MasterData.asJsonString(accountDTO)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String responseBody = result.getResponse().getContentAsString();
		AccountDTO accountDTOResponse = new Gson().fromJson(responseBody, AccountDTO.class);
		yakshaAssert(currentTest(),
				(result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value() ? "true" : "false"),
				exceptionTestFile);
	}

	@Test
	public void testUpdateAccountInvalidDataException() throws Exception {
		AccountDTO accountDTO = MasterData.getAccountDTO();
		accountDTO.setAccountType(null);
		accountDTO.setBalance(null);
		accountDTO.setId(null);
		accountDTO.setNumber(null);
		accountDTO.setUser(null);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/accounts/" + accountDTO.getId())
				.content(MasterData.asJsonString(accountDTO)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(),
				(result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value() ? "true" : "false"),
				exceptionTestFile);
	}

	@Test
	public void testUpdateAccountByIdResourceNotFoundException() throws Exception {
		AccountDTO accountDTO = MasterData.getAccountDTO();

		GlobalExceptionHandler.ErrorResponse exResponse = new GlobalExceptionHandler.ErrorResponse(
				HttpStatus.BAD_REQUEST.value(), "Account not found with id: " + accountDTO.getId());

		when(this.accountService.updateAccount(eq(accountDTO.getId()), any()))
				.thenThrow(new ResourceNotFoundException("Account", "id", accountDTO.getId()));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/accounts/" + accountDTO.getId())
				.content(MasterData.asJsonString(accountDTO)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contains(exResponse.getMessage()) ? "true" : "false"),
				exceptionTestFile);

	}

	@Test
	public void testGetAccountByIdResourceNotFoundException() throws Exception {
		AccountDTO accountDTO = MasterData.getAccountDTO();
		accountDTO.setId((long) -10);

		GlobalExceptionHandler.ErrorResponse exResponse = new GlobalExceptionHandler.ErrorResponse(
				HttpStatus.BAD_REQUEST.value(), "" + accountDTO.getId());

		when(this.accountService.getAccountById(accountDTO.getId()))
				.thenThrow(new ResourceNotFoundException("Account", "id", accountDTO.getId()));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/accounts/" + accountDTO.getId())
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contains(exResponse.getMessage()) ? "true" : "false"),
				exceptionTestFile);

	}

	@Test
	public void testDeleteAccountByIdResourceNotFoundException() throws Exception {
		AccountDTO accountDTO = MasterData.getAccountDTO();
		accountDTO.setId((long) -10);
		GlobalExceptionHandler.ErrorResponse exResponse = new GlobalExceptionHandler.ErrorResponse(
				HttpStatus.BAD_REQUEST.value(), "" + accountDTO.getId());

		when(this.accountService.deleteAccount(accountDTO.getId()))
				.thenThrow(new ResourceNotFoundException("Account", "id", accountDTO.getId()));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/accounts/" + accountDTO.getId())
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contains(exResponse.getMessage()) ? "true" : "false"),
				exceptionTestFile);

	}

}
