package com.wirecard.sg.sample.mapper.provider;

import java.util.Map;

public class UserProvider {

	// for user client pagination
	public String getUserByRole(Map<String, Object> parameters) {
		String username = (String) parameters.get("username");
		StringBuilder query = new StringBuilder();
		query.append("select * from auth_user au LEFT JOIN role r ON r.role_id=au.role_id ");
		if (username != null && !username.equals("")) {
			query.append("where au.username like #{username} ");
		}
		query.append(" ORDER BY au.auth_user_id DESC LIMIT #{pageSize} OFFSET #{pageNo}");
		return query.toString();
	}
	
	public String getUserCountByRole(Map<String, Object> parameters) {
		String username = (String) parameters.get("username");
		StringBuilder query = new StringBuilder();
		query.append("select * from auth_user au LEFT JOIN role r ON r.role_id=au.role_id ");
		if (username != null && !username.equals("")) {
			query.append("where au.username like #{username} ");
		}
		return query.toString();
	}
}
