package com.spring.security.demo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.spring.security.demo.entity.AuthCustomer;

@Repository
public class AuthCustomerRepository {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	public Optional<AuthCustomer> login(String username, String password){
        return jdbcTemplate.queryForObject(
                "select * from auth_customer where username = ? and password=?",
                new Object[]{username, password},
                (rs, rowNum) ->
                        Optional.of(new AuthCustomer(
                                rs.getInt("id"),
                                rs.getString("username"),
                                rs.getString("password"),
                                rs.getString("token")
                        ))
        );
	}
	
	public List<AuthCustomer> findByToken(String token){
        return jdbcTemplate.query(
                "select * from auth_customer where token like ?",
                new Object[]{token},
                (rs, rowNum) ->
                    new AuthCustomer(
                            rs.getInt("id"),
                            rs.getString("username"),
                            rs.getString("password"),
                            rs.getString("token")
                )
        );
	}

	public void save(AuthCustomer custom) {
		jdbcTemplate.update("update auth_customer set token = ? where id=? ", custom.getToken(), custom.getId());
		
	}

	public Optional<AuthCustomer> findById(Integer id) {
        return jdbcTemplate.queryForObject(
                "select * from auth_customer where id = ?",
                new Object[]{id},
                (rs, rowNum) ->
                    Optional.of(new AuthCustomer(
                            rs.getInt("id"),
                            rs.getString("username"),
                            rs.getString("password"),
                            rs.getString("token")
                ))
        );
	}
	
}
