var minJobid = 0;
var maxJobid = 0;
var Jobid;
var workcleanform;
var workplace;
var pages = 1;
var pageSize = 1;
var orderBy;

$(function() {
	findWanteedJobList();
})
function findWanteedJobList(data) {
	if (data != null) {
		pages = data;
	}
	$.ajax({
		url : "findJobList.action",
		mothod : "GET",
		dataType : 'HTML',
		data : {
			job_id : Jobid,
			workcleanform : workcleanform,
			pages : pages,
			pageSize : pageSize
		},
		success : function(data) {
			$("#part0").html(data);
		}
	})
}
function change(para) {
	var job_id = document.getElementById("job_id");
	job_id.value = para;

	if (para == 0) {
		Jobid = null;
		regain();
		findWanteedJobList();
	}
	var lis = document.querySelectorAll("#choices li");
	if (para < lis.length) {
		for (var i = 0; i < lis.length; i++) {
			lis[i].style.color = "#999";
		}
	}
	if (minJobid != 0 && maxJobid != 0) {
		for (var i = minJobid; i <= maxJobid; i++) {
			document.getElementById("job_id" + i).style.color = "#999";
		}
	}
	openProfession(para);
	Jobid = para;
	document.getElementById("job_id" + para).style.color = "#0C8";
	if (para > lis.length) {
		findWanteedJobList();
	}
}

function openProfession(para) {
	$
			.ajax({
				url : "profession.action",
				mothod : "GET",
				dataType : "JSON",
				data : "jobid=" + para,
				success : function(data) {
					if (data.code == 1) {
						$("#profession li").remove();
						minJobid = data.obj[0].id;
						maxJobid = data.obj[data.obj.length - 1].id;
						for (var i = 0; i < data.obj.length; i++) {
							$("#profession").append(
									"<li  id=job_id" + data.obj[i].id
											+ " onclick=change("
											+ data.obj[i].id + ")>"
											+ data.obj[i].parameter + "</li>");
						}
					}
					if (para > 0) {
						document.getElementById("choiceprofession").style.display = "block";
					} else {
						document.getElementById("choiceprofession").style.display = "none";
					}
				}
			});

}

function choice1(para) {
	var a = "普通排序";
	var b = data.innerHTML;
	if (a == b) {
		workplace = null;
	} else {
		workplace = para.innerHTML;
	}
	sort1.style.display = "none";
	$
			.ajax({
				url : "sortList.action",
				mothod : "POST",
				dataType : 'HTML',
				data : {
					orderBy : workplace,
				},
				success : function(data) {
					document.getElementsByClassName("ways")[0].innerHTML = para.innerHTML;
					$("#part0").html(data);
				}
			})
}
function choice2(data) {
	var a = "不限";
	var b = data.innerHTML;
	if (a == b) {
		workcleanform = null;
	} else {
		workcleanform = data.innerHTML;
	}
	document.getElementsByClassName("ways")[1].innerHTML = data.innerHTML;
	sort2.style.display = "none";

	findWanteedJobList();
}
function regain() {
	workcleanform = null;
	workplace = null;
	document.getElementsByClassName("ways")[0].innerHTML = "默认排序";
	document.getElementsByClassName("ways")[1].innerHTML = "结算方式";
}

function get(id) {
	return document.getElementById(id);
}

var sort1 = get("sort1");
var sort2 = get("sort2");
var onoff = true;
document.getElementsByClassName("sort")[0].onclick = function() {
	if (onoff) {
		sort1.style.display = "block";
		sort2.style.display = "none";
	} else {
		sort1.style.display = "none";
	}
	onoff = !onoff;
}

document.getElementsByClassName("sort")[1].onclick = function() {
	if (onoff) {
		sort2.style.display = "block";
		sort1.style.display = "none";
	} else {
		sort2.style.display = "none";
	}
	onoff = !onoff;
}
