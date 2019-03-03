package com.yunforge.collect.jackson.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class DataIDSerializer  extends JsonSerializer<String>
{
	@Override
	public void serialize(String constValue, JsonGenerator aJsonGenerator, SerializerProvider aSerializerProvider) throws IOException, JsonProcessingException {
		String result = "";
		switch (constValue) {
		case "11":
			result = "产量";
			break;
		case "21":
			result = "销量";
			break;
		case "31":
			result = "批发价";
			break;
		case "41":
			result = "市场价";
			break;
		case "42":
			result = "市场最高价";
			break;
		case "43":
			result = "市场最低价";
			break;
		case "44":
			result = "市场平均价";
			break;
		case "45":
			result = "市场大宗价";
			break;
		}
		aJsonGenerator.writeString(result);
	}
}