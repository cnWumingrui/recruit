package com.yc.test;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.yc.bean.Job;
import com.yc.biz.JobBiz;


@SpringBootTest
public class JobTests {
	@Resource(name="jobBizImpl")
	private JobBiz jobBiz;
	@Test //查找
	public void getJob() {
		List<Job> list=new ArrayList<Job>();
		Job job=new Job();
		//job.setKeycode("行业");
		job.setFathernode(1);
		list =jobBiz.getAllJob();
		System.out.println(list);
	}
	@Test //查找
	public void getJobBack() {
		List<Job> list=new ArrayList<Job>();
		Job job=new Job();
		job.setId(10);
		list =jobBiz.getJobBack(job);
		System.out.println(list);
	}

}
