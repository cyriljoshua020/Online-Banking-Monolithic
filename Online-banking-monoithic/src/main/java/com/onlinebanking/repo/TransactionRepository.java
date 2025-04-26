package com.onlinebanking.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onlinebanking.entity.Transaction;
import com.onlinebanking.entity.TransactionType;
import com.onlinebanking.entity.User;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
	List<Transaction> findByType(TransactionType type);

	List<Transaction> findByUser(User user);
}
