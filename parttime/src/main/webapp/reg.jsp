<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<link rel="stylesheet" type="text/css" href="css/regstyle.css" />
<%@ include file="header1.jsp"%>



<div id="d2">
	<p id="p2">注册</p>
	<form class="el-form" id="el-form">
		<div class="el-form-item">
			<div class="el-form-item__content">
				<input type="hidden" name="merchant_telephone" value="${telephone}">
			</div>
		</div>
		<div class="el-form-item">
			<div class="el-form-item__content">
				<div class="el-input">
					<input autocomplete="off" placeholder="密码" type="password" name="password" rows="2" validateevent="true" class="el-input__inner">
				</div>
			</div>
			<div class="el-form-item">
				<div class="el-form-item__content">
					<div class="el-input">
						<input autocomplete="off" placeholder="确认密码" type="password" name="merchant_password" rows="2" maxlength="11" validateevent="true"
							class="el-input__inner"
						>

					</div>
				</div>
			</div>
			<div class="el-form-item">
				<div class="el-form-item__content">
					<div class="el-input">
						<input autocomplete="off" placeholder="姓名" type="text" name="merchant_name" rows="2" maxlength="11" validateevent="true" class="el-input__inner">

					</div>
				</div>
			</div>
			<div class="el-form-item">
				<div class="el-form-item__content">
					<div class="el-input">
						<input autocomplete="off" placeholder="营业执照" type="text" name="merchant_license" rows="2" maxlength="11" validateevent="true"
							class="el-input__inner"
						>
						<img src="img/√.png" id="dui" />
						<img src="img/×.png" id="cha">
					</div>
				</div>
			</div>
			<div class="el-form-item">
				<div class="el-form-item__content">
					<div class="el-input">
						<input autocomplete="off" placeholder="身份证号码" type="text" name="merchant_idcard" rows="2" maxlength="18" validateevent="true"
							class="el-input__inner"
						>

					</div>
				</div>
			</div>
			<div class="el-form-item">
				<div class="el-form-item__content">
					<div class="el-input">
						<input autocomplete="off" placeholder="店主名" type="text" name="merchant_hostname" rows="2" maxlength="11" validateevent="true"
							class="el-input__inner"
						>

					</div>
				</div>
			</div>
			<div class="el-form-item">
				<div class="el-form-item__content">
					<div class="el-input">
						<input autocomplete="off" placeholder="邮箱" type="text" name="merchant_email" rows="2" maxlength="11" validateevent="true"
							class="el-input__inner"
						>

					</div>
				</div>
			</div>
			<div class="el-form-item">
				<div class="el-form-item__content">
					<button type="button" onclick="doit()" class="el-button next-btn el-button--primary">
						<span>注册</span>
					</button>
				</div>
			</div>
	</form>
	<a style="margin-top: 10px;" class="txt" href="login.jsp">返回登录</a>
</div>

</div>
<div style="height: 200px;"></div>
<script type="text/javascript" src="js/reg.js"></script>
</body>
</html>
