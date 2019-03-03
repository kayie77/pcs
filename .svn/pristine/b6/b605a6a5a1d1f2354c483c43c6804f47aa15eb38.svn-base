/**   
 * @Title: JsonDateSerializer.java 
 * @Package com.yunforge.common.spring 
 * @Description: TODO 
 * @author Oliver Wen  
 * @date 2015年10月9日 下午1:44:55 
 * @version V1.0   
 */
package com.yunforge.core.web.springmvc;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * @ClassName: JsonDateSerializer
 * @Description: TODO
 * @author Oliver Wen
 * @date 2015年10月9日 下午1:44:55
 * 
 */
@Component
public class JsonDateSerializer extends JsonSerializer<Date> {

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	@Override
	public void serialize(Date date, JsonGenerator gen,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {

		String formattedDate = dateFormat.format(date);

		gen.writeString(formattedDate);
	}

}
