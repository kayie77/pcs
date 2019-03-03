package com.yunforge.collect.jackson.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class ReportedTypeSerializer  extends JsonSerializer<Integer>
{
	@Override
	public void serialize(Integer constValue, JsonGenerator aJsonGenerator, SerializerProvider aSerializerProvider) throws IOException, JsonProcessingException {
		String result = "";
		switch (constValue) {
		case 1:
			result = "日报";
			break;
		case 2:
			result = "周报";
			break;
		case 3:
			result = "月报";
			break;
		}
		aJsonGenerator.writeString(result);
	}
}