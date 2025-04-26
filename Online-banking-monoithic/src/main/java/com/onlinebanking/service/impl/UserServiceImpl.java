package com.onlinebanking.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlinebanking.dto.UserDTO;
import com.onlinebanking.entity.Account;
import com.onlinebanking.entity.Transaction;
import com.onlinebanking.entity.User;
import com.onlinebanking.exception.AlreadyExistsException;
import com.onlinebanking.exception.ResourceNotFoundException;
import com.onlinebanking.repo.AccountRepository;
import com.onlinebanking.repo.TransactionRepository;
import com.onlinebanking.repo.UserRepository;
import com.onlinebanking.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	private AccountRepository accountRepository;

	@Override
	public User createUser(UserDTO userDTO) {
		validateUniqueUsername(userDTO);
		User user = new User();
		user.setName(userDTO.getName());
		user.setUsername(userDTO.getUsername());
		user.setPassword(userDTO.getPassword());
		user.setAge(userDTO.getAge());
		user.setGender(userDTO.getGender());
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public List<User> searchUsersByName(String name) {
		return userRepository.findByNameContaining(name);
	}

	@Override
	public User getUserById(Long id) {
		return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
	}

	@Override
	public User updateUser(Long id, UserDTO userDTO) {
//		validateUniqueUsername(userDTO);
		User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
		if (user != null) {
			if (userDTO.getUsername().equals(user.getUsername())) {
				user.setName(userDTO.getName());
				user.setUsername(userDTO.getUsername());
				user.setPassword(userDTO.getPassword());
				user.setAge(userDTO.getAge());
				user.setGender(userDTO.getGender());
				return userRepository.save(user);
			} else {
				throw new AlreadyExistsException("Cannot update username : " + user.getUsername());
			}
		}
		return null;
	}

	@Override
	public boolean deleteUser(Long id) {
		User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
		if (user == null) {
			return false;
		}

		// Delete associated account records
		List<Account> accounts = accountRepository.findByUser(user);
		accountRepository.deleteInBatch(accounts);

		// Delete associated transaction records
		List<Transaction> transactions = transactionRepository.findByUser(user);
		transactionRepository.deleteInBatch(transactions);

		userRepository.delete(user);
		return true;
	}

	private void validateUniqueUsername(UserDTO userDTO) {
		User user = userRepository.findByUsername(userDTO.getUsername());
		if (user != null && user.getId() != userDTO.getId()) {
			throw new AlreadyExistsException("User already exists with this username : " + user.getUsername());
		}
	}
}
