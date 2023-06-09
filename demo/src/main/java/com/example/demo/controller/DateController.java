package com.example.demo.controller;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.repository.DateEntityRepository;

@RestController
@RequestMapping(value = "/dateApi")
public class DateController {
	
	@Autowired
	DateEntityRepository  dateEntityRepository;
	
	@RequestMapping("/updateBirthday")
	void updateBirthday(@RequestParam(name = "sqlDate") Date sqlDate){
		dateEntityRepository.updateBirthday(sqlDate);
	};

}
