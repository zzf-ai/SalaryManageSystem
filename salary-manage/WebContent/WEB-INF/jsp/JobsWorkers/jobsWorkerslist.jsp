<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>员工职位信息列表</title>
	<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
	<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
	<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		function frmSubmit() {
			document.form1.submit();
		}
		//查找全部
		function findAll(a) {
			a.href='JobsWorkerslist.action';
		}
		//按工号查找
		function findByWno(a) {
			var s='wno='+document.getElementById('text').value;
			a.href='JobsWorkerslistByWno.action?'+s;
		}
		//按部门查找
		function findByJdept(a) {
			var s='jdept='+document.getElementById('text').value;
			a.href='JobsWorkerslistByJdept.action?'+s;
		}

		//修改模态框
		$(function () { $('#updateUserModal').on('hide.bs.modal', function () {
			// 关闭时清空edit状态为update
			$("#act").val("update");
		})
		});

		//添加模态框
		$(function () { $('#addUserModal').on('hide.bs.modal', function () {
			// 关闭时清空edit状态为add
			$("#act").val("add");
		})
		});

		//获取信息
		$(function() {
			$('#updateUserModal').on('show.bs.modal', function (event) {
				var a = $(event.relatedTarget) // a that triggered the modal
				var id=a.data('id'), wno = a.data('wno')+","+a.data('wname'),jno= a.data('jno')+","+a.data('jname')+","+a.data('jdept');
				//alert(id)
				var modal = $(this)
				modal.find('#id').val(id);
				//modal.find('#wno2').val(wno);
				//alert(modal.find("#wno2 option[text]"));
				modal.find('#wno2').find("option").each(function() {
					if ($(this).text().trim() == wno) {
						$(this).attr("selected", true);
					}
				});
				modal.find('#jno2').find("option").each(function() {
					if ($(this).text().trim() == jno) {
						$(this).attr("selected", true);
					}
				});
			});
		});
	</script>
</head>
<body id="show-refectory-html">
<nav class="navbar navbar-default">
	<div class="container-fluid">
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
				<li><a href="${pageContext.request.contextPath}/toLeader.action">主页 <span class="sr-only">(current)</span></a></li>
				<li><a href="${pageContext.request.contextPath }/workerslist.action">员工个人信息管理</a></li>
				<li><a href="${pageContext.request.contextPath }/jobslist.action">公司职位信息管理</a></li>
				<li class="active"><a href="${pageContext.request.contextPath }/JobsWorkerslist.action">员工职位信息管理</a></li>
				<li><a href="${pageContext.request.contextPath }/WSalaryslist.action">员工工资信息查询</a></li>
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
		</div><!-- /.navbar-collapse -->
	</div><!-- /.container-fluid -->
</nav>
<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">
			<h3>
				员工职位列表
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
						<form class="form-search">
							<input class="input-medium search-query" type="text" id="text" /> <a class="btn btn-primary" onclick="findByJdept(this)">按部门查找</a>
							<a class="btn btn-primary" onclick="findByWno(this)">按工号查找</a>
							<a class="btn btn-primary" onclick="findAll(this)">全部查询</a>
						</form>
					</div>
					<form action="${pageContext.request.contextPath }/wjdelete.action" method="post" id="form1" name="form1">
						<table class="table table-hover">
							<thead>
							<tr>
								<th>
									选择
								</th>
								<th>
									工号
								</th>
								<th>
									姓名
								</th>
								<th>
									职位编号
								</th>
								<th>
									职位名称
								</th>
								<th>
									所属部门
								</th>
								<th>
									操作 &nbsp;| &nbsp; <a class="btn btn-info" href="#" onclick="javascript:frmSubmit();">多选删除</a>&nbsp;&nbsp;
									<button type="button" class="btn btn-warning" data-toggle="modal" data-target="#addUserModal">添加</button>
								</th>
							</tr>
							</thead>
							<tbody>
							<c:forEach items="${JobsWorkersList}" var="worker">
								<tr>
									<td><input type="checkbox" name="id"
											   value="${worker.id }"></td>
									<td>${worker.wno }</td>
									<td>${worker.wname }</td>

									<c:forEach items="${worker.jobslist}" var="job">
										<td>&nbsp;&nbsp;${job.jno}</td>
										<td>${job.jname}</td>
										<td>${job.jdept }</td>
										<td>
											<button type="button" class="btn btn-success" data-toggle="modal" data-target="#updateUserModal"
													data-id="${worker.id}" data-wno="${worker.wno}" data-wname="${worker.wname}"
													data-jno="${job.jno}" data-jname="${job.jname}" data-jdept="${job.jdept}">
												修改
											</button>
											<a class="btn btn-info" onclick="javascript:frmSubmit();">删除</a>
										</td>
									</c:forEach>
								</tr>
							</c:forEach>
							</tbody>
						</table>
					</form>
				</div>
			</div>
		</div>
	</div>
	<!--添加业务模态框-->
	<form method="post" action="${pageContext.request.contextPath }/insertWj.action" class="form-horizontal" role="form" id="form_data" style="margin: 20px;">
		<div class="modal fade" id="addUserModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
							&times;
						</button>
						<h4 class="modal-title" id="myModalLabel">
							添加员工职位信息
						</h4>
					</div>
					<div class="modal-body">
						<form class="form-horizontal" role="form">
							<div class="form-group">
								<label for="wno" class="col-sm-3 control-label">员工</label>
								<div class="col-sm-9">
									<select class="form-control" name="wno" id="wno">
										<c:forEach items="${Workers}" var="worker">
											<option>
													${worker.wno},${worker.wname}
											</option>
										</c:forEach>
									</select>
								</div>
							</div>

							<div class="form-group">
								<label for="jno" class="col-sm-3 control-label">职位</label>
								<div class="col-sm-9">
									<select class="form-control" name="jno" id="jno">
										<c:forEach items="${Jobs}" var="job">
											<option>
												${job.jno},${job.jname},${job.jdept}
											</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭
						</button>
						<button type="submit" class="btn btn-primary">
							提交
						</button><span id="tip"> </span>
					</div>
				</div><!-- /.modal-content -->
			</div><!-- /.modal -->
		</div>
	</form>

	<!--修改业务模态框-->
	<form method="post" action="${pageContext.request.contextPath }/wjupdate.action" class="form-horizontal" role="form" id="form_data2" style="margin: 20px;">
		<div class="modal fade" id="updateUserModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel2" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
							&times;
						</button>
						<h4 class="modal-title" id="myModalLabel2">
							修改员工职位
						</h4>
					</div>
					<div class="modal-body">
						<form class="form-horizontal" role="form">
							<div class="form-group">
								<label for="wno2" class="col-sm-3 control-label">员工</label>
								<div class="col-sm-9">
									<select class="form-control" name="wno" id="wno2">
										<c:forEach items="${Workers}" var="worker">
											<option>
													<%--<input type="hidden" name="jno" value="${job.jno}" id="jno">--%>
													${worker.wno},${worker.wname}
											</option>
										</c:forEach>
									</select>
								</div>
							</div>

							<div class="form-group">
								<label for="jno2" class="col-sm-3 control-label">职位</label>
								<div class="col-sm-9">
									<select class="form-control" name="jno" id="jno2">
										<c:forEach items="${Jobs}" var="job">
											<option>
													<%--<input type="hidden" name="jno" value="${job.jno}" id="jno">--%>
													${job.jno},${job.jname},${job.jdept}
											</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<%--<div class="form-group">
								<label for="wno2" class="col-sm-3 control-label">工号</label>
								<div class="col-sm-9">
									<input type="text" class="form-control" id="wno2" name="wno" value=""
										   placeholder="工号">
								</div>
							</div>--%>
							<div class="form-group">
								<div class="col-sm-9">
									<input type="hidden" class="form-control" id="id" name="id" value="">
								</div>
							</div>


							<%--<div class="form-group">
								<label for="jno2" class="col-sm-3 control-label">职位编号</label>
								<div class="col-sm-9">
									<input type="text" class="form-control" name="jno" value="" id="jno2"
										   placeholder="职位编号">
								</div>
							</div>--%>

						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭
						</button>
						<button type="submit" class="btn btn-primary">
							提交
						</button><span id="tip2"> </span>
					</div>
				</div><!-- /.modal-content -->
			</div><!-- /.modal -->
		</div>
	</form>

</div>
</body>
</html>