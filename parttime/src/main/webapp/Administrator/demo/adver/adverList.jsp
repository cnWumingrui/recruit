<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>广告一览</title>
    <link rel="stylesheet" href="../frame/layui/css/layui.css">
    <!--<link rel="stylesheet" href="http://cdn.datatables.net/1.10.13/css/jquery.dataTables.min.css">-->
    <link rel="stylesheet" href="../frame/static/css/style.css">
    <link rel="icon" href="../frame/static/image/code.png">
    <script src="../js/jquery.min.js"></script>
</head>
<body class="body">

<!-- 工具集 -->
<div class="my-btn-box">
    <span class="fl">
        <a class="layui-btn layui-btn-danger radius btn-delect" id="btn-delete-all">批量删除</a>
        <a class="layui-btn btn-add btn-default" id="btn-add">添加</a>
        <a class="layui-btn btn-add btn-default" id="btn-refresh"><i class="layui-icon">&#x1002;</i></a>
    </span>
   
</div>
<!-- 编辑表单 -->


<!-- 添加表单 -->
<div class="layui-row" id="test" style="display: none;">
		<form class="layui-form" id="Adver_toAdd_action" name="Adver_toAdd_action">
			<div class="layui-form-item">
				<label class="layui-form-label">链接</label>
				<div class="layui-input-block">
				     <input id="toURL" name="toURL" type="text"  lay-verify="required|toURL"  
				     placeholder="请输入链接" class="layui-input" style="width:250px;">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">广告方</label>
				<div class="layui-input-block">
				     <input id="customer" name="customer" type="text"  lay-verify="required|customer"  
				     placeholder="请输入广告方" class="layui-input" style="width:250px;">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">描述</label>
				<div class="layui-input-block">
				     <input id="describes" name="describes" type="text"  lay-verify="required|describe"  
				     placeholder="请输入描述" class="layui-input" style="width:250px;">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">广告图片</label>
				<div class="layui-input-block">
		            <!-- 上传按钮 -->
		            <button type="button" class="layui-btn" id="upload1">上传图片</button>
		            <!-- 隐藏的input -->
		            <input type="hidden" id="picture" name="picture" value=""/>
		            <!-- 预览区域 -->
		            <div class="layui-upload-list">
		                <img class="layui-upload-img" width="100px" height="80px" id="demo1"/>
		                <p id="demoText"></p>
		            </div>
		        </div>
			</div>
			<div class="layui-form-item">
			     <label class="layui-form-label">结算方式</label>
				 <div class="layui-input-block">
				     <select name="timeway" lay-verify="required">
				     	<option value="0">月结</option>
						<option value="1">季度</option>
						<option value="2">半年</option>
						<option value="3">全年</option>
					 </select>
				 </div>
			</div>
			<div class="layui-form-item">
			     <label class="layui-form-label">当前状态</label>
				 <div class="layui-input-block">
				     <select name="status" lay-verify="required">
				     	<option value="0">下架</option>
						<option value="1">上架中</option>
					 </select>
				 </div>
			</div>
			<div class="layui-form-item">
			     <div class="layui-input-block">
			         <button class="layui-btn"  lay-submit lay-filter="*">立即提交</button>
			         <button type="reset" class="layui-btn layui-btn-primary">重置</button>
			     </div>
			</div>
		</form>
		
</div>

<!-- 表格 -->
<div id="dateTable" lay-filter="demo">
</div>

<script type="text/javascript" src="../frame/layui/layui.js"></script>
<script type="text/javascript" src="../js/index.js"></script>
<script type="text/javascript" th:inline="javascript">
	layui.use('upload', function(){
		 // 操作对象
        var $ = layui.jquery
        	,upload = layui.upload;
        		
	    var uploadInst = upload.render({
	    	 	elem: '#upload1'
	             , url: '/upload.action'
	             , size: 2000
	             , accept:'images'
	             , before: function (obj) {
	                 //预读本地文件示例，不支持ie8
	                 obj.preview(function (index, file, result) {
	                     $('#demo1').attr('src', result); //图片链接（base64）
	                 });
	             }
	             , done: function (res) {
	                 //如果上传失败
	                 if (res.code == 0) {
	                     return layer.msg('上传失败');
	                 }
	                 //上传成功
	                 //打印后台传回的地址: 把地址放入一个隐藏的input中, 和表单一起提交到后台, 此处略..
	                 /*   console.log(res.data.src);*/
	                 document.getElementById("picture").value = res.obj;
	                 var demoText = $('#demoText');
	                 demoText.html('<span style="color: #8f8f8f;">上传成功!!!</span>');
	             }
	             , error: function () {
	                 //演示失败状态，并实现重传
	                 var demoText = $('#demoText');
	                 demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-mini demo-reload">重试</a>');
	                 demoText.find('.demo-reload').on('click', function () {
	                     uploadInst.upload();
	                 });
	             }
	    });
	});

</script>
<script type="text/javascript" th:inline="javascript">
    // layui方法
    layui.use(['form', 'layer','table','layer', 'vip_table'], function () {
	       var form = layui.form
	       , table = layui.table
	       , layer = layui.layer
	       , vipTable = layui.vip_table;
        // 表格渲染
        var tableIns = table.render({
        	 elem: '#dateTable'                  //指定原始表格元素选择器（推荐id选择器）
                 , height: vipTable.getFullHeight()    //容器高度
                 , response:{
                 	statusName: 'code'
                 	,statusCode: 200
                 	,msgName: 'msg'
                 	,countName: 'count'
                 	,dataName: 'data'
                 }
                 , cols: [[                  //标题栏
                     {checkbox: true, sort: true, fixed: true, space: true}
                     , {field: 'id', title: '编号', width: 60}
                     //TODO:显示图片
                     , {field: 'picture', title: '图片地址', width: 120}
                     , {field: 'toURL', title: '链接地址', width: 180}
                     , {field: 'customer', title: '商家名', width: 120}
                     , {field: 'describes', title: '描述', width: 120}
                     , {field: 'jointime', title: '添加时间', width: 120}
                     , {field: 'timeway', title: '购买方式', width: 120}
                     , {field: 'status', title: '状态', width: 100}
                     , {fixed: 'right', title: '操作', width: 200, align: 'center', toolbar: '#barDemo'} //这里的toolbar值是模板元素的选择器
                 ]]
                 , id: 'dataCheck'
                 , url: '/Administrator/Advertise_list.action'
                 , method: 'post'
                 , page: true
                 , limits: [30, 60, 90, 150, 300]
                 , limit: 30 //默认采用30
                 , loading: false
        });
     
      //添加
		$('#btn-add').on('click', function () {
			layer.open({
					id:1,
			        type: 1,
			        title:'添加新广告',
			        skin:'layui-layer-rim',
			        area:['600px', '500px'],
			        shadeClose: true,
			        content: $("#test")
			 		,
			});
			form.render();
			form.verify({
				customer : function(value, item){
	                if (value.length == 0) {
	                    return '广告商不能为空！';
	                }
	            },
				
				timeway : function(value, item){
	                if (value.length == 0) {
	                    return '结算方式不得为空！';
	                }
	            },
	 
		        picture : function(value, item){
		        	if (value.length == 0) {
	                    return '图片不得为空！';
	                }
		        },
		        
	        });  
	         /**
	          * 通用表单提交(AJAX方式)
	          */
	         form.on('submit(*)', function (data) {
	             $.ajax({
	            	 	url:"/Administrator/Advertise_toAdd.action",
	        			data:$("#Adver_toAdd_action").serialize(),
	        			dataType:"JSON",
	        			method : "POST",
	        			success:function(info){
	        				if(info.code==1){
	        					layer.msg('添加成功');
		        				tableIns.reload();
		        				window.location.refresh();
	        				}else{
	        					layer.alert(info.msg);
	        				}
	        			}
	             });
	             return false;
	         });
			
        });
		
        // 刷新
        $('#btn-refresh').on('click', function () {
            tableIns.reload();
        });
		//批量删除
		$('#btn-delete-all').on('click',function(){
			var checkStatus = table.checkStatus('dataCheck'),data=checkStatus.data,delList=[];
			data.forEach(function(n,i){
				delList.push(n.id);
			});
			if(delList!=''){
				layer.confirm('确定要删除所选项么？',function(index){
					$.ajax({
						url:"/Administrator/Advertise_delMutil.action",
						type:'post',
						data:"list="+delList,
						dataType:'JSON',
						success:function(data){
							if(data.code==1){
								layer.alert('删除成功');
								tableIns.reload();
							}else{
								layer.msg(data.msg);
							}
						},
						'error':function(){
							layer.msg('系统错误！')
						}
					});
				})
			}else{
				layer.alert('请选择要删除的行！');
			}
		});
		//删除
		table.on('tool(demo)', function(obj){
			var data = obj.data;//当前行数据
			if(obj.event === 'del'){
				layui.layer.confirm('真的删除此项么', function(index){
					$.ajax({
						url:"/Administrator/Advertise_del.action",
						data:"id="+data.id,
						dataType:'JSON',
						success:function(data){
							if(data.code==1){
								layer.alert('删除成功');
								tableIns.reload();
							}else{
								layer.alert(data.msg);
							}
						}
					});
				});
			}else if(obj.event === 'change'){
				layui.layer.confirm('确定更改此条广告状态？', function(index){
					$.ajax({
						url:"/Administrator/Advertise_out.action",
						data:"id="+data.id,
						dataType:'JSON',
						success:function(data){
							if(data.code==1){
								layer.alert('修改成功');
								tableIns.reload();
							}else{
								layer.alert(data.msg);
							}
						}
					});
				});
			}else if(obj.event === 'edit'){
		            $(function () {
		            	// 获取选中行
			            layer.open({
							id:1,
					        type: 1,
					        title:'编辑广告',
					        skin:'layui-layer-rim',
					        area:['600px', '500px'],
					        shadeClose: true,
					        content: '<div class="layui-row" id="test1">'
					        		 +		'<form class="layui-form" id="Adver_update_action" name="Adver_update_action">'
										 +		'<input id="id" name="id" value="'+data.id+'" type="hidden">'
										 +		'<div class="layui-form-item">'
										 +		'<label class="layui-form-label">链接</label>'
										 +			'<div class="layui-input-block">'
										 +			     '<input id="toURL" name="toURL" type="text" value="'+data.toURL+'" lay-verify="required|toURL" placeholder="请输入链接" class="layui-input" style="width:250px;">'
										 +			'</div>'
										 +		'</div>'
										 +		'<div class="layui-form-item">'
										 +		'<label class="layui-form-label">广告方</label>'
										 +			'<div class="layui-input-block">'
										 +			     '<input id="customer" name="customer" type="text" value="'+data.customer+'" lay-verify="required|toURL" placeholder="请输入广告方" class="layui-input" style="width:250px;">'
										 +			'</div>'
										 +		'</div>'
										 +		'<div class="layui-form-item">'
										 +		'<label class="layui-form-label">描述</label>'
										 +			'<div class="layui-input-block">'
										 +			     '<input id="describes" name="describes" type="text" value="'+data.describes+'" lay-verify="required|toURL" placeholder="请输入描述" class="layui-input" style="width:250px;">'
										 +			'</div>'
										 +		'</div>'
										 +		'<div class="layui-form-item">'
										 +			'<label class="layui-form-label">广告图片</label>'
										 +			'<div class="layui-input-block">'
										 +	            '<!-- 上传按钮 -->'
										 +	            '<button type="button" class="layui-btn" id="upload1">上传图片</button>'
										 +	            '<!-- 隐藏的input -->'
										 +	            '<input type="hidden" id="picture" name="picture" value="'+data.picture+'"/>'
										 +	            '<!-- 预览区域 -->'
										 +	            '<div class="layui-upload-list">'
										 +	                '<img class="layui-upload-img" width="100px" height="80px" id="demo1"/>'
										 +	                '<p id="demoText"></p>'
										 +	            '</div>'
										 +	        '</div>'
										 +		'</div>'
										 +		 '<div class="layui-form-item">'
										 +		     '<label class="layui-form-label">结算方式</label>'
										 +			 '<div class="layui-input-block">'
										 +			     '<select id="timeway" name="timeway" lay-verify="required">'
										 +			     	'<option value="0">月结</option>'
										 +					'<option value="1">季度</option>'
										 +					'<option value="2">半年</option>'
										 +					'<option value="3">全年</option>'
										 +				 '</select>'
										 +			 '</div>'
										 +		'</div>'
										 +		 '<div class="layui-form-item">'
										 +		     '<label class="layui-form-label">当前状态</label>'
										 +			 '<div class="layui-input-block">'
										 +			     '<select id="status" name="status" lay-verify="required">'
										 +			     	'<option value="0">下架</option>'
										 +					'<option value="1">上架中</option>'
										 +				 '</select>'
										 +			 '</div>'
										 +		'</div>'
										 +		'<div class="layui-form-item">'
										 +		     '<div class="layui-input-block">'
										 +		         '<button class="layui-btn"  lay-submit lay-filter="*">立即提交</button>'
										 +		         '<button type="reset" class="layui-btn layui-btn-primary">重置</button>'
										 +		     '</div>'
										 +		'</div>'
									 +	'</form>'
								     +'</div>'
							,
					 		
						});
		            	var way;
		            	var status;
		            	if(data.timeway == '月结'){
		            		way =0;
		            	}else if(data.timeway == '季度'){
		            		way =1;
		            	}else if(data.timeway == '半年'){
		            		way =2;
		            	}else if(data.timeway == '全年'){
		            		way =3;
		            	}
		            	if(data.status =='上架中'){
		            		status=1;
		            	}else if(data.status == '下架'){
		            		status =0;
		            	}
		            	$("select[name='timeway']").find("option[value='"+way+"']").attr('selected','selected');
		            	$("select[name='status']").find("option[value='"+status+"']").attr('selected','selected');
						form.render();
						form.verify({
							customer : function(value, item){
				                if (value.length == 0) {
				                    return '广告商不能为空！';
				                }
				            },
							
							timeway : function(value, item){
				                if (value.length == 0) {
				                    return '结算方式不得为空！';
				                }
				            },
				 
					        picture : function(value, item){
					        	if (value.length == 0) {
				                    return '图片不得为空！';
				                }
					        },
					        
				        });  
				         /**
				          * 通用表单提交(AJAX方式)
				          */
				         form.on('submit(*)', function (data) {
				             $.ajax({
				            	 	url:"/Administrator/Advertise_update.action",
				        			data:$("#Adver_update_action").serialize(),
				        			dataType:"JSON",
				        			method : "POST",
				        			success:function(info){
				        				if(info.code==1){
				        					layer.msg('修改成功');
					        				tableIns.reload();
				        				}else{
				        					layer.alert(info.msg);
				        				}
				        			}
				             });
				             return false;
				         });    

		            });
			}
		});


        // you code ...
    });
</script>
<!-- 表格操作按钮集 -->
<script type="text/html" id="barDemo">
	<a class="layui-btn layui-btn-mini layui-btn-normal" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-mini layui-btn-normal" lay-event="change">更改状态</a>
    <a class="layui-btn layui-btn-mini layui-btn-danger" lay-event="del">删除</a>
</script>
</body>
</html>