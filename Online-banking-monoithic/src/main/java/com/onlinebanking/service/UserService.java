package com.onlinebanking.service;

import java.util.List;

import com.onlinebanking.dto.UserDTO;
import com.onlinebanking.entity.User;

public interface UserService {
	User createUser(UserDTO userDTO);

	List<User> getAllUsers();

	List<User> searchUsersByName(String name);

	User getUserById(Long id);

	User updateUser(Long id, UserDTO userDTO);

	boolean deleteUser(Long id);
}
