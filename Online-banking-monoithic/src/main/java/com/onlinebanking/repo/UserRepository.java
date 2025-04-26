package com.onlinebanking.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onlinebanking.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	List<User> findByNameContaining(String name);

	User findByUsername(String username);

	User findByusername(String userName);
}
