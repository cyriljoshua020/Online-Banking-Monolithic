package com.onlinebanking.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onlinebanking.entity.Account;
import com.onlinebanking.entity.User;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
	Account findByNumber(String number);

	List<Account> findByUser_NameContainingIgnoreCase(String userName);

	boolean existsByNumber(String number);

	List<Account> findByUser(User user);
}
