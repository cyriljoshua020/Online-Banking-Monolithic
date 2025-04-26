package com.onlinebanking.service;

import java.util.List;

import com.onlinebanking.dto.AccountDTO;
import com.onlinebanking.entity.Account;

public interface AccountService {
	Account createAccount(AccountDTO accountDTO);

	Account getAccountById(Long id);

	Account getAccountByNumber(String number);

	List<Account> getAllAccounts();

	List<Account> searchAccountsByUser(String userName);

	Account updateAccount(Long id, AccountDTO accountDTO);

	boolean deleteAccount(Long id);
}
