package com.yc.biz.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.bean.Merchant_baseinfo;
import com.yc.bean.Merchant_wantedjob;
import com.yc.biz.MerchantBiz;
import com.yc.dao.BaseDao;
import com.yc.dao.RedisDao;
import com.yc.utils.Encrypt;
import com.yc.web.dto.JsonModel;

@Service
@Transactional
public class MerchantBizImpl implements MerchantBiz {

	@Resource(name = "baseDaoImpl")
	private BaseDao baseDao;


	/*
	 * (non-Javadoc)判断用户是否存在
	 * 
	 * @see com.yc.biz.MerchantBiz#isExists(com.yc.bean.Merchant_baseinfo)
	 */
	@Override
	public boolean isExists(Merchant_baseinfo mbInfo) {
		int r = baseDao.getCount(mbInfo, "isExists");
		if (r == 1) {
			return true;
		}
		return false;
	}

	/*
	 * (non-Javadoc) 注册
	 * 
	 * @see com.yc.biz.MerchantBiz#register(com.yc.bean.Merchant_baseinfo)
	 */
	@Override
	public boolean register(Merchant_baseinfo mbInfo) {
		String tel = mbInfo.getMerchant_telephone();
		String pwd = mbInfo.getMerchant_password();
		String encryptPwd = Encrypt.md5AndSha2(pwd);
		mbInfo.setMerchant_password(encryptPwd);
		if (tel != null && tel != "") {
			mbInfo.setMerchant_username(tel);
		}
		int r = baseDao.save(mbInfo, "RegMerchantbaseinfo");
		if (r == 1) {
			return true;
		}
		return false;
	}

	/*
	 * (non-Javadoc)登陆
	 * 
	 * @see com.yc.biz.MerchantBiz#login(com.yc.bean.Merchant_baseinfo)
	 */
	@Override
	public Merchant_baseinfo login(Merchant_baseinfo mbInfo) {
		int r = baseDao.getCount(mbInfo, "isExistsWithLoign");
		Merchant_baseinfo rembInfo = null;
		if (r == 1) {
			rembInfo = (Merchant_baseinfo) baseDao.findOne(mbInfo, "getMerchantbaseinfoByLogin");
		}
		return rembInfo;
	}

	/*
	 * (non-Javadoc)商家发布工作信息
	 * 
	 * @see com.yc.biz.MerchantBiz#insertMwjob(com.yc.bean.Merchant_wantedjob)
	 */
	@Override
	public int insertMwjob(Merchant_wantedjob mwJob) {
		int r = baseDao.save(mwJob, "InsertMerchantwantedjob");
		if (r == 1) {
			return r;
		}
		return 0;
	}

	/*
	 * (non-Javadoc)更新商户基本信息
	 * 
	 * @see com.yc.biz.MerchantBiz#updateBaseinfo(com.yc.bean.Merchant_baseinfo)
	 */
	@Override
	public int updateBaseinfo(Merchant_baseinfo mbInfo) {
		return baseDao.update(mbInfo, "upMerchantbaseinfo");
	}

	@Override
	public int updateWantedjob(Merchant_wantedjob mwJob) {
		return baseDao.update(mwJob, "UpdateMerchantwantedjob");
	}

	/*
	 * (non-Javadoc)根据id查基本信息
	 * 
	 * @see
	 * com.yc.biz.MerchantBiz#findoneMerchant_baseinfo(com.yc.bean.Merchant_baseinfo
	 * )
	 */
	@Override
	public Merchant_baseinfo findoneMerchant_baseinfo(Merchant_baseinfo mbInfo) {
		if (mbInfo.getMerchant_username() != null) {
			return (Merchant_baseinfo) baseDao.findOne(mbInfo, "getMerchantbaseinfoByUsername");
		}
		return (Merchant_baseinfo) baseDao.findOne(mbInfo, "getMerchantbaseinfo");
	}

	@Override
	public Merchant_wantedjob findoneMerchant_wantedjob(Merchant_wantedjob mwJob) {
		return (Merchant_wantedjob) baseDao.findOne(mwJob, "getMerchantwantjob");
	}

	@Override
	public int insertBaseInfo(Merchant_baseinfo mbInfo) {
		return baseDao.save(mbInfo, "insertMerchantbaseInfo");
	}

	@Override
	public boolean isExistsWithLogin(Merchant_baseinfo mbInfo) {
		int r = baseDao.getCount(mbInfo, "isExistsWithLoign");
		System.out.println(r);
		if (r == 1) {
			return true;
		}
		return false;
	}

	@Override
	public List<Merchant_wantedjob> findListWantedJob(Merchant_wantedjob mwJob) {
		return baseDao.findAll(mwJob, "findListWantedJob");
	}

	/*
	 * (non-Javadoc)查出所有的工作信息
	 * 
	 * @see com.yc.biz.MerchantBiz#findAllWantedJob(java.lang.Class,
	 * java.util.Map)
	 */
	@Override
	public JsonModel<Merchant_wantedjob> findAllWantedJob( Map map) {
		JsonModel<Merchant_wantedjob> jsonModel = new JsonModel<Merchant_wantedjob>();
		int pages = (int) map.get("pages");
		int pageSize = (int) map.get("pageSize");
		int count = baseDao.getCount(Merchant_wantedjob.class, "findWantedjobConditionCount");
		int total = count % pageSize == 0 ? count / pageSize : count / pageSize
				+ 1;
		int start = (pages - 1) * pageSize;
		map.put("start", start);
		map.put("pageSize", pageSize);
		List<Merchant_wantedjob> list = baseDao.findAll(Merchant_wantedjob.class, "selectWantedjobCondition",map);
		jsonModel.setTotal(total);
		
		jsonModel.setRows(list);
		jsonModel.setTotal(total);
		jsonModel.setPages(pages);
		jsonModel.setPageSize(pageSize);
		return jsonModel;
	}
	
	@Override
	public List<Map<String, Object>> findAllList(
			Class<Merchant_baseinfo> Merchant_baseinfo, Map<String, Object> map) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> returnmap = new HashMap<String, Object>();
		int totalPages = (int) baseDao.getFunc1(Merchant_baseinfo, "getAllMerchantListTotal");
		returnmap.put("total", totalPages);
		List<Merchant_baseinfo> Merchant_baseinfoList = baseDao.findAll(Merchant_baseinfo, "getAllMerchantList", map);
		returnmap.put("Merchant_baseinfoList", Merchant_baseinfoList);
		list.add(returnmap);
		return list;
	}

	@Override
	public int setPower(Merchant_baseinfo merchant_baseinfo) {
		merchant_baseinfo = (Merchant_baseinfo) baseDao.findOne(merchant_baseinfo, "getMerchantbaseinfo");
		if(merchant_baseinfo.getPower()==0){
			merchant_baseinfo.setPower(1);
		}else if(merchant_baseinfo.getPower()==1){
			merchant_baseinfo.setPower(0);
		}
		return baseDao.update(merchant_baseinfo, "setMerchantPower");
	}

}
