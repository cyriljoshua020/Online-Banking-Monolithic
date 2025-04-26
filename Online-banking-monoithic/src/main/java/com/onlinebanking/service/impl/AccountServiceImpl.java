package com.onlinebanking.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlinebanking.dto.AccountDTO;
import com.onlinebanking.entity.Account;
import com.onlinebanking.entity.User;
import com.onlinebanking.exception.AlreadyExistsException;
import com.onlinebanking.exception.ResourceNotFoundException;
import com.onlinebanking.repo.AccountRepository;
import com.onlinebanking.repo.UserRepository;
import com.onlinebanking.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public Account createAccount(AccountDTO accountDTO) {
		validateUniqueAccountNumber(accountDTO);
		Account account = new Account();
		account.setUser(accountDTO.getUser());
		account.setNumber(accountDTO.getNumber());
		account.setBalance(accountDTO.getBalance());
		account.setAccountType(accountDTO.getAccountType());
		return accountRepository.save(account);
	}

	@Override
	public Account getAccountById(Long id) {
		return accountRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Account", "id", id));
	}

	@Override
	public Account getAccountByNumber(String number) {
		return accountRepository.findByNumber(number);
	}

	@Override
	public List<Account> getAllAccounts() {
		return accountRepository.findAll();
	}

	@Override
	public List<Account> searchAccountsByUser(String userName) {
		User user = userRepository.findByusername(userName);
		if (user == null) {
			throw new ResourceNotFoundException("Account", "Name: ", userName);
		}
		List<Account> accounts = accountRepository.findByUser(user);
		return accounts;
	}

	@Override
	public Account updateAccount(Long id, AccountDTO account) {
		Account account1 = accountRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Account not found with id", "id", id));
//		validateUniqueAccountNumber(account);
		if (account1 != null) {
			if (account.getNumber().equals(account1.getNumber())) {
				account1.setUser(account.getUser());
				account1.setNumber(account.getNumber());
				account1.setBalance(account.getBalance());
				account1.setAccountType(account.getAccountType());
				return accountRepository.save(account1);
			} else {
				throw new AlreadyExistsException("Cannot update account number : " + account.getNumber());
			}
		}
		return null;
	}

	@Override
	public boolean deleteAccount(Long id) {
		Account account = accountRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Account not found with id", "id", id));

		if(account == null) {
			return false;
		}
		accountRepository.delete(account);
		return true;
	}

	private void validateUniqueAccountNumber(AccountDTO accountDTO) {
		Account account = accountRepository.findByNumber(accountDTO.getNumber());
		if (account != null && account.getId() != accountDTO.getId()) {
			throw new AlreadyExistsException("Account number already exists with this number : " + account.getNumber());
		}
	}
}
