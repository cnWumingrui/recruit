package com.yc.biz;

import java.util.List;
import java.util.Map;

import com.yc.bean.Job;
import com.yc.web.dto.JsonModel;

public interface JobBiz {
	/**
	 * 根据id查某个的信息
	 * 
	 * @param house
	 * @return
	 */
	public List<Job>  getJob(Job Job);
	/**
	 * 查询所有的兼职信息
	 * @return
	 */
	public List<Job>  getAllJob();
	/**
	 * 查询类型兼职
	 * @return
	 */
	public List<Job>  getJobBack(Job job);

	/**
	 * 添加房子
	 * @param house
	 * @return 
	 */
	public boolean saveJob(Job Job);

	/**
	 * 更新
	 * @param house
	 * @return
	 */
	public boolean updateJob(Job Job);

	/**
	 * 删除单个房子信息
	 * @param house
	 * @return
	 */
	public boolean delSingleJob(Job Job);

	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	public boolean delMutiJob(List<Object> ids);

	/**
	 * 查询
	 * 
	 * @param map
	 * @return
	 */
	public JsonModel<Job> findJobCondition(Map<String, Object> map);
	
}
