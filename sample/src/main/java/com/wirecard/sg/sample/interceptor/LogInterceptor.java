package com.wirecard.sg.sample.interceptor;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.ContentCachingResponseWrapper;
import org.springframework.web.util.WebUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wirecard.sg.sample.model.Log;

public class LogInterceptor extends HandlerInterceptorAdapter {
	private static final Logger logger = LogManager.getLogger(LogInterceptor.class);

	@Autowired
	ObjectMapper mapper;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws IOException {
		log(request, response, handler);
		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		super.afterCompletion(request, response, handler, ex);
	}

	public void log(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
		Log log = new Log();
		log.setHttpStatus(response.getStatus());
		log.setHttpMethod(request.getMethod());
		log.setPath(request.getRequestURI());
		log.setClientIp(request.getRemoteAddr());
		log.setJavaMethod(handler.toString());
		log.setResponse(getResponsePayload(response));
		logger.debug(mapper.writeValueAsString(log));
	}

	private String getResponsePayload(HttpServletResponse response) {
		ContentCachingResponseWrapper wrapper = WebUtils.getNativeResponse(response,
				ContentCachingResponseWrapper.class);
		if (wrapper != null) {

			byte[] buf = wrapper.getContentAsByteArray();
			if (buf.length > 0) {
				int length = Math.min(buf.length, 5120);
				try {
					return new String(buf, 0, length, wrapper.getCharacterEncoding());
				} catch (UnsupportedEncodingException ex) {
				}
			}
		}
		return "[unknown]";
	}
}
