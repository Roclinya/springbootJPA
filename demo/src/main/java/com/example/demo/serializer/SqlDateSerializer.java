package com.example.demo.serializer;

import java.io.IOException;
import java.sql.Date;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.*;

public class SqlDateSerializer extends JsonSerializer <Date>{
	
	private static final DateTimeFormatter dtfTime = DateTimeFormatter.ofPattern("yyyy/MM/dd");

	@Override
	public void serialize(Date value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
		gen.writeString(value.toLocalDate().format(dtfTime));
	}



}
