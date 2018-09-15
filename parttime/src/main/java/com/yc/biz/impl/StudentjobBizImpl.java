package com.yc.biz.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.yc.bean.Student_baseinfo;
import com.yc.bean.Student_wantedjob;
import com.yc.biz.StudentBiz;
import com.yc.biz.StudentjobBiz;
import com.yc.dao.BaseDao;

@Service
public class StudentjobBizImpl implements StudentjobBiz {
	@Resource(name = "baseDaoImpl")
	private BaseDao<Student_wantedjob> baseDao;

	// 大学生简历
	public boolean registerjob(Student_wantedjob student_wantedjob) {
		int r = baseDao.save(student_wantedjob, "insertjob");
		if (r == 1) {
			return true;
		}
		return false;
	}

	// 修改简历
	public boolean updatejob(Student_wantedjob student_wantedjob) {
		int r = baseDao.save(student_wantedjob, "updatejob");
		if (r == 1) {
			return true;
		}
		return false;
	}

	@Override
	public Student_wantedjob studentjob(Student_wantedjob student_wantedjob) {
		Student_wantedjob ss = baseDao.findOne(student_wantedjob, "studentjob");
		return ss;
	}

}
