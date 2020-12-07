<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page trimDirectiveWhitespaces="true"%>


<!DOCTYPE HTML>
<html>
<head>
<%-- ${pageContext.request.contextPath} --%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户主界面</title>
	<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
	<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
	<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>

</head>
<body>
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<%--<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
					<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>--%>
				<a class="navbar-brand" href="#">企业工资信息管理系统</a>
			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li class="active"><a href="${pageContext.request.contextPath}/toAdmin.action">主页 <span class="sr-only">(current)</span></a></li>
					<li><a href="${pageContext.request.contextPath }/userslist.action">用户账户管理</a></li>
					<li><a href="${pageContext.request.contextPath }/WSalaryslistbyno2.action">我的工资信息</a></li>

				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="#">${USER_SESSION.authority}</a></li>
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">${USER_SESSION.usercode}<span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="${pageContext.request.contextPath }/toMyInfo.action">个人信息</a></li>
							<li><a href="${pageContext.request.contextPath }/toChangePassword.action">修改密码</a></li>
							<li role="separator" class="divider"></li>
							<li><a href="${pageContext.request.contextPath }/logout.action">注销</a></li>
						</ul>
					</li>
				</ul>
			</div>
		</div>
	</nav>
	<div class="container-fluid">
			<div class="row">
				<div class="col-sm-6 col-md-2"></div>
				<div class="col-sm-6 col-md-4">
					<div class="thumbnail" style="border: none">
						<div class="caption">
							<span class="glyphicon glyphicon-tasks" style="font-size: xx-large" aria-hidden="true"></span>
							<h3>用户账号管理</h3>
							<p>管理本系统所有用户账号...</p>
							<p><a href="${pageContext.request.contextPath }/userslist.action" class="btn btn-primary" role="button">进入</a></p>
						</div>
					</div>
				</div>
				<div class="col-sm-6 col-md-4">
					<div class="thumbnail" style="border: none">
						<div class="caption">
							<span class="glyphicon glyphicon-eye-open" style="font-size: xx-large" aria-hidden="true"></span>
							<h3>我的工资信息</h3>
							<p>查看个人工资信息...</p>
							<p><a href="${pageContext.request.contextPath }/WSalaryslistbyno2.action?wno=${USER_SESSION.wno}" class="btn btn-primary" role="button">进入</a></p>
						</div>
					</div>
				</div>
			</div>
	</div>

</body>
</html>