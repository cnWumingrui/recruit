<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<body class="login-body body">

<div class="login-box">
    <form class="layui-form layui-form-pane" id="User_login_action" name="User_login_action">
        <div class="layui-form-item">
            <h3>后台登录系统</h3>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">账号：</label>

            <div class="layui-input-inline">
                <input name="admin_username" id="User_login_action_username"  required="true" 
            			type="text" name="account" class="layui-input" lay-verify="account" placeholder="账号"
                       	autocomplete="on" maxlength="20"/>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">密码：</label>

            <div class="layui-input-inline">
                <input name="admin_password" id="User_login_action_password"  required="true"
                		type="password" name="password" class="layui-input" lay-verify="password" placeholder="密码"
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
            <button type="reset" class="layui-btn layui-btn-danger btn-reset">重置</button>
            <button type="button" class="layui-btn btn-submit" lay-submit="" lay-filter="sub" onclick="doit()">立即登录</button>
        </div>
    </form>
</div>

<script type="text/javascript" src="demo/frame/layui/layui.js"></script>
<script type="text/javascript">
	layui.use(['form', 'layer'], function () {
	
	    // 提交监听
	    //form.on('submit(sub)', function (data) {
	        //layer.alert(JSON.stringify(data.field), {
	        //   title: '最终的提交信息'
	        //});
	       //return false;
	    //});
		 // 操作对象
	    var form = layui.form
	            , layer = layui.layer
	            , $ = layui.jquery;
	
	    // 验证
	    form.verify({
	        //code: function (value) {
	        //    if (value == "") {
	        //        return "请输入验证码";
	        //    }
	        //}
	    });
	});
	
	function forgot(){
    		alert('请联系后台管理员!QQ:1016732082')
    }
	
    function doit() {
    	
    		$.ajax({
    			url : "/Administrator/Admin_login.action",
    			data : $("#User_login_action").serialize(),
    			dataType : "JSON",
    			method : "POST",
    			success : function(data) {
    				if (data.code == 1) {
    					layer.msg("登录成功");
    					window.location.href = "Administrator/demo/index.jsp";
    				} else {
    					layer.alert("登录失败:" + data.msg);
    				}
    			}
    		});
    	}
</script>
</body>
</html>