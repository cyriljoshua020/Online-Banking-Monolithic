package com.onlinebanking.dto;

import java.math.BigDecimal;



import com.onlinebanking.entity.TransactionType;
import com.onlinebanking.entity.User;

import jakarta.validation.constraints.NotNull;

public class TransactionDTO {
	private Long id;

	@NotNull
	private User user;

	@NotNull
	private BigDecimal amount;

	@NotNull
	private TransactionType type;

	public TransactionDTO() {
		super();
	}

	public TransactionDTO(Long id, User user, BigDecimal amount, TransactionType type) {
		super();
		this.id = id;
		this.user = user;
		this.amount = amount;
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public TransactionType getType() {
		return type;
	}

	public void setType(TransactionType type) {
		this.type = type;
	}

}
