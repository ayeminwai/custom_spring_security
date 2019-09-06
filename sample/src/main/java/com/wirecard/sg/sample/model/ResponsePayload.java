package com.wirecard.sg.sample.model;

import java.util.List;

import com.wirecard.sg.sample.dto.AuditLog;
import com.wirecard.sg.sample.dto.User;
import com.wirecard.sg.sample.dto.UserDetail;

public class ResponsePayload<T extends Object> {

	private String message;
	private UserDetail userDetail;
	private List<UserDetail> userDetails;
	private User user;
	private List<User> users;
	private List<T> lists;
	private List<AuditLog> auditLogs;

	public ResponsePayload() {
		super();
	}
	
	public ResponsePayload(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public UserDetail getUserDetail() {
		return userDetail;
	}

	public ResponsePayload<T> setUserDetail(UserDetail userDetail) {
		this.userDetail = userDetail;
		return this;
	}

	public List<UserDetail> getUserDetails() {
		return userDetails;
	}

	public ResponsePayload<T> setUserDetails(List<UserDetail> userDetails) {
		this.userDetails = userDetails;
		return this;
	}

	public User getUser() {
		return user;
	}

	public ResponsePayload<T> setUser(User user) {
		this.user = user;
		return this;
	}

	public List<User> getUsers() {
		return users;
	}

	public ResponsePayload<T> setUsers(List<User> users) {
		this.users = users;
		return this;
	}

	public List<T> getLists() {
		return lists;
	}

	public ResponsePayload<T> setLists(List<T> lists) {
		this.lists = lists;
		return this;
	}

	public List<AuditLog> getAuditLogs() {
		return auditLogs;
	}

	public ResponsePayload<T> setAuditLogs(List<AuditLog> auditLogs) {
		this.auditLogs = auditLogs;
		return this;
	}
}
