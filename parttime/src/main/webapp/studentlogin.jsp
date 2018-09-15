<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="css/loginstyle.css" />

<%@ include file="header.jsp"%>
<script type="text/javascript" src="js/jquery-1.11.0.min.js"></script>
<script type="text/javascript">

function doit() {

	var username = document.getElementById("Stu_username").value;
	var password = document.getElementById("Stu_password").value;
	/* 		var power = document.getElementById("power").value;
	 */
	 if (username == null || username == "") {
		alert("用户名不能为空");
		return;
	}
	if (password == null || password == "") {
		alert("密码不能为空");
		return;
	}
	
	$.ajax({
		url : "Student_login.action",
		method : "POST",
		dataType : "JSON",
		data : $("#el-form").serialize(),
		success : function(data) {
			if (data.code == 1) {
				alert("登录成功,准备跳往首页");
				window.location.href = "a.html";
			} else {
				alert(data.msg);
			}
		}
	});

} 

</script>



<div id="d2" style="height: 364.5px; display: block;">
	<img src="img/pcicon1.png" class="i1" style="width: 48px; height: 48px;" />
	<form class="el-form" id="el-form">
		<input autocomplete="off" placeholder="手机号码" type="text"  id="Stu_username" name="Stu_username" rows="2" maxlength="11" validateevent="true" class="el-input__inner">
		<input autocomplete="off" placeholder="密码" type="password"  id="Stu_password"  name="Stu_password" rows="2" validateevent="true" class="el-input__inner">
		<input type="button"  onclick="doit()" name="submit" id="sub" class="el-button next-btn el-button--primary"  value="登录"/>
	</form>
	<a style="margin-top:10px;" class="txt" href="reg.jsp">没有账号？立即注册</a>
</div>
</div>
<div style="height: 200px;"></div>
<script type="text/javascript" src="js/login.js"></script>


<script>
	var d2 = document.getElementById("d2");
	var d3 = document.getElementById("d3");
	var d4 = document.getElementById("d4");
	function zc() {
		d2.style.display = "none";
		d3.style.display = "block";
	}
	function dl() {
		d3.style.display = "none";
		d2.style.display = "block";
	}
</script>

