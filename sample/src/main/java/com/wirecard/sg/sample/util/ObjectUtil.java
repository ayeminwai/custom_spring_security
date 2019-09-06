package com.wirecard.sg.sample.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wirecard.sg.sample.dto.AuditLog;

public class ObjectUtil {
	public static AuditLog difference(AuditLog auditLog, Object v1, Object v2) throws IllegalArgumentException,
			IllegalAccessException, InvocationTargetException, JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		HashMap<String, Object> preI = new HashMap<>(), postI = new HashMap<>(), tmpPreI = new HashMap<>();
		for (Method m1 : v1.getClass().getMethods()) {
			if (isGetter(m1)) {
				tmpPreI.put(m1.getName().substring(3), m1.invoke(v1));
			}
		}

		for (Method m2 : v2.getClass().getMethods()) {
			if (isGetter(m2)) {
				if (!tmpPreI.containsValue(m2.invoke(v2))) {
					postI.put(m2.getName().substring(3), m2.invoke(v2));
					preI.put(m2.getName().substring(3), tmpPreI.get(m2.getName().substring(3)));
				}
			}
		}
		return auditLog.setPreImage(mapper.writeValueAsString(preI)).setPostImage(mapper.writeValueAsString(postI.isEmpty()? "": postI));
	}

	private static boolean isGetter(Method method) {
		if (!method.getName().startsWith("get"))
			return false;
		if (method.getParameterTypes().length != 0)
			return false;
		if (void.class.equals(method.getReturnType()))
			return false;
		return true;
	}
}
