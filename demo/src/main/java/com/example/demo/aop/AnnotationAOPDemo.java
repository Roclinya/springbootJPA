package com.example.demo.aop;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AnnotationAOPDemo {
	
	//使用 @LogExecutionTime annotation的方式做AOP
	@RequestMapping(value = "/LogAnnotationDemo")
	@LogExecutionTime
	public ResponseEntity serve() throws InterruptedException {
		Thread.sleep(2000);
		return new ResponseEntity(HttpStatus.OK);
	}
;
}
