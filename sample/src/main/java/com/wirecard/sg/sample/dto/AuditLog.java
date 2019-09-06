package com.wirecard.sg.sample.dto;

public class AuditLog {
	private Integer auditLogId;
	private String entityName;
	private Integer primaryKey;
	private User user;
	private String lastUpdatedDate;
	private String lastUpdatedTime;
	private String actionStatus;
	private String preImage;
	private String PostImage;

	public AuditLog() {
	}

	public Integer getAuditLogId() {
		return auditLogId;
	}

	public void setAuditLogId(Integer auditLogId) {
		this.auditLogId = auditLogId;
	}

	public String getEntityName() {
		return entityName;
	}

	public AuditLog setEntityName(String entityName) {
		this.entityName = entityName;
		return this;
	}

	public Integer getPrimaryKey() {
		return primaryKey;
	}

	public AuditLog setPrimaryKey(Integer primaryKey) {
		this.primaryKey = primaryKey;
		return this;
	}

	public User getUser() {
		return user;
	}

	public AuditLog setUser(User user) {
		this.user = user;
		return this;
	}

	public String getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	public AuditLog setLastUpdatedDate(String lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
		return this;
	}

	public String getLastUpdatedTime() {
		return lastUpdatedTime;
	}

	public AuditLog setLastUpdatedTime(String lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
		return this;
	}

	public String getActionStatus() {
		return actionStatus;
	}

	public AuditLog setActionStatus(String actionStatus) {
		this.actionStatus = actionStatus;
		return this;
	}

	public String getPreImage() {
		return preImage;
	}

	public AuditLog setPreImage(String preImage) {
		this.preImage = preImage;
		return this;
	}

	public String getPostImage() {
		return PostImage;
	}

	public AuditLog setPostImage(String postImage) {
		PostImage = postImage;
		return this;
	}
	
}
