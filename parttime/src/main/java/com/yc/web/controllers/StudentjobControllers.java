package com.yc.web.controllers;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yc.bean.Student_wantedjob;
import com.yc.biz.StudentjobBiz;
import com.yc.web.dto.JsonModel;



@RestController //类注解 同时使用@Controller 和 @ResponseBody
public class StudentjobControllers {
	
	@Resource(name="studentjobBizImpl")
	private StudentjobBiz studentjobBiz;
	
	
	  //TODO 添加简历
	  @PostMapping("Studentjob.action")
	  public JsonModel registerjob(Student_wantedjob student_wantedjob,HttpServletRequest request,HttpSession session){
		  JsonModel jm=new JsonModel();
		  int stu_id=(int) session.getAttribute("stu_id");
		  student_wantedjob.setStu_id(stu_id);
		   String province = request.getParameter("province");
			String city = request.getParameter("city");
			String school = request.getParameter("school");
			int job_id = Integer.parseInt(request.getParameter("jobid"));
			String path = province + "_" + city + "_" + school;
			System.out.println(province + "\t" + city + "\t" + school + "\t" + job_id);
			student_wantedjob.setJob_id(job_id);
			student_wantedjob.setWorkplace(path);
		  Student_wantedjob ss=studentjobBiz.studentjob(student_wantedjob);
		  if(ss==null){  
			 boolean result=studentjobBiz.registerjob(student_wantedjob);
			 if(result){
				 jm.setCode(1);
		         jm.setMsg("简历提交成功");
		         session.setAttribute("student_wantedjob", student_wantedjob);
			 }else{
			      
			 jm.setCode(0);
			 jm.setMsg("简历提交失败");
			 }
		  }else{
			  jm.setCode(0);    //TODO:更新操作
		  }

		  return jm;
		  
	  }
	  
	  
	  
	  
 //TODO 修改求职意向
	  public JsonModel updatejob(Student_wantedjob student_wantedjob,HttpServletRequest request,HttpSession session){
		  JsonModel jm=new JsonModel();
		  int stu_id=(int) session.getAttribute("stu_id");
		  student_wantedjob.setStu_id(stu_id);
		  Student_wantedjob ss=studentjobBiz.studentjob(student_wantedjob);
		  if(ss!=null){
			 
			  
			 boolean result=studentjobBiz.updatejob(student_wantedjob);
			 if(result){
				 jm.setCode(1);
		         jm.setMsg("简历修改成功");
			 }else{
			      
			 jm.setCode(0);
			 jm.setMsg("简历修改败");
			 }
		  }else{
			  jm.setCode(0);    //TODO:更新操作
		  }

		  return jm;
	  }
	
}
