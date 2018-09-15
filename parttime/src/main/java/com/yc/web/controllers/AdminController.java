package com.yc.web.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yc.bean.Admin;
import com.yc.bean.JsonModel;
import com.yc.biz.AdminBiz;
import com.yc.utils.Encrypt;
import com.yc.utils.RequestUtils;

@Controller
public class AdminController {

	
	@Resource(name="adminBizImpl")
	private AdminBiz adminBiz;
	
	@RequestMapping("/Administrator/Admin_login.action")
	@ResponseBody
	public JsonModel login(JsonModel jm, HttpSession session, Admin admin, HttpServletRequest request) throws Exception{
		 if (request == null) {
	            throw (new Exception("getIpAddr method HttpServletRequest Object is null"));
	     }
//		String zccode = request.getParameter("zccode");
//		String rand = session.getAttribute("sRand").toString();
		List<String> UserList = new ArrayList<String>();
		boolean r = adminBiz.isUnameExists(admin);
//		if (!rand.equals(zccode)) {
//			jm.setCode(0);
//			jm.setMsg("验证码错误");
//		} else 
		if(admin.getAdmin_username()=="" || admin.getAdmin_username() == null){
			jm.setCode(0);
			jm.setMsg("账户不能为空");
		}else if(admin.getAdmin_password()=="" || admin.getAdmin_password() == null){
			jm.setCode(0);
			jm.setMsg("密码不能为空");
		}else if (!r) {
			jm.setCode(0);
			jm.setMsg("用户不存在");
		} else {
			List<String> list = (List<String>) session.getAttribute("UserList");
			if (list != null && list.size() > 0) {
				for (String username1 : list) {
					if (admin.getAdmin_username().equals(username1)) {
						jm.setCode(0);
						jm.setMsg("该用户已经登陆");
					} else {
						admin.setAdmin_password(Encrypt.md5(admin.getAdmin_password())); 
						admin = adminBiz.login(admin);
						if (admin != null) {
							//存ip
							 String ipString = request.getHeader("x-forwarded-for");
						        if (StringUtils.isEmpty(ipString) || "unknown".equalsIgnoreCase(ipString)) {
						            ipString = request.getHeader("Proxy-Client-IP");
						        }
						        if (StringUtils.isEmpty(ipString) || "unknown".equalsIgnoreCase(ipString)) {
						            ipString = request.getHeader("WL-Proxy-Client-IP");
						        }
						        if (StringUtils.isEmpty(ipString) || "unknown".equalsIgnoreCase(ipString)) {
						            ipString = request.getRemoteAddr();
						        }
						     
						        // 多个路由时，取第一个非unknown的ip
						        final String[] arr = ipString.split(",");
						        for (final String str : arr) {
						            if (!"unknown".equalsIgnoreCase(str)) {
						                ipString = str;
						                break;
						            }
						        }
						        admin.setAdmin_lastip(ipString);
						        int i = adminBiz.saveIp(admin);
							//
							jm.setCode(1);
							jm.setMsg("登录成功");
							UserList.add(admin.getAdmin_username());
							session.setAttribute("username", admin.getAdmin_username());
							session.setAttribute("UserList", UserList);
							session.setAttribute("permission", admin.getAdmin_permission());
						} else {
							jm.setCode(0);
							jm.setMsg("账号或密码错误");
						}
					}
				}
			} else {
				admin.setAdmin_password(Encrypt.md5(admin.getAdmin_password()));
				admin = adminBiz.login(admin);
				if (admin != null) {
					String ipString = request.getHeader("x-forwarded-for");
			        if (StringUtils.isEmpty(ipString) || "unknown".equalsIgnoreCase(ipString)) {
			            ipString = request.getHeader("Proxy-Client-IP");
			        }
			        if (StringUtils.isEmpty(ipString) || "unknown".equalsIgnoreCase(ipString)) {
			            ipString = request.getHeader("WL-Proxy-Client-IP");
			        }
			        if (StringUtils.isEmpty(ipString) || "unknown".equalsIgnoreCase(ipString)) {
			            ipString = request.getRemoteAddr();
			        }
			     
			        // 多个路由时，取第一个非unknown的ip
			        final String[] arr = ipString.split(",");
			        for (final String str : arr) {
			            if (!"unknown".equalsIgnoreCase(str)) {
			                ipString = str;
			                break;
			            }
			        }
			        admin.setAdmin_lastip(ipString);
			        int i = adminBiz.saveIp(admin);
					jm.setCode(1);
					jm.setMsg("登录成功");
					UserList.add(admin.getAdmin_username());
					session.setAttribute("username", admin.getAdmin_username());
					session.setAttribute("UserList", UserList);
					session.setAttribute("permission", admin.getAdmin_permission());
				} else {
					jm.setCode(0);
					jm.setMsg("账号或密码错误");
				}
			}
		}
		return jm;
	}
	
	@RequestMapping("/Administrator/Admin_register.action")
	@ResponseBody
	public JsonModel register(JsonModel jm, Admin user, HttpSession session) {

			boolean r = adminBiz.isUnameExists(user);
			if (!r) {
				boolean re;
				try {
					user.setAdmin_password(Encrypt.md5(user.getAdmin_password()));
					re = adminBiz.reg(user);
					if (re) {
						jm.setCode(1);
						jm.setMsg("注册成功");
					}
				} catch (Exception e) {
					jm.setCode(0);
					jm.setMsg(e.getMessage());
				}
				
			} else {
				jm.setCode(0);
				jm.setMsg("注册失败，已经有该用户");
			}
			session.setAttribute("jsonModel", jm);
			return jm;
		
	}
	
	@RequestMapping("/Administrator/Admin_update.action")
	@ResponseBody
	public JsonModel update(JsonModel jm, Admin user, HttpSession session) {
		
			user.setAdmin_oldpassword(Encrypt.md5(user.getAdmin_oldpassword()));
			int r =   adminBiz.updatePwd(user);
			if (r==1) {
				int re;
				try {
					user.setAdmin_password(Encrypt.md5(user.getAdmin_password()));
					re = (int)adminBiz.update(user);
					if (re==1) {
						jm.setCode(1);
						jm.setMsg("修改成功");
					}
				} catch (Exception e) {
					jm.setCode(0);
					jm.setMsg(e.getMessage());
				}
			} else {
				jm.setCode(0);
				jm.setMsg("修改失败，两次输入密码不正确...");
			}
			session.setAttribute("jsonModel", jm);
			return jm;
		
	}
	
	@RequestMapping("/Administrator/Admin_userList.action")
	@ResponseBody
	public String adminList(HttpServletRequest request, HttpSession session,@RequestParam(name = "page") Integer pages,
			@RequestParam(name = "limit") Integer pagesize) {
		Map<String, Object> map = new HashMap<String, Object>();
		Integer start = (pages - 1) * pagesize;
		map.put("start", start);
		map.put("pages", pages);
		map.put("pagesize", pagesize);
		try {
            List<Map<String,Object>> list = adminBiz.selectList(Admin.class, map);
            List<Admin> adminlist = new ArrayList<Admin>();
            int total = 0;
            for(Map<String,Object> returnmap : list){
            	total = (Integer)returnmap.get("total");
            	adminlist = (List<Admin>) returnmap.get("adminList");
            }
            JSONArray json = new JSONArray();
            //将集合变为json
            for (Admin u: adminlist) {
                JSONObject jo = new JSONObject();
                if(!u.getAdmin_username().equals(session.getAttribute("username"))){
                	 jo.put("admin_id",u.getAdmin_id());
                     jo.put("admin_username",u.getAdmin_username());
                     if(u.getAdmin_permission() == 0){
                     	jo.put("admin_permission","总管理");
                     }else{
                     	jo.put("admin_permission", "子管理");
                     }
                     jo.put("admin_jointime",u.getAdmin_jointime().toString());
                     jo.put("admin_lastip", u.getAdmin_lastip());
                     json.add(jo);
                }
            }
            //将数据准备好layUI的格式
            JSONObject jobj = new JSONObject();
            jobj.put("code",0);
            jobj.put("msg","");
            jobj.put("count",total);
            jobj.put("data",json);
//            System.out.println("total"+total+jobj.toString());
            return jobj.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
		return null;
	}
	
	@RequestMapping("/Admin_logout.action")
	public String logout(HttpSession session, HttpServletRequest request) {
		String username = request.getParameter("admin_username");
		List<String> adminList = new ArrayList<String>();
		if (session.getAttribute("adminList") != null) {
			adminList = (List<String>) session.getAttribute("adminList");
			adminList.remove(username);
		}
		session.setAttribute("adminList", adminList);
		session.removeAttribute("admin_username");
		session.invalidate();
		return "Administrator/Login";
	}
	
	@RequestMapping("/Administrator/Admin_del.action")
	@ResponseBody
	public JsonModel del(JsonModel jm, HttpSession session, int admin_id, HttpServletRequest request) {
		Admin a = new Admin();
		a.setAdmin_id(admin_id);
		boolean flag = adminBiz.delete(a);
		if(flag){
			jm.setCode(1);
			jm.setMsg("删除成功!");
		}else{
			jm.setCode(0);
			jm.setMsg("删除失败!");
		}
		session.setAttribute("jsonModel", jm);
		return jm;
	}
	
	@RequestMapping("/Administrator/Admin_delMutil.action")
	@ResponseBody
	public JsonModel delMutil(JsonModel jm, HttpSession session,Integer[] list, HttpServletRequest request) {
		List<Admin> adminList = new ArrayList<Admin>();
		List<Integer> intList = Arrays.asList(list);
		for(int i=0;i<intList.size();i++){
			Admin a = new Admin();
			a.setAdmin_id((Integer)intList.get(i));
			adminList.add(a);
		}
		boolean flag = adminBiz.delMutil(adminList);
		if(flag){
			jm.setCode(1);
			jm.setMsg("删除成功!");
		}else{
			jm.setCode(0);
			jm.setMsg("删除失败!");
		}
		session.setAttribute("jsonModel", jm);
		return jm;
	}
	
	@RequestMapping("/Administrator/Admin_re.action")
	@ResponseBody
	public JsonModel re(JsonModel jm, HttpSession session, int admin_id, HttpServletRequest request) {
		Admin a = new Admin();
		a.setAdmin_id(admin_id);
		int flag = adminBiz.re(a);
		if(flag==1){
			jm.setCode(1);
			jm.setMsg("重置成功!");
		}else{
			jm.setCode(0);
			jm.setMsg("重置失败!");
		}
		session.setAttribute("jsonModel", jm);
		return jm;
	}
}
