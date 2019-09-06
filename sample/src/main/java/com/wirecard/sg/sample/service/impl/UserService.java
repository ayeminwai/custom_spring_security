package com.wirecard.sg.sample.service.impl;

import java.security.NoSuchAlgorithmException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wirecard.sg.sample.dto.User;
import com.wirecard.sg.sample.exception.NullException;
import com.wirecard.sg.sample.exception.PasswordException;
import com.wirecard.sg.sample.mapper.UserMapper;
import com.wirecard.sg.sample.model.ResponsePayload;
import com.wirecard.sg.sample.service.GeneralServices;
import com.wirecard.sg.sample.util.CryptoUtil;
import com.wirecard.sg.sample.util.MsgUtil;
import com.wirecard.sg.sample.util.StringUtil;

@Service
public class UserService implements GeneralServices<User> {
	private static final Logger logger = LogManager.getLogger(UserService.class);
	@Autowired
	UserMapper userMapper;
	
	@Autowired
	AuditService auditService;

	@Override
	public ResponsePayload<User> get(Integer id) {
		return new ResponsePayload<User>().setUser(userMapper.get(id));
	}

	@Override
	public ResponsePayload<User> getAll() {
		return new ResponsePayload<User>().setUsers(userMapper.getAll());
	}

	@Override
	public ResponsePayload<User> insert(User user, Authentication authentication) throws NoSuchAlgorithmException {
		validateUser(user);
		if (user.getAuthUserId() == null) {
			user.setPassword(CryptoUtil.encryptPassword(user.getPassword()));
			userMapper.insert(user);
			return new ResponsePayload<User>("Register successfully.");
		} else {
			return new ResponsePayload<User>("User already registered. Go to login page.");
		}
	}

	@Transactional
	@Override
	public void delete(Integer id, Authentication authentication) {
		
		userMapper.delete(id);
	}

	private void validateUser(User user) {
		if (user == null)
			throw new NullException(MsgUtil.USER_NOT_NULL);
		if (user.getUsername() == null)
			throw new NullException(MsgUtil.USER_NAME_NOT_NULL);
		if (user.getPassword() == null)
			throw new NullException(MsgUtil.USER_PASSWORD_NOT_NULL);
		if (user.getRole() == null)
			throw new NullException(MsgUtil.USER_ROLE_NOT_NULL);
		if (!StringUtil.isEqual(user.getPassword(), user.getConfirmPassword()))
			throw new PasswordException(MsgUtil.CONFIRM_PASSWORD_UNMATCH);
	}

	public ResponsePayload<User> forgetPassword(User user) {
		validateUser(user);
		try {
			user.setPassword(CryptoUtil.encryptPassword(user.getPassword()));
		} catch (NoSuchAlgorithmException e) {
			logger.warn("Please check the password");
			e.printStackTrace();
		}
		
		userMapper.forgetPassword(user);
		return new ResponsePayload<User>("Password changed successfully.");
	}
}
