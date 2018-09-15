package com.yc.biz.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yc.bean.Student_baseinfo;
import com.yc.bean.Student_wantedjob;
import com.yc.biz.StudentBiz;
import com.yc.dao.BaseDao;

@Service
public class StudentBizImpl implements StudentBiz {
	
	@Resource(name = "baseDaoImpl")
	private BaseDao<Student_baseinfo> baseDao;
	
	/**
	 * 注册
	 */
	
	public  boolean register1(Student_baseinfo student_baseinfo) {
		int r=baseDao.save(student_baseinfo, "insert1");
		if(r==1){
		return true;
		}
		return false;
	}
	 
	/**
	 * 完善基本信息
	 */
	public boolean register2(Student_baseinfo student_baseinfo) {
		int r=baseDao.update(student_baseinfo, "update");
		if(r>0){
			return true;
			}
			return false;
	}
	/**
	 * 登录
	 */
	 @Override
	public Student_baseinfo login(Student_baseinfo student_baseinfo) {
		 Student_baseinfo s =baseDao.findOne(student_baseinfo, "getStudentlogin");
	    return s;
	}

	 /**
	  * 判断用户是否注册
	  */
	 @Override
	public boolean validate(Student_baseinfo student_baseinfo) {
		Student_baseinfo r=baseDao.findOne(student_baseinfo, "isUserExists");
		if(r!=null){
			return false;
		}
		return true;
	}

	 @Override
	public Student_baseinfo getStudentId(Integer stu_id) {
		Student_baseinfo s = new Student_baseinfo();
		s.setStu_id(stu_id);
		return baseDao.findOne(s, "getStudent");
	}

	@Override
	public int update(Student_baseinfo student_baseinfo) {
		return baseDao.update(student_baseinfo, "update");
	}

}
