package com.example.demo.entity;

import javax.persistence.*;

import lombok.Data;

@Entity
@IdClass(AccountId.class)
@Table(name = "account")
@Data
public class Account {
    @Id
    private String accountNumber;

    @Id
    private String accountType;

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

    
    
    // other fields, getters and setters
}