package com.yc.web.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ZSetOperations.TypedTuple;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yc.bean.Applyinfo;
import com.yc.bean.Merchant_baseinfo;
import com.yc.bean.Merchant_wantedjob;
import com.yc.biz.MerchantBiz;
import com.yc.dao.RedisDao;
import com.yc.utils.Encrypt;
import com.yc.web.dto.JsonModel;

@Api(value = "/merchant", tags = "商家模块")
@Controller
public class MerchantControllers {

	@Resource(name = "merchantBizImpl")
	private MerchantBiz mb;

	@Autowired
	JsonModel jm;

	@Autowired
	private RedisDao redisDao;

	/**
	 * 商家注册
	 * 
	 * @param request
	 * @param mbInfo
	 * @param session
	 * @return
	 */
	@PostMapping("Merchant_baseinfoReg.action")
	@ApiOperation(value = "商家注册", notes = "商家注册")
	// name–参数名 value–参数说明 required–是否必填 dataType–重写属性类型
	@ApiImplicitParam(name = "merchant_baseinfo", value = "Merchant_baseinfo", dataType = "Merchant_baseinfo")
	public ModelAndView Merchant_baseinfoReg(HttpServletRequest request, Merchant_baseinfo mbInfo, HttpSession session) {
		boolean isExists = mb.isExists(mbInfo);
		ModelAndView mad = new ModelAndView();
		String username = mbInfo.getMerchant_username();
		request.setAttribute("telephone", username);

		if (username != null && username != "") {
			mbInfo.setMerchant_telephone(username);
		}
		if (!isExists) {
			mad.setViewName("reg");
			return mad;
		}
		return mad;
	}

	@ApiOperation(value = "商家注册", notes = "商家注册2")
	// name–参数名 value–参数说明 required–是否必填 dataType–重写属性类型
	@ApiImplicitParam(name = "merchant_baseinfo", value = "Merchant_baseinfo", dataType = "Merchant_baseinfo")
	@ResponseBody
	@PostMapping("/addMerchant_baseInfo.action")
	public JsonModel AddMerchant_baseinfo(Merchant_baseinfo mbInfo, HttpSession session) {
		try {
			boolean ok = mb.register(mbInfo);
			if (ok) {
				jm.setCode(1);
			}
		} catch (Exception e) {
			jm.setCode(0);
			jm.setMsg(e.toString());
		}
		return jm;
	}

	@ApiOperation(value = "商家登陆", notes = "商家登陆")
	// name–参数名 value–参数说明 required–是否必填 dataType–重写属性类型
	@ApiImplicitParam(name = "merchant_baseinfo", value = "Merchant_baseinfo", dataType = "Merchant_baseinfo")
	@ResponseBody
	@PostMapping("/Merchant_baseinfoLogin.action")
	public JsonModel Merchant_baseinfo_login(Merchant_baseinfo mbInfo, HttpServletRequest request, HttpSession session,
			String username, String password) {
		jm.setCode(0);
		List<String> merchantLoginList = new ArrayList<String>();
		String encryptPwd = Encrypt.md5AndSha2(password);
		mbInfo.setMerchant_password(encryptPwd);
		mbInfo.setMerchant_username(username);

		mbInfo = mb.login(mbInfo);
		if (mbInfo != null) {
			List<String> LoginList = (List<String>) session.getAttribute("merchantLoginList");
			if (LoginList != null && LoginList.size() > 0) {
				for (String username1 : LoginList) {
					if (mbInfo.getMerchant_username().equals(username1)) {
						jm.setCode(2);
						return jm;
					}
				}
			}
			try {
				jm.setCode(1);
				jm.setMsg("登陆成功");
				session.setAttribute("loginuerid", mbInfo.getMerchant_id());
				merchantLoginList.add(mbInfo.getMerchant_username());
				String loginusername = mbInfo.getMerchant_username();
				session.setAttribute("loginusername", loginusername);
				session.setAttribute("merchantLoginList", merchantLoginList);
			} catch (Exception e) {
				jm.setMsg("账号或密码错误");
			}
		} else {
			jm.setMsg("用户不存在");
		}

		session.setAttribute("jsonModel", jm);
		return jm;
	}

	@ApiOperation(value = "商家下线", notes = "商家下线")
	// name–参数名 value–参数说明 required–是否必填 dataType–重写属性类型
	@ApiImplicitParam(name = "merchant_baseinfo", value = "Merchant_baseinfo", dataType = "Merchant_baseinfo")
	@RequestMapping("Merchant_logout.action")
	public String logout(HttpSession session, HttpServletRequest request) {
		String loginusername = (String) session.getAttribute("loginusername");
		List<String> merchantLoginList = new ArrayList<String>();
		if (session.getAttribute("merchantLoginList") != null) {
			merchantLoginList = (List<String>) session.getAttribute("merchantLoginList");
			merchantLoginList.remove(loginusername);
		}
		session.setAttribute("merchantLoginList", merchantLoginList);
		session.removeAttribute("hostname");
		session.invalidate();
		return "login";
	}

	@ApiOperation(value = "商家基本信息更新", notes = "跳转到商家基本信息更新")
	// name–参数名 value–参数说明 required–是否必填 dataType–重写属性类型
	@ApiImplicitParam(name = "merchant_baseinfo", value = "Merchant_baseinfo", dataType = "Merchant_baseinfo")
	@ResponseBody
	@PostMapping("user/toUpdateMerchant_baseinfo.action")
	public JsonModel toUpdateMerchant_baseinfo(HttpServletRequest request, Merchant_baseinfo mbInfo) {
		mbInfo = mb.findoneMerchant_baseinfo(mbInfo);
		jm.setCode(1);
		jm.setObj(mbInfo);
		return jm;
	}

	@ApiOperation(value = "商家基本信息更新", notes = "商家基本信息更新")
	// name–参数名 value–参数说明 required–是否必填 dataType–重写属性类型
	@ApiImplicitParam(name = "merchant_baseinfo", value = "Merchant_baseinfo", dataType = "Merchant_baseinfo")
	@ResponseBody
	@PostMapping("user/updateMerchant_baseinfo.action")
	public JsonModel UpdateMerchant_baseInfo(Merchant_baseinfo mbInfo, HttpServletRequest request) {
		try {
			int r = mb.updateBaseinfo(mbInfo);
			if (r > 0) {
				jm.setCode(1);
				jm.setMsg("更新成功");
			} else {
				jm.setCode(0);
				jm.setMsg("更新失败");
			}
		} catch (Exception e) {
			jm.setCode(0);
			jm.setMsg(e.toString());
		}
		return jm;
	}

	@ApiOperation(value = "商家发布信息", notes = "商家添加工作信息")
	// name–参数名 value–参数说明 required–是否必填 dataType–重写属性类型
	@ApiImplicitParam(name = "merchant_wantedjob", value = "Merchant_wantedjob", dataType = "Merchant_wantedjob")
	@ResponseBody
	@PostMapping("user/addMerchant_wantedJob.action")
	public JsonModel insertMerchant_wantedJob(Merchant_wantedjob mwJob, HttpServletRequest request, String province, String city,
			String school) {
		String path = province + "_" + city + "_" + school;
		mwJob.setWorkplace(path);
		System.out.println(province + "\t" + city + "\t" + school + "\t" );
		try {
			int r = mb.insertMwjob(mwJob);
			if (r > 0) {
				jm.setCode(1);
				jm.setMsg("发布成功");
			} else {
				jm.setCode(0);
				jm.setMsg("发布失败");
			}
		} catch (Exception e) {
			jm.setCode(0);
			jm.setMsg(e.toString());
		}
		return jm;
	}

	@ApiOperation(value = "商家工作信息", notes = "跳转到商家工作信息更新")
	// name–参数名 value–参数说明 required–是否必填 dataType–重写属性类型
	@ApiImplicitParam(name = "merchant_wantedjob", value = "Merchant_wantedjob", dataType = "Merchant_wantedjob")
	@ResponseBody
	@PostMapping("toUpdateMerchant_wantedJob.action")
	public JsonModel toUpdateMerchant_wantedJob(Merchant_wantedjob mwJob) {
		Map<String, Object> map = new HashMap<String, Object>();
		mwJob = mb.findoneMerchant_wantedjob(mwJob);
		jm.setCode(1);
		jm.setObj(mwJob);
		return jm;
	}

	@ApiOperation(value = "商家工作信息", notes = "商家工作信息更新")
	// name–参数名 value–参数说明 required–是否必填 dataType–重写属性类型
	@ApiImplicitParam(name = "merchant_baseinfo", value = "Merchant_baseinfo", dataType = "Merchant_baseinfo")
	@ResponseBody
	@PostMapping("user/updateMerchant_wantedJob.action")
	public JsonModel UpdateMerchant_wantedJob(Merchant_wantedjob mwJob, HttpServletRequest request) {
		try {
			int r = mb.updateWantedjob(mwJob);
			if (r > 0) {
				jm.setCode(1);
				jm.setMsg("更新成功");
			}
		} catch (Exception e) {
			jm.setCode(0);
			jm.setMsg(e.toString());
		}
		return jm;
	}

	/**
	 * 查找商家自己发布的所有信息
	 * 
	 * @param merchant_id
	 * @return
	 */
	@ApiOperation(value = "商家工作信息", notes = "查找商家自己发布的所有信息")
	// name–参数名 value–参数说明 required–是否必填 dataType–重写属性类型
	@ApiImplicitParam(name = "merchant_baseinfo", value = "Merchant_baseinfo", dataType = "Merchant_baseinfo")
	@ResponseBody
	@PostMapping("user/findWantedJobList.action")
	public JsonModel findWantedJobList(Merchant_wantedjob mwJob) {
		List<Merchant_wantedjob> mwJoblist = mb.findListWantedJob(mwJob);
		jm.setObj(mwJoblist);
		return jm;
	}

	@RequestMapping("/Administrator/Merchant_baseinfoList.action")
	@ResponseBody
	public String adminList(HttpServletRequest request, HttpSession session, @RequestParam(name = "page") Integer pages,
			@RequestParam(name = "limit") Integer pagesize) {
		Map<String, Object> map = new HashMap<String, Object>();
		Integer start = (pages - 1) * pagesize;
		map.put("start", start);
		map.put("pages", pages);
		map.put("pagesize", pagesize);
		try {
			List<Map<String, Object>> list = mb.findAllList(Merchant_baseinfo.class, map);
			List<Merchant_baseinfo> Merchant_baseinfolist = new ArrayList<Merchant_baseinfo>();
			int total = 0;
			for (Map<String, Object> returnmap : list) {
				total = (Integer) returnmap.get("total");
				Merchant_baseinfolist = (List<Merchant_baseinfo>) returnmap.get("Merchant_baseinfoList");
			}
			JSONArray json = new JSONArray();
			// 将集合变为json
			for (Merchant_baseinfo u : Merchant_baseinfolist) {
				JSONObject jo = new JSONObject();
				jo.put("merchant_id", u.getMerchant_id());
				jo.put("merchant_name", u.getMerchant_name());
				jo.put("merchant_username", u.getMerchant_username());
				jo.put("merchant_telephone", u.getMerchant_telephone());
				jo.put("merchant_license", u.getMerchant_license());
				jo.put("merchant_idcard", u.getMerchant_idcard());
				jo.put("merchant_hostname", u.getMerchant_hostname());
				jo.put("merchant_email", u.getMerchant_email());
				jo.put("power", u.getPower());
				json.add(jo);
			}
			// 将数据准备好layUI的格式
			JSONObject jobj = new JSONObject();
			jobj.put("code", 0);
			jobj.put("msg", "");
			jobj.put("count", total);
			jobj.put("data", json);
			// System.out.println("total"+total+jobj.toString());
			return jobj.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping("/Administrator/Merchant_baseinfoPower.action")
	@ResponseBody
	public JsonModel del(JsonModel jm, HttpSession session, int merchant_id, HttpServletRequest request) {
		Merchant_baseinfo a = new Merchant_baseinfo();
		a.setMerchant_id(merchant_id);
		int flag = mb.setPower(a);
		if (flag == 1) {
			jm.setCode(1);
			jm.setMsg("设置成功!");
		} else {
			jm.setCode(0);
			jm.setMsg("设置失败!");
		}
		session.setAttribute("jsonModel", jm);
		return jm;
	}

	/**
	 * 查找商家已经发布的所有信息
	 * 
	 * @param merchant_id
	 * @return
	 */
	@ApiOperation(value = "商家工作信息", notes = "条件查找所有信息")
	// name–参数名 value–参数说明 required–是否必填 dataType–重写属性类型
	@ApiImplicitParam(name = "merchant_baseinfo", value = "Merchant_baseinfo", dataType = "Merchant_baseinfo")
	@GetMapping("/findJobList.action")
	public ModelAndView ListJob(HttpServletRequest request, HttpSession session, Integer pages, Integer pageSize,
			Merchant_wantedjob mwJob) {
		Map<String, Object> map = new HashMap<String, Object>();
		ModelAndView mav = new ModelAndView();
		map.put("job_id", mwJob.getJob_id());
		map.put("workcleanform", mwJob.getWorkcleanform());
		map.put("pages", pages);
		map.put("pageSize", pageSize);
		JsonModel<Merchant_wantedjob> list = mb.findAllWantedJob(map);
		request.setAttribute("list", list);
		session.setAttribute("merchant_wantedjob", list.getRows());
		mav.setViewName("show");
		if (list.getRows() == null || list.getRows().size() <= 0) {
			list.setPages(0);
			list.setPageSize(0);
		}
		return mav;
	}

	/**
	 * 排行工作信息
	 * 
	 * @param merchant_id
	 * @return
	 */
	@ApiOperation(value = "排行工作信息", notes = "排行信息")
	// name–参数名 value–参数说明 required–是否必填 dataType–重写属性类型
	@ApiImplicitParam(name = "orderBy", value = "String", dataType = "Merchant_baseinfo")
	@RequestMapping("/sortList.action")
	public ModelAndView sort(HttpServletRequest request, String orderBy) {
		ModelAndView mav = new ModelAndView();
		JsonModel<Merchant_wantedjob> list = new JsonModel();
		if (orderBy.equals("热门兼职")) {
			Set<TypedTuple<String>> set = redisDao.rank("JobRank", 0, 10);
			ServletContext application = request.getServletContext();
			List<Merchant_wantedjob> rankList = (List<Merchant_wantedjob>) application.getAttribute("rankList");

			if (rankList != null && rankList.size() > 0) {
				List<Merchant_wantedjob> sortList = new ArrayList();
				for (TypedTuple<String> t : set) {
					for (Merchant_wantedjob mw : rankList)
						if (t.getValue().equals(mw.getMerchant_wantedjob_id() + "")) {
							sortList.add(mw);
						}
				}
				list.setPages(0);
				list.setPageSize(0);
				list.setRows(sortList);

			}
		}
		request.setAttribute("list", list);
		mav.setViewName("show");
		return mav;
	}

	/**
	 * 详细信息信息
	 * 
	 * @param merchant_id
	 * @return
	 */
	@ApiOperation(value = "工作详细信息", notes = "详细信息信息")
	// name–参数名 value–参数说明 required–是否必填 dataType–重写属性类型
	@ApiImplicitParam(name = "merchant_baseinfo", value = "Merchant_baseinfo", dataType = "Merchant_baseinfo")
	@GetMapping("/todetail.action")
	public ModelAndView DetailJob(HttpServletRequest request, HttpSession session, Merchant_wantedjob mwJob) {

		ModelAndView mav = new ModelAndView();
		List<Merchant_wantedjob> list = (List<Merchant_wantedjob>) session.getAttribute("merchant_wantedjob");
		for (Merchant_wantedjob mw : list) {
			if (mw.getMerchant_wantedjob_id() == mwJob.getMerchant_wantedjob_id()) {
				mwJob = mw;
			}
		}
		request.setAttribute("Merchant_wantedjob", mwJob);
		mav.setViewName("indexdetail");
		// redis中计数
		redisDao.addCount("JobRank", mwJob.getMerchant_wantedjob_id() + "");
		double num = redisDao.getValue("JobRank", mwJob.getMerchant_wantedjob_id() + "");
		if (num <= 1) {
			// 将数据存到排行榜list中，避免查询数据库
			ServletContext application = request.getServletContext();
			List<Merchant_wantedjob> rankList = (List<Merchant_wantedjob>) application.getAttribute("rankList");
			rankList.add(mwJob);
		}

		return mav;
	}

}
