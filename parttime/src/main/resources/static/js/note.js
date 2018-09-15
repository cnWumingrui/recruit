var random;
function sendNote(){
	
	var Merchant_username=document.getElementById("Merchant_username").value;
	var toTime=document.getElementById("t1");
	toTime.innerHTML=" <br/>";
	$.ajax({
		url : "sendNote.action",
		data : "Merchant_username="+Merchant_username,
		dataType : "JSON",
		method : "POST",
		success : function (data){
			random=data.obj;
		}
	});
}
function judge(data){
	var code=data.value;
	
	if(code===random  && random!=null){
	}else{
		alert("手机验证错误");
	}
}
