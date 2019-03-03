package com.yunforge.collect.jackson.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class DataPonitTypeSerializer  extends JsonSerializer<Integer>
{
	@Override
	public void serialize(Integer constValue, JsonGenerator aJsonGenerator, SerializerProvider aSerializerProvider) throws IOException, JsonProcessingException {
		String result = "";
		switch (constValue) {
		case 1:
			result = "农贸市场";
			break;
		case 2:
			result = "批发市场";
			break;
		case 3:
			result = "龙头企业";
			break;
		case 4:
			result = "合作组织";
			break;
		}
		aJsonGenerator.writeString(result);
	}
}