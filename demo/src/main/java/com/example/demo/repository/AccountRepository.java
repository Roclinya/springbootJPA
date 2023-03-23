package com.example.demo.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Account;

@Transactional
@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
	
	List<Account> findByAccountNumber(String accountNumber);
	
	 
}
