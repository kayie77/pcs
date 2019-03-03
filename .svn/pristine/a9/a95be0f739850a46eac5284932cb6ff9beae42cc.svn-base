package com.yunforge.collect.jackson.serializer;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.yunforge.base.model.User;

public class UserSerializer  extends JsonSerializer<User>
{
	@Override
	public void serialize(User user, JsonGenerator aJsonGenerator, SerializerProvider aSerializerProvider) throws IOException, JsonProcessingException {
		aJsonGenerator.writeString(user.getUsername());
	}
}