<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="header.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" type="text/css" href="css/merchant.css" />


<div id="header">
	<div class="header-m">
		<img src="img/logo_about.png" />
		<div id="nav">
			<a href="index.jsp">首页</a>
			<a href="" class="entrance">学生：${studentusername } 的个人中心</a>
		</div>
	</div>
</div>
<div id="content">
	<div class="cont_left" style="display: block;">
		<span class="tit">基本信息</span>
		<div class="details"></div>
	</div>
	<!-- ===========================================基本信息====================================================== -->
	<div id="baseinfo" class="cont_left">
		<span class="tit">基本信息</span>
		<div class="details">
			<form id="baseinfo1" style="text-align: center"></form>
		</div>
	</div>
	<!-- ===========================================发布信息====================================================== -->
	<div id="parttimejob" class="cont_left">
		<span class="tit">发布意向</span>
		<div class="details">
			<form id="parrtimejob">

				<input type="hidden" name="stu_id" value="${stu_id }" />
				<input type="hidden" id="job_id" name="job_id" value=" " />
				<!--*********************选择地区*********************-->

				<div class="choiceMerchant">
					<div class="choiceC">工作区域：</div>
					<!-- --------------------------省信息-------------------------- -->
					<div class="rows">
						<div class="label">省份</div>
						<div class="divide">|</div>
						<div class="text">
							<select id="province" name="province"></select>
						</div>
					</div>
					<!-- --------------------------市信息-------------------------- -->
					<div class="rows">
						<div class="label">城市</div>
						<div class="divide">|</div>
						<div class="text">
							<select id="city" name="city"></select>
						</div>
					</div>
					<!-- -------------------------学校信息-------------------------- -->
					<div class="rows">
						<div class="label">学校</div>
						<div class="divide">|</div>
						<div class="text">
							<select id="school" name="school"></select>
						</div>
					</div>
				</div>

				<!--*********************选择工作*********************-->
				<div class="choiceMerchant">
					<div class="choiceC">职位分类：</div>
					<ul class="choiceB" id="choices" style="float: rigth;">
						<li class="choicelist" id="job_id0" onclick="change(0)">不限</li>
						<c:forEach items="${industryList }" var="job">
							<c:if test="${job.fathernode==0 }">
								<li id="job_id${job.id }" onclick="change(${job.id })">${job.parameter}</li>
							</c:if>
						</c:forEach>
					</ul>
				</div>

				<div class="choice">
					<div id="choiceprofession">
						<div id="choiceC" class="choiceC">更多选择：</div>
						<ul class="professions" id="profession" style="width: 676px; float: right;">
						</ul>
					</div>
				</div>
				<!--*********************选择工作*********************-->


				&nbsp; &nbsp;工资：
				<input type="text" name="salary" placeholder="" />
				工资结算:
				<select name="workcleanform">
					<option>日结</option>
					<option>周结</option>
					<option>月结</option>
					<option>完工结</option>
				</select>


				<br />

				&nbsp; &nbsp;<input type="button" onclick="studentjob()" value="提交求职意向" />
			</form>
		</div>
	</div>
	<!-- ===========================================右边====================================================== -->
	<div class="right">
		<div class="message">
			<div class="title">学生信息</div>
			<span class="button" onclick="tochangeBaseinfo1(${stu_id})">基本信息</span>
			<span class="button" onclick="showList()">兼职意向</span>
		</div>
	</div>
	<script type="text/javascript" src="js/index.js"></script>
	<script type="text/javascript" src="js/studentjob.js"></script>
	<script type="text/javascript" src="js/school.js"></script>
	<script type="text/javascript" src="js/Allschool.js"></script>
	</body>
	</html>