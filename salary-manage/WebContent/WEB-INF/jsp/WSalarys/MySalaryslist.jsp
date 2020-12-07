<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的工资信息</title>
	<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
	<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
	<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
</head>
<body>
<nav class="navbar navbar-default">
	<div class="container-fluid">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
				<span class="sr-only">Toggle navigation</span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#">企业工资信息管理系统</a>
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			<c:if test="${USER_SESSION.authority=='财务'}">
				<ul class="nav navbar-nav">
					<li><a href="${pageContext.request.contextPath}/toCustomer.action">主页 <span class="sr-only">(current)</span></a></li>
					<li><a href="${pageContext.request.contextPath }/jobslist.action">公司职位信息查询</a></li>
					<li><a href="${pageContext.request.contextPath }/JobsWorkerslist.action">员工工资结算</a></li>
					<li><a href="${pageContext.request.contextPath }/WSalaryslist.action">员工工资信息管理</a></li>
					<li class="active"><a href="${pageContext.request.contextPath }/WSalaryslistbyno2.action">我的工资信息</a></li>
				</ul>
			</c:if>
			<c:if test="${USER_SESSION.authority=='普通员工'}">
				<ul class="nav navbar-nav">
					<li><a href="${pageContext.request.contextPath}/toEmployee.action">主页 <span class="sr-only">(current)</span></a></li>
					<li><a href="${pageContext.request.contextPath }/jobslist.action">公司职位信息查询</a></li>
					<li class="active"><a href="${pageContext.request.contextPath }/WSalaryslistbyno2.action">我的工资信息</a></li>
				</ul>
			</c:if>
			<c:if test="${USER_SESSION.authority=='系统管理员'}">
				<ul class="nav navbar-nav">
					<li><a href="${pageContext.request.contextPath}/toAdmin.action">主页 <span class="sr-only">(current)</span></a></li>
					<li><a href="${pageContext.request.contextPath }/userslist.action">用户账户管理</a></li>
					<li class="active"><a href="${pageContext.request.contextPath }/WSalaryslistbyno2.action">我的工资信息</a></li>
				</ul>
			</c:if>
			<c:if test="${USER_SESSION.authority=='公司高层'}">
				<ul class="nav navbar-nav">
					<li><a href="${pageContext.request.contextPath}/toLeader.action">主页 <span class="sr-only">(current)</span></a></li>
					<li><a href="${pageContext.request.contextPath }/workerslist.action">员工个人信息管理</a></li>
					<li><a href="${pageContext.request.contextPath }/jobslist.action">公司职位信息管理</a></li>
					<li><a href="${pageContext.request.contextPath }/JobsWorkerslist.action">员工职位信息管理</a></li>
					<li><a href="${pageContext.request.contextPath }/WSalaryslist.action">员工工资信息查询</a></li>
					<li class="active"><a href="${pageContext.request.contextPath }/WSalaryslistbyno2.action">我的工资信息</a></li>
				</ul>
			</c:if>
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
		</div><!-- /.navbar-collapse -->
	</div><!-- /.container-fluid -->
</nav>

<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">
			<h3 style="margin-left: 100px">
				我的工资信息
			</h3>
		</div>
	</div>
	<div class="row-fluid">
		<h5 class="page-header"></h5>
	</div>
	<form class="form-horizontal text-center">
		<c:forEach items="${WSalaryList}" var="wsalary">
			<div class="form-group">
				<label for="wno" class="col-sm-2 control-label">工号</label>
				<div class="col-sm-3">
					<input type="text" class="form-control" id="wno" value="${wsalary.wno }" readonly>
				</div>
			</div>
			<div class="form-group">
				<label for="wname" class="col-sm-2 control-label">姓名</label>
				<div class="col-sm-3">
					<input type="text" class="form-control" id="wname" value="${wsalary.wname}" readonly>
				</div>
			</div>
			<div class="form-group">
				<label for="jno" class="col-sm-2 control-label">职位编号</label>
				<div class="col-sm-3">
					<input type="text" class="form-control" id="jno" value="${wsalary.jno}" readonly>
				</div>
			</div>
			<div class="form-group">
				<label for="jname" class="col-sm-2 control-label">职位名称</label>
				<div class="col-sm-3">
					<input type="text" class="form-control" id="jname" value="${wsalary.jname}" readonly>
				</div>
			</div>
			<div class="form-group">
				<label for="jdept" class="col-sm-2 control-label">所属部门</label>
				<div class="col-sm-3">
					<input type="text" class="form-control" id="jdept" value="${wsalary.jdept}" readonly>
				</div>
			</div>
			<div class="form-group">
				<label for="jsalary" class="col-sm-2 control-label">基本工资</label>
				<div class="col-sm-3">
					<input type="text" class="form-control" id="jsalary" value="${wsalary.jsalary}" readonly>
				</div>
			</div>
			<div class="form-group">
				<label for="jbonus" class="col-sm-2 control-label">奖金</label>
				<div class="col-sm-3">
					<input type="text" class="form-control" id="jbonus" value="${wsalary.jbonus}" readonly>
				</div>
			</div>
			<div class="form-group">
				<label for="total" class="col-sm-2 control-label">总工资</label>
				<div class="col-sm-3">
					<input type="text" class="form-control" id="total" value="${wsalary.total}" readonly>
				</div>
			</div>
		</c:forEach>
	</form>

</div>
</body>
</html>