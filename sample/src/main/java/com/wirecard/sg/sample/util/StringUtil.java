package com.wirecard.sg.sample.util;

import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class StringUtil {
	public static boolean isEqual(String str1, String str2) {
		return str1.equals(str2);
	}

	@SuppressWarnings("unchecked")
	public static Map<String, Object> jsonStringToMap(String json) throws JsonParseException, JsonMappingException, IOException {
		return new ObjectMapper().readValue(json, Map.class);
	}
}
