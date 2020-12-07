<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>职位信息列表</title>
	<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
	<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
	<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		function exam() {
			var data={"jno":$('#jno').val()};
			$.ajax({
				type:"post",
				url:"${pageContext.request.contextPath }/ajaxCheck4.action",
				contentType: 'application/json;charset=utf-8',
				data:"json",
				data:JSON.stringify(data),
				success:function(data){
					console.log(data.toString());
					if(data.toString()!='ok'){
						document.getElementById("error1").style.display="none";
						document.form_data.submit();
					}
					else{
						document.getElementById("error1").style.display="block";
					}
				}
			});
		}
		function exam1() {
			var data={"jno":$('#jno2').val()};
			var d=$('#jno2').val();
			var t=$('#jno3').val();
			$.ajax({
				type:"post",
				url:"${pageContext.request.contextPath }/ajaxCheck4.action",
				contentType: 'application/json;charset=utf-8',
				data:"json",
				data:JSON.stringify(data),
				success:function(data){
					console.log(data.toString());
					if(data.toString()!='ok'){
						document.getElementById("error2").style.display="none";
						document.form_data2.submit();
					}
					else{
						if(d==t) {
							document.getElementById("error2").style.display = "none";
							document.form_data2.submit();
						}
						else
							document.getElementById("error2").style.display="block";
					}
				}
			});
		}
		function frmSubmit() {
			var s=document.getElementsByName("jnoArray");
			var c='';
			for(var i=0;i<s.length;i++){
				if(s[i].checked==true){
					c+=s;
				}
			}
			if(c.length==0){
				alert("请选择再删除！")
			}
			else{
				document.form1.submit();
			}
		}
		//查找全部
		function findAll(a) {
			a.href='jobslist.action';
		}
		//按职位查找
		function findByJname(a) {
			var s='jname='+document.getElementById('text').value;
			a.href='jobslistbyname.action?'+s;
		}
		//按部门查找
		function findByJdept(a) {
			var s='jdept='+document.getElementById('text').value;
			a.href='jobslistbyJdept.action?'+s;
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
				var jid = a.data('jid'),jno = a.data('jno'), jname = a.data('jname'), jsalary = a.data('jsalary'),jbonus=a.data('jbonus'),jdept=a.data('jdept');
				var modal = $(this)
				modal.find('#jid2').val(jid);
				modal.find('#jno2').val(jno);
				modal.find('#jno3').val(jno);
				modal.find('#jname2').val(jname);
				modal.find('#jsalary2').val(jsalary);
				modal.find('#jbonus2').val(jbonus);
				modal.find('#jdept2').val(jdept);
			});
		});
	</script>
</head>
<body id="show-refectory-html">
<nav class="navbar navbar-default">
	<div class="container-fluid">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<a class="navbar-brand" href="#">企业工资信息管理系统</a>
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			<c:if test="${USER_SESSION.authority=='财务'}">
				<ul class="nav navbar-nav">
					<li><a href="${pageContext.request.contextPath}/toCustomer.action">主页 <span class="sr-only">(current)</span></a></li>
					<li class="active"><a href="${pageContext.request.contextPath }/jobslist.action">公司职位信息查询</a></li>
					<li><a href="${pageContext.request.contextPath }/JobsWorkerslist2.action">员工工资结算</a></li>
					<li><a href="${pageContext.request.contextPath }/WSalaryslist.action">员工工资信息管理</a></li>
					<li><a href="${pageContext.request.contextPath }/WSalaryslistbyno2.action?wno=${USER_SESSION.wno}">我的工资信息</a></li>
				</ul>
			</c:if>
			<c:if test="${USER_SESSION.authority=='普通员工'}">
				<ul class="nav navbar-nav">
					<li><a href="${pageContext.request.contextPath}/toEmployee.action">主页 <span class="sr-only">(current)</span></a></li>
					<li class="active"><a href="${pageContext.request.contextPath }/jobslist.action">公司职位信息查询</a></li>
					<li><a href="${pageContext.request.contextPath }/WSalaryslistbyno2.action?wno=${USER_SESSION.wno}">我的工资信息</a></li>
				</ul>
			</c:if>
			<c:if test="${USER_SESSION.authority=='公司高层'}">
				<ul class="nav navbar-nav">
					<li><a href="${pageContext.request.contextPath}/toLeader.action">主页 <span class="sr-only">(current)</span></a></li>
					<li><a href="${pageContext.request.contextPath }/workerslist.action">员工个人信息管理</a></li>
					<li class="active"><a href="${pageContext.request.contextPath }/jobslist.action">公司职位信息管理</a></li>
					<li><a href="${pageContext.request.contextPath }/JobsWorkerslist.action">员工职位信息管理</a></li>
					<li><a href="${pageContext.request.contextPath }/WSalaryslist.action">员工工资信息查询</a></li>
					<li><a href="${pageContext.request.contextPath }/WSalaryslistbyno2.action?wno=${USER_SESSION.wno}">我的工资信息</a></li>
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
			<h3>
				公司职位信息列表
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
							<input class="input-medium search-query" type="text" id="text" /> <a class="btn btn-primary" onclick="findByJname(this)">按职位查找</a>
							<a class="btn btn-primary" onclick="findByJdept(this)">按部门查询</a>
							<a class="btn btn-primary" onclick="findAll(this)">全部查询</a>
						</form>
					</div>
					<form action="${pageContext.request.contextPath }/jobsdelete.action" method="post" id="form1" name="form1">
						<table class="table table-hover">
							<thead>
							<tr>
								<c:if test="${USER_SESSION.authority=='公司高层'}">
									<th>
										选择
									</th>
								</c:if>
								<th>
									职位编号
								</th>
								<th>
									职位名称
								</th>
								<th>
									基本工资
								</th>
								<th>
									奖金
								</th>
								<th>
									所属部门
								</th>
								<c:if test="${USER_SESSION.authority=='公司高层'}">
									<th>
										操作 &nbsp;| &nbsp; <a class="btn btn-info" href="#" onclick="javascript:frmSubmit();">多选删除</a>&nbsp;&nbsp;
										<button type="button" class="btn btn-warning" data-toggle="modal" data-target="#addUserModal">添加</button>
									</th>
								</c:if>
							</tr>
							</thead>
							<tbody>
							<c:forEach items="${jobList}" var="job">
								<tr>
									<c:if test="${USER_SESSION.authority=='公司高层'}">
										<td><input type="checkbox" name="jnoArray" value="${job.jno }"></td>
									</c:if>
									<td>&nbsp;&nbsp;${job.jno }</td>
									<td>${job.jname }</td>
									<td>${job.jsalary }</td>
									<td>${job.jbonus}</td>
									<td>${job.jdept}</td>
									<c:if test="${USER_SESSION.authority=='公司高层'}">
										<td>
											<button type="button" class="btn btn-success" data-toggle="modal" data-target="#updateUserModal"
													data-jid="${job.jid}" data-jno="${job.jno}" data-jname="${job.jname}"
													data-jsalary="${job.jsalary}" data-jbonus="${job.jbonus}" data-jdept="${job.jdept}">
												修改
											</button>
											<a class="btn btn-info" onclick="javascript:frmSubmit();">删除</a>
										</td>
									</c:if>
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
	<form method="post" action="${pageContext.request.contextPath }/jobsinsert.action" class="form-horizontal" role="form" id="form_data" name="form_data" style="margin: 20px;">
		<div class="modal fade" id="addUserModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
							&times;
						</button>
						<h4 class="modal-title" id="myModalLabel">
							添加公司职位信息
						</h4>
					</div>
					<div class="modal-body">
						<form class="form-horizontal" role="form">
							<div class="form-group">
								<label for="jno" class="col-sm-3 control-label">职位编号</label>
								<div class="col-sm-6">
									<input type="text" class="form-control" id="jno" name="jno" value=""
										   placeholder="职位编号">
								</div>
								<div class="col-md-3">
									<span style="display:none; color: red" id="msg3"></span>
									<span style="display:none; color: red" id="error1">职位编号已存在</span>
								</div>
							</div>
							<div class="form-group">
								<label for="jname" class="col-sm-3 control-label">职位名称</label>
								<div class="col-sm-6">
									<input type="text" class="form-control" id="jname" name="jname" value=""
										   placeholder="职位名称">
								</div>
							</div>

							<div class="form-group">
								<label for="jsalary" class="col-sm-3 control-label">基本工资</label>
								<div class="col-sm-6">
									<input type="text" class="form-control" name="jsalary" value="" id="jsalary"
										   placeholder="基本工资">
								</div>
							</div>
							<div class="form-group">
								<label for="jbonus" class="col-sm-3 control-label">奖金</label>
								<div class="col-sm-6">
									<input type="text" class="form-control" name="jbonus" value="" id="jbonus"
										   placeholder="奖金">
								</div>
							</div>

							<div class="form-group">
								<label for="jdept" class="col-sm-3 control-label">所属部门</label>
								<div class="col-sm-6">
									<input type="text"  class="form-control"  name="jdept" value="" id="jdept"
										   placeholder="所属部门">
								</div>
							</div>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭
						</button>
						<button type="button" class="btn btn-primary" onclick="exam()">
							提交
						</button><span id="tip"> </span>
					</div>
				</div><!-- /.modal-content -->
			</div><!-- /.modal -->
		</div>
	</form>

	<!--修改业务模态框-->
	<form method="post" action="${pageContext.request.contextPath }/jobsupdate.action" class="form-horizontal" role="form" id="form_data2" name="form_data2" style="margin: 20px;">
		<div class="modal fade" id="updateUserModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel2" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
							&times;
						</button>
						<h4 class="modal-title" id="myModalLabel2">
							修改公司职位信息
						</h4>
					</div>
					<div class="modal-body">
						<form class="form-horizontal" role="form">
							<div class="form-group">
								<div class="col-sm-6">
									<input type="hidden" class="form-control" id="jid2" name="jid" value="">
								</div>
							</div>

							<div class="form-group">
								<label for="jno2" class="col-sm-3 control-label">职位编号</label>
								<div class="col-sm-6">
									<input type="text" class="form-control" id="jno2" name="jno" value=""
										   placeholder="职位编号">
								</div>
								<div class="col-md-3">
									<span style="display:none; color: red" id="msg4"></span>
									<span style="display:none; color: red" id="error2">职位编号已存在</span>
								</div>
							</div>

							<div class="form-group">
								<div class="col-sm-6">
									<input type="hidden" class="form-control" id="jno3" value="">
								</div>
							</div>

							<div class="form-group">
								<label for="jname2" class="col-sm-3 control-label">职位名称</label>
								<div class="col-sm-6">
									<input type="text" class="form-control" id="jname2" name="jname" value=""
										   placeholder="职位名称">
								</div>
							</div>

							<div class="form-group">
								<label for="jsalary2" class="col-sm-3 control-label">基本工资</label>
								<div class="col-sm-6">
									<input type="text" class="form-control" name="jsalary" value="" id="jsalary2"
										   placeholder="基本工资">
								</div>
							</div>
							<div class="form-group">
								<label for="jbonus2" class="col-sm-3 control-label">奖金</label>
								<div class="col-sm-6">
									<input type="text" class="form-control" name="jbonus" value="" id="jbonus2"
										   placeholder="奖金">
								</div>
							</div>

							<div class="form-group">
								<label for="jdept2" class="col-sm-3 control-label">所属部门</label>
								<div class="col-sm-6">
									<input type="text"  class="form-control"  name="jdept" value="" id="jdept2"
										   placeholder="所属部门">
								</div>
							</div>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭
						</button>
						<button type="button" class="btn btn-primary" onclick="exam1()">
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