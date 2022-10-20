package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Member;

public interface IndexService {
	
	Member findeMember(Long id);
	
	List contextLoads();
}