package com.yunforge.collect.jackson.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class ReceiveStatusSerializer  extends JsonSerializer<Integer>
{
	@Override
	public void serialize(Integer constValue, JsonGenerator aJsonGenerator, SerializerProvider aSerializerProvider) throws IOException, JsonProcessingException {
		String result = "";
		switch (constValue) {
		case 0:
			result = "未领取";
			break;
		case 1:
			result = "已领取";
			break;
		}
		aJsonGenerator.writeString(result);
	}
}