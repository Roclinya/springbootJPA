package com.example.demo.configuration;

import java.sql.Date;

import org.springframework.boot.json.JsonParser;
import org.springframework.context.annotation.*;

import com.example.demo.serializer.SqlDateSerializer;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.std.*;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers.DateDeserializer;
import com.fasterxml.jackson.databind.deser.std.NumberDeserializers.BooleanDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.*;

@Configuration
public class ObjectMapperConfig {
	
	   @Bean
	    public ObjectMapper objectMapper() {
	        return createObjectMapper();
	    }
	   
	   private ObjectMapper createObjectMapper(){
	        ObjectMapper objectMapper = new ObjectMapper();
	        SimpleModule simpleModule = new SimpleModule();
	        /**
	         * 序列化：对象=>jsonString
	         */
//	        simpleModule.addSerializer(WashEnum.class, new WashEnumSerializer());
//	        simpleModule.addSerializer(IEnum.class, new EnumSerializer());
//	        simpleModule.addSerializer(Date.class, new DateSerializer());
//	        simpleModule.addSerializer(Boolean.class, new BooleanSerializer());
	        simpleModule.addSerializer(Date.class, new SqlDateSerializer());
	        //忽略null字段
	        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
	        /**
	         * 反序列化：jsonString=>对象
	         */
	        //允许json属性名不使用双引号
//	        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
	        //忽略不存在字段
	        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	        simpleModule.addDeserializer(String.class, new StringDeserializer());
//	        simpleModule.addDeserializer(Date.class, new DateDeserializer());
//	        simpleModule.addDeserializer(WashEnum.class, new WashEnumDeserializer());
//	        simpleModule.addDeserializer(Enum.class, new EnumDeserializer());//反序列化枚举，
//	        simpleModule.addDeserializer(Boolean.class, new BooleanDeserializer());
	        objectMapper.registerModule(simpleModule);
	        return objectMapper;
	    }

}
