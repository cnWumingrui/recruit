function doit() {
	$.ajax({
		url : "addMerchant_baseInfo.action",
		method : "POST",
		dataType : "JSON",
		data : $("#el-form").serialize(),
		success : function(data) {
			if (data.code == 1) {
				alert("注册成功,准备跳往首页");
				window.location.href = "index.jsp?list=1";
			} else {
				alert(data.msg);
			}
		}
	});
}