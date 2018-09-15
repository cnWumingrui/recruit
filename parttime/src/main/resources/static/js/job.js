//$(function(){
//	alert("1")
//})
//function change(para){
//	  var lis=document.querySelectorAll("#choices li");
//	  for(var i=0;i<lis.length;i++){
//	      lis[i].style.color="#999";
//	  }
//  	 document.getElementById("job_id"+para).style.color="#0C8";
//	 document.getElementById("choiceprofession").style.display="block";
//	 getProfession(para);
//}
//
//function getProfession(para){
//	
//	$.ajax({
//		url:"profession.action",
//		mothod:"POST",
//		dataType:"JSON",
//		data:"jobid=2" ,
//		success:function(data){
//			if(data.code==1){
//				("#profession").html(data);
//			}
//		}
//	});
//}