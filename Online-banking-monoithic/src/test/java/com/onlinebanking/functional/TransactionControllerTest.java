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
import com.onlinebanking.controller.TransactionController;
import com.onlinebanking.dto.TransactionDTO;
import com.onlinebanking.entity.Transaction;
import com.onlinebanking.service.TransactionService;
import com.onlinebanking.utils.MasterData;

@WebMvcTest(TransactionController.class)
@AutoConfigureMockMvc
@ContextConfiguration(classes = OnlineBankingApplication.class)
public class TransactionControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private TransactionService transactionService;

	@Test
	public void testCreateTransaction() throws Exception {
		TransactionDTO savedTransactionDTO = MasterData.getTransactionDTO();
		Transaction Transaction = MasterData.convertTransactionDTOToTransaction(savedTransactionDTO);
		when(transactionService.createTransaction(any())).thenReturn(Transaction);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/transactions")
				.content(MasterData.asJsonString(savedTransactionDTO)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(savedTransactionDTO))
						? "true"
						: "false"),
				businessTestFile);

	}

	@Test
	public void testCreateTransactionIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		TransactionDTO savedTransactionDTO = MasterData.getTransactionDTO();
		Transaction Transaction = MasterData.convertTransactionDTOToTransaction(savedTransactionDTO);
		when(this.transactionService.createTransaction(any())).then(new Answer<Transaction>() {

			@Override
			public Transaction answer(InvocationOnMock invocation) throws Throwable {
				count[0]++;
				return Transaction;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/transactions")
				.content(MasterData.asJsonString(savedTransactionDTO)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);
	}

	@Test
	public void testGetTransactionById() throws Exception {
		TransactionDTO TransactionDTO = MasterData.getTransactionDTO();
		Transaction Transaction = MasterData.convertTransactionDTOToTransaction(TransactionDTO);
		when(this.transactionService.getTransactionById(TransactionDTO.getId())).thenReturn(Transaction);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/transactions/" + TransactionDTO.getId())
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(TransactionDTO))
						? "true"
						: "false"),
				businessTestFile);

	}

	@Test
	public void testGetTransactionByIdIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		TransactionDTO TransactionDTO = MasterData.getTransactionDTO();
		Transaction Transaction = MasterData.convertTransactionDTOToTransaction(TransactionDTO);
		when(this.transactionService.getTransactionById(TransactionDTO.getId())).then(new Answer<Transaction>() {

			@Override
			public Transaction answer(InvocationOnMock invocation) throws Throwable {
				count[0]++;
				return Transaction;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/transactions/" + TransactionDTO.getId())
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}

	@Test
	public void testGetAllTransactions() throws Exception {
		List<TransactionDTO> TransactionDTOS = MasterData.getTransactionsDTOList();
		List<Transaction> Transactions = MasterData.convertTransactionDTOListToTransactionList(TransactionDTOS);
		when(this.transactionService.getAllTransactions()).thenReturn(Transactions);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/transactions")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(TransactionDTOS))
						? "true"
						: "false"),
				businessTestFile);

	}

	@Test
	public void testGetAllTransactionsIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		List<TransactionDTO> TransactionDTOS = MasterData.getTransactionsDTOList();
		List<Transaction> Transactions = MasterData.convertTransactionDTOListToTransactionList(TransactionDTOS);
		when(this.transactionService.getAllTransactions()).then(new Answer<List<Transaction>>() {

			@Override
			public List<Transaction> answer(InvocationOnMock invocation) throws Throwable {
				count[0]++;
				return Transactions;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/transactions/")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}

	@Test
	public void testUpdateTransaction() throws Exception {
		TransactionDTO updateTransactionDTO = MasterData.getTransactionDTO();
		Transaction Transaction = MasterData.convertTransactionDTOToTransaction(updateTransactionDTO);
		when(this.transactionService.updateTransaction(eq(updateTransactionDTO.getId()), any()))
				.thenReturn(Transaction);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/transactions/" + updateTransactionDTO.getId())
				.content(MasterData.asJsonString(updateTransactionDTO)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(updateTransactionDTO))
						? "true"
						: "false"),
				businessTestFile);

	}

	@Test
	public void testUpdateTransactionIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		TransactionDTO updateTransactionDTO = MasterData.getTransactionDTO();
		Transaction Transaction = MasterData.convertTransactionDTOToTransaction(updateTransactionDTO);
		when(this.transactionService.updateTransaction(eq(updateTransactionDTO.getId()), any()))
				.then(new Answer<Transaction>() {

					@Override
					public Transaction answer(InvocationOnMock invocation) throws Throwable {
						count[0]++;
						return Transaction;
					}
				});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/transactions/" + updateTransactionDTO.getId())
				.content(MasterData.asJsonString(updateTransactionDTO)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}

	@Test
	public void testDeleteTransaction() throws Exception {
		TransactionDTO updateTransactionDTO = MasterData.getTransactionDTO();
		Transaction Transaction = MasterData.convertTransactionDTOToTransaction(updateTransactionDTO);
		when(this.transactionService.deleteTransaction(updateTransactionDTO.getId())).thenReturn(true);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/transactions/" + updateTransactionDTO.getId())
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(), (result.getResponse().getContentAsString().contentEquals("") ? "true" : "false"),
				businessTestFile);

	}

	@Test
	public void testDeleteTransactionIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		TransactionDTO updateTransactionDTO = MasterData.getTransactionDTO();
		Transaction Transaction = MasterData.convertTransactionDTOToTransaction(updateTransactionDTO);
		when(this.transactionService.deleteTransaction(updateTransactionDTO.getId())).then(new Answer<Boolean>() {

			@Override
			public Boolean answer(InvocationOnMock invocation) throws Throwable {
				count[0]++;
				return true;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/transactions/" + updateTransactionDTO.getId())
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}

	@Test
	public void testSearchTransactionByUsername() throws Exception {
		TransactionDTO updateTransactionDTO = MasterData.getTransactionDTO();
		Transaction Transaction = MasterData.convertTransactionDTOToTransaction(updateTransactionDTO);
		List<Transaction> Transactions = MasterData
				.convertTransactionDTOListToTransactionList(MasterData.getTransactionsDTOList());
		when(this.transactionService.searchTransactionsByType(updateTransactionDTO.getType())).thenReturn(Transactions);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/transactions/search?type=" + updateTransactionDTO.getType())
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(Transactions)) ? "true"
						: "false"),
				businessTestFile);
	}

	@Test
	public void testSearchTransactionByUsernameIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		TransactionDTO updateTransactionDTO = MasterData.getTransactionDTO();
		Transaction Transaction = MasterData.convertTransactionDTOToTransaction(updateTransactionDTO);
		List<Transaction> Transactions = MasterData
				.convertTransactionDTOListToTransactionList(MasterData.getTransactionsDTOList());

		when(this.transactionService.searchTransactionsByType(updateTransactionDTO.getType()))
				.then(new Answer<List<Transaction>>() {

					@Override
					public List<Transaction> answer(InvocationOnMock invocation) throws Throwable {
						count[0]++;
						return Transactions;
					}
				});
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/transactions/search?type=" + updateTransactionDTO.getType())
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);
	}

}
