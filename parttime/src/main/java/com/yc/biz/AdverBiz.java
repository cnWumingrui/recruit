package com.yc.biz;

import java.util.List;
import java.util.Map;

import com.yc.bean.Admin;
import com.yc.bean.Advertise;

public interface AdverBiz {
	
	public boolean toAdd(Advertise advertise);
	
	public boolean delete(Advertise advertise);
	
	public boolean delMutil(List<Advertise> list);
	
	public int update(Advertise advertise);
	
	public int selectFunc();
	
	public List<Advertise> findAllList();
	
	public List<Map<String, Object>> selectList(Class<Advertise> advertise, Map<String, Object> map);
	
	public List<Advertise> findTimeway(Advertise way);
	
	public int outAdver(Advertise out);
	
	public int selectOne(Advertise adver);
	
	public Advertise selectName(Advertise adver);
}
