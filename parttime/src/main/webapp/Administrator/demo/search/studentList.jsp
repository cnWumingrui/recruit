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
    <title>管理账户</title>
    <link rel="stylesheet" href="../frame/layui/css/layui.css">
    <!--<link rel="stylesheet" href="http://cdn.datatables.net/1.10.13/css/jquery.dataTables.min.css">-->
    <link rel="stylesheet" href="../frame/static/css/style.css">
    <link rel="icon" href="../frame/static/image/code.png">
     <script src="../js/jquery-1.11.0.min.js"></script>
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

<!-- 表格 -->
<div id="dateTable" lay-filter="demo">

</div>

<script type="text/javascript" src="../frame/layui/layui.js"></script>
<script type="text/javascript" src="../js/index.js"></script>
<script type="text/javascript">

    // layui方法
    layui.use(['table', 'form', 'layer', 'vip_table'], function () {

        // 操作对象
        var form = layui.form
                , table = layui.table
                , layer = layui.layer
                , vipTable = layui.vip_table
                , $ = layui.jquery;

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
                , {field: 'admin_id', title: '编号', width: 80}
                , {field: 'admin_username', title: '用户名', width: 120}
                , {field: 'admin_permission', title: '权限组', width: 120}
                , {field: 'admin_jointime', title: '创建时间', width: 180}
                , {fixed: 'right', title: '操作', width: 150, align: 'center', toolbar: '#barDemo'} //这里的toolbar值是模板元素的选择器
            ]]
            , id: 'dataCheck'
            , url: '/Administrator/Admin_userList.action'
            , method: 'post'
            , page: true
            , limits: [30, 60, 90, 150, 300]
            , limit: 30 //默认采用30
            , loading: false
        });

        // 获取选中行
        //table.on('checkbox(dataCheck)', function (obj) {
        //    layer.msg('123');
        //    console.log(obj.checked); //当前是否选中状态
        //    console.log(obj.data); //选中行的相关数据
        //    console.log(obj.type); //如果触发的是全选，则为：all，如果触发的是单选，则为：one
        //});
		//添加
		$('#btn-add').on('click', function () {
			layer.open({
					id:1,
			        type: 1,
			        title:'添加新用户',
			        skin:'layui-layer-rim',
			        area:['400px', '400px'],
			        shadeClose: true,
			        content: '<form class="layui-form" id="Admin_register_action" name="Admin_register_action">'
			          				+'<div class="layui-form-item">'
			          					+'<label class="layui-form-label">用户名</label>'
			           					+'<div class="layui-input-block">'
			            					+'<input id="admin_username" name="admin_username" type="text"  lay-verify="required|username"  placeholder="请输入用户名" class="layui-input" style="width:250px;">'
			            				+'</div>'
			            			+'</div>'
			            			+'<div class="layui-form-item">'
			            				+'<label class="layui-form-label">密      码</label>'
			            				+'<div class="layui-input-inline">'
			            					+'<input id="admin_password" name="admin_password" type="password"  lay-verify="required|password"  placeholder="请输入密码" class="layui-input" style="width:250px;">'
			            				+'</div>'
			            			+'</div>'
			            			+'<div class="layui-form-item">'
			            				+'<label class="layui-form-label">确认密码</label>'
			            				+'<div class="layui-input-inline">'
			            					+'<input id="repassword" name="repassword" type="password"  lay-verify="required|repassword"  placeholder="请输入密码" class="layui-input" style="width:250px;">'
			            				+'</div>'
		            				+'</div>'
		            				+'<div class="layui-form-item">'
		            					+'<label class="layui-form-label">权        限:</label>'
			            				+'<div class="layui-input-block">'
			            					+'<select name="admin_permission" lay-verify="required">'
												+'<option value="1">子管理</option>'
												+'<option value="0">总管理</option>'
											+'</select>'
			            				+'</div>'
		            				+'</div>'
		            				+'<div class="layui-form-item">'
		            			    	+'<div class="layui-input-block">'
		            			      		+'<button class="layui-btn"  lay-submit lay-filter="*">立即提交</button>'
		            			      		+'<button type="reset" class="layui-btn layui-btn-primary">重置</button>'
		            			    	+'</div>'
		            			  	+'</div>'
			           		 +'</form>'
			 		,
			});
			form.render();
			form.verify({
				username : function(value, item){
	                if (value.length == 0) {
	                    return '用户名不能为空！';
	                }
	            },
				
				repassword : function(value, item){
	                if (value.length == 0) {
	                    return '请确认密码！';
	                }
	            },
	 
		        password : function(value, item){
		        	if (value.length == 0) {
	                    return '请确认密码！';
	                }else if(value.length < 6){
		                return "密码长度不能小于6位";
		            }else if(!new RegExp($("#repassword").val()).test(value)){
		                return "两次输入密码不一致，请重新输入！";
		            }
		        },
		        
	        });  
	         /**
	          * 通用表单提交(AJAX方式)
	          */
	         form.on('submit(*)', function (data) {
	             $.ajax({
	            	 url:"/Administrator/Admin_register.action",
	        			data:$("#Admin_register_action").serialize(),
	        			dataType:"JSON",
	        			method : "POST",
	        			success:function(info){
	        				if(info.code==1){
	        					layer.msg('注册成功');
		        				tableIns.reload();
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
				delList.push(n.admin_id);
			});
			if(delList!=''){
				layer.confirm('确定要删除所选项么？',function(index){
					$.ajax({
						url:"/Administrator/Admin_delMutil.action",
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
				layui.layer.confirm('真的删除此账户么', function(index){
					$.ajax({
						url:"/Administrator/Admin_del.action",
						data:"admin_id="+data.admin_id,
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
			}else if(obj.event === 're'){
				layui.layer.confirm('真的重置此账户密码么(默认重置为：a)', function(index){
					$.ajax({
						url:"/Administrator/Admin_re.action",
						data:"admin_id="+data.admin_id,
						dataType:'JSON',
						success:function(data){
							if(data.code==1){
								layer.alert('重置成功!');
							}else{
								layer.alert(data.msg);
							}
							tableIns.reload();
						}
					});
				});
			}
		});
        // you code ...

    });
</script>
<!-- 表格操作按钮集 -->
<script type="text/html" id="barDemo">
	<a class="layui-btn layui-btn-mini layui-btn-danger" lay-event="del">删除</a>
    <a class="layui-btn layui-btn-mini layui-btn-normal" lay-event="re">重置密码</a>
</script>
</body>
</html>