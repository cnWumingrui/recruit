<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="header.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<link rel="stylesheet" type="text/css" href="css/merchant.css" />
<div id="header">
	<div class="header-m">
		<img src="img/logo_about.png" />
		<div id="nav">
			<a href="../index.jsp">首页</a>
			<a href="" class="entrance">商家：${loginusername } 的个人中心</a>
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
			<form id="baseinfo" style="text-align: center"></form>
		</div>
	</div>


	<!-- ===========================================招聘信息====================================================== -->
	<div id="jobinfo" class="cont_left">
		<span class="tit">招聘信息</span>
		<div class="details">
			<table id="showlist">

			</table>
			 <div id="showStudent"> </div>
		</div>
	</div>


	<!-- ===========================================发布信息====================================================== -->
	<div id="parttimejob" class="cont_left">
		<span class="tit">发布兼职</span>
		<div class="details">
			<form id="parrtimejob">
				<input type="hidden" name="merchant_id" value="${loginuerid }" />
				<input type="hidden" id="job_id" name="job_id" value="1" />

				<p class="txt1">
					标题：
					<input type="text" name="title" placeholder="填写您的工作标题" />
				</p>

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

				&nbsp; &nbsp;工资结算:
				<select name="workcleanform">
					<option>日结</option>
					<option>周结</option>
					<option>月结</option>
					<option>完工结</option>
				</select>
				工资：
				<input type="text" name="salary" placeholder="" />
				</br>
				<p class="txt">
					工作描述:
					<textarea name="workdescp" placeholder="请填入这份工作需要的技能..."></textarea>
				</p>
				<p class="txt">
					工作需求:
					<textarea name="workdemand" placeholder="请填入这份工作需要的技能..."></textarea>
				</p>
				<p class="txt">
					工作内容:
					<textarea name="workcontent" placeholder="描述该工作的内容"> </textarea>
				</p>
				<input type="button" onclick="postJobInfo()" value="发布消息" />
			</form>
		</div>
	</div>
	<!-- ===========================================右边====================================================== -->
	<div class="right">
		<div class="message">
			<div class="title">信息发布</div>
			<span class="button" onclick="tochangeBaseinfo(${loginusername})">基本信息</span>
			<span class="button" onclick="showList(${loginuerid})">招聘信息</span>
			<span class="button">发布兼职</span>
		</div>
	</div>
	<script type="text/javascript" src="js/index.js"></script>
	<script type="text/javascript" src="js/merchant.js"></script>
	<script type="text/javascript" src="js/school.js"></script>
	<script type="text/javascript" src="js/Allschool.js"></script>
	</body>
	</html>