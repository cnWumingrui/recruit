$(function() {
	var id = document.getElementById("merchant_wantedjob_id").value;
	$.ajax({
		url : "toUpdateMerchant_wantedJob.action",
		dataType : "JSON",
		data : "merchant_wantedjob_id=" + id,
		method : "POST",
		success : function(data) {
			var mwJob = data.obj;
			var workclean = mwJob.workcleanform;
			$("#txt0").html(
					"标题：<input type='text'  name='title' value='"
					+ mwJob.title + "'/>");
			$("#txt1").html(
					"工资：<input type='text'  name='salary' value='"
							+ mwJob.salary + "'/>");

			$("#workcleanform").find("option[value = '" + workclean + "']")
					.attr("selected", "selected");

			$("#P_workdescp").html(
					"工作描述:<textarea id='workdescp' name='workdescp' >"
							+ mwJob.workdescp + " </textarea>");

			$("#P_workdemand").html(
					"工作内容:<textarea id='workdemand' name='workdemand' >"
							+ mwJob.workdemand + " </textarea>");

			$("#P_workcontent").html(
					"工作内容:<textarea id='workcontent' name='workcontent' >"
							+ mwJob.workcontent + " </textarea>");
		}
	});
});

function updataworkinfo() {
	$.ajax({
		url : "updateMerchant_wantedJob.action",
		data : $("#parrtimejob").serialize(),
		dataType : "JSON",
		method : "POST",
		success : function(data) {
			if (data.code == 1) {
				alert(data.msg + "...跳完前一页");
				window.location.href = "merchant.jsp";
			}
		}
	});
}