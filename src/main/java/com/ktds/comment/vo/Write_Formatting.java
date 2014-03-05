package com.ktds.comment.vo;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

public class Write_Formatting extends JsonSerializer<Timestamp> {
	@Override
	public void serialize(Timestamp comment_date, JsonGenerator gen,
			SerializerProvider arg2) throws IOException,
			JsonProcessingException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");
		String formattedDate = formatter.format(comment_date);

		gen.writeString(formattedDate);

	}
}
