package com.yc.biz;

import java.util.List;
import java.util.Map;

import com.yc.bean.Admin;

public interface AdminBiz {
	
	public Admin login(Admin admin);
	
	public boolean reg(Admin admin);
	
	public List<Admin> findAllList();
	
	public boolean delete(Admin admin);
	
	public boolean delMutil(List<Admin> list);
	
	public boolean isUnameExists(Admin admin);
	
	public int selectFunc();
	
	public List<Map<String, Object>> selectList(Class<Admin> admin, Map<String, Object> map); 
	
	public int updatePwd(Admin admin);
	
	public int update(Admin admin);
	
	public int re(Admin admin);
	
	public int saveIp(Admin admin);
}
