 package com.yc.biz.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yc.bean.Admin;
import com.yc.bean.Advertise;
import com.yc.biz.AdverBiz;
import com.yc.dao.BaseDao;

@Service
public class AdverBizImpl implements AdverBiz {
	@Resource(name="baseDaoImpl")
	private BaseDao baseDao;
	
	@Override
	public boolean toAdd(Advertise advertise) {
		int r = baseDao.save(advertise, "toAdd");;
		if(r==1){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean delete(Advertise advertise) {
		int r = baseDao.del(advertise, "delete");
		if(r==1){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean delMutil(List<Advertise> list) {
		int r = (Integer)baseDao.del(Advertise.class, list, "delMutil");
		if(r>0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public int update(Advertise advertise) {
		return (int)baseDao.update(advertise, "update");
	}
	
	@Override
	public int selectFunc() {
		return (int) baseDao.getFunc1(Admin.class, "selectFunc");
	}
	
	@Override
	public List<Map<String, Object>> selectList(Class<Advertise> advertise,
			Map<String, Object> map) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> returnmap = new HashMap<String, Object>();
		int totalPages = (int) baseDao.getFunc1(advertise, "selectFunc");
		returnmap.put("total", totalPages);
		List<Admin> adverList = baseDao.findAll(advertise, "findList", map);
		returnmap.put("adverList", adverList);
		list.add(returnmap);
		return list;
	}

	@Override
	public List<Advertise> findAllList() {
		List<Advertise> list = baseDao.findAll(Advertise.class, "findAllList");
		if(list != null && list.size()>0){
			return list;
		}
		return null;
	}

	//查找结算方式的广告
	@Override
	public List<Advertise> findTimeway(Advertise way) {
		List<Advertise> list = baseDao.findAll(way, "findTimeway");
		if(list.size()>0 && list!=null){
			return list;
		}
		return null;
	}

	//上线下线广告
	@Override
	public int outAdver(Advertise out) {
		if(out.getStatus()==0){
			out.setStatus(1);
		}else if(out.getStatus()==1){
			out.setStatus(0);
		}
		return baseDao.update(out, "outAdver");
	}

	@Override
	public int selectOne(Advertise adver) {
		
		return (int)baseDao.findOne(adver, "selectOne");
	}

	@Override
	public Advertise selectName(Advertise adver) {
		
		return (Advertise) baseDao.findOne(adver, "select");
	}

}
