<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>职位信息列表</title>
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
<script type="text/javascript">
	function frmSubmit() {
		document.form1.submit();
	}
	function findByJname(a) {
		var s='jname='+document.getElementById('text').value;
		a.href='jobslistbyname.action?'+s;
	}
	function findByJsalary(a) {
		var s='jsalary='+document.getElementById('text').value;
		a.href='jobslistbysalary.action?'+s;
	}
	
</script>
</head>
<body>
	<table align="left" width="190" border="0" cellspacing="0"
		cellpadding="0" style="border-collapse: collapse;">
		<tr>
			<td>用户：${USER_SESSION.user_code} &nbsp;&nbsp;&nbsp;&nbsp; <a
				href="${pageContext.request.contextPath }/logout.action"> 退出登录 </a>
				<br> <br> <a
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
			</td>
		</tr>
	</table>
	<center>职位信息</center>
	<form action="${pageContext.request.contextPath }/jobsdelete.action"
		id="form1" name="form1" method="post">
		<table align="center" width="600" border="1" cellspacing="0"
			cellpadding="0" style="border-collapse: collapse;"
			bordercolor="#0099FF">

			<tr>
				<td colspan="3"><center>
						请输入:&nbsp;<input type="text" id="text">
					</center></td>
				<td style="border-right-style: none"><a href=""
					onclick="findByJname(this)">按关键字查询</a></td>
				<td style="border-left-style: none">&nbsp;&nbsp;&nbsp;&nbsp;<a
					href="" onclick="findByJsalary(this)">按工资查询</a></td>

			</tr>
			<tr>
				<td>选择</td>
				<td>职位号</td>
				<td>职位名</td>
				<td>基本工资</td>
				<td>操作&nbsp;&nbsp;<a
					href="${pageContext.request.contextPath }/jobspreinsert.action">添加
				</a>&nbsp;&nbsp;<a href="#" onclick="javascript:frmSubmit();">删除</a>
				</td>
			</tr>
			<c:forEach items="${jobList}" var="job">
				<tr>
					<td><input type="checkbox" name="jnoArray" value="${job.jno }"></td>
					<td>&nbsp;&nbsp;${job.jno }</td>
					<td>${job.jname }</td>
					<td>${job.jsalary }</td>
					<td><a
						href="${pageContext.request.contextPath }/jobspreupdate.action?jno=${job.jno }&jname=${job.jname }
							&jsalary=${job.jsalary }">
							修改 </a></td>
				</tr>
			</c:forEach>
		</table>
	</form>
</body>
</html>