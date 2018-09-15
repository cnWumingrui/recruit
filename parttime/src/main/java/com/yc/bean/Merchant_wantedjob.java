package com.yc.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Hsiao
 *
 */
public class Merchant_wantedjob implements Serializable {

	private static final long serialVersionUID = 6514928772075448137L;
	private Integer merchant_wantedjob_id;
	private Integer merchant_id;
	private double salary;
	private Integer job_id;
	private String worktime;
	private String workplace;
	private String workdescp;
	private String workcleanform;
	private String workdemand;
	private String workcontent;
	private String posttime;
	private String title;

	public Merchant_wantedjob() {
		super();
	}

	public Merchant_wantedjob(Integer merchant_wantedjob_id, Integer merchant_id, double salary, Integer job_id, String worktime,
			String workplace, String workdescp, String workcleanform, String workdemand, String workcontent, String posttime,
			String title) {
		super();
		this.merchant_wantedjob_id = merchant_wantedjob_id;
		this.merchant_id = merchant_id;
		this.salary = salary;
		this.job_id = job_id;
		this.worktime = worktime;
		this.workplace = workplace;
		this.workdescp = workdescp;
		this.workcleanform = workcleanform;
		this.workdemand = workdemand;
		this.workcontent = workcontent;
		this.posttime = posttime;
		this.title = title;
	}

	@Override
	public String toString() {
		return "Merchant_wantedjob [merchant_wantedjob_id=" + merchant_wantedjob_id + ", merchant_id=" + merchant_id
				+ ", salary=" + salary + ", job_id=" + job_id + ", worktime=" + worktime + ", workplace=" + workplace
				+ ", workdescp=" + workdescp + ", workcleanform=" + workcleanform + ", workdemand=" + workdemand
				+ ", workcontent=" + workcontent + ", posttime=" + posttime + ", title=" + title + "]";
	}

	public Integer getMerchant_wantedjob_id() {
		return merchant_wantedjob_id;
	}

	public void setMerchant_wantedjob_id(Integer merchant_wantedjob_id) {
		this.merchant_wantedjob_id = merchant_wantedjob_id;
	}

	public Integer getMerchant_id() {
		return merchant_id;
	}

	public void setMerchant_id(Integer merchant_id) {
		this.merchant_id = merchant_id;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public Integer getJob_id() {
		return job_id;
	}

	public void setJob_id(Integer job_id) {
		this.job_id = job_id;
	}

	public String getWorktime() {
		return worktime;
	}

	public void setWorktime(String worktime) {
		this.worktime = worktime;
	}

	public String getWorkplace() {
		return workplace;
	}

	public void setWorkplace(String workplace) {
		this.workplace = workplace;
	}

	public String getWorkdescp() {
		return workdescp;
	}

	public void setWorkdescp(String workdescp) {
		this.workdescp = workdescp;
	}

	public String getWorkcleanform() {
		return workcleanform;
	}

	public void setWorkcleanform(String workcleanform) {
		this.workcleanform = workcleanform;
	}

	public String getWorkdemand() {
		return workdemand;
	}

	public void setWorkdemand(String workdemand) {
		this.workdemand = workdemand;
	}

	public String getWorkcontent() {
		return workcontent;
	}

	public void setWorkcontent(String workcontent) {
		this.workcontent = workcontent;
	}

	public String getPosttime() {
		return posttime;
	}

	public void setPosttime(String posttime) {
		this.posttime = posttime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
