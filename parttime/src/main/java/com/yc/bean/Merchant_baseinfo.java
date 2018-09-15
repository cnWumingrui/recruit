package com.yc.bean;

import java.io.Serializable;

public class Merchant_baseinfo implements Serializable {

	private static final long serialVersionUID = 8649949170814941480L;
	private int merchant_id;
	private String merchant_name;// ; '商家名',
	private String merchant_username;// '商家登陆用户名',
	private String merchant_password;// '商家登陆密码',
	private String merchant_telephone;// '商家电话',
	private String merchant_license;// '营业执照码',
	private String merchant_idcard;// '店主身份证',
	private String merchant_hostname;// 店主名
	private String merchant_email;// 商家邮箱
	private int power;// 权限

	public Merchant_baseinfo() {
		super();
	}

	public Merchant_baseinfo(String merchant_name, String merchant_username, String merchant_password, String merchant_telephone,
			String merchant_license, String merchant_idcard, String merchant_hostname, String merchant_email) {
		super();
		this.merchant_name = merchant_name;
		this.merchant_username = merchant_username;
		this.merchant_password = merchant_password;
		this.merchant_telephone = merchant_telephone;
		this.merchant_license = merchant_license;
		this.merchant_idcard = merchant_idcard;
		this.merchant_hostname = merchant_hostname;
		this.merchant_email = merchant_email;
	}

	@Override
	public String toString() {
		return "Merchant_baseinfo [merchant_id=" + merchant_id + ", merchant_name=" + merchant_name + ", merchant_username="
				+ merchant_username + ", merchant_password=" + merchant_password + ", merchant_telephone=" + merchant_telephone
				+ ", merchant_license=" + merchant_license + ", merchant_idcard=" + merchant_idcard + ", merchant_hostname="
				+ merchant_hostname + ", merchant_email=" + merchant_email + "]";
	}

	public int getMerchant_id() {
		return merchant_id;
	}

	public void setMerchant_id(int merchant_id) {
		this.merchant_id = merchant_id;
	}

	public String getMerchant_name() {
		return merchant_name;
	}

	public void setMerchant_name(String merchant_name) {
		this.merchant_name = merchant_name;
	}

	public String getMerchant_username() {
		return merchant_username;
	}

	public void setMerchant_username(String merchant_username) {
		this.merchant_username = merchant_username;
	}

	public String getMerchant_password() {
		return merchant_password;
	}

	public void setMerchant_password(String merchant_password) {
		this.merchant_password = merchant_password;
	}

	public String getMerchant_telephone() {
		return merchant_telephone;
	}

	public void setMerchant_telephone(String merchant_telephone) {
		this.merchant_telephone = merchant_telephone;
	}

	public String getMerchant_license() {
		return merchant_license;
	}

	public void setMerchant_license(String merchant_license) {
		this.merchant_license = merchant_license;
	}

	public String getMerchant_idcard() {
		return merchant_idcard;
	}

	public void setMerchant_idcard(String merchant_idcard) {
		this.merchant_idcard = merchant_idcard;
	}

	public String getMerchant_hostname() {
		return merchant_hostname;
	}

	public void setMerchant_hostname(String merchant_hostname) {
		this.merchant_hostname = merchant_hostname;
	}

	public String getMerchant_email() {
		return merchant_email;
	}

	public void setMerchant_email(String merchant_email) {
		this.merchant_email = merchant_email;
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}
}
