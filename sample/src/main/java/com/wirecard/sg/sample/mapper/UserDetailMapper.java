package com.wirecard.sg.sample.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.wirecard.sg.sample.dto.UserDetail;

@Mapper
public interface UserDetailMapper {

	String TABLE_NAME = " user_detail ";
	String COLUMNS = "user_name, age, gender, address, phone";
	String PROPERTIES = "#{userName}, #{age}, #{gender}, #{address}, #{phone}";

	@Insert("INSERT INTO " + TABLE_NAME + "(" + COLUMNS + ") VALUES(" + PROPERTIES + ")")
	@Options(useGeneratedKeys=true, keyProperty="userDetailId", keyColumn="user_detail_id")
	public int insert(UserDetail userDetail);

	@Update("UPDATE " + TABLE_NAME
			+ " SET user_name=#{userName}, age=#{age}, gender=#{gender}, address=#{address}, phone=#{phone} WHERE user_detail_id=#{userDetailId}")
	public void update(UserDetail userDetail);

	@Select("SELECT * FROM " + TABLE_NAME + " WHERE user_name = #{userName}")
	public UserDetail findByUserName(String userName);

	@Select("SELECT * FROM " + TABLE_NAME + " WHERE user_detail_id = #{userDetailId}")
	public UserDetail get(Integer userDetailId);

	@Select("SELECT * FROM " + TABLE_NAME)
	public List<UserDetail> getAll();

	@Delete("DELETE FROM " + TABLE_NAME + " WHERE user_detail_id=#{userDetailId}")
	public void delete(Integer userDetailId);
}
