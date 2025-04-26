package com.onlinebanking.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.onlinebanking.dto.AccountDTO;
import com.onlinebanking.dto.TransactionDTO;
import com.onlinebanking.dto.UserDTO;
import com.onlinebanking.entity.Account;
import com.onlinebanking.entity.AccountType;
import com.onlinebanking.entity.Gender;
import com.onlinebanking.entity.Transaction;
import com.onlinebanking.entity.TransactionType;
import com.onlinebanking.entity.User;

public class MasterData {
	public static AccountDTO getAccountDTO() {
		AccountDTO accountDTO = new AccountDTO();
		accountDTO.setId((long) 1);
		accountDTO.setNumber("1234567890");
		accountDTO.setUser(getUser());
		accountDTO.setBalance(new BigDecimal("10000"));
		accountDTO.setAccountType(AccountType.SAVINGS);
		return accountDTO;
	}

	public static Account convertAccountDTOToAccount(AccountDTO accountDTO) {
		Account account = new Account();
		account.setAccountType(accountDTO.getAccountType());
		account.setBalance(accountDTO.getBalance());
		account.setId(accountDTO.getId());
		account.setNumber(accountDTO.getNumber());
		account.setUser(accountDTO.getUser());
		return account;
	}

	public static List<Account> convertAccountDTOListToAccountList(List<AccountDTO> accountDTOS) {
		List<Account> accountLists = new ArrayList<Account>();

		for (int index = 0; index < accountDTOS.size(); index++) {
			AccountDTO accountDTO = accountDTOS.get(index);

			Account account = new Account();
			account.setAccountType(accountDTO.getAccountType());
			account.setBalance(accountDTO.getBalance());
			account.setId(accountDTO.getId());
			account.setNumber(accountDTO.getNumber());
			account.setUser(accountDTO.getUser());

			accountLists.add(account);
		}

		return accountLists;
	}

	public static List<AccountDTO> getAccountsDTOList() {
		AccountDTO accountDTO1 = new AccountDTO();
		accountDTO1.setId((long) 1);
		accountDTO1.setNumber("1234567890");
		accountDTO1.setUser(getUsersList().get(0));
		accountDTO1.setBalance(new BigDecimal("10000"));
		accountDTO1.setAccountType(AccountType.SAVINGS);

		AccountDTO accountDTO2 = new AccountDTO();
		accountDTO2.setId((long) 2);
		accountDTO2.setNumber("1234567891");
		accountDTO2.setUser(getUsersList().get(1));
		accountDTO2.setBalance(new BigDecimal("20000"));
		accountDTO2.setAccountType(AccountType.CURRENT);

		List<AccountDTO> accountDTOList = new ArrayList<AccountDTO>();
		accountDTOList.add(accountDTO1);
		accountDTOList.add(accountDTO2);
		return accountDTOList;
	}

	public static Account getAccount() {
		Account account = new Account();
		account.setId((long) 1);
		account.setNumber("1234567890");
		account.setUser(getUser());
		account.setBalance(new BigDecimal("10000"));
		account.setAccountType(AccountType.SAVINGS);
		return account;
	}

	public static List<Account> getAccountsList() {
		Account account1 = new Account();
		account1.setId((long) 1);
		account1.setNumber("1234567890");
		account1.setUser(getUsersList().get(0));
		account1.setBalance(new BigDecimal("10000"));
		account1.setAccountType(AccountType.SAVINGS);

		Account account2 = new Account();
		account2.setId((long) 2);
		account2.setNumber("1234567891");
		account2.setUser(getUsersList().get(1));
		account2.setBalance(new BigDecimal("20000"));
		account2.setAccountType(AccountType.CURRENT);

		List<Account> accountList = new ArrayList<Account>();
		accountList.add(account1);
		accountList.add(account2);
		return accountList;
	}

	public static TransactionDTO getTransactionDTO() {
		TransactionDTO transactionDTO = new TransactionDTO();
		transactionDTO.setId((long) 1);
		transactionDTO.setAmount(new BigDecimal(1100));
		transactionDTO.setType(TransactionType.DEPOSIT);
		transactionDTO.setUser(getUser());
		return transactionDTO;
	}

	public static List<TransactionDTO> getTransactionsDTOList() {
		TransactionDTO transactionDTO1 = new TransactionDTO();
		transactionDTO1.setId((long) 1);
		transactionDTO1.setAmount(new BigDecimal(1100));
		transactionDTO1.setType(TransactionType.DEPOSIT);
		transactionDTO1.setUser(getUsersList().get(0));

		TransactionDTO transactionDTO2 = new TransactionDTO();
		transactionDTO2.setId((long) 2);
		transactionDTO2.setAmount(new BigDecimal(1000));
		transactionDTO2.setType(TransactionType.WITHDRAWAL);
		transactionDTO2.setUser(getUsersList().get(1));

		List<TransactionDTO> transactions = new ArrayList<TransactionDTO>();
		transactions.add(transactionDTO1);
		transactions.add(transactionDTO2);
		return transactions;
	}

	public static Transaction getTransaction() {
		Transaction transaction = new Transaction();
		transaction.setId((long) 1);
		transaction.setAmount(new BigDecimal(1100));
		transaction.setType(TransactionType.DEPOSIT);
		transaction.setUser(getUser());
		return transaction;
	}

	public static List<Transaction> getTransactionsList() {
		Transaction transaction1 = new Transaction();
		transaction1.setId((long) 1);
		transaction1.setAmount(new BigDecimal(1100));
		transaction1.setType(TransactionType.DEPOSIT);
		transaction1.setUser(getUsersList().get(0));

		Transaction transaction2 = new Transaction();
		transaction2.setId((long) 2);
		transaction2.setAmount(new BigDecimal(1000));
		transaction2.setType(TransactionType.WITHDRAWAL);
		transaction2.setUser(getUsersList().get(1));

		List<Transaction> transactions = new ArrayList<Transaction>();
		transactions.add(transaction1);
		transactions.add(transaction2);
		return transactions;
	}

	public static Transaction convertTransactionDTOToTransaction(TransactionDTO transactionDTO) {
		Transaction transaction = new Transaction();
		transaction.setAmount(transactionDTO.getAmount());
		transaction.setId(transactionDTO.getId());
		transaction.setType(transactionDTO.getType());
		transaction.setUser(transactionDTO.getUser());
		return transaction;
	}

	public static List<Transaction> convertTransactionDTOListToTransactionList(List<TransactionDTO> transactionDTOS) {
		List<Transaction> transactionLists = new ArrayList<Transaction>();

		for (int index = 0; index < transactionDTOS.size(); index++) {
			TransactionDTO transactionDTO = transactionDTOS.get(index);

			Transaction transaction = new Transaction();
			transaction.setAmount(transactionDTO.getAmount());
			transaction.setId(transactionDTO.getId());
			transaction.setType(transactionDTO.getType());
			transaction.setUser(transactionDTO.getUser());

			transactionLists.add(transaction);
		}

		return transactionLists;
	}

	public static UserDTO getUserDTO() {
		UserDTO userDTO = new UserDTO();
		userDTO.setId((long) 1);
		userDTO.setAge(28);
		userDTO.setGender(Gender.MALE);
		userDTO.setName("Random Name");
		userDTO.setPassword("randompassword");
		userDTO.setUsername("randomusername");
		return userDTO;
	}

	public static List<UserDTO> getUsersDTOList() {
		UserDTO userDTO1 = new UserDTO();
		userDTO1.setId((long) 1);
		userDTO1.setAge(28);
		userDTO1.setGender(Gender.MALE);
		userDTO1.setName("Random Name");
		userDTO1.setPassword("randompassword");
		userDTO1.setUsername("randomusername");

		UserDTO userDTO2 = new UserDTO();
		userDTO2.setId((long) 3);
		userDTO2.setAge(29);
		userDTO2.setGender(Gender.MALE);
		userDTO2.setName("Random Name 2");
		userDTO2.setPassword("randompassword2");
		userDTO2.setUsername("randomusername2");

		List<UserDTO> usersDTO = new ArrayList<>();
		usersDTO.add(userDTO1);
		usersDTO.add(userDTO2);
		return usersDTO;
	}

	public static User getUser() {
		User user = new User();
		user.setId((long) 1);
		user.setAge(28);
		user.setGender(Gender.MALE);
		user.setName("Random Name");
		user.setPassword("randompassword");
		user.setUsername("randomusername");
		return user;
	}

	public static List<User> getUsersList() {
		User user1 = new User();
		user1.setId((long) 1);
		user1.setAge(28);
		user1.setGender(Gender.MALE);
		user1.setName("Random Name");
		user1.setPassword("randompassword");
		user1.setUsername("randomusername");

		User user2 = new User();
		user2.setId((long) 2);
		user2.setAge(29);
		user2.setGender(Gender.MALE);
		user2.setName("Random Name 2");
		user2.setPassword("randompassword2");
		user2.setUsername("randomusername2");

		List<User> usersDTO = new ArrayList<>();
		usersDTO.add(user1);
		usersDTO.add(user2);
		return usersDTO;
	}

	public static String randomStringWithSize(int size) {
		String s = "";
		for (int i = 0; i < size; i++) {
			s.concat("A");
		}
		return s;
	}

	public static String asJsonString(final Object obj) {
		try {
			final ObjectMapper mapper = new ObjectMapper();
			mapper.registerModule(new JavaTimeModule());
			mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
			final String jsonContent = mapper.writeValueAsString(obj);

			return jsonContent;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static User convertUserDTOToUser(UserDTO userDTO) {
		User user = new User();
		user.setAge(userDTO.getAge());
		user.setGender(userDTO.getGender());
		user.setId(userDTO.getId());
		user.setName(userDTO.getName());
		user.setPassword(userDTO.getPassword());
		user.setUsername(userDTO.getUsername());
		return user;
	}

	public static List<User> convertUserDTOListToUserList(List<UserDTO> userDTOS) {
		List<User> userLists = new ArrayList<User>();

		for (int index = 0; index < userDTOS.size(); index++) {
			UserDTO userDTO = userDTOS.get(index);

			User user = new User();
			user.setAge(userDTO.getAge());
			user.setGender(userDTO.getGender());
			user.setId(userDTO.getId());
			user.setName(userDTO.getName());
			user.setPassword(userDTO.getPassword());
			user.setUsername(userDTO.getUsername());

			userLists.add(user);
		}

		return userLists;
	}

}
