package com.example.demo.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Account;

//@Transactional
@Repository
public interface AccountRepository extends JpaRepository<Account, String> {

	List<Account> findByAccountNumber(String accountNumber);

	@Query("select a from Account a where a.accountNumber = :accountNumber")
	List<Account> selectAccountTypeByAccountNumber(@Param("accountNumber") String accountNumber);

}
