<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="header.jsp"%>
<link rel="stylesheet" type="text/css" href="css/detail.css" />
<div id="parttimejob" class="cont_left">
	<span class="tit">修改工作信息</span>
	<div class="details">
		<form id="parrtimejob">
			<input type="hidden" id="merchant_wantedjob_id" name="merchant_wantedjob_id" value="<%=request.getParameter("id")%>" />
			<p id="txt0" class="txt1"></p>
			<p id="txt1" class="txt1"></p>
			<p class="txt1">
				工资结算:
				<select id="workcleanform" name="workcleanform">
					<option value="日结">日结</option>
					<option value="周结">周结</option>
					<option value="月结">月结</option>
					<option value="完工结">完工结</option>
				</select>
			</p>
			<br />
			<p id="P_workdescp" class="txt"></p>
			<p id="P_workdemand" class="txt"></p>
			<p id="P_workcontent" class="txt"></p>
			<input type="button" onclick="updataworkinfo()" value="发布消息" />
		</form>
	</div>
	<a id="t1" href="merchant.jsp">返回上一页</a>
</div>

<script type="text/javascript" src="js/index.js"></script>
<script type="text/javascript" src="js/merchant.js"></script>
<script type="text/javascript" src="js/school.js"></script>
<script type="text/javascript" src="js/Allschool.js"></script>
<script type="text/javascript" src="js/detail.js"></script>
</body>
</html>