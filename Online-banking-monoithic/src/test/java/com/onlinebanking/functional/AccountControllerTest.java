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
import com.onlinebanking.controller.AccountController;
import com.onlinebanking.dto.AccountDTO;
import com.onlinebanking.entity.Account;
import com.onlinebanking.service.AccountService;
import com.onlinebanking.utils.MasterData;

@WebMvcTest(AccountController.class)
@AutoConfigureMockMvc
@ContextConfiguration(classes = OnlineBankingApplication.class)
public class AccountControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private AccountService accountService;

	@Test
	public void testCreateAccount() throws Exception {
		AccountDTO savedAccountDTO = MasterData.getAccountDTO();
		Account account = MasterData.convertAccountDTOToAccount(savedAccountDTO);
		when(accountService.createAccount(any())).thenReturn(account);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/accounts")
				.content(MasterData.asJsonString(savedAccountDTO)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(savedAccountDTO))
						? "true"
						: "false"),
				businessTestFile);

	}

	@Test
	public void testCreateAccountIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		AccountDTO savedAccountDto = MasterData.getAccountDTO();
		Account account = MasterData.convertAccountDTOToAccount(savedAccountDto);
		when(this.accountService.createAccount(any())).then(new Answer<Account>() {

			@Override
			public Account answer(InvocationOnMock invocation) throws Throwable {
				count[0]++;
				return account;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/accounts")
				.content(MasterData.asJsonString(savedAccountDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);
	}

	@Test
	public void testGetAccountById() throws Exception {
		AccountDTO accountDTO = MasterData.getAccountDTO();
		Account account = MasterData.convertAccountDTOToAccount(accountDTO);
		when(this.accountService.getAccountById(accountDTO.getId())).thenReturn(account);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/accounts/" + accountDTO.getId())
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(accountDTO)) ? "true"
						: "false"),
				businessTestFile);

	}

	@Test
	public void testGetAccountByIdIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		AccountDTO accountDto = MasterData.getAccountDTO();
		Account account = MasterData.convertAccountDTOToAccount(accountDto);
		when(this.accountService.getAccountById(accountDto.getId())).then(new Answer<Account>() {

			@Override
			public Account answer(InvocationOnMock invocation) throws Throwable {
				count[0]++;
				return account;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/accounts/" + accountDto.getId())
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}

	@Test
	public void testGetAllAccounts() throws Exception {
		List<AccountDTO> accountDTOS = MasterData.getAccountsDTOList();
		List<Account> accounts = MasterData.convertAccountDTOListToAccountList(accountDTOS);
		when(this.accountService.getAllAccounts()).thenReturn(accounts);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/accounts").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(accountDTOS)) ? "true"
						: "false"),
				businessTestFile);

	}

	@Test
	public void testGetAllAccountsIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		List<AccountDTO> accountDTOS = MasterData.getAccountsDTOList();
		List<Account> accounts = MasterData.convertAccountDTOListToAccountList(accountDTOS);
		when(this.accountService.getAllAccounts()).then(new Answer<List<Account>>() {

			@Override
			public List<Account> answer(InvocationOnMock invocation) throws Throwable {
				count[0]++;
				return accounts;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/accounts/").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}

	@Test
	public void testUpdateAccount() throws Exception {
		AccountDTO updateAccountDTO = MasterData.getAccountDTO();
		Account account = MasterData.convertAccountDTOToAccount(updateAccountDTO);
		when(this.accountService.updateAccount(eq(updateAccountDTO.getId()), any())).thenReturn(account);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/accounts/" + updateAccountDTO.getId())
				.content(MasterData.asJsonString(updateAccountDTO)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(updateAccountDTO))
						? "true"
						: "false"),
				businessTestFile);

	}

	@Test
	public void testUpdateAccountIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		AccountDTO updateAccountDTO = MasterData.getAccountDTO();
		Account account = MasterData.convertAccountDTOToAccount(updateAccountDTO);
		when(this.accountService.updateAccount(eq(updateAccountDTO.getId()), any())).then(new Answer<Account>() {

			@Override
			public Account answer(InvocationOnMock invocation) throws Throwable {
				count[0]++;
				return account;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/accounts/" + updateAccountDTO.getId())
				.content(MasterData.asJsonString(updateAccountDTO)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}

	@Test
	public void testDeleteAccount() throws Exception {
		AccountDTO updateAccountDTO = MasterData.getAccountDTO();
		Account account = MasterData.convertAccountDTOToAccount(updateAccountDTO);
		when(this.accountService.deleteAccount(updateAccountDTO.getId())).thenReturn(true);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/accounts/" + updateAccountDTO.getId())
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(), (result.getResponse().getContentAsString().contentEquals("") ? "true" : "false"),
				businessTestFile);

	}

	@Test
	public void testDeleteAccountIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		AccountDTO updateAccountDTO = MasterData.getAccountDTO();
		Account account = MasterData.convertAccountDTOToAccount(updateAccountDTO);
		when(this.accountService.deleteAccount(updateAccountDTO.getId())).then(new Answer<Boolean>() {

			@Override
			public Boolean answer(InvocationOnMock invocation) throws Throwable {
				count[0]++;
				return true;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/accounts/" + updateAccountDTO.getId())
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}

	@Test
	public void testSearchAccountByUsername() throws Exception {
		AccountDTO updateAccountDTO = MasterData.getAccountDTO();
		Account account = MasterData.convertAccountDTOToAccount(updateAccountDTO);
		List<Account> accounts = MasterData.convertAccountDTOListToAccountList(MasterData.getAccountsDTOList());
		when(this.accountService.searchAccountsByUser(updateAccountDTO.getUser().getName())).thenReturn(accounts);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/accounts/search?userName=" + updateAccountDTO.getUser().getName())
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(accounts)) ? "true"
						: "false"),
				businessTestFile);
	}

	@Test
	public void testSearchAccountByUsernameIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		AccountDTO updateAccountDTO = MasterData.getAccountDTO();
		Account account = MasterData.convertAccountDTOToAccount(updateAccountDTO);
		List<Account> accounts = MasterData.convertAccountDTOListToAccountList(MasterData.getAccountsDTOList());

		when(this.accountService.searchAccountsByUser(updateAccountDTO.getUser().getName()))
				.then(new Answer<List<Account>>() {

					@Override
					public List<Account> answer(InvocationOnMock invocation) throws Throwable {
						count[0]++;
						return accounts;
					}
				});
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/accounts/search?userName=" + updateAccountDTO.getUser().getName())
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);
	}

}
