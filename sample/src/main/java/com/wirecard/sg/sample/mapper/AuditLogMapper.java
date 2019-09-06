package com.wirecard.sg.sample.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import com.wirecard.sg.sample.dto.AuditLog;

@Mapper
public interface AuditLogMapper {

	String TABLE_NAME = " audit_log ";
	String COLUMNS = " entity_name, primary_key, last_updated_date, last_updated_time,last_updated_by,action_status, pre_image, post_image";
	String PROPERTIES = "#{entityName}, #{primaryKey}, #{lastUpdatedDate}, #{lastUpdatedTime}, #{user.authUserId}, #{actionStatus}, #{preImage}, #{postImage}";

	@Insert("INSERT INTO " + TABLE_NAME + "(" + COLUMNS + ") VALUES(" + PROPERTIES + ")")
	public int insert(AuditLog auditLog);

	@Select("SELECT * FROM " + TABLE_NAME + " al LEFT JOIN auth_user au ON au.auth_user_id=al.last_updated_by WHERE last_updated_by = #{id}")
	@ResultMap("Audit_User")
	public List<AuditLog> getByUser(Integer id);

	@Select("SELECT * FROM " + TABLE_NAME + " al LEFT JOIN auth_user au ON au.auth_user_id=al.last_updated_by ")
	@ResultMap("Audit_User")
	public List<AuditLog> getAll();

}
