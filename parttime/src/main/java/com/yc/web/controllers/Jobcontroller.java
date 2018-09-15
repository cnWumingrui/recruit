package com.yc.web.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.yc.bean.Job;
import com.yc.biz.JobBiz;
import com.yc.web.dto.JsonModel;

@Slf4j
@Api(value = "/user", tags = "用户模块")
// 该注解放在一个类上面，用来表明类中包含作为HTTP接口的方法，该注解的value用来设置接口的前缀
@RestController
public class Jobcontroller {
	@Resource(name = "jobBizImpl")
	private JobBiz jobBiz;

	// value用于方法描述 notes用于提示内容
	@ApiOperation(value = "查找所有职业", notes = "所有职业")
	// name–参数名 value–参数说明 required–是否必填 dataType–重写属性类型
	@ApiImplicitParam(name = "job", value = "job", required = false, dataType = "Job")
	@GetMapping("getAllJob.action")
	@ResponseBody
	public JsonModel getAllJob() {
		List<Job> list = jobBiz.getAllJob();
		JsonModel jsonModel = new JsonModel();
		if (list != null && list.size() > 0) {
			jsonModel.setCode(1);
			jsonModel.setObj(list);
		} else {
			jsonModel.setCode(0);
			jsonModel.setMsg("查询失败");
		}
		return jsonModel;
	}

	// value用于方法描述 notes用于提示内容
	@ApiOperation(value = "条件查找职业", notes = "条件查找职业")
	// name–参数名 value–参数说明 required–是否必填 dataType–重写属性类型
	@ApiImplicitParam(name = "job", value = "job", required = false, dataType = "Job")
	@PostMapping("getJob.action")
	@ResponseBody
	public JsonModel getJob(Job job) {
		List<Job> list = jobBiz.getJob(job);

		JsonModel jsonModel = new JsonModel();
		if (list != null && list.size() > 0) {
			jsonModel.setCode(1);
			jsonModel.setObj(list);
		} else {
			jsonModel.setCode(0);
			jsonModel.setMsg("查询失败");
		}
		return jsonModel;
	}

	@RequestMapping("profession.action")
	@ResponseBody
	public JsonModel getProfession(String jobid, ServletRequest request, HttpSession session) {
		ServletContext application = request.getServletContext();
		List<Job> professions = new ArrayList<Job>();
		JsonModel jsonModel = new JsonModel();
		int jobId = Integer.parseInt(jobid);
		if (jobId > 0) {
			List<Job> industryList = (List<Job>) application.getAttribute("industryList");
			for (Job j : industryList) {
				if (j.getFathernode() == jobId) {
					professions.add(j);
				}
			}
			if (professions != null && professions.size() > 0) {
				jsonModel.setCode(1);
				jsonModel.setObj(professions);
			} else {
				jsonModel.setCode(0);
			}
		} else {
			jsonModel.setCode(0);
		}
		return jsonModel;
	}
}
