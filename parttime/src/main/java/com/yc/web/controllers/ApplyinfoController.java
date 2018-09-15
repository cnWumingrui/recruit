package com.yc.web.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yc.bean.Applyinfo;
import com.yc.biz.ApplyinfoBiz;
import com.yc.web.dto.JsonModel;

@Slf4j
@Api(value = "/applyinfo", tags = "应聘后信息")
// 该注解放在一个类上面，用来表明类中包含作为HTTP接口的方法，该注解的value用来设置接口的前缀
@Controller
public class ApplyinfoController {
	@Resource(name = "applyinfoBizImpl")
	private ApplyinfoBiz applyinfoBiz;

	@Autowired
	JsonModel jsonModel;

	// value用于方法描述 notes用于提示内容
	@ApiOperation(value = "存储应聘信息", notes = "点击应聘操作")
	// name–参数名 value–参数说明 required–是否必填 dataType–重写属性类型
	@ApiImplicitParam(name = "applyinfo", value = "applyinfo", required = true, dataType = "Applyinfo")
	@RequestMapping("user/save_applyinfo.action")
	@ResponseBody
	public JsonModel save_applyinfo(Applyinfo applyinfo,HttpSession session) {
		//TODO: 取学生id
		int stu_id= (int) session.getAttribute("stu_id");
	//	int stu_id = 1;
		applyinfo.setStu_id(stu_id);
		List<Applyinfo> isexist = applyinfoBiz.seleStudentLsit(applyinfo);
		if (isexist == null || isexist.size() > 0) {
			jsonModel.setCode(1);
		} else {
			boolean flag = applyinfoBiz.saveApplyinfo(applyinfo);
			if (flag) {
				jsonModel.setCode(1);
				jsonModel.setMsg("添加成功");
			} else {
				jsonModel.setCode(0);
			}
		}
		return jsonModel;

	}

	// value用于方法描述 notes用于提示内容
	@ApiOperation(value = "查询应聘信息", notes = "查看应聘信息")
	// name–参数名 value–参数说明 required–是否必填 dataType–重写属性类型
	@ApiImplicitParam(name = "applyinfo", value = "applyinfo", required = true, dataType = "Applyinfo")
	@PostMapping("user/select_applyinfo")
	@ResponseBody
	public JsonModel select_applyinfo(Applyinfo applyinfo) {

		List<Applyinfo> list = applyinfoBiz.getApplyinfo(applyinfo);
		if (list != null && list.size() > 0) {
			jsonModel.setCode(1);
			jsonModel.setObj(list);
		} else {
			jsonModel.setCode(0);
		}
		return jsonModel;
	}

	// value用于方法描述 notes用于提示内容
	@ApiOperation(value = "查询应聘信息", notes = "查看应聘信息")
	// name–参数名 value–参数说明 required–是否必填 dataType–重写属性类型
	@ApiImplicitParam(name = "applyinfo", value = "applyinfo", required = true, dataType = "Applyinfo")
	@RequestMapping("user/selectStudentList.action")
	@ResponseBody
	public JsonModel selectStudentList(Applyinfo applyinfo) {

		List<Applyinfo> list = applyinfoBiz.seleStudentLsit(applyinfo);
		if (list != null && list.size() > 0) {
			jsonModel.setCode(1);
			jsonModel.setObj(list);
		} else {
			jsonModel.setObj(list);
			jsonModel.setCode(0);
		}
		return jsonModel;
	}

}
