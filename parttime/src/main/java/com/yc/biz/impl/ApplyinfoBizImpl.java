package com.yc.biz.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yc.bean.Applyinfo;
import com.yc.bean.Student_baseinfo;
import com.yc.biz.ApplyinfoBiz;
import com.yc.dao.BaseDao;

@Service
public class ApplyinfoBizImpl implements ApplyinfoBiz {

	@Resource(name = "baseDaoImpl")
	private BaseDao<Applyinfo> baseDao;

	// 根据条件查询应聘信息

	@Override
	public List<Applyinfo> getApplyinfo(Applyinfo applyinfo) {
		List<Applyinfo> list = baseDao.findAll(applyinfo, "findApplyinfoCondition");
		return list;
	}

	// 查看所有应聘信息，供后台使用
	@Override
	public List<Applyinfo> getAllApplyinfo() {
		List<Applyinfo> list = baseDao.findAll(Applyinfo.class, "findApplyinfoCondition");
		return list;
	}

	@Override
	public boolean saveApplyinfo(Applyinfo applyinfo) {

		int r = baseDao.save(applyinfo, "saveApplyinfo");
		if (r > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean delApplyinfo(Applyinfo applyinfo) {
		return false;
	}

	@Override
	public List<Applyinfo> seleStudentLsit(Applyinfo applyinfo) {
		return baseDao.findAll(applyinfo, "selectStudentListwithApply");
	}
	
	@Override
	public boolean updataApplyinfo(Applyinfo applyinfo) {
		baseDao.update(applyinfo, "cancelApplyinfo");
		return true;
	}
}
