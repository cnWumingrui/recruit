package com.yc.test;

import java.util.Set;

import lombok.extern.slf4j.Slf4j;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ZSetOperations.TypedTuple;
import org.springframework.test.context.junit4.SpringRunner;

import com.yc.dao.RedisDao;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {
	@Autowired
	private RedisDao redisDao;

//	@Test
//	public void setKey() {
//		for (int i = 1; i <= 10; i++) {
//			redisDao.addCount("Rank",i+"");
//		}
//	}
//	@Test
//	public void delKey() {
//		redisDao.delValue("JobRank",32+"");
//	}
	@Test
	public void getKey() {
		Set<TypedTuple<String>> set=redisDao.rank("JobRank",0,-1);
		for(TypedTuple<String> t:set){
			System.out.println(t.getValue()+":"+t.getScore());
		}
	}

}
