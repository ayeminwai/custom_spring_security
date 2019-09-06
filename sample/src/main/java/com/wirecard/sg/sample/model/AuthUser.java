package com.wirecard.sg.sample.model;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;


public class AuthUser extends User{
	
	private static final long serialVersionUID = -519223188644040199L;
	private Integer userId;
	
	public AuthUser(
			Integer userId,
			String username,
			String password,
			boolean enabled,
			boolean accountNonExpired,
			boolean credentialsNonExpired,
			boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		
		super(
			  username,
			  password,
			  enabled,
			  accountNonExpired,
			  credentialsNonExpired,
			  accountNonLocked,
			  authorities);
		
		this.userId=userId;
	}
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
}
