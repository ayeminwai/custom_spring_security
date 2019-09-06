package com.wirecard.sg.sample.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.wirecard.sg.sample.dto.UserDetail;
import com.wirecard.sg.sample.enumator.AuditActions;
import com.wirecard.sg.sample.enumator.CommonEnum;
import com.wirecard.sg.sample.exception.NullException;
import com.wirecard.sg.sample.mapper.UserDetailMapper;
import com.wirecard.sg.sample.mapper.UserMapper;
import com.wirecard.sg.sample.model.ResponsePayload;
import com.wirecard.sg.sample.service.GeneralServices;
import com.wirecard.sg.sample.util.MsgUtil;

@Service
public class UserDetailService implements GeneralServices<UserDetail> {
	@Autowired
	UserDetailMapper userDetailMapper;

	@Autowired
	AuditService auditService;

	@Autowired
	UserMapper userMapper;

	@Override
	public ResponsePayload<UserDetail> get(Integer id) {
		return new ResponsePayload<UserDetail>().setUserDetail(userDetailMapper.get(id));
	}

	@Override
	public ResponsePayload<UserDetail> getAll() {
		return new ResponsePayload<UserDetail>().setUserDetails(userDetailMapper.getAll());
	}

	@Transactional
	@Override
	public ResponsePayload<UserDetail> insert(UserDetail userDetail, Authentication authenication) throws NoSuchAlgorithmException, JsonProcessingException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		validateUserDetail(userDetail);
		if (userDetail.getUserDetailId() == null) {
			userDetailMapper.insert(userDetail);
			auditService.saveAuditLog(userDetail, AuditActions.INSERT,CommonEnum.HistoryTable.USER_DETAIL_HISTORY.getKey(), authenication);
			return new ResponsePayload<UserDetail>(MsgUtil.USER_DETAIL_SAVE_SUCCESS);
		} else {
			auditService.saveAuditLog(userDetail, AuditActions.UPDATE,CommonEnum.HistoryTable.USER_DETAIL_HISTORY.getKey(), authenication);
			userDetailMapper.update(userDetail);
			return new ResponsePayload<UserDetail>(MsgUtil.USER_DETAIL_UPDATE_SUCCESS);
		}
	}

	@Transactional
	@Override
	public void delete(Integer id, Authentication authentication) throws JsonProcessingException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		auditService.saveAuditLog(userDetailMapper.get(id), AuditActions.DELETE, CommonEnum.HistoryTable.USER_DETAIL_HISTORY.getKey(), authentication);
		userDetailMapper.delete(id);
	}

	private void validateUserDetail(UserDetail userDetail) {
		if (userDetail == null)
			throw new NullException(MsgUtil.USER_DETAIL_NOT_NULL);
		if (userDetail.getUserName() == null)
			throw new NullException(MsgUtil.USER_DETAIL_NAME_NOT_NULL);
	}

}
