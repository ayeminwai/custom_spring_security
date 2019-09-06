package com.wirecard.sg.sample.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.wirecard.sg.sample.dto.Role;

@Mapper
public interface RoleMapper {

	String TABLE_NAME = " role ";
	String COLUMNS = "role_name";
	String PROPERTIES = "#{roleName}";

	@Insert("INSERT INTO " + TABLE_NAME + "(" + COLUMNS + ") VALUES(" + PROPERTIES + ")")
	public int insert(Role role);

	@Update("UPDATE " + TABLE_NAME + " SET role_name=#{roleName} WHERE role_id=#{roleId}")
	public void update(Role role);

	@Select("SELECT * FROM " + TABLE_NAME + " WHERE role_id = #{roleId}")
	public Role get(Integer roleId);

	@Select("SELECT * FROM " + TABLE_NAME)
	public List<Role> getAll();

	@Select("SELECT * FROM " + TABLE_NAME + " WHERE role_name=#{roleName}")
	public Role findOneByRoleName(String roleName);

	@Delete("DELETE FROM " + TABLE_NAME + " WHERE role_id=#{roleId}")
	public void delete(Integer roleId);

}
