package com.onlinebanking.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.onlinebanking.dto.TransactionDTO;
import com.onlinebanking.entity.Transaction;
import com.onlinebanking.entity.TransactionType;
import com.onlinebanking.exception.ResourceNotFoundException;
import com.onlinebanking.repo.TransactionRepository;
import com.onlinebanking.service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService {
	private final TransactionRepository transactionRepository;

	public TransactionServiceImpl(TransactionRepository transactionRepository) {
		this.transactionRepository = transactionRepository;
	}

	@Override
	public Transaction createTransaction(TransactionDTO transactionDTO) {
		Transaction transaction = new Transaction();
		transaction.setUser(transactionDTO.getUser());
		transaction.setAmount(transactionDTO.getAmount());
		transaction.setType(transactionDTO.getType());
		return transactionRepository.save(transaction);
	}

	@Override
	public Transaction getTransactionById(Long id) {
		return transactionRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Transaction", "id", id));
	}

	@Override
	public List<Transaction> getAllTransactions() {
		return transactionRepository.findAll();
	}

	@Override
	public List<Transaction> searchTransactionsByType(TransactionType type) {
		return transactionRepository.findByType(type);
	}

	@Override
	public Transaction updateTransaction(Long id, TransactionDTO transactionDTO) {
		Transaction transaction = transactionRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Transaction", "id", id));
		if (transaction != null) {
			transaction.setUser(transactionDTO.getUser());
			transaction.setAmount(transactionDTO.getAmount());
			transaction.setType(transactionDTO.getType());
			return transactionRepository.save(transaction);
		}
		return null;
	}

	@Override
	public boolean deleteTransaction(Long id) {
		Transaction transaction = transactionRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Transaction", "id", id));

		if(transaction == null) {
			return false;
		}
		transactionRepository.delete(transaction);
		return true;
	}
}
