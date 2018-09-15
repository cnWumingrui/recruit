package com.yc.bean;

import java.io.Serializable;
import java.util.List;

public class JsonModel implements Serializable {

	private static final long serialVersionUID = 9167318014268539793L;
	private Integer code;
	private String msg;
	private Object obj;

	public JsonModel(Integer code, String msg, Object obj) {
		super();
		this.code = code;
		this.msg = msg;
		this.obj = obj;
	}

	private Integer total;// *总记录数
	private Integer pages;// 当前第几页
	private Integer pageSize;// 每页xx条
	private List rows;

	public JsonModel() {
		super();
	}

	public Integer getCode() {
		return code;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getPages() {
		return pages;
	}

	public void setPages(Integer pages) {
		this.pages = pages;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public List getRows() {
		return rows;
	}

	public void setRows(List rows) {
		this.rows = rows;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	@Override
	public String toString() {
		return "JsonModel [code:" + code + ", msg:" + msg + ", obj:" + obj
				+ ", total=" + total + ", pages:" + pages + ", pageSize:"
				+ pageSize + ", rows:" + rows + "]";
	}
	
	
}
