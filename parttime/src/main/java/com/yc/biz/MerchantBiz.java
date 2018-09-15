package com.yc.biz;

import java.util.List;
import java.util.Map;

import com.yc.bean.Merchant_baseinfo;
import com.yc.bean.Merchant_wantedjob;
import com.yc.web.dto.JsonModel;

public interface MerchantBiz {

	/**
	 * 注册判断是否存在该用户
	 * 
	 * @param mbInfo
	 * @return
	 */
	public boolean isExists(Merchant_baseinfo mbInfo);

	/**
	 * 登陆判断是否存在该用户
	 * 
	 * @param mbInfo
	 * @return
	 */
	public boolean isExistsWithLogin(Merchant_baseinfo mbInfo);

	/**
	 * 注册商家账号
	 * 
	 * @param mbinfo
	 * @return
	 */
	public boolean register(Merchant_baseinfo mbInfo);

	/**
	 * 商家登陆
	 * 
	 * @param mbInfo
	 * @return
	 */
	public Merchant_baseinfo login(Merchant_baseinfo mbInfo);

	/**
	 * 商家发布工作信息
	 * 
	 * @param mwJob
	 * @return
	 */
	public int insertMwjob(Merchant_wantedjob mwJob);

	/**
	 * 根据 商家id查询商家基本信息
	 * 
	 * @param merchant_id
	 * @return Merchant_baseinfo
	 */
	public Merchant_baseinfo findoneMerchant_baseinfo(Merchant_baseinfo mbInfo);

	/**
	 * 更新商家基本信息
	 * 
	 * @param mbInfo
	 * @return
	 */
	public int updateBaseinfo(Merchant_baseinfo mbInfo);

	/**
	 * 根据商家id查询商家发布信息
	 * 
	 * @param merchant_id
	 * @return
	 */
	public Merchant_wantedjob findoneMerchant_wantedjob(Merchant_wantedjob mwJob);

	/**
	 * 更新发布信息
	 * 
	 * @param mwJob
	 * @return
	 */
	public int updateWantedjob(Merchant_wantedjob mwJob);

	/**
	 * 添加商家基本信息
	 * 
	 * @param mbInfo
	 * @return
	 */
	public int insertBaseInfo(Merchant_baseinfo mbInfo);

	/**
	 * 查找商家发布的消息
	 * 
	 * @param Merchant_id
	 * @return List
	 */
	public List<Merchant_wantedjob> findListWantedJob(Merchant_wantedjob mwJob);

	/**
	 * 待条件的查询所有的岗位信息
	 * 
	 */
	public JsonModel<Merchant_wantedjob> findAllWantedJob( Map map);
	
	/**
	 * 查找商家List
	 */
	public List<Map<String, Object>> findAllList(Class<Merchant_baseinfo> Merchant_baseinfo, Map<String, Object> map);
	
	/**
	 * 权限管理
	 */
	public int setPower(Merchant_baseinfo merchant_baseinfo);
}
