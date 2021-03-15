<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
<title>修改密码页面</title>
<meta http-equiv=Content-Type content="text/html; charset=utf-8">
	<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
	<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
	<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
	<meta content="MSHTML 6.00.2600.0" name=GENERATOR>
<script>
// 判断是登录账号和密码是否为空
function check(){
    var password = $("#password").val();
    var confirm = $("#confirm").val();

    if(password==null||password==""){
    	$("#msg1").text("请输入密码！");
		$("#msg1").show().delay(1000).hide(0);
        return false;
    }  
    else if(confirm==null||confirm==""){
    	$("#msg2").text("请确认密码！");
		$("#msg2").show().delay(1000).hide(0);
        return false;
    }
    else if(password!=confirm||confirm==""){
    	$("#msg2").text("两次密码不相同！");
		$("#msg2").show().delay(1000).hide(0);
        return false;
    }
    return true;
}

function check1(){
    var password = $("#password").val();
    var confirm = $("#confirm").val();

    if(password==null||password==""){
    	$("#msg1").text("请输入密码！");
		$("#msg1").show().delay(1000).hide(0);
        return false;
    }
    else if(confirm==null||confirm==""){
    	$("#msg2").text("请确认密码！");
		$("#msg2").show().delay(1000).hide(0);
        return false;
    }
    else if(password!=confirm||confirm==""){
    	$("#msg2").text("两次密码不相同！");
		$("#msg2").show().delay(1000).hide(0);
        return false;
    }
    return true;
}
</script>
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
			<c:if test="${USER_SESSION.authority=='公司高层'}">
				<ul class="nav navbar-nav">
					<li><a href="${pageContext.request.contextPath}/toLeader.action">主页 <span class="sr-only">(current)</span></a></li>
					<li><a href="${pageContext.request.contextPath }/workerslist.action">员工个人信息管理</a></li>
					<li><a href="${pageContext.request.contextPath }/jobslist.action">公司职位信息管理</a></li>
					<li><a href="${pageContext.request.contextPath }/JobsWorkerslist.action">人事管理</a></li>
					<li><a href="${pageContext.request.contextPath }/WSalaryslist.action">员工工资信息管理</a></li>
					<li><a href="${pageContext.request.contextPath }/MySalaryslist.action">我的工资信息</a></li>
				</ul>
			</c:if>
			<c:if test="${USER_SESSION.authority=='财务'}">
				<ul class="nav navbar-nav">
					<li><a href="${pageContext.request.contextPath}/toCustomer.action">主页 <span class="sr-only">(current)</span></a></li>
					<li><a href="${pageContext.request.contextPath }/jobslist.action">公司职位信息管理</a></li>
					<li><a href="${pageContext.request.contextPath }/JobsWorkerslist2.action">员工工资结算</a></li>
					<li><a href="${pageContext.request.contextPath }/WSalaryslist.action">员工工资信息管理</a></li>
					<li><a href="${pageContext.request.contextPath }/MySalaryslist.action">我的工资信息</a></li>
				</ul>
			</c:if>
			<c:if test="${USER_SESSION.authority=='普通员工'}">
				<ul class="nav navbar-nav">
					<li><a href="${pageContext.request.contextPath}/toEmployee.action">主页 <span class="sr-only">(current)</span></a></li>
					<li><a href="${pageContext.request.contextPath }/jobslist.action">公司职位信息管理</a></li>
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
			<div class="panel-title">修改密码</div>
		</div>
		<div class="panel-body" >
			<form id="signupform" class="form-horizontal" role="form" method="post" action="${pageContext.request.contextPath}/changePassword.action" onsubmit="return check()">
				<div class="form-group">
					<label for="usercode" class="col-md-3 control-label">账号</label>
					<div class="col-md-6">
						<input type="text" class="form-control" id="usercode" name="usercode" value="${USER_SESSION.usercode}" readonly>
					</div>
					<div class="col-md-3">
					</div>
				</div>
				<div class="form-group">
					<label for="password" class="col-md-3 control-label">新密码</label>
					<div class="col-md-6">
						<input type="password" class="form-control" id="password" name="password" placeholder="输入密码" onblur="check1()">
					</div>
					<div class="col-md-3">
						<span style="display:none; color: red" id="msg1"></span>
					</div>
				</div>
				<div class="form-group">
					<label for="confirm" class="col-md-3 control-label">确认密码</label>
					<div class="col-md-6">
						<input type="password" class="form-control" id="confirm" name="confirm" placeholder="确认密码" onblur="check1()">
					</div>
					<div class="col-md-3">
						<span style="display:none; color: red" id="msg2"></span>
					</div>
				</div>
				<div class="form-group">
					<div class="col-md-6">
						<input type="hidden" class="form-control" id="userid" name="user_id" value="${USER_SESSION.user_id}">
					</div>
				</div>

				<div class="form-group">
					<!-- Button -->
					<div class="col-md-offset-3 col-md-9">
						<input type="submit" class="btn btn-success">
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