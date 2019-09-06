package com.wirecard.sg.sample.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.wirecard.sg.sample.dto.User;
import com.wirecard.sg.sample.exception.NotExistException;
import com.wirecard.sg.sample.mapper.UserMapper;
import com.wirecard.sg.sample.model.AuthUser;

@Service
public class UserServiceImpl implements UserDetailsService {
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userMapper.findOneByUsername(username);
		
		if (user == null)
			throw new NotExistException("user name");
		
		return new AuthUser(user.getAuthUserId(), user.getUsername(), user.getPassword(), true, true, true, true,
				getAuthorites(user));
	}

	private List<GrantedAuthority> getAuthorites(User user) {
		return Arrays.asList(new SimpleGrantedAuthority(user.getRole().getRoleName()));
	}

}