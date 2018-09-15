package com.yc.bean;

import java.io.Serializable;

public class Job implements Serializable {

	private static final long serialVersionUID = 6679494839252228185L;

	private Integer id;
	private String keycode;
	private Object parameter;
	private Integer fathernode;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getKeycode() {
		return keycode;
	}
	public void setKeycode(String keycode) {
		this.keycode = keycode;
	}
	public Object getParameter() {
		return parameter;
	}
	public void setParameter(Object parameter) {
		this.parameter = parameter;
	}
	public Integer getFathernode() {
		return fathernode;
	}
	public void setFathernode(Integer fathernode) {
		this.fathernode = fathernode;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "Job [id=" + id + ", keycode=" + keycode + ", parameter="
				+ parameter + ", fathernode=" + fathernode + "]";
	}

}
