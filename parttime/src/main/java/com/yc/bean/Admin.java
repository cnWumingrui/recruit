package com.yc.bean;

import java.io.Serializable;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Admin implements Serializable {

	/**
	 * 	1	id	int
		2	username	varchar2
		3	userpassword	varchar2
		4	join_time	date

	 */
	private static final long serialVersionUID = 371525309521087732L;

	private Integer admin_id;
	private String admin_username;
	private String admin_password;
	private Date admin_jointime;
	private String admin_lastip;
	private Integer admin_permission;
	
	private String admin_oldpassword;
	
	private String newdate;
	
	public Integer getAdmin_id() {
		return admin_id;
	}
	public void setAdmin_id(Integer admin_id) {
		this.admin_id = admin_id;
	}
	public String getAdmin_username() {
		return admin_username;
	}
	public void setAdmin_username(String admin_username) {
		this.admin_username = admin_username;
	}
	public String getAdmin_password() {
		return admin_password;
	}
	public void setAdmin_password(String admin_password) {
		this.admin_password = admin_password;
	}
	public Date getAdmin_jointime() {
		return admin_jointime;
	}
	
	public void setAdmin_jointime(Date admin_join_time) {
		this.admin_jointime = admin_join_time;
	}
	
	public Integer getAdmin_permission() {
		return admin_permission;
	}

	public void setAdmin_permission(Integer admin_permission) {
		this.admin_permission = admin_permission;
	}

	public String getAdmin_oldpassword() {
		return admin_oldpassword;
	}
	public void setAdmin_oldpassword(String admin_oldpassword) {
		this.admin_oldpassword = admin_oldpassword;
	}
	public String getAdmin_lastip() {
		return admin_lastip;
	}
	public void setAdmin_lastip(String admin_lastip) {
		this.admin_lastip = admin_lastip;
	}
	@Override
	public String toString() {
		return "Admin [admin_id=" + admin_id + ", admin_username="
				+ admin_username + ", admin_password=" + admin_password
				+ ", admin_jointime=" + admin_jointime + ", admin_lastip="
				+ admin_lastip + ", admin_permission=" + admin_permission
				+ ", admin_oldpassword=" + admin_oldpassword + ", newdate="
				+ newdate + "]";
	}

	
	
	
}
