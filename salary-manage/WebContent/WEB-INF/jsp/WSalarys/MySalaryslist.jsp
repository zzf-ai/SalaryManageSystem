<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
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
					<li><a href="${pageContext.request.contextPath }/JobsWorkerslist2.action">员工工资结算</a></li>
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
	<div class="row-fluid">
		<div class="span12">
			<div class="row-fluid">
				<div class="span12">
					<div class="col-md-9">
						<%--<form class="form-search">
							<input class="input-medium search-query" type="text" id="text" /> <a class="btn btn-primary" onclick="findByWname(this)">按姓名关键字查找</a>
							<a class="btn btn-primary" onclick="findByWno(this)">按工号查找</a> <a class="btn btn-primary" onclick="findByJdept(this)">按部门查询</a> <a class="btn btn-primary" onclick="findAll(this)">全部查询</a>
						</form>--%>
					</div>
					<form action="${pageContext.request.contextPath }/WSalarysdelete.action" method="post" id="form1" name="form1">
						<table class="table table-hover" id="#tab">
							<thead>
							<tr>
								<th>
									工号
								</th>
								<th>
									姓名
								</th>
								<th>
									职位名称
								</th>
								<th>
									所属部门
								</th>
								<th>
									基本工资
								</th>
								<th>
									奖金
								</th>
								<th>
									总工资
								</th>
								<th>
									工资月份
								</th>
								<th>
									发放日期
								</th>
							</tr>
							</thead>
							<tbody>
							<c:forEach items="${WSalaryList}" var="wsalary">
								<tr>
									<td>${wsalary.wno }</td>
									<td>${wsalary.wname }</td>
									<td>${wsalary.jname }</td>
									<td>${wsalary.jdept }</td>
									<td>${wsalary.jsalary }</td>
									<td>${wsalary.jbonus }</td>
									<td>${wsalary.total }</td>
									<td>${wsalary.settledate }</td>
									<td><fmt:formatDate value="${wsalary.grantdate}" pattern="yyyy-MM-dd"/></td>
								</tr>
							</c:forEach>
							</tbody>
						</table>
					</form>
				</div>
			</div>
		</div>
	</div>

</div>
</body>
</html>