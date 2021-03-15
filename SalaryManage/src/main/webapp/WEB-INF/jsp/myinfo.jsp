<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
<title>个人信息页面</title>
<meta http-equiv=Content-Type content="text/html; charset=utf-8">
	<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
	<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
	<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
	<meta content="MSHTML 6.00.2600.0" name=GENERATOR>
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
			<c:if test="${USER_SESSION.authority=='财务'}">
			<ul class="nav navbar-nav">
				<li><a href="${pageContext.request.contextPath}/toCustomer.action">主页 <span class="sr-only">(current)</span></a></li>
				<li><a href="${pageContext.request.contextPath }/jobslist.action">公司职位信息查询</a></li>
				<li><a href="${pageContext.request.contextPath }/JobsWorkerslist2.action">员工工资结算</a></li>
				<li><a href="${pageContext.request.contextPath }/WSalaryslist.action">员工工资信息管理</a></li>
				<li><a href="${pageContext.request.contextPath }/MySalaryslist.action">我的工资信息</a></li>
			</ul>
			</c:if>
			<c:if test="${USER_SESSION.authority=='公司高层'}">
			<ul class="nav navbar-nav">
				<li><a href="${pageContext.request.contextPath}/toLeader.action">主页 <span class="sr-only">(current)</span></a></li>
				<li><a href="${pageContext.request.contextPath }/workerslist.action">员工个人信息管理</a></li>
				<li><a href="${pageContext.request.contextPath }/jobslist.action">公司职位信息管理</a></li>
				<li><a href="${pageContext.request.contextPath }/JobsWorkerslist.action">人事管理</a></li>
				<li><a href="${pageContext.request.contextPath }/WSalaryslist.action">员工工资信息查询</a></li>
				<li><a href="${pageContext.request.contextPath }/MySalaryslist.action">我的工资信息</a></li>
			</ul>
			</c:if>
			<c:if test="${USER_SESSION.authority=='普通员工'}">
			<ul class="nav navbar-nav">
				<li><a href="${pageContext.request.contextPath}/toEmployee.action">主页 <span class="sr-only">(current)</span></a></li>
				<li><a href="${pageContext.request.contextPath }/jobslist.action">公司职位信息查询</a></li>
				<li><a href="${pageContext.request.contextPath }/MySalaryslist.action">我的工资信息</a></li>
			</ul>
			</c:if>
			<c:if test="${USER_SESSION.authority=='系统管理员'}">
			<ul class="nav navbar-nav">
				<li><a href="${pageContext.request.contextPath}/toAdmin.action">主页 <span class="sr-only">(current)</span></a></li>
				<li><a href="${pageContext.request.contextPath }/userslist.action">用户账户管理</a></li>
				<li><a href="${pageContext.request.contextPath }/MySalaryslist.action">我的工资信息</a></li>
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
		</div>
	</div>
</nav>
<div id="changebox" style="margin-top:50px" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
	<div class="panel panel-info">
		<div class="panel-heading">
			<div class="panel-title">我的个人信息</div>
		</div>
		<div class="panel-body" >
			<form id="signupform" class="form-horizontal" role="form" method="post" action="${pageContext.request.contextPath}/workersupdate2.action">
				<div class="form-group">
					<label for="usercode" class="col-md-3 control-label">账号</label>
					<div class="col-md-6">
						<input type="text" class="form-control" id="usercode" name="usercode" value="${USER_SESSION.usercode}">
					</div>
					<div class="col-md-3">
					</div>
				</div>
				<div class="form-group">
					<label for="authority" class="col-md-3 control-label">身份</label>
					<div class="col-md-6">
						<input type="text" class="form-control" id="authority" name="authority" value="${USER_SESSION.authority}" readonly>
					</div>
					<div class="col-md-3">
					</div>
				</div>
				<c:forEach items="${workerList}" var="worker">
					<div class="form-group">
						<div class="col-md-6">
							<input type="hidden" class="form-control" id="wid" name="wid" value="${worker.wid}">
						</div>
					</div>
					<div class="form-group">
						<label for="wno" class="col-md-3 control-label">工号</label>
						<div class="col-md-6">
							<input type="text" class="form-control" id="wno" name="wno" value="${worker.wno}" readonly>
						</div>
						<div class="col-md-3">
						</div>
					</div>
					<div class="form-group">
						<label for="wname" class="col-sm-3 control-label">姓名</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="wname"  name="wname" value="${worker.wname }" id="wname2">
						</div>
					</div>

					<div class="form-group">
						<label for="wsex" class="col-sm-3 control-label">性别</label>
						<div class="col-sm-6">
							<input type="radio" name="wsex" id="man2" value="男" <c:if test="${worker.wsex eq '男'}"> checked="checked"</c:if>>男
							&nbsp;&nbsp;
							<input type="radio" name="wsex" id="women2" value="女" <c:if test="${worker.wsex eq '女'}"> checked="checked"</c:if>>女
						</div>
					</div>
					<div class="form-group">
						<label for="wnative" class="col-sm-3 control-label">籍贯</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" name="wnative" value="${worker.wnative }" id="wnative">
						</div>
					</div>

					<div class="form-group">
						<label for="wphone" class="col-sm-3 control-label">手机号</label>
						<div class="col-sm-6">
							<input type="text"  class="form-control"  name="wphone" value="${worker.wphone }" id="wphone">
						</div>
					</div>

					<div class="form-group">
						<div class="col-md-6">
							<input type="hidden" class="form-control" id="userid" name="user_id" value="${USER_SESSION.user_id}">
						</div>
					</div>

				</c:forEach>

				<div class="form-group">
					<!-- Button -->
					<div class="col-md-offset-3 col-md-9">
						<input type="submit" class="btn btn-success" value="保存">
						<span style="margin-left:8px;">or</span>
						<input type="reset" class="btn btn-info">
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
</body>
</html>