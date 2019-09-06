package com.wirecard.sg.sample.model;

import java.util.List;

import com.wirecard.sg.sample.dto.User;
import com.wirecard.sg.sample.dto.UserDetail;

public class RequestPayload<T> {
	private UserDetail userDetail;
	private User user;
	private List<UserDetail> userDetails;
	private List<User> users;

	public UserDetail getUserDetail() {
		return userDetail;
	}

	public void setUserDetail(UserDetail userDetail) {
		this.userDetail = userDetail;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<UserDetail> getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(List<UserDetail> userDetails) {
		this.userDetails = userDetails;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
}
