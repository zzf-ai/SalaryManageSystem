<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page trimDirectiveWhitespaces="true"%>


<!DOCTYPE HTML>
<html>
<head>
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
				<a class="navbar-brand" href="#">简易版人事&工资信息管理系统</a>
			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li class="active"><a href="${pageContext.request.contextPath}/toLeader.action">主页 <span class="sr-only">(current)</span></a></li>
					<li><a href="${pageContext.request.contextPath }/workerslist.action">员工个人信息管理</a></li>
					<li><a href="${pageContext.request.contextPath }/jobslist.action">公司职位信息管理</a></li>
					<li><a href="${pageContext.request.contextPath }/JobsWorkerslist.action">人事管理</a></li>
					<li><a href="${pageContext.request.contextPath }/WSalaryslist.action">员工工资信息查询</a></li>
					<li><a href="${pageContext.request.contextPath }/MySalaryslist.action">我的工资信息</a></li>
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
					<%--<li><a href="#">${USER_SESSION.authority}</a></li>--%>
					<%--<li><a href="${pageContext.request.contextPath }/toMyInfo.action"><strong>${USER_SESSION.usercode}(${USER_SESSION.authority})</strong></a></li>
					<li><a href="${pageContext.request.contextPath }/logout.action">注销</a></li>--%>
				</ul>
			</div>
		</div>
	</nav>
	<div class="container-fluid">
			<div class="row">
				<div class="col-sm-6 col-md-2"></div>
				<div class="col-sm-6 col-md-3">
					<div class="thumbnail" style="border: none">
						<div class="caption">
							<span class="glyphicon glyphicon-user" style="font-size: xx-large" aria-hidden="true"></span>
							<h3>员工个人信息管理</h3>
							<p>对员工个人信息进行增删查改...</p>
							<p><a href="${pageContext.request.contextPath }/workerslist.action" class="btn btn-primary" role="button">进入</a> </p>
						</div>
					</div>
				</div>
				<div class="col-sm-6 col-md-3">
					<div class="thumbnail" style="border: none">
						<div class="caption">
							<span class="glyphicon glyphicon-tasks" style="font-size: xx-large" aria-hidden="true"></span>
							<h3>公司职位信息管理</h3>
							<p>对公司职位信息进行增删查改...</p>
							<p><a href="${pageContext.request.contextPath }/jobslist.action" class="btn btn-primary" role="button">进入</a></p>
						</div>
					</div>
				</div>
				<div class="col-sm-6 col-md-3">
					<div class="thumbnail" style="border: none">
						<div class="caption">
							<span class="glyphicon glyphicon-search" style="font-size: xx-large" aria-hidden="true"></span>
							<h3>人事管理</h3>
							<p>可管理公司员工及对应的职位信息，进行人事安排...</p>
							<p><a href="${pageContext.request.contextPath }/JobsWorkerslist.action" class="btn btn-primary" role="button">进入</a></p>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-6 col-md-2"></div>
				<div class="col-sm-6 col-md-3">
					<div class="thumbnail" style="border: none">
						<div class="caption">
							<span class="glyphicon glyphicon-pencil" style="font-size: xx-large" aria-hidden="true"></span>
							<h3>员工工资信息查询</h3>
							<p>对已结算的员工工资信息查询...</p>
							<p><a href="${pageContext.request.contextPath }/WSalaryslist.action" class="btn btn-primary" role="button">进入</a></p>
						</div>
					</div>
				</div>
				<div class="col-sm-6 col-md-3">
					<div class="thumbnail" style="border: none">
						<div class="caption">
							<span class="glyphicon glyphicon-eye-open" style="font-size: xx-large" aria-hidden="true"></span>
							<h3>我的工资信息</h3>
							<p>查看个人工资信息...</p>
							<p><a href="${pageContext.request.contextPath }/MySalaryslist.action" class="btn btn-primary" role="button">进入</a></p>
						</div>
					</div>
				</div>
			</div>
	</div>

</body>
</html>