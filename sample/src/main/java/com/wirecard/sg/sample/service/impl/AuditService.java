package com.wirecard.sg.sample.service.impl;

import java.lang.reflect.InvocationTargetException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wirecard.sg.sample.dto.AuditLog;
import com.wirecard.sg.sample.dto.UserDetail;
import com.wirecard.sg.sample.enumator.AuditActions;
import com.wirecard.sg.sample.enumator.CommonEnum;
import com.wirecard.sg.sample.mapper.AuditLogMapper;
import com.wirecard.sg.sample.mapper.UserDetailMapper;
import com.wirecard.sg.sample.mapper.UserMapper;
import com.wirecard.sg.sample.model.ResponsePayload;
import com.wirecard.sg.sample.util.DateUtil;
import com.wirecard.sg.sample.util.ObjectUtil;

@Service
public class AuditService {
	@Autowired
	AuditLogMapper auditLogMapper;

	@Autowired
	UserMapper userMapper;

	@Autowired
	UserDetailMapper userDetailMapper;

	@Autowired
	ObjectMapper mapper;

	// General Method
	protected void saveAuditLog(Object obj, AuditActions actions, String entityName, Authentication authentication)
			throws JsonProcessingException, IllegalArgumentException, IllegalAccessException,
			InvocationTargetException {
		AuditLog auditLog = getAuditLogObject(obj, actions, entityName, authentication);

		if (CommonEnum.HistoryTable.USER_DETAIL_HISTORY.getKey().equals(entityName)) {
			UserDetail preImage = (UserDetail) obj;
			auditLog.setPrimaryKey(preImage.getUserDetailId());
			UserDetail postImage;

			if (actions.equals(AuditActions.UPDATE)) {
				postImage = preImage;
				preImage = userDetailMapper.get(preImage.getUserDetailId());
				auditLog = ObjectUtil.difference(auditLog, preImage, postImage);
			} else {
				auditLog.setPreImage(mapper.writeValueAsString(preImage)).setPostImage(mapper.writeValueAsString(""));
			}

			auditLogMapper.insert(auditLog);
		} else if (CommonEnum.HistoryTable.USER_HISTORY.getKey().equals(entityName)) {
			System.out.println("Method not implement yet.");
		}
	}

	private AuditLog getAuditLogObject(Object obj, AuditActions actions, String table, Authentication authentication) {
		return new AuditLog().setUser(userMapper.findOneByUsername(authentication.getName()))
				.setActionStatus(actions.toString()).setEntityName(table)
				.setLastUpdatedDate(DateUtil.getCurrentDateByString())
				.setLastUpdatedTime(DateUtil.getCurrentTimeByString());
	}

	public ResponsePayload<AuditLog> get(Authentication authentication) {
		return new ResponsePayload<AuditLog>().setAuditLogs(
				auditLogMapper.getByUser(userMapper.findOneByUsername(authentication.getName()).getAuthUserId()));
	}

	public ResponsePayload<AuditLog> getAll() {
		return new ResponsePayload<AuditLog>().setAuditLogs(auditLogMapper.getAll());
	}
}
