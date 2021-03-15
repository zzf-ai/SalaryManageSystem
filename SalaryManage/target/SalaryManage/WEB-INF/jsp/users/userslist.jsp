<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户账户列表</title>

	<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
	<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
	<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		//删除提交
	function frmSubmit() {
		var s=document.getElementsByName("user_ids");
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
		a.href='${pageContext.request.contextPath }/userslist.action?';
	}
	//按工号查找
	function findByWno(a) {
		var s='wno='+document.getElementById('text').value;
		a.href='${pageContext.request.contextPath }/findUserByWno.action?'+s;
	}
	//按账号查找
	function findByUserCode(a) {
		var s='usercode='+document.getElementById('text').value;
		a.href='${pageContext.request.contextPath }/findUserCode.action?'+s;
	}
	//开启修改模态框
	$(function () { $('#updateUserModal').on('hide.bs.modal', function () {

		$("#act").val("update");
	})
	});

	//获取信息
	$(function() {
		$('#updateUserModal').on('show.bs.modal', function (event) {
			var a = $(event.relatedTarget) // a that triggered the modal
			var authority = a.data('authority');
			var userid=a.data('userid');
			var modal = $(this)
			modal.find('#userid').val(userid);
			if (authority == '系统管理员') {
				document.getElementById('admin').checked = true;
			} else if (authority == '财务'){
				document.getElementById('finance').checked = true;
			} else if(authority == '公司高层'){
				document.getElementById('leader').checked = true;
			} else if (authority == '普通员工') {
				document.getElementById('worker').checked = true;
			}
		});
	});
</script>
</head>
<body id="show-refectory-html">
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
				<a class="navbar-brand" href="#">简易版人事&工资信息管理系统</a>
			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li><a href="${pageContext.request.contextPath}/toAdmin.action">主页 <span class="sr-only">(current)</span></a></li>
					<li class="active"><a href="${pageContext.request.contextPath }/userslist.action">用户账户管理</a></li>
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
				</ul>
			</div><!-- /.navbar-collapse -->
		</div><!-- /.container-fluid -->
	</nav>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12">
				<h3>
					用户账户列表
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
								<input placeholder="输入账号或工号" class="input-medium search-query" type="text" id="text" value="${usercode!=null?usercode:wno}" />
								<a class="btn btn-primary" onclick="findByUserCode(this)">按账号查找</a>
								<a class="btn btn-primary" onclick="findByWno(this)">按工号查找</a> <a class="btn btn-primary" onclick="findAll(this)">查找全部</a>
							</form>
						</div>
						<form action="${pageContext.request.contextPath }/Usersdelete.action" method="post" id="form1" name="form1">
							<table class="table table-hover">
								<thead>
								<tr>
									<th>
										选择
									</th>
									<th>
										账号
									</th>
									<th>
										权限
									</th>
									<th>
										绑定的工号
									</th>
									<th>
										操作 &nbsp;| &nbsp; <a class="btn btn-info" href="#" onclick="javascript:frmSubmit();">多选删除</a>&nbsp;&nbsp;
									</th>
								</tr>
								</thead>
								<tbody>
								<c:forEach items="${userList.datas}" var="user">
									<tr>
										<td><input type="checkbox" name="user_ids"
												   value="${user.user_id}"></td>
										<td>${user.usercode}</td>
										<td>${user.authority}</td>
										<td>${user.wno }</td>
										<td>

											<button type="button" class="btn btn-success" data-toggle="modal" data-target="#updateUserModal"
												data-authority="${user.authority}" data-userid="${user.user_id}" >
											授权
											</button>
											<a class="btn btn-info" onclick="javascript:frmSubmit();">删除</a>
										</td>
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
				当前第${userList.currentPage}页，总共${userList.totalPage}页，总共${userList.totalCount}条记录
			</div>
		</div>
		<!-- 分页条信息 -->
		<div class="row">
			<div class="col-md-12 text-center">
				<nav aria-label="Page navigation">
					<c:if test="${flag0==0}">
						<ul class="pagination">
							<li><a href="${pageContext.request.contextPath }/userslist.action?currentPage=1" >首页</a></li>
							<c:if test="${userList.currentPage!=1}">
								<li>
									<a href="${pageContext.request.contextPath }/userslist.action?currentPage=${userList.currentPage-1}" aria-label="Previous">
										<span aria-hidden="true">&laquo;</span>
									</a>
								</li>
							</c:if>

							<c:forEach begin="1" end="${userList.totalPage}" var="pageNum">
								<li <c:if test="${pageNum==userList.currentPage}">class="active"</c:if> ><a href="${pageContext.request.contextPath }/userslist.action?currentPage=${pageNum}">${pageNum}</a></li>
							</c:forEach>
							<c:if test="${userList.currentPage!=userList.totalPage}">
								<li>
									<a href="${pageContext.request.contextPath }/userslist.action?currentPage=${userList.currentPage+1}" aria-label="Next">
										<span aria-hidden="true">&raquo;</span>
									</a>
								</li>
							</c:if>
							<li><a href="${pageContext.request.contextPath }/userslist.action?currentPage=${userList.totalPage}">尾页</a></li>
						</ul>
					</c:if>
					<c:if test="${flag0==1}">
						<ul class="pagination">
							<li><a href="${pageContext.request.contextPath }/findUserCode.action?currentPage=1&wname=${wname}" >首页</a></li>
							<c:if test="${userList.currentPage!=1}">
								<li>
									<a href="${pageContext.request.contextPath }/findUserCode.action?currentPage=${userList.currentPage-1}&wname=${wname}" aria-label="Previous">
										<span aria-hidden="true">&laquo;</span>
									</a>
								</li>
							</c:if>
							<c:forEach begin="1" end="${userList.totalPage}" var="pageNum">
								<li <c:if test="${pageNum==userList.currentPage}">class="active"</c:if> ><a href="${pageContext.request.contextPath }/findUserCode.action?currentPage=${pageNum}&usercode=${usercode}">${pageNum}</a></li>
							</c:forEach>
							<c:if test="${userList.currentPage!=userList.totalPage}">
								<li>
									<a href="${pageContext.request.contextPath }/findUserCode.action?currentPage=${userList.currentPage+1}&wname=${usercode}" aria-label="Next">
										<span aria-hidden="true">&raquo;</span>
									</a>
								</li>
							</c:if>
							<li><a href="${pageContext.request.contextPath }/findUserCode.action?currentPage=${userList.totalPage}&wname=${usercode}">尾页</a></li>
						</ul>
					</c:if>
					<c:if test="${flag0==2}">
						<ul class="pagination">
							<li><a href="${pageContext.request.contextPath }/findUserByWno.action?currentPage=1&wname=${wno}" >首页</a></li>
							<c:if test="${userList.currentPage!=1}">
								<li>
									<a href="${pageContext.request.contextPath }/findUserByWno.action?currentPage=${userList.currentPage-1}&wno=${wno}" aria-label="Previous">
										<span aria-hidden="true">&laquo;</span>
									</a>
								</li>
							</c:if>
							<c:forEach begin="1" end="${userList.totalPage}" var="pageNum">
								<li <c:if test="${pageNum==userList.currentPage}">class="active"</c:if> ><a href="${pageContext.request.contextPath }/findUserByWno.action?currentPage=${pageNum}">${pageNum}</a></li>
							</c:forEach>
							<c:if test="${userList.currentPage!=userList.totalPage}">
								<li>
									<a href="${pageContext.request.contextPath }/findUserByWno.action?currentPage=${userList.currentPage+1}&wno=${wno}" aria-label="Next">
										<span aria-hidden="true">&raquo;</span>
									</a>
								</li>
							</c:if>
							<li><a href="${pageContext.request.contextPath }/findUserByWno.action?currentPage=${userList.totalPage}&wno=${wno}">尾页</a></li>
						</ul>
					</c:if>
				</nav>
			</div>
		</div>

		<!--授权业务模态框-->
		<form method="post" action="${pageContext.request.contextPath }/grant.action" class="form-horizontal" role="form" id="form_data2" style="margin: 20px;">
			<div class="modal fade" id="updateUserModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel2" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
								&times;
							</button>
							<h4 class="modal-title" id="myModalLabel2">
								授权
							</h4>
						</div>
						<div class="modal-body">
							<form class="form-horizontal" role="form">
								<div class="form-group">
									<div class="col-sm-9">
										<input type="hidden" name="user_id" id="userid" />
									</div>
								</div>
								<div class="form-group">
									<label for="authority" class="col-sm-3 control-label">权限</label>
									<div class="col-sm-9">
										<input type="radio" name="authority" id="admin" value="系统管理员" <c:if test="${user.authority eq '系统管理员'}"> checked="checked"</c:if>>系统管理员
										&nbsp;&nbsp;
										<input type="radio" name="authority" id="leader" value="公司高层" <c:if test="${user.authority eq '公司高层'}"> checked="checked"</c:if>>公司高层
										&nbsp;&nbsp;
										<input type="radio" name="authority" id="finance" value="财务" <c:if test="${user.authority eq '财务'}"> checked="checked"</c:if>>财务
										&nbsp;&nbsp;
										<input type="radio" name="authority" id="worker" value="普通员工" <c:if test="${worker.wsex eq '普通员工'}"> checked="checked"</c:if>>普通员工
									</div>
								</div>
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