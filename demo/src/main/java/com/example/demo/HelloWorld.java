package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.aop.LogExecutionTime;
import com.example.demo.exception.ExceptionCollecter;

@RestController
public class HelloWorld {
	
//	@RequestMapping("/entrance")
	@RequestMapping("/hello")
	@ResponseStatus(value = HttpStatus.CREATED)//修改Http狀態: 201
	public String hello(){
		try {
			int failValue = 1 / 0;
			
		} catch (Exception e) {
			// TODO: handle exception
			throw new ExceptionCollecter("0909", "test exception(測試丟出錯誤)");
		}
		return "Hey, Spring Boot 的 Hello World ! ";
	}
	//使用 @LogExecutionTime annotation的方式做AOP
	@RequestMapping("/LogHelloExecute")
	@LogExecutionTime
	public void serve() throws InterruptedException {
		Thread.sleep(2000);
	}

}
