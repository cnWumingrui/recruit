package com.yc.biz;

import com.yc.bean.Student_baseinfo;
import com.yc.bean.Student_wantedjob;


public interface StudentBiz {
	 /**
     * 学生注册
     * @param Student_baseinfo
     * @return
     */
	public  boolean register1(Student_baseinfo student_baseinfo);
	
	 /**
     * 学生基本信息完善
     * @param Student_baseinfo
     * @return
     */
	public boolean register2(Student_baseinfo student_baseinfo);
	
	/**
     * 用户名是否已经存在
     * @param Student_baseinfo
     * @return
     */
	public boolean validate(Student_baseinfo student_baseinfo);
	
	/**
	 * 根据学生id查用户
	 * @param stu_id
	 * @return
	 */
	public Student_baseinfo getStudentId(Integer stu_id);
	
	/**
	 * 更新
	 */
	public int update(Student_baseinfo student_baseinfo);
	
	/**
	 * 登录
	 * @param student_baseinfo
	 * @return
	 */
	public Student_baseinfo login(Student_baseinfo student_baseinfo);
	
	
}
