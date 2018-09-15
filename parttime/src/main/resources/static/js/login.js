var d2 = document.getElementById("d2");
var d3 = document.getElementById("d3");
var radio;

function zc() {
	d2.style.display = "none";
	d3.style.display = "block";
}
function dl() {
	d3.style.display = "none";
	d2.style.display = "block";
}

function changeVilidateCode(obj) {
	var timenow = new Date().getTime();
	obj.src = "image.jsp?d=" + timenow;
}

function doit() {
	var username = document.getElementById("username");
	var password = document.getElementById("password");
	if (username == null || username == "") {
		alert("用户名不能为空");
		return;
	}
	if (password == null || password == "") {
		alert("密码不能为空");
		return;
	}
	radio = $('input:radio[name="gender"]:checked').val();
	if (radio == 1) {
		$.ajax({
			url : "Merchant_baseinfoLogin.action",
			data : $("#loginForm").serialize(),
			dataType : "JSON",
			method : "POST",
			success : function(data) {
				if (data.code == 1) {
					alert("登陆成功");
					window.location.href = "index.jsp?list=1";
				} else if (data.code == 2) {
					alert(data.msg + "，准备跳往首页...");
					window.location.href = "index.jsp?list=1";
				} else {
					alert("登陆失败:" + data.msg);
					return;
				}
			}
		});
	} else {
		$.ajax({
			url : "Student_login.action",
			method : "POST",
			dataType : "JSON",
			data : $("#loginForm").serialize(),
			success : function(data) {
				if (data.code == 1) {
					alert("登陆成功");
					window.location.href = "index.jsp ";
				} else if (data.code == 2) {
					alert(data.msg + "，准备跳往首页...");
					window.location.href = "index.jsp ";
				} else {
					alert("登陆失败:" + data.msg);
					return;
				}
			}
		});
	}

}