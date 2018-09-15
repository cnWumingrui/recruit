<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="css/regstyle.css" />

<script type="text/javascript" src="js/jquery-1.11.0.min.js"></script>
<script type="text/javascript">
	function doit() {

		var username = document.getElementById("Stu_username")
		var password = document.getElementById("Stu_password").value;
		var password2 = document.getElementById("Stu_password2").value;
		/* 		var power = document.getElementById("power").value;
		 */if (username == null || username == "") {
			alert("用户名不能为空");
			return;
		}
		if (password == null || password == "") {
			alert("密码不能为空");
			return;
		}
		if (password2 == null || password2 == "") {
			alert("密码不能为空");
			return;
		}
		if(password!==password2){
			
			alert("两次密码不一致");
			return;
		}
		
		$.ajax({
			url : "Student_baseinfoReg.action",
			method : "POST",
			dataType : "JSON",
			data : $("#el-form").serialize(),
			success : function(data) {
				if (data.code == 1) {
					alert("注册成功,准备跳往基本信息填写");
					window.location.href = "studentreg2.jsp";
				} else {
					alert(data.msg);
				}
			}
		});
	
	} 
</script>

<div id="img">
<img src="img/logo.png" id="i1"/>
<ul>
<li><a href="" style="color:rgb(0,204,136);">首页</a></li>
<li><a href="">找兼职</a></li>
<li><a href="">小任务</a></li>
<li><div id="d1"><a href="" style="margin-left:30px;">登录</a></div></li>
</ul>
<p id="p1">大学生</p>
<span id="s1">兼职招聘平台</span>
<div id="d2">
<p id="p2">注册</p>
<form class="el-form" id="el-form">
<div class="el-form-item">
<div class="el-form-item__content">
<div class="el-input" style="margin-top:-40px;">
<input autocomplete="off" placeholder="手机号码" type="text" id="Stu_username" name="Stu_username" rows="2" maxlength="11" validateevent="true" class="el-input__inner">
<img src="img/√.png" id="dui"/>
<img src="img/×.png" id="cha">
</div>
</div>
</div>
<div class="el-form-item">
<div class="el-form-item__content">
<div class="el-input">
<input autocomplete="off" placeholder="密码" type="password" id="Stu_password" name="Stu_password" rows="2" validateevent="true" class="el-input__inner">
<img src="img/√.png" id="dui"/>
<img src="img/×.png" id="cha">
</div>
</div>

<div class="el-form-item">
<div class="el-form-item__content">
<div class="el-input">
<input autocomplete="off" placeholder="确认密码" type="password" id="Stu_password2"  rows="2" validateevent="true" class="el-input__inner">
<img src="img/√.png" id="dui"/>
<img src="img/×.png" id="cha">
</div>
</div>

<div class="el-form-item">
<div class="el-form-item__content">
<input type="button"  onclick="doit()" name="submit" id="sub" class="el-button next-btn el-button--primary"  value="立即注册"/>
</div>
</div>
</form>
<a style="margin-top:10px;" class="txt" href="login.jsp">返回登录</a>
</div>



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



