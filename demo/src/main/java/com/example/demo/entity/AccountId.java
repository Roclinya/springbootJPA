package com.example.demo.entity;

import java.io.Serializable;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountId implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
    private String accountNumber;

    private String accountType;

    // default constructor

//    public AccountId(String accountNumber, String accountType) {
//        this.accountNumber = accountNumber;
//        this.accountType = accountType;
//    }

    // equals() and hashCode()
}
