package com.yunforge.collect.jackson.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class TaskStatusSerializer  extends JsonSerializer<Integer>
{
	@Override
	public void serialize(Integer constValue, JsonGenerator aJsonGenerator, SerializerProvider aSerializerProvider) throws IOException, JsonProcessingException {
		String result = "";
		switch (constValue) {
		case 0:
			result = "未启用";
			break;
		case 1:
			result = "在用";
			break;
		case -1:
			result = "到期";
			break;
		case -2:
			result = "停用";
			break;
		}
		aJsonGenerator.writeString(result);
	}
}