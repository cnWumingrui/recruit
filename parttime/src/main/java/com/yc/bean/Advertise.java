package com.yc.bean;

import java.io.Serializable;
import java.sql.Date;

public class Advertise implements Serializable{

	/**
	 * `id` int NOT NULL primary key AUTO_INCREMENT COMMENT '广告id',
  	 *`toURL` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '跳转链接',
  	 *`customer` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '客户名',
  	 *`picture`	varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '图片地址',
  	 *`describe` varchar(2000) NOT NULL CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '广告词',
  	 *`jointime` date NOT NULL COMMENT '添加时间',
  	 *`outtime` date NOT NULL COMMENT '下架时间',
  	 *`status` int NOT NULL COMMENT '广告状态:0下架/1上架'
	 */
	private static final long serialVersionUID = -3310210268148980806L;
	
	private Integer id;
	private String picture;
	private String toURL;
	private String customer;
	private String describes;
	private Date jointime;
	private Integer timeway;
	private Integer status;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getToURL() {
		return toURL;
	}
	public void setToURL(String toURL) {
		this.toURL = toURL;
	}
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public String getDescribes() {
		return describes;
	}
	public void setDescribes(String describe) {
		this.describes = describe;
	}
	public Date getJointime() {
		return jointime;
	}
	public void setJointime(Date jointime) {
		this.jointime = jointime;
	}

	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getTimeway() {
		return timeway;
	}
	public void setTimeway(Integer timeway) {
		this.timeway = timeway;
	}
	@Override
	public String toString() {
		return "Advertise [id=" + id + ", picture=" + picture + ", toURL="
				+ toURL + ", customer=" + customer + ", describe=" + describes
				+ ", jointime=" + jointime + ", timeway=" + timeway
				+ ", status=" + status + "]";
	}
	
	
}
