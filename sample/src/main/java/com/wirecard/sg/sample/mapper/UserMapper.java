package com.wirecard.sg.sample.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.wirecard.sg.sample.dto.User;

@Mapper
public interface UserMapper{

	String TABLE_NAME = " auth_user ";
	String COLUMNS = "username, password, role_id";
	String PROPERTIES = "#{username}, #{password}, #{role.roleId}";

	@Insert("INSERT INTO " + TABLE_NAME + "(" + COLUMNS + ") VALUES(" + PROPERTIES + ")")
	public int insert(User user);

	@Update("UPDATE " + TABLE_NAME + " SET username=#{username} WHERE auth_user_id=#{authUserId}")
	public void update(User user);

	@Select("SELECT * FROM " + TABLE_NAME + " au LEFT JOIN role r ON r.role_id=au.role_id WHERE au.auth_user_id = #{authUserId}")
	public User get(Integer authUserId);

	@Select("SELECT * FROM " + TABLE_NAME)
	public List<User> getAll();

	@Select("SELECT * FROM " + TABLE_NAME + " au LEFT JOIN role r ON r.role_id=au.role_id WHERE au.username=#{name}")
	@ResultMap("User_Role")
	public User findOneByUsername(String name);

	@Delete("DELETE FROM " + TABLE_NAME + " WHERE auth_user_id=#{authUserId}")
	public void delete(Integer userId);

	@Update("UPDATE " + TABLE_NAME + " SET password=#{password} WHERE username=#{username}")
	public void forgetPassword(User user);

}
