<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>登录页面</title>
<meta http-equiv=Content-Type content="text/html; charset=utf-8">

<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.4.1.min.js">
	
</script>
<meta content="MSHTML 6.00.2600.0" name=GENERATOR>
<script>
	// 判断是登录账号和密码是否为空
	function check() {
		var usercode = $("#usercode").val();
		var password = $("#password").val();
		if (usercode == "" || password == "") {
			$("#message").text("账号或密码不能为空！");
			return false;
		}
		return true;
	}
	function toRegister() {
		window.location.href = "toRegister.action";
	}
</script>
</head>
<body leftMargin=0 topMargin=0 marginwidth="0" marginheight="0"
	background="${pageContext.request.contextPath}/images/rights.jpg">

	<div ALIGN="center">
		<table border="0" width="1140px" cellspacing="0" cellpadding="0"
			id="table1">
			<tr>
				<td height="93"></td>
				<!-- <td></td> -->
			</tr>
			<tr>
				<td class="login_msg" width="400" height="400" align="center">
					<!-- margin:0px auto; 控制当前标签居中 -->
					<fieldset style="width: auto; margin: 0px auto;">
						<legend>
							<font style="font-size: 15px" face="宋体"> 登录ZZF工资信息管理系统 </font>
						</legend>
						<font color="red"> <%-- 提示信息--%> <span id="message">${msg}</span>
						</font>
						<%-- 提交后利用后台到达位置：/WEB-INF/jsp/customer.jsp--%>
						<form action="${pageContext.request.contextPath }/login.action"
							method="post" onsubmit="return check()">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br /> <br /> 账&nbsp;号：<input
								id="usercode" type="text" name="usercode" /> <br /> <br />
							密&nbsp;码：<input id="password" type="password" name="password" />
							<br /> <br /> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<center>
								<input type="submit" value="登录" />
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="button" value="注册"
									onclick="toRegister()" />
							</center>
						</form>
					</fieldset>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>
