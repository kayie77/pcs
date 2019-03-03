package com.yunforge.collect.jackson.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class NullableSerializer  extends JsonSerializer<Integer>
{
	@Override
	public void serialize(Integer constValue, JsonGenerator aJsonGenerator, SerializerProvider aSerializerProvider) throws IOException, JsonProcessingException {
		String result = "";
		switch (constValue) {
		case 1:
			result = "必填";
			break;
		case 2:
			result = "选填";
			break;
		}
		aJsonGenerator.writeString(result);
	}
}