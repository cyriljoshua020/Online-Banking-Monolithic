package com.onlinebanking.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.onlinebanking.dto.TransactionDTO;
import com.onlinebanking.entity.Transaction;
import com.onlinebanking.entity.TransactionType;
import com.onlinebanking.service.TransactionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/transactions")
@CrossOrigin
public class TransactionController {
	
	@Autowired
	private TransactionService transactionService;

	@PostMapping
	public ResponseEntity<Transaction> createTransaction(@Valid @RequestBody TransactionDTO transactionDTO) {
		Transaction createdTransaction = transactionService.createTransaction(transactionDTO);
		return new ResponseEntity<>(createdTransaction, HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<Transaction>> getAllTransactions() {
		List<Transaction> transactions = transactionService.getAllTransactions();
		return new ResponseEntity<>(transactions, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Transaction> getTransactionById(@PathVariable("id") Long id) {
		Transaction transaction = transactionService.getTransactionById(id);
		return new ResponseEntity<>(transaction, HttpStatus.OK);
	}

	@GetMapping("/search")
	public ResponseEntity<List<Transaction>> searchTransactionsByType(@RequestParam("type") TransactionType type) {
		List<Transaction> transactions = transactionService.searchTransactionsByType(type);
		return new ResponseEntity<>(transactions, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Transaction> updateTransaction(@Valid @PathVariable("id") Long id,
			@RequestBody TransactionDTO transactionDTO) {
		Transaction updatedTransaction = transactionService.updateTransaction(id, transactionDTO);
		return new ResponseEntity<>(updatedTransaction, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteTransaction(@PathVariable("id") Long id) {
		transactionService.deleteTransaction(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
