package com.spring.security.demo.entity;

public class AuthCustomer {
    private Integer id;
    private String username;
    private String password;
    private String token;
    
	public AuthCustomer(int id, String username, String password, String token) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.token = token;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	@Override
	public String toString() {
		return "AuthUser{" +
        "username='" + username + '\'' +
        ", password='" + password + '\'' +
        '}';
	}
}
