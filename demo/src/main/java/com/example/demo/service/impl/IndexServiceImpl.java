package com.example.demo.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Member;
import com.example.demo.repository.MemberRepository;
import com.example.demo.service.IndexService;

@Service
public class IndexServiceImpl implements IndexService {
	
	@Autowired
	private MemberRepository memberRepository;

	@Override
	public Member findeMember(Long id) {
		Optional<Member> o = memberRepository.findById(id);
		if(o.isEmpty()) {
			return new Member();
		}
		//回傳根據id所找到的member物件的value
		return memberRepository.findById(id).get();
	}

}