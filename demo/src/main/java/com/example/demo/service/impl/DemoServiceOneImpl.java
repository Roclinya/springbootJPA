package com.example.demo.service.impl;

import org.springframework.stereotype.Service;

import com.example.demo.service.DemoService;


@Service("one") // <--
public class DemoServiceOneImpl implements DemoService {

	@Override
	public void doService() {
		System.out.println("This is one...");

	}

}
