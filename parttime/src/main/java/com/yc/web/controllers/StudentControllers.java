package com.yc.web.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.yc.bean.Merchant_baseinfo;
import com.yc.bean.Student_baseinfo;
import com.yc.bean.Student_wantedjob;
import com.yc.biz.StudentBiz;
import com.yc.biz.StudentjobBiz;
import com.yc.utils.Encrypt;
import com.yc.web.dto.JsonModel;

@RestController
// 类注解 同时使用@Controller 和 @ResponseBody
public class StudentControllers {

	@Autowired
	JsonModel jm;

	@Resource(name = "studentBizImpl")
	private StudentBiz studentBiz;

	@Resource(name = "studentjobBizImpl")
	private StudentjobBiz studentjobBiz;

	@RequestMapping("/Student_baseinfoReg.action")
	public JsonModel register1(Student_baseinfo student_baseinfo, HttpServletRequest request, HttpSession session) {

		try {
			boolean result1 = studentBiz.validate(student_baseinfo);
			if (result1) {
				boolean result2 = studentBiz.register1(student_baseinfo);
				if (result2) {
					jm.setCode(1);
					jm.setMsg("注册成功");
					int stu_id = student_baseinfo.getStu_id();
					session.setAttribute("stu_id", stu_id);
				}
			} else {
				jm.setCode(0);
				jm.setMsg("注册失败，该用户已经被注册");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jm;

	}

	/**
	 * 基本信息完善
	 * 
	 * @param student_baseinfo
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/Student_baseinfoReg2.action")
	// TODO:赋值电话
	public JsonModel register2(Student_baseinfo student_baseinfo, HttpServletRequest request, HttpSession session) {
		int stu_id = (int) session.getAttribute("stu_id"); // TODO:隐藏域传ID
		student_baseinfo.setStu_id(stu_id);
		String name = request.getParameter("Stu_name");
		String telephone = request.getParameter("Stu_telephone");
		String idcard = request.getParameter("Stu_idcard");
		String email = request.getParameter("Stu_email");
		student_baseinfo.setStu_name(name);
		student_baseinfo.setStu_telephone(telephone);
		student_baseinfo.setStu_idcard(idcard);
		student_baseinfo.setStu_email(email);
		try {
			boolean result = studentBiz.register2(student_baseinfo); // TODO:抛出错误
			if (result) {
				jm.setCode(1);
				return jm;
			}

			jm.setCode(0);
			jm.setMsg("基本信息填写失败");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return jm;
	}

	/**
	 * 登录
	 * 
	 * @param student_baseinfo
	 * @param request
	 * @param session
	 * @return
	 */
	@PostMapping("/Student_login.action")
	public JsonModel login(Student_baseinfo student_baseinfo, HttpServletRequest request, HttpSession session, String username,
			String password) {
		String encryptPwd = Encrypt.md5AndSha2(password);
		student_baseinfo.setStu_username(username);
		student_baseinfo.setStu_password(password);
		Student_baseinfo s = studentBiz.login(student_baseinfo);
		if (s != null) {
			List<String> loginlist = new ArrayList<String>();
			loginlist.add(s.getStu_username());
			List<String> loginlist2 = (List<String>) session.getAttribute("loginlist");
			if (loginlist2 != null) {
				for (Object object : loginlist2) {
					if (object.equals(s.getStu_username())) {
						jm.setMsg("用户已经在线");
						jm.setCode(2);
						return jm;
					}
				}
			}
			System.out.println(s.getStu_name());
			session.setAttribute("loginlist", loginlist);
			session.setAttribute("studentusername", s.getStu_name());
			jm.setMsg("登录成功");
			jm.setCode(1);
			session.setAttribute("stu_id", s.getStu_id());
		} else {
			jm.setMsg("用户名或密码错误");
			jm.setCode(0);
		}
		return jm;
	}

	/**
	 * 下线
	 * 
	 * @param student_baseinfo
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("Student_logout.action")
	public String notlogin(Student_baseinfo student_baseinfo, HttpServletRequest request, HttpSession session) {
		String loginusername = (String) session.getAttribute("studentusername");
		List<String> merchantLoginList = new ArrayList<String>();
		if (session.getAttribute("loginlist") != null) {
			merchantLoginList = (List<String>) session.getAttribute("loginlist");
			merchantLoginList.remove(loginusername);
		}

		session.setAttribute("loginlist", merchantLoginList);
		session.removeAttribute("hostname");
		session.invalidate();
		return "login";
	}

	/**
	 * 更新基本信息
	 * 
	 * @param student_baseinfo
	 * @param request
	 * @param session
	 * @return
	 */

	@ApiOperation(value = "商家基本信息更新", notes = "跳转到商家基本信息更新")
	// name–参数名 value–参数说明 required–是否必填 dataType–重写属性类型
	@ApiImplicitParam(name = "student_baseinfo", value = "student_baseinfo", dataType = "student_baseinfo")
	@ResponseBody
	@PostMapping("user/toUpdateStudent_baseinfo.action")
	public JsonModel toUpdate(HttpServletRequest request, String stu_id) {
		
		Student_baseinfo s = studentBiz.getStudentId(Integer.parseInt(stu_id));
		jm.setCode(1);
		jm.setObj(s);
		return jm;
	}

	@ApiOperation(value = "商家基本信息更新", notes = "商家基本信息更新")
	// name–参数名 value–参数说明 required–是否必填 dataType–重写属性类型
	@ApiImplicitParam(name = "merchant_baseinfo", value = "Merchant_baseinfo", dataType = "Merchant_baseinfo")
	@ResponseBody
	@PostMapping("user/updateStudent_baseinfo.action")
	public JsonModel Update(Student_baseinfo mbInfo, HttpServletRequest request) {
		try {
			int r = studentBiz.update(mbInfo);
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
}
