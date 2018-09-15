<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<body class="login-body body">

<div class="login-box">
    <form class="layui-form" id="Admin_update_action" name="Admin_update_action">
        <div class="layui-form-item">
            <h3>修改密码</h3>
        </div>
        <input type="hidden" class="text" name="admin_username" value="${username }"/>
        <div class="layui-form-item">
            <label class="layui-form-label">原密码：</label>
            <div class="layui-input-inline">
                <input id="admin_oldpassword"  required="true" 
            			type="password" name="admin_oldpassword" class="layui-input" lay-verify="oldepwd" placeholder="原密码"
                       	autocomplete="on" maxlength="20"/>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">密码：</label>

            <div class="layui-input-inline">
                <input name="admin_password" id="admin_password"  required="true"
                		type="password" class="layui-input" lay-verify="password" placeholder="密码"
                       	maxlength="20"/>
            </div>
        </div>
        <!-- <div class="layui-form-item">
            <label class="layui-form-label">验证码：</label>

            <div class="layui-input-inline">
                <input type="number" name="code" class="layui-input" lay-verify="code" placeholder="验证码" 
                	maxlength="4"/><img src="../frame/static/image/v.png" alt="">
            </div>
        </div> -->
        <div class="layui-form-item">
        	<div class="layui-input-block">
            	<button type="reset" class="layui-btn layui-btn-danger btn-reset">重置</button>
            	<button type="button" class="layui-btn" lay-submit lay-filter="sub">立即修改</button>
            </div>
        </div>
    </form>
</div>

<script type="text/javascript" src="../frame/layui/layui.js"></script>
<script type="text/javascript">
	layui.use(['form', 'layer'], function () {
	
		// 操作对象
	    var form = layui.form
	            , layer = layui.layer
	            , $ = layui.jquery;
	
	    // 验证
	    form.verify({
	        oldpwd: function (value) {
	            if (value == "") {
	                return "请输入原密码！";
	            }
	        },
	    	password: function(value){
	    		if(value == ""){
	    			return "请输入要修改的密码！";
	    		}else if(value.length < 6){
	                return "密码长度不能小于6位";
	            }else if(new RegExp($("#admin_oldpassword").val()).test(value)){
	                return "两次输入密码不能一致，请重新输入！";
	            }
	    	}
	    });
	    // 提交监听
	    form.on('submit(sub)', function (data) {
	    	$.ajax({
    			url:"/Administrator/Admin_update.action",
    			data:$("#Admin_update_action").serialize(),
    			dataType:"JSON",
    			success:function(data){
    				if(data.code==1){
    					layer.msg("修改成功");
    				}else{
    					layer.alert(data.msg);
    				}
    			}
    		});
	       return false;
	    });
	});
	
</script>
</body>
</html>