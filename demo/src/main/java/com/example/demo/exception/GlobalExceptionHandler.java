package com.example.demo.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//Advice也是一種AOP的實現
@ControllerAdvice
public class GlobalExceptionHandler {
	// 捕捉其他所有异常
//	@ExceptionHandler(Exception.class)
	//捕捉ExceptionCollecter的異常執行GlobalExceotion()方法處理
	@ExceptionHandler(ExceptionCollecter.class)
//	@ResponseStatus(HttpStatus.BAD_REQUEST) 此處設定沒有效用
	// 將外部的Exception內容透過參數傳進來
	public ResponseEntity<Object> GlobalExceotion(ExceptionCollecter exception) {
		Logger myLogger = LoggerFactory.getLogger(exception.getClass().getName());
		myLogger.error("Global Exception Handler. Message: {}", exception.getMessage());
		myLogger.error("Error Code is " + exception.getStrExceptionCode());
		// 組裝Response Body
//		String jsonRspBody = "this is Error Message: " + exception.getMessage();
		String jsonRspBody = "Error Message: " + exception.getStrExceptionMessage();
		// 設定Response的Header資訊
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=utf-8");

		// 回覆一個Response實體,並且定義他的body,headers,http狀態碼
		return new ResponseEntity<>(jsonRspBody, headers, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
