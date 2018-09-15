package com.yc.web.dto;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class JsonModel<T> {
	private Integer code;
	private String msg;
	private Object obj;

	private Integer total;// 总记录数
	private Integer pages;// 当前第几页
	private Integer pageSize;// 每页多少条
	private List<T> rows;// 记录集合

	public JsonModel() {
		super();
	}

	public JsonModel(Integer code, String msg, Object obj) {
		super();
		this.code = code;
		this.msg = msg;
		this.obj = obj;
	}

	@Override
	public String toString() {
		return "JsonModel [code=" + code + ", msg=" + msg + ", obj=" + obj + ", total=" + total + ", pages=" + pages
				+ ", pageSize=" + pageSize + ", rows=" + rows + "]";
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

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	public Integer getCode() {
		return code;
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

}
