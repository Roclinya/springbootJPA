package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Member;
import com.example.demo.repository.MemberRepository;

@Service
public class userDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private MemberRepository memberRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
		 Member entity = new Member();
		 entity.setId(1L);
		 entity.setUsrName("user1");
		 entity.setUsrPwd("user1");
		 memberRepository.save(entity);
		 
		Member member = memberRepository.findById(1L).get();
				 
      UserDetails userDetails = User.builder()
    		  .username(member.getUsrName())
    		  .password("{noop}"+member.getUsrPwd()) 
    		  .roles("USER")
    		  .build();
//      UserDetails userDetails = User.builder()
//    		  .username("user123")
//    		  .password("{noop}123")
//    		  .roles("USER")
//    		  .build();
	
		return userDetails;
	}



}
