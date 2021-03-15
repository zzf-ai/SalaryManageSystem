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
	<link href="https://cdn.bootcss.com/bootstrap-datetimepicker/4.17.47/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
	<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
	<script src="https://cdn.bootcss.com/moment.js/2.24.0/moment-with-locales.js"></script>
	<script src="https://cdn.bootcss.com/bootstrap-datetimepicker/4.17.47/js/bootstrap-datetimepicker.min.js"></script>
	<script src="http://cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
	<script type="text/javascript">
		$(function () {
			var defaultdate='${settledate}';
			$('#datetimepicker1').datetimepicker({
				format: 'YYYY-MM',
				locale: moment.locale('zh-cn'),
				defaultDate: defaultdate,
			});
		});
		//查找全部
		function findAll(a) {
			a.href='MySalaryslist.action';
		}
		//按月份查询
		function findBySettleDate(a) {
			var s='settledate='+$('#text2').val();
			// $('#text3').attr('value',$('#text2').val());
			$.cookie('month',$('#text2').val());
			a.href='MySalaryslistBySettleDate.action?'+s;
		}
		function nextPage(a) {
			var s=$.cookie('month');
			a.href="${pageContext.request.contextPath }/MySalaryslistBySettleDate.action?currentPage=${WSalaryList.currentPage+1}&settledate="+s;
		}

		function prePage(a) {
			var s=$.cookie('month');
			a.href="${pageContext.request.contextPath }/MySalaryslistBySettleDate.action?currentPage=${WSalaryList.currentPage-1}&settledate="+s;
		}

		function firstPage(a) {
			var s=$.cookie('month');
			a.href="${pageContext.request.contextPath }/MySalaryslistBySettleDate.action?currentPage=1&settledate="+s;
		}

		function endPage(a) {
			var s=$.cookie('month');
			a.href="${pageContext.request.contextPath }/MySalaryslistBySettleDate.action?currentPage=${WSalaryList.totalPage}&settledate="+s;
		}
		function onePage(a,t) {
			var s=$.cookie('month');
			a.href="${pageContext.request.contextPath }/MySalaryslistBySettleDate.action?currentPage="+t+"&settledate="+s;
		}
	</script>
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
					<li class="active"><a href="${pageContext.request.contextPath }/MySalaryslist.action">我的工资信息</a></li>
				</ul>
			</c:if>
			<c:if test="${USER_SESSION.authority=='普通员工'}">
				<ul class="nav navbar-nav">
					<li><a href="${pageContext.request.contextPath}/toEmployee.action">主页 <span class="sr-only">(current)</span></a></li>
					<li><a href="${pageContext.request.contextPath }/jobslist.action">公司职位信息查询</a></li>
					<li class="active"><a href="${pageContext.request.contextPath }/MySalaryslist.action">我的工资信息</a></li>
				</ul>
			</c:if>
			<c:if test="${USER_SESSION.authority=='系统管理员'}">
				<ul class="nav navbar-nav">
					<li><a href="${pageContext.request.contextPath}/toAdmin.action">主页 <span class="sr-only">(current)</span></a></li>
					<li><a href="${pageContext.request.contextPath }/userslist.action">用户账户管理</a></li>
					<li class="active"><a href="${pageContext.request.contextPath }/MySalaryslist.action">我的工资信息</a></li>
				</ul>
			</c:if>
			<c:if test="${USER_SESSION.authority=='公司高层'}">
				<ul class="nav navbar-nav">
					<li><a href="${pageContext.request.contextPath}/toLeader.action">主页 <span class="sr-only">(current)</span></a></li>
					<li><a href="${pageContext.request.contextPath }/workerslist.action">员工个人信息管理</a></li>
					<li><a href="${pageContext.request.contextPath }/jobslist.action">公司职位信息管理</a></li>
					<li><a href="${pageContext.request.contextPath }/JobsWorkerslist.action">人事管理</a></li>
					<li><a href="${pageContext.request.contextPath }/WSalaryslist.action">员工工资信息查询</a></li>
					<li class="active"><a href="${pageContext.request.contextPath }/MySalaryslist.action">我的工资信息</a></li>
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
					<div class="col-md-2">
						<div class="input-group date" id="datetimepicker1">
							<input class="form-control" type="text" id="text2" />
							<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
						</div>
					</div>
					<div class="col-md-2">
						<a href="#" class="btn btn-primary" onclick="findBySettleDate(this)">按工资月份查找</a>
						<a class="btn btn-primary" onclick="findAll(this)">全部查询</a>
					</div>
					<%--<div class="col-md-9">
					</div>--%>
					<form action="${pageContext.request.contextPath }/WSalarysdelete.action" method="post" id="form1" name="form1">
						<table class="table table-hover" id="#tab">
							<thead>
							<tr>
								<th>
									序号
								</th>
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
							<c:forEach items="${WSalaryList.datas}" var="wsalary" varStatus="s">
								<tr>
									<td>${s.count}</td>
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
	<!-- 分页文字信息 -->
	<div class="row">
		<div class="col-md-12 text-center">
			当前第${WSalaryList.currentPage}页，总共${WSalaryList.totalPage}页，总共${WSalaryList.totalCount}条记录
		</div>
	</div>
	<!-- 分页条信息 -->
	<div class="row">
		<div class="col-md-12 text-center">
			<nav aria-label="Page navigation">
				<c:if test="${flag5==0}">
					<ul class="pagination">
						<li><a href="${pageContext.request.contextPath }/MySalaryslist.action?currentPage=1" >首页</a></li>
						<c:if test="${WSalaryList.currentPage!=1}">
							<li>
								<a href="${pageContext.request.contextPath }/MySalaryslist.action?currentPage=${WSalaryList.currentPage-1}" aria-label="Previous">
									<span aria-hidden="true">&laquo;</span>
								</a>
							</li>
						</c:if>

						<c:forEach begin="1" end="${WSalaryList.totalPage}" var="pageNum">
							<li <c:if test="${pageNum==WSalaryList.currentPage}">class="active"</c:if> ><a href="${pageContext.request.contextPath }/MySalaryslist.action?currentPage=${pageNum}">${pageNum}</a></li>
						</c:forEach>
						<c:if test="${WSalaryList.currentPage!=WSalaryList.totalPage}">
							<li>
								<a href="${pageContext.request.contextPath }/MySalaryslist.action?currentPage=${WSalaryList.currentPage+1}" aria-label="Next">
									<span aria-hidden="true">&raquo;</span>
								</a>
							</li>
						</c:if>
						<li><a href="${pageContext.request.contextPath }/MySalaryslist.action?currentPage=${WSalaryList.totalPage}">尾页</a></li>
					</ul>
				</c:if>
				<c:if test="${flag5==1}">
					<ul class="pagination">
						<li><a href="#" onclick="firstPage(this)">首页</a></li>
						<c:if test="${WSalaryList.currentPage!=1}">
							<li>
								<a href="#" aria-label="Previous">
									<span aria-hidden="true">&laquo;</span>
								</a>
							</li>
						</c:if>
						<c:forEach begin="1" end="${WSalaryList.totalPage}" var="pageNum">
							<li <c:if test="${pageNum==WSalaryList.currentPage}">class="active"</c:if> ><a href="#" onclick="onePage(this,${pageNum})">${pageNum}</a></li>
						</c:forEach>
						<c:if test="${WSalaryList.currentPage!=WSalaryList.totalPage}">
							<li>
								<a href="#" onclick="nextPage(this)" aria-label="Next">
									<span aria-hidden="true">&raquo;</span>
								</a>
							</li>
						</c:if>
						<li><a onclick="endPage(this)">尾页</a></li>
					</ul>
				</c:if>
			</nav>
		</div>
	</div>

</div>
</body>
</html>