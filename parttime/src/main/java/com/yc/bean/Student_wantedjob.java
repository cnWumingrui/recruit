package com.yc.bean;

import java.io.Serializable;

//学生兼职意向
public class Student_wantedjob implements Serializable {

	
	private static final long serialVersionUID = -8800568671911543018L;
    
	private Integer stu_id;
	private Integer  job_id;// 职业名
	private String  workplace;// 所在学校
	private String salary;// 薪资
	private String workcleanform;
	public Integer getStu_id() {
		return stu_id;
	}
	public void setStu_id(Integer stu_id) {
		this.stu_id = stu_id;
	}
	public Integer getJob_id() {
		return job_id;
	}
	public void setJob_id(Integer job_id) {
		this.job_id = job_id;
	}
	public String getWorkplace() {
		return workplace;
	}
	public void setWorkplace(String workplace) {
		this.workplace = workplace;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	
	
	public String getWorkcleanform() {
		return workcleanform;
	}
	public void setWorkcleanform(String workcleanform) {
		this.workcleanform = workcleanform;
	}
	
	
	public Student_wantedjob() {
		super();
	}
	@Override
	public String toString() {
		return "Student_wantedjob [stu_id=" + stu_id + ", job_id=" + job_id
				+ ", workplace=" + workplace + ", salary=" + salary
				+ ", workcleanform=" + workcleanform + "]";
	}
	public Student_wantedjob(Integer stu_id, Integer job_id, String workplace,
			String salary, String workcleanform) {
		super();
		this.stu_id = stu_id;
		this.job_id = job_id;
		this.workplace = workplace;
		this.salary = salary;
		this.workcleanform = workcleanform;
	}

	
	
	
}
