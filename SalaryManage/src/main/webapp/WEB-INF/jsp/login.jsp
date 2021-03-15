<%--
  Created by IntelliJ IDEA.
  User: 招志锋
  Date: 2020-10-24
  Time: 8:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>登录</title>
	<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
	<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
	<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
	<script>
		$(document).ready(function(){
			var msg=$("#msg").text();
			if(msg!="")
			{
				$("#msg").show().delay(1000).hide(0);
				return false;
			}
		});
		// 判断是登录账号和密码是否为空
		function check() {
			var usercode = $("#usercode").val();
			var password = $("#password").val();
			var validateCode = $("#validateCode").val();
			if (usercode == "" || password == "") {
				$("#erro").text("账号或密码不能为空！");
				$("#erro").show().delay(1000).hide(0);

				return false;
			}
			if (validateCode=="") {
				$("#erro").text("请输入验证码！");
				$("#erro").show().delay(1000).hide(0);
				return false;
			}
			return true;
		}
		function toRegister() {
			window.location.href = "toRegister.action";
		}

		function getPic() {
			$("#code").attr("src","${pageContext.request.contextPath }/validateCode.action?flag="+Math.random())
		}

	</script>
</head>

<body>
<div class="container">
	<div id="loginbox" style="margin-top:50px;" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
		<div class="panel panel-info" >
			<div class="panel-heading">
				<div class="panel-title">简易版人事&工资信息管理系统</div>
				<%--<div style="float:right; font-size: 80%; position: relative; top:-10px"><a href="#">Forgot password?</a></div>--%>
			</div>

			<div style="padding-top:30px" class="panel-body" >

				<span style="color: red" id="msg">${msg}</span>
				<span style="display:none; color: red" id="erro"></span>
				<div style="display: none" id="login-alert" class="alert alert-danger col-sm-12"><font></font></div>

				<form id="loginform" method="post" class="form-horizontal" role="form" action="${pageContext.request.contextPath }/login.action" onsubmit="return check()">

					<div style="margin-bottom: 25px" class="input-group">
						<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
						<input id="usercode" type="text" class="form-control" name="usercode" value="" placeholder="usercode">
					</div>

					<div style="margin-bottom: 25px" class="input-group">
						<span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
						<input id="password" type="password" class="form-control" name="password" placeholder="password" maxlength="8">
					</div>

					<div style="margin-bottom: 25px" class="input-group">
						<div class="row">
							<div class="col-sm-7">
								<input id="validateCode" type="text" class="form-control input-large" name="validateCode" value="" placeholder="验证码">
							</div>
							<div class="col-sm-1">
								<img src="validateCode.action" id="code" title="看不清，点击换一张" alt="" width="100" height="32" class="center-block" style="height: 43px;cursor: pointer;" onclick="getPic()"/>
							</div>
						</div>
					</div>

					<%--<div class="input-group">
						<div class="checkbox">
							<label>
								<input id="login-remember" type="checkbox" name="remember" value="1"> Remember me
							</label>
						</div>
					</div>--%>


					<div style="margin-top:10px" class="form-group">
						<!-- Button -->
						<div class="col-sm-12 controls">
							<button type="submit" class="btn btn-success">登录</button>
							<a id="btn-fblogin" class="btn btn-primary" onclick="toRegister()">注册</a>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
</body>
</html>
