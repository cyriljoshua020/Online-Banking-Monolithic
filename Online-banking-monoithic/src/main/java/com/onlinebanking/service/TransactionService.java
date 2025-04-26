package com.onlinebanking.service;

import java.util.List;

import com.onlinebanking.dto.TransactionDTO;
import com.onlinebanking.entity.Transaction;
import com.onlinebanking.entity.TransactionType;

public interface TransactionService {
	Transaction createTransaction(TransactionDTO transactionDTO);

	Transaction getTransactionById(Long id);

	List<Transaction> getAllTransactions();

	List<Transaction> searchTransactionsByType(TransactionType type);

	Transaction updateTransaction(Long id, TransactionDTO transactionDTO);

	boolean deleteTransaction(Long id);
}
