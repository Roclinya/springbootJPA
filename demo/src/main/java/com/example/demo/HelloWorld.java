package com.example.demo;

import java.io.InterruptedIOException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.aop.LogExecutionTime;
import com.example.demo.exception.ExceptionCollecter;

@RestController
public class HelloWorld {
	
//	@RequestMapping("/entrance")
	@RequestMapping("/execute")
	@ResponseStatus(value = HttpStatus.CREATED)//修改Http狀態: 201	
	public void execute()  throws Exception{
		deals() ;
	}
	public void deals()  throws InterruptedIOException{
		System.out.println("file execute");
		try{
			hello();
		}catch(ExceptionCollecter e){
		    System.out.println("Exception Code: " + e.getStrExceptionCode());
		    System.out.println("Exception Message: " + e.getMessage()); // 此處會取得 strExceptionMessage
//			throw ie;
			/**
			 * 使用initCause函數，把hello函數中的ExceptionCollecter轉爲Exception
			 * 並把ExceptionCollecter傳遞給外層。
			 */
			InterruptedIOException ie = new InterruptedIOException("file is interrupted");
			ie.initCause(e);
			throw ie;
		}
		
	}
	
	
	
	public String hello(){
		try {
			int failValue = 1 / 0;
			
		} catch (Exception e) {
			// TODO: handle exception
//			ExceptionCollecter ie = new ExceptionCollecter("0909", "test exception(測試丟出錯誤)");
//			ExceptionCollecter ie = new ExceptionCollecter("0909", "test exception(測試丟出錯誤)");
//			ie.initCause(e);
//			throw ie;
			throw new ExceptionCollecter("0909",e);
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
