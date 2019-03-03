package com.yunforge.collect.jackson.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class EnableOrDisableSerializer  extends JsonSerializer<Integer>
{
	@Override
	public void serialize(Integer value, JsonGenerator jsonGenerator, SerializerProvider aSerializerProvider) throws IOException, JsonProcessingException {
		if(value == 1){
			jsonGenerator.writeString("启用");
		}else{
			jsonGenerator.writeString("停用");
		}
	}
}