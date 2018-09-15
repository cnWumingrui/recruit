package com.yc.biz.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yc.bean.Admin;
import com.yc.biz.AdminBiz;
import com.yc.dao.BaseDao;
@Service
public class AdminBizImpl implements AdminBiz {
	@Resource(name="baseDaoImpl")
	private BaseDao baseDao;
	
	@Override
	public Admin login(Admin admin) {
		List<Admin> list = baseDao.findAll(admin, "login");
		if(list != null && list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public boolean reg(Admin admin) {
		int r = baseDao.save(admin, "reg");;
		if(r==1){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public List<Admin> findAllList() {
		List<Admin> list = baseDao.findAll(Admin.class, "findAllList");
		if(list != null && list.size()>0){
			return list;
		}
		return null;
	}

	@Override
	public boolean delete(Admin admin) {
		int r = baseDao.del(admin, "delete");
		if(r==1){
			return true;
		}else{
			return false;
		}
	}
	
	@Override
	public boolean delMutil(List<Admin> list) {
		int r = (Integer)baseDao.del(Admin.class, list, "delMutilAdmin");
		if(r>0){
			return true;
		}else{
			return false;
		}
		
	}

	@Override
	public boolean isUnameExists(Admin admin) {
		Admin i = (Admin) baseDao.findOne(admin, "isExists");
		if (i != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int selectFunc() {
		return (int) baseDao.getFunc1(Admin.class, "selectFunc");
	}

	@Override
	public List<Map<String, Object>> selectList(Class<Admin> admin, Map<String, Object> map) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> returnmap = new HashMap<String, Object>();
		int totalPages = (int) baseDao.getFunc1(admin, "selectFunc");
		returnmap.put("total", totalPages);
		List<Admin> adminList = baseDao.findAll(admin, "findList", map);
		returnmap.put("adminList", adminList);
		list.add(returnmap);
		return list;
	}

	@Override
	public int updatePwd(Admin admin) {
		return (int)baseDao.getFunc1(admin, "updatePwd");
	}

	@Override
	public int update(Admin admin) {
		return (int)baseDao.update(admin, "update");
	}

	@Override
	public int re(Admin admin) {
		return baseDao.update(admin, "re");
	}

	@Override
	public int saveIp(Admin admin) {
		return baseDao.update(admin, "ip");
	}


}
