package com.yc.dao;

import java.util.Set;

import org.springframework.data.redis.core.ZSetOperations.TypedTuple;



/**
 * redis实现热点推送 
 * @author skj
 *
 */
public interface RedisDao {

	/**
	 * @param name  职位id
	 * @return    当前点击数
	 */
	public double getValue(String rank, String name);
	
	/**
	 * 点击一次 增加1
	 * @param name 职位id
	 */
	public void addCount(String rank,String name);
	/**
	 * 点击一次 增加1
	 * @param name 职位id
	 */
	public void addCount(String rank,String name,double count);
	/**
	 * 删除当前这条信息
	 * @param name
	 */
	public  void delValue(String rank,String name);
	
	/**
	 * 选择排行榜多么名
	 * @param start
	 * @param end
	 * @return
	 */
	public Set<TypedTuple<String>> rank(String rank,int start,int end);
}