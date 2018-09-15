package com.yc.biz.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yc.bean.Job;
import com.yc.biz.JobBiz;
import com.yc.dao.BaseDao;
import com.yc.web.dto.JsonModel;

@Service
public class JobBizImpl implements JobBiz {

	@Resource(name="baseDaoImpl")
	private BaseDao<Job> baseDao;
	//根据条件查询
	@Override
	public List<Job>  getJob(Job job) {
		
		List<Job> list = baseDao.findAll(job, "findJobCondition");
		return list;

	}
	//查询所有
	@Override
	public List<Job> getAllJob() {
		
		return baseDao.findAll(Job.class, "findJobCondition");
	}

	@Override
	public List<Job> getJobBack(Job job) {
		return baseDao.findAll(job, "findJobBack");
	}

	@Override
	public JsonModel<Job> findJobCondition(Map<String, Object> map) {
		return null;
	}

	@Override
	public boolean saveJob(Job Job) {
		return false;
	}

	@Override
	public boolean updateJob(Job Job) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delSingleJob(Job Job) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delMutiJob(List<Object> ids) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}
