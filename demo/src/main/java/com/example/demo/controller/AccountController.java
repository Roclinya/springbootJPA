package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.*;
import com.example.demo.repository.AccountRepository;

@RestController
@RequestMapping(value = "/account")
public class AccountController {

	@Autowired
	AccountRepository accountRepository;

	private static final String PAGE = "index";

	@RequestMapping("/getAccountTypeByNumber")
	ResponseEntity<String> getAccountTypeByNumber(@RequestParam(name = "accountNumber") String accountNumber) {
		List<Account> result = accountRepository.findByAccountNumber(accountNumber);
		String accountType = result.get(0).getAccountType();
		System.out.println(accountType);
		return ResponseEntity.ok(accountType);
	}

	

}
