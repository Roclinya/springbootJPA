package com.example.demo.service.impl;

import com.example.demo.entity.Member;
import com.example.demo.repository.MemberRepository;
import com.example.demo.service.CheckTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class CheckTransactionServiceimpl implements CheckTransactionService {
    @Autowired
    MemberRepository memberRepository;
    @Override
    public void checkData() {
        // 測試只抓該Entity的單筆資料回傳,使用select回傳單筆資料
        Member singleResult = memberRepository.getSingleResult();
        singleResult.seteMail("NewEamil@gmail.com");
        Optional<Member> data = memberRepository.findById(1L);
        data.ifPresent(e-> System.out.println("Before Save "+data.get().geteMail())); //Before Save 1@gmail.com
        memberRepository.save(singleResult);
        data.ifPresent(e-> System.out.println("After Save "+data.get().geteMail())); //After Save 1@gmail.com
    }

}
