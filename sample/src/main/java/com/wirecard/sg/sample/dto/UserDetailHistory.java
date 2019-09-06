package com.wirecard.sg.sample.dto;

public class UserDetailHistory {
	private Integer userDetailHistoryId;
	private Integer userDetailId;
	private String userName;
	private Integer age;
	private String gender;
	private String address;
	private String phone;

	public UserDetailHistory() {
		// TODO Auto-generated constructor stub
	}

	public Integer getUserDetailHistoryId() {
		return userDetailHistoryId;
	}

	public UserDetailHistory setUserDetailHistoryId(Integer userDetailHistoryId) {
		this.userDetailHistoryId = userDetailHistoryId;
		return this;
	}

	public Integer getUserDetailId() {
		return userDetailId;
	}

	public UserDetailHistory setUserDetailId(Integer userDetailId) {
		this.userDetailId = userDetailId;
		return this;
	}

	public String getUserName() {
		return userName;
	}

	public UserDetailHistory setUserName(String userName) {
		this.userName = userName;
		return this;
	}

	public Integer getAge() {
		return age;
	}

	public UserDetailHistory setAge(Integer age) {
		this.age = age;
		return this;
	}

	public String getGender() {
		return gender;
	}

	public UserDetailHistory setGender(String gender) {
		this.gender = gender;
		return this;
	}

	public String getAddress() {
		return address;
	}

	public UserDetailHistory setAddress(String address) {
		this.address = address;
		return this;
	}

	public String getPhone() {
		return phone;
	}

	public UserDetailHistory setPhone(String phone) {
		this.phone = phone;
		return this;
	}

}
