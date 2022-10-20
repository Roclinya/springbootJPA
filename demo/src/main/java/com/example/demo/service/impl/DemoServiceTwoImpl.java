package com.example.demo.service.impl;

import org.springframework.stereotype.Service;

import com.example.demo.service.DemoService;

@Service("two") // <--
public class DemoServiceTwoImpl implements DemoService {

	@Override
	public void doService() {
		System.out.println("This is two...");

	}

}
