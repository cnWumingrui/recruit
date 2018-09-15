<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="header.jsp"%>
<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<link rel="stylesheet" type="text/css" href="css/index.css" />
<div id="header">
	<div class="header-m">
		<img src="img/logo_about.png" />
		<div id="nav">
			<a href="#">首页</a>
			<c:if test="${loginusername!=null }">
				<a href="merchant.jsp" class="entrance">商家：${loginusername }</a>
				<a href="Merchant_logout.action" class="entrance">下线</a>
			</c:if>
			<c:if test="${studentusername!=null }">
				<a href="studentjob.jsp" class="entrance">学生：${studentusername }</a>
				<a href="Student_logout.action" class="entrance">下线</a>
			</c:if>
			<c:if test="${loginusername==null && studentusername==null   }">
				<a href="login.jsp" class="entrance">登陆\注册</a>
			</c:if>
		</div>
	</div>
</div>
<div id="content">
	<div id="part">
		<div id="choose">
			<div class="choice">
				<div class="choiceC">工作区域：</div>
				<div class="rows">
					<div class="label">省份</div>
					<div class="divide">|</div>
					<div class="text">
						<select id="province" name="province"></select>
					</div>
				</div>
				<div class="rows">
					<div class="label">城市</div>
					<div class="divide">|</div>
					<div class="text">
						<select id="city" name="city"></select>
					</div>
				</div>
				<div class="rows">
					<div class="label">学校</div>
					<div class="divide">|</div>
					<div class="text">
						<select id="school" name="school"></select>
					</div>
				</div>
			</div>
			<div class="choice">
				<div class="choiceC">职位分类：</div>
				<ul class="choiceA" id="choices" style="width: 676px; float: right; margin-right: 35px;">
					<li class="active" id="job_id0" onclick="change(0)">不限</li>
					<c:forEach items="${industryList }" var="job">
						<c:if test="${job.fathernode==0 }">
							<li id="job_id${job.id }" onclick="change(${job.id })">${job.parameter}</li>
						</c:if>
					</c:forEach>
				</ul>
			</div>
			<div class="choice">
				<div id="choiceprofession">
					<div class="choiceC">更多选择：</div>
					<ul class="professions" id="profession" style="width: 676px; float: right;">

					</ul>
				</div>
			</div>
		</div>
	</div>
	<div id="method">
		<div class="sort" style="margin-left: 50px;">
			<span class="ways">默认排序</span>
			<img src="img/arrowgrey@3x.png" />
		</div>
		<div class="sort">
			<span class="ways">结算方式</span>
			<img src="img/arrowgrey@3x.png" />
		</div>
		<ul class="sorted" id="sort1">
			<li onclick="choice1(this)">普通排序</li>
			<li onclick="choice1(this)">离我最近</li>
			<li onclick="choice1(this)">最新发布</li>
			<li onclick="choice1(this)">热门兼职</li>
			<li onclick="choice1(this)">薪资最高</li>
		</ul>
		<ul class="sorted" id="sort2">
			<li onclick="choice2(this)">不限</li>
			<li onclick="choice2(this)">日结</li>
			<li onclick="choice2(this)">周结</li>
			<li onclick="choice2(this)">月结</li>
			<li onclick="choice2(this)">完工结算</li>
			<li onclick="choice2(this)">其他</li>
		</ul>
	</div>
	<ul class="parts" id="part0">
	</ul>

</div>
<div id="footer">
	<div class="center">
		<div class="item">
			<h3>关于我们</h3>
			<a href="">关于我们</a>
			<a href="">商家入驻</a>
			<a href="">找兼职</a>
		</div>
		<div class="item">
			<h3>友情链接</h3>
			<a href="">青团社招聘版</a>
			<a href="">云地推</a>
		</div>
		<div class="item">
			<h3>联系我们</h3>
			<div>
				<span>客服热线:</span>
				<a href="">0571-56076688</a>
			</div>
			<div>
				<span>商务合作:</span>
				<a href="">business@qtshe.com</a>
			</div>
		</div>
		<div class="item">
			<h3>加入我们</h3>
			<div>
				<span>地址:</span>
				<a href="">
					杭州市余杭区文一西路
					<br />
					金之源大厦
				</a>
				<a href="">社招入口 ></a>
			</div>
		</div>
	</div>
	<div class="code">
		<p>@2018 杭州弧途科技有限公司 版权所有.浙ICP备14013428号-1</p>
		<p>浙公网备案 330110022012737号</p>
	</div>
</div>
<div id="connect">
	<img src="img/connect.png" />
</div>
<script type="text/javascript" src="js/index.js"></script>
<script type="text/javascript" src="js/show.js"></script>
<script type="text/javascript" src="js/AllSchool.js"></script>
<script type="text/javascript" src="js/school.js"></script>
</body>
</html>