package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Account;
import com.example.demo.repository.AccountRepository;

@RestController
@RequestMapping(value = "/account")
public class AccountController {

	@Autowired
	AccountRepository accountRepository;

	private static final String PAGE = "index";

	//分別使用Query JPQL與Derived Query(不寫JPQL)的方式取得AccountNumber
	//localhost:8080/account/getAccountTypeByNumber?accountNumber=002
	// Derived Query Methods
	@RequestMapping("/getAccountTypeByNumber")
	ResponseEntity<String> getAccountTypeByNumber(@RequestParam(name = "accountNumber") String accountNumber) {
		List<Account> result = accountRepository.findByAccountNumber(accountNumber);
		String accountType = result.get(0).getAccountType();
		System.out.println(accountType);
		return ResponseEntity.ok(accountType);
	}

	// Without Derived Query Methods
	//localhost:8080/account/selectAccountTypeByAccountNumber?accountNumber=002
	@RequestMapping("/selectAccountTypeByAccountNumber")
	ResponseEntity<String> selectAccountTypeByAccountNumber(
			@RequestParam(name = "accountNumber") String accountNumber) {
		List<Account> result = accountRepository.selectAccountTypeByAccountNumber(accountNumber);
		String accountType = result.get(0).getAccountType();
		System.out.println(accountType);
		return ResponseEntity.ok(accountType);
	}

}
