package com.yc.bean;

import java.io.Serializable;

public class Applyinfo implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private Integer status;
	private Integer stu_id;
	private Integer merchant_wantedjob_id;
	private Student_baseinfo student_baseinfo;
	private Merchant_wantedjob merchant_wantedjob;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Student_baseinfo getStudent_baseinfo() {
		return student_baseinfo;
	}

	public void setStudent_baseinfo(Student_baseinfo student_baseinfo) {
		this.student_baseinfo = student_baseinfo;
	}

	public Merchant_wantedjob getMerchant_wantedjob() {
		return merchant_wantedjob;
	}

	public void setMerchant_wantedjob(Merchant_wantedjob merchant_wantedjob) {
		this.merchant_wantedjob = merchant_wantedjob;
	}

	public Integer getStu_id() {
		return stu_id;
	}

	public void setStu_id(Integer stu_id) {
		this.stu_id = stu_id;
	}

	public Integer getMerchant_wantedjob_id() {
		return merchant_wantedjob_id;
	}

	public void setMerchant_wantedjob_id(Integer merchant_wantedjob_id) {
		this.merchant_wantedjob_id = merchant_wantedjob_id;
	}

	@Override
	public String toString() {
		return "Applyinfo [id=" + id + ", status=" + status + ", stu_id=" + stu_id + ", merchant_wantedjob_id="
				+ merchant_wantedjob_id + ", student_baseinfo=" + student_baseinfo + ", merchant_wantedjob=" + merchant_wantedjob
				+ "]";
	}

}
