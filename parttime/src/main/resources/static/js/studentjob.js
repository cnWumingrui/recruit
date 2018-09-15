$(function() {
	$(".message .button").click(function() {
		$("#content .cont_left").css("display", "none");
		$("#content .cont_left").eq($(this).index()).css("display", "block");
	});
});

function studentjob() {
	$.ajax({
		url : "Studentjob.action",
		data : $("#parrtimejob").serialize(),
		dataType : "JSON",
		method : "POST",
		success : function(data) {
			if (data.code == 1) {
				alert(data.msg);
			} else {
				alert(data.msg);
			}
		}
	});
}
function tochangeBaseinfo1(stu_id) {
	$
			.ajax({
				url : "user/toUpdateStudent_baseinfo.action",
				method : "POST",
				data : "stu_id=" + stu_id,
				dataType : "JSON",
				success : function(data) {
					var str;
					var baseinfo = data.obj;
					str = "<input  type='hidden' id='stu_id' name='stu_id' value='"
							+ baseinfo.stu_id
							+ "'/>"
							+ "<p>学生姓名:<input name='stu_name' id='stu_name' value="
							+ baseinfo.stu_name
							+ "   validateevent='true' class='el-input__inner'/></p><br />";

					str += "<p>密码：<input name='stu_password' id='stu_password'  value="
							+ baseinfo.stu_password
							+ "   validateevent='true' class='el-input__inner'/></p><br />";

					str += "<p>电话：<input name='stu_telephone' id='stu_telephone' value="
							+ baseinfo.stu_telephone
							+ "   validateevent='true' class='el-input__inner'/></p><br />";

					str += "<p>Email：<input name='stu_email' id='stu_email' value="
							+ baseinfo.stu_email
							+ "   validateevent='true' class='el-input__inner/'></p><br />";

					str += "<p>身份证：<input name='stu_idcard' id='stu_idcard' value="
							+ baseinfo.stu_idcard
							+ "   validateevent='true' class='el-input__inner'/></p><br />";

					str += "<input type='button' onclick='changabaseinfo1()' value='修改'/>";
					$("#baseinfo1").html(str);
				}
			});
}

function changabaseinfo1() {
	$.ajax({
		url : "user/updateStudent_baseinfo.action",
		data : $("#baseinfo1").serialize(),
		dataType : "JSON",
		method : "POST",
		success : function(data) {
			if (data.code == 1) {
				alert(data.msg);
			} else {
				alert(data.msg);
			}
		}
	})}