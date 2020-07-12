<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE HTML>
<html>
<head>
<%-- ${pageContext.request.contextPath} --%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户主界面</title>

<style type="text/css">
TD {
	FONT-SIZE: 18px;
	COLOR: #000000;
	FONT-FAMILY: '宋体'
}

BODY {
	FONT-SIZE: 18px;
	COLOR: #000000;
	FONT-FAMILY: '宋体'
}

INPUT {
	FONT-SIZE: 14px;
	COLOR: #000000;
	FONT-FAMILY: '宋体'
}

a {
	text-decoration: none;
}

a:hover {
	color: red;
}
</style>
</head>
<body>

	<h1 align="center">欢迎使用ZZF工资信息管理系统</h1>
	<table align="center" width="700" border="0" cellspacing="0"
		cellpadding="0" style="border-collapse: collapse;">
		<tr>
			<td>
				<center>
					用户：${USER_SESSION.user_code} &nbsp;&nbsp;&nbsp;&nbsp; <a
						href="${pageContext.request.contextPath }/logout.action"> 退出登录
					</a> <br> <br> <a
						href="${pageContext.request.contextPath }/workerslist.action">
						员工个人信息管理模块 </a> <br> <br> <a
						href="${pageContext.request.contextPath }/jobslist.action">
						公司职位信息管理模块 </a> <br> <br> <a
						href="${pageContext.request.contextPath }/JobsWorkerslist.action">
						员工职位信息模块 </a> <br> <br> <a
						href="${pageContext.request.contextPath }/RPrecordslist.action">
						奖惩记录管理模块 </a> <br> <br> <a
						href="${pageContext.request.contextPath }/WSalaryslist.action">
						员工工资信息管理模块 </a> <br>
				</center>
			</td>
		</tr>
	</table>
</body>
</html>