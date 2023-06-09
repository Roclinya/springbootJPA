package com.example.demo.controller;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.*;
import com.example.demo.serializer.SqlDateSerializer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

@RestController
@RequestMapping(value = "/mapperApi")
public class ObjectMapperController {
	@Autowired
	ObjectMapper objectMapper;

	//SqlDateSerializer設定sql date格式為yyyy/MM/dd
	//localhost:8080/mapperApi/test01
	@RequestMapping("/test01")
	void test01() throws JsonProcessingException {

		Date time = new Date(System.currentTimeMillis());// sql date  yyyy-MM-dd
		DateMapperEntity2 dateMapperEntity2 = new DateMapperEntity2(new DateMapperEntity("name1", time), "name2Value");

//		 ObjectMapper mapper = new ObjectMapper();
//		 
//		 SimpleModule module = new SimpleModule();
//		 module.addSerializer(DateMapperEntity.class, new SqlDateSerializer());
//		 mapper.registerModule(module);
//
//		 String serialized = mapper.writeValueAsString(dateMapperEntity2);
		//經由SqlDateSerializer轉成 yyyy/MM/dd
		//結果 {"dateMapperEntity":{"name":"name1","date":"2023/05/19"},"name2":"name2Value"}
		System.out.println("結果 " + objectMapper.writeValueAsString(dateMapperEntity2));

	};

}
