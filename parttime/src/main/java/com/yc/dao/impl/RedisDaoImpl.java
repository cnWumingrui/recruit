package com.yc.dao.impl;

import java.util.Set;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.data.redis.core.ZSetOperations.TypedTuple;
import org.springframework.stereotype.Repository;

import com.yc.dao.RedisDao;

/**
 * redis实现热点推送
 * 
 * @author skj
 *
 */
@Repository
public class RedisDaoImpl implements RedisDao {

	@Resource(name = "redisTemplate")
	private RedisTemplate<String, String> rt;

	
	

	/**
	 * @param name
	 *            职位id
	 * @return 当前点击数
	 */
	@Override
	public double getValue(String rank,String name) {
		ZSetOperations<String, String> vo = rt.opsForZSet();

		return vo.score(rank, name);
	}

	/**
	 * 点击一次 增加1
	 * 
	 * @param name
	 *            职位id
	 */
	@Override
	public void addCount(String rank,String name) {
		ZSetOperations<String, String> vo = rt.opsForZSet();

		vo.incrementScore(rank, name, 1d);
	}

	/**
	 * 点击一次 增加1
	 * 
	 * @param name
	 *            职位id
	 */
	@Override
	public void addCount(String rank,String name, double count) {
		ZSetOperations<String, String> vo = rt.opsForZSet();

		vo.incrementScore(rank, name, count);
	}

	/**
	 * 删除当前这条信息
	 * 
	 * @param name
	 */
	@Override
	public void delValue(String rank,String name) {
		ZSetOperations<String, String> vo = rt.opsForZSet();
		vo.remove(rank, name);
	}

	@Override
	public Set<TypedTuple<String>> rank(String rank,int start, int end) {
		ZSetOperations<String, String> vo = rt.opsForZSet();
		Set<TypedTuple<String>> set = vo.reverseRangeWithScores(rank, start,
				end);
		return set;
	}

}