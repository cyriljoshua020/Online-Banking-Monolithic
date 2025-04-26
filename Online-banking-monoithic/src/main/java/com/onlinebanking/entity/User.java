package com.onlinebanking.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 20)
	@Size(min = 3, max = 20)
	private String name;

	@Column(nullable = false, length = 20, unique = true)
	@Size(min = 3, max = 20)
	private String username;

	@Column(nullable = false, length = 20)
	@Size(min = 3, max = 20)
	private String password;

	@Column(nullable = false)
	@Min(18)
	@Max(99)
	private Integer age;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Gender gender;

	public User() {
		super();
	}

	public User(Long id, @Size(min = 3, max = 20) String name, @Size(min = 3, max = 20) String username,
			@Size(min = 3, max = 20) String password, @Min(18) @Max(99) Integer age, Gender gender) {
		super();
		this.id = id;
		this.name = name;
		this.username = username;
		this.password = password;
		this.age = age;
		this.gender = gender;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

}
