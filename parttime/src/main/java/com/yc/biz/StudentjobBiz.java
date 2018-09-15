package com.yc.biz;

import com.yc.bean.Student_wantedjob;

public interface StudentjobBiz {
   
	/**
	 * 添加简历
	 * @param student_wantedjob
	 * @return
	 */
	public boolean registerjob(Student_wantedjob student_wantedjob);
   
	 /**
	  * 更新简历
	  * @param student_wantedjob
	  * @return
	  */
	public boolean updatejob(Student_wantedjob student_wantedjob);
	
	
	public Student_wantedjob studentjob(Student_wantedjob student_wantedjob);
}
