package com.onlinebanking.boundary;

import static com.onlinebanking.utils.TestUtils.boundaryTestFile;
import static com.onlinebanking.utils.TestUtils.currentTest;
import static com.onlinebanking.utils.TestUtils.testReport;
import static com.onlinebanking.utils.TestUtils.yakshaAssert;

import java.util.Set;



import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.onlinebanking.dto.AccountDTO;
import com.onlinebanking.dto.TransactionDTO;
import com.onlinebanking.dto.UserDTO;
import com.onlinebanking.utils.MasterData;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

@ExtendWith(SpringExtension.class)
public class BoundaryTests {
	private static Validator validator;

	@BeforeAll
	public static void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void testIfUserIsNotNullInAccount() throws Exception {
		AccountDTO accountDTO = MasterData.getAccountDTO();
		accountDTO.setUser(null);
		Set<ConstraintViolation<AccountDTO>> violations = validator.validate(accountDTO);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testIfAccountNumberIsNotNullInAccount() throws Exception {
		AccountDTO accountDTO = MasterData.getAccountDTO();
		accountDTO.setNumber(null);
		Set<ConstraintViolation<AccountDTO>> violations = validator.validate(accountDTO);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testIfBalanceIsNotNullInAccount() throws Exception {
		AccountDTO accountDTO = MasterData.getAccountDTO();
		accountDTO.setBalance(null);
		Set<ConstraintViolation<AccountDTO>> violations = validator.validate(accountDTO);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testIfAccountTypeIsNotNullInAccount() throws Exception {
		AccountDTO accountDTO = MasterData.getAccountDTO();
		accountDTO.setAccountType(null);
		Set<ConstraintViolation<AccountDTO>> violations = validator.validate(accountDTO);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testIfUserIsNotNullInTransaction() throws Exception {
		TransactionDTO transactionDTO = MasterData.getTransactionDTO();
		transactionDTO.setUser(null);
		Set<ConstraintViolation<TransactionDTO>> violations = validator.validate(transactionDTO);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testIfAmountIsNotNullInv() throws Exception {
		TransactionDTO transactionDTO = MasterData.getTransactionDTO();
		transactionDTO.setAmount(null);
		Set<ConstraintViolation<TransactionDTO>> violations = validator.validate(transactionDTO);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testIfTransactionTypeIsNotNullInTransaction() throws Exception {
		TransactionDTO transactionDTO = MasterData.getTransactionDTO();
		transactionDTO.setType(null);
		Set<ConstraintViolation<TransactionDTO>> violations = validator.validate(transactionDTO);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testIfNameIsNotNullInUser() throws Exception {
		UserDTO userDTO = MasterData.getUserDTO();
		userDTO.setName(null);
		Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDTO);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testIfNameIsNotAcceptingLessThan3CharsInUser() throws Exception {
		UserDTO userDTO = MasterData.getUserDTO();
		userDTO.setName(MasterData.randomStringWithSize(2));
		Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDTO);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testIfNameIsNotAcceptingMoreThan20CharsInUser() throws Exception {
		UserDTO userDTO = MasterData.getUserDTO();
		userDTO.setName(MasterData.randomStringWithSize(21));
		Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDTO);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testIfUserNameIsNotNullInUser() throws Exception {
		TransactionDTO transactionDTO = MasterData.getTransactionDTO();
		transactionDTO.setType(null);
		Set<ConstraintViolation<TransactionDTO>> violations = validator.validate(transactionDTO);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testIfUserNameIsNotAcceptingLessThan3CharsInUser() throws Exception {
		UserDTO userDTO = MasterData.getUserDTO();
		userDTO.setUsername(MasterData.randomStringWithSize(2));
		Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDTO);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testIfUserNameIsNotAcceptingMoreThan20CharsInUser() throws Exception {
		UserDTO userDTO = MasterData.getUserDTO();
		userDTO.setUsername(MasterData.randomStringWithSize(21));
		Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDTO);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testIfPasswordIsNotNullInUser() throws Exception {
		TransactionDTO transactionDTO = MasterData.getTransactionDTO();
		transactionDTO.setType(null);
		Set<ConstraintViolation<TransactionDTO>> violations = validator.validate(transactionDTO);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}
	
	@Test
	public void testIfPasswordIsNotAcceptingLessThan3CharsInUser() throws Exception {
		UserDTO userDTO = MasterData.getUserDTO();
		userDTO.setPassword(MasterData.randomStringWithSize(2));
		Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDTO);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testIfPasswordIsNotAcceptingMoreThan20CharsInUser() throws Exception {
		UserDTO userDTO = MasterData.getUserDTO();
		userDTO.setPassword(MasterData.randomStringWithSize(21));
		Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDTO);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testIfAgeIsNotNullInUser() throws Exception {
		TransactionDTO transactionDTO = MasterData.getTransactionDTO();
		transactionDTO.setType(null);
		Set<ConstraintViolation<TransactionDTO>> violations = validator.validate(transactionDTO);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}
	
	@Test
	public void testIfAgeMustBeMinimum18InUser() throws Exception {
		UserDTO userDTO = MasterData.getUserDTO();
		userDTO.setAge(17);
		Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDTO);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testIfAgeMustBeMaximum99InUser() throws Exception {
		UserDTO userDTO = MasterData.getUserDTO();
		userDTO.setAge(100);
		Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDTO);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}


	@Test
	public void testIfGenderIsNotNullInUser() throws Exception {
		TransactionDTO transactionDTO = MasterData.getTransactionDTO();
		transactionDTO.setType(null);
		Set<ConstraintViolation<TransactionDTO>> violations = validator.validate(transactionDTO);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}
}
