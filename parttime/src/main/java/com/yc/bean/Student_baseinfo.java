package com.yc.bean;

import java.io.Serializable;

//学生基本信息
public class Student_baseinfo implements Serializable {

	
	private static final long serialVersionUID = -2202569582149637841L;
	private Integer stu_id; //兼职学生id',
	private String stu_name;// '兼职学生姓名',
	private String stu_username;// '兼职学生用户名',
	private String stu_password;// '兼职学生密码',
	private String stu_telephone;// '兼职学生电话',
	private String stu_idcard;// '兼职学生身份证',
	private String stu_email; //兼职学生邮箱
	
	
	
	public String getStu_email() {
		return stu_email;
	}
	public void setStu_email(String stu_email) {
		this.stu_email = stu_email;
	}
	public Integer getStu_id() {
		return stu_id;
	}
	public void setStu_id(Integer stu_id) {
		this.stu_id = stu_id;
	}
	public String getStu_name() {
		return stu_name;
	}
	public void setStu_name(String stu_name) {
		this.stu_name = stu_name;
	}
	public String getStu_username() {
		return stu_username;
	}
	public void setStu_username(String stu_username) {
		this.stu_username = stu_username;
	}
	public String getStu_password() {
		return stu_password;
	}
	public void setStu_password(String stu_password) {
		this.stu_password = stu_password;
	}
	public String getStu_telephone() {
		return stu_telephone;
	}
	public void setStu_telephone(String stu_telephone) {
		this.stu_telephone = stu_telephone;
	}
	public String getStu_idcard() {
		return stu_idcard;
	}
	public void setStu_idcard(String stu_idcard) {
		this.stu_idcard = stu_idcard;
	}
	
	public Student_baseinfo() {
		
	}
	public Student_baseinfo(Integer stu_id, String stu_name,
			String stu_username, String stu_password, String stu_telephone,
			String stu_idcard, String stu_email) {
		super();
		this.stu_id = stu_id;
		this.stu_name = stu_name;
		this.stu_username = stu_username;
		this.stu_password = stu_password;
		this.stu_telephone = stu_telephone;
		this.stu_idcard = stu_idcard;
		this.stu_email = stu_email;
	}
	@Override
	public String toString() {
		return "Student_baseinfo [stu_id=" + stu_id + ", stu_name=" + stu_name
				+ ", stu_username=" + stu_username + ", stu_password="
				+ stu_password + ", stu_telephone=" + stu_telephone
				+ ", stu_idcard=" + stu_idcard + ", stu_email=" + stu_email
				+ "]";
	}

	
	
	
	
}
