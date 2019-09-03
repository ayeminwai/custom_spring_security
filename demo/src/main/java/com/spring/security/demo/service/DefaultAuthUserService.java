package com.spring.security.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.spring.security.demo.dao.AuthCustomerRepository;
import com.spring.security.demo.entity.AuthCustomer;

@Service
public class DefaultAuthUserService implements AuthUserService {
	
	@Autowired
	AuthCustomerRepository authCustomerRepository;

	@Override
	public String login(String username, String password) {
        Optional<AuthCustomer> authUser = authCustomerRepository.login(username,password);
        if(authUser.isPresent()){
            String token = UUID.randomUUID().toString();
            AuthCustomer custom= authUser.get();
            custom.setToken(token);
            authCustomerRepository.save(custom);
            return token;
        }
        
        return StringUtils.EMPTY;
	}

	@Override
	public Optional<User> findByToken(String token) {
        List<AuthCustomer> authUser= authCustomerRepository.findByToken(token);
        if(authUser.size() > 0){
            AuthCustomer authCustom = authUser.get(0);
            User user= new User(authCustom.getUsername(), authCustom.getPassword(), true, true, true, true,
                    AuthorityUtils.createAuthorityList("USER"));
            return Optional.of(user);
        }
        return  Optional.empty();
	}

	@Override
	public AuthCustomer findById(Integer id) {
        Optional<AuthCustomer> authUser= authCustomerRepository.findById(id);
        return authUser.orElse(null);
	}
}
