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
    <title>商户一览</title>
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
                {type:'numbers'}
                , {field: 'merchant_id', unresize: true,sort: true, title: '编号', width: 80}
                , {field: 'merchant_name', title: '商家名', width: 150}
                , {field: 'merchant_username', title: '商家登陆用户名', width: 140}
                , {field: 'merchant_telephone', title: '商家电话', width: 130}
                , {field: 'merchant_license', title: '营业执照码', width: 150}
                , {field: 'merchant_idcard', title: '店主身份证', width: 180}
                , {field: 'merchant_hostname', title: '店主名', width: 120}
                , {field: 'merchant_email', title: '商家邮箱', width: 180}
                , {field: 'power',sort: true, title:'发布权限', width:110, templet: '#checkboxTpl', unresize: true}
                //, {fixed: 'right', title: '操作', width: 150, align: 'center', toolbar: '#barDemo'} //这里的toolbar值是模板元素的选择器
            ]]
            , id: 'dataCheck'
            , url: '/Administrator/Merchant_baseinfoList.action'
            , method: 'post'
            , page: true
            , limits: [30, 60, 90, 150, 300]
            , limit: 30 //默认采用30
            , loading: false
        });
	      //监听行单击事件（单击事件为：rowDouble）
	        table.on('row(dateTable)', function(obj){
	          var data = obj.data;
	          
	          layer.alert(JSON.stringify(data), {
	            title: '当前行数据：'
	          });
	          
	          //标注选中样式
	          obj.tr.addClass('layui-table-click').siblings().removeClass('layui-table-click');
	        });
	      //监听锁定操作
	        form.on('checkbox(lockDemo)', function(obj){
	        	
	        		 $.ajax({
		            	 url:"/Administrator/Merchant_baseinfoPower.action",
		        			data:"merchant_id="+this.value,
		        			dataType:"JSON",
		        			method : "POST",
		        			success:function(info){
		        				if(info.code==1){
		        					layer.tips('设置成功');
			        				tableIns.reload();
		        				}else{
		        					layer.tips('设置失败');
		        				}
		        			}
		             });
	        });
		
        // 刷新
        $('#btn-refresh').on('click', function () {
            tableIns.reload();
        });
		
    });
</script>
<script type="text/html" id="checkboxTpl">
  <!-- 这里的 checked 的状态只是演示 -->
  <input type="checkbox" name="lock" value="{{d.merchant_id}}" title="锁定" lay-filter="lockDemo" {{ d.power == 0 ? 'checked' : 'true' }}>
</script>
<!-- 表格操作按钮集 -->
<script type="text/html" id="barDemo">
	<input type="checkbox" checked="" name="open" lay-skin="switch" lay-filter="switchTest" title="权限开关">
</script>
</body>
</html>