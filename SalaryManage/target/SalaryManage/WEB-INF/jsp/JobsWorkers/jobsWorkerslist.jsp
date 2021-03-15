<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>人事管理</title>
	<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
	<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
	<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		//使用如下方式实现弹出提示信息自动消失
		//tip是提示信息，type:'success'是成功信息，'danger'是失败信息,'info'是普通信息,'warning'是警告信息
		function ShowTip(tip, type) {
			var $tip = $('#tips');
			if ($tip.length == 0||$tip==null) {
				// 设置样式，也可以定义在css文件中
				$tip = $('<span id="tips" style="position:fixed;top:50px;left: 50%;z-index:9999;height: 35px;padding: 0 20px;line-height: 35px;"></span>');
				$('body').append($tip);
			}
			$tip.stop(true).prop('class', 'alert alert-' + type).text(tip).css('margin-left', -$tip.outerWidth() / 2).fadeIn().delay(40000).fadeOut();//设置显示位置和显示时间和消失时间
		}

		function ShowMsg(msg) {
			ShowTip(msg, 'info');
		}

		function ShowSuccess(msg) {
			ShowTip(msg, 'success');
		}

		function ShowFailure(msg) {
			ShowTip(msg, 'danger');
		}

		function ShowWarn(msg, $focus, clear) {
			ShowTip(msg, 'warning');
			if ($focus) {
				$focus.focus();
				if (clear) $focus.val('');
			}
			return false;
		}

		//删除提交
		function frmSubmit() {
			var s=document.getElementsByName("id");
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
				delubmit();
			}
		}
		//删除
		function delubmit() {

			var checkID=[];
			$("input[name='id']:checked").each(function(i){
				checkID[i] = $(this).val();
			});
			//返回字符串，用text
			$.ajax({
				type:'post',
				url:'${pageContext.request.contextPath }/wjdelete.action',
				data:{"id":checkID},
				traditional: true,
				dataType:'text',
				success:function(data){
					console.log(data)
					if(data=='true'){

						window.location.reload();
						ShowSuccess("删除成功");
					}else {
						window.location.reload();
						ShowFailure("删除失败")
					}
				}
			});
		}

		//修改
		function changeSubmit() {
			$("#form_data2").find('#wno2').removeAttr("disabled");

			$.ajax({
				type:'post',
				url:'${pageContext.request.contextPath }/wjupdate.action',
				data:$("#form_data2").serialize(),
				success:function(data){
					console.log(data);
					if(data=='true'){
						window.location.reload();
						ShowSuccess("修改成功")
					}else {
						window.location.reload();
						ShowFailure("修改失败")
					}
				}
			})
		}

		//添加
		function addSubmit() {
			var currntpage='${JobsWorkersList.currentPage}';
			var datasize = '${JobsWorkersList.datas.size()}';
			var pageSize='${JobsWorkersList.pageSize}';
			var totalpage='${JobsWorkersList.totalPage}';

			if(datasize==pageSize && currntpage==totalpage){
				currntpage=parseInt(currntpage)+1;
			}else if(datasize==pageSize && currntpage!=totalpage) {
				currntpage=parseInt(totalpage)+1;
			}
			$.ajax({
				type:'post',
				url:'${pageContext.request.contextPath }/insertWj.action',
				data:$("#form_data").serialize(),
				success:function(data){
					console.log(data)
					if(data=='true'){
						window.location.href='${pageContext.request.contextPath }/JobsWorkerslist.action?currentPage='+currntpage;
						ShowSuccess("添加成功");
					}else {
						window.location.reload();
						ShowFailure("添加失败")
					}
				}
			})
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
				modal.find('#wno2').attr("disabled","disabled")
				modal.find('#jno2').find("option").each(function() {
					if ($(this).text().trim() == jno) {
						$(this).attr("selected", true);
					}
				});
			});
		});
	</script>
</head>
<body name="body">
<nav class="navbar navbar-default">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="#">简易版人事&工资信息管理系统</a>
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li><a href="${pageContext.request.contextPath}/toLeader.action">主页 <span class="sr-only">(current)</span></a></li>
				<li><a href="${pageContext.request.contextPath }/workerslist.action">员工个人信息管理</a></li>
				<li><a href="${pageContext.request.contextPath }/jobslist.action">公司职位信息管理</a></li>
				<li class="active"><a href="${pageContext.request.contextPath }/JobsWorkerslist.action">人事管理</a></li>
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
							<input placeholder="输入部门关键字或工号" class="input-medium search-query" type="text" id="text" value="${jdept!=null?jdept:wno}" /> <a class="btn btn-primary" onclick="findByJdept(this)">按部门查找</a>
							<a class="btn btn-primary" onclick="findByWno(this)">按工号查找</a>
							<a class="btn btn-primary" onclick="findAll(this)">全部查询</a>
						</form>
					</div>
					<form id="form1" name="form1">
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
									<c:if test="${flag3==0}">
										<button type="button" class="btn btn-warning" data-toggle="modal" data-target="#addUserModal">添加</button>
									</c:if>
								</th>
							</tr>
							</thead>
							<tbody>
							<c:forEach items="${JobsWorkersList.datas}" var="worker">
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

	<!-- 分页文字信息 -->
	<div class="row">
		<div class="col-md-12 text-center">
			当前第${JobsWorkersList.currentPage}页，总共${JobsWorkersList.totalPage}页，总共${JobsWorkersList.totalCount}条记录
		</div>
	</div>
	<!-- 分页条信息 -->
	<div class="row">
		<div class="col-md-12 text-center">
			<nav aria-label="Page navigation">
				<c:if test="${flag3==0}">
					<ul class="pagination">
						<li><a href="${pageContext.request.contextPath }/JobsWorkerslist.action?currentPage=1" >首页</a></li>
						<c:if test="${JobsWorkersList.currentPage!=1}">
							<li>
								<a href="${pageContext.request.contextPath }/JobsWorkerslist.action?currentPage=${JobsWorkersList.currentPage-1}" aria-label="Previous">
									<span aria-hidden="true">&laquo;</span>
								</a>
							</li>
						</c:if>

						<c:forEach begin="1" end="${JobsWorkersList.totalPage}" var="pageNum">
							<li <c:if test="${pageNum==JobsWorkersList.currentPage}">class="active"</c:if> ><a href="${pageContext.request.contextPath }/JobsWorkerslist.action?currentPage=${pageNum}">${pageNum}</a></li>
						</c:forEach>
						<c:if test="${JobsWorkersList.currentPage!=JobsWorkersList.totalPage}">
							<li>
								<a href="${pageContext.request.contextPath }/JobsWorkerslist.action?currentPage=${JobsWorkersList.currentPage+1}" aria-label="Next">
									<span aria-hidden="true">&raquo;</span>
								</a>
							</li>
						</c:if>
						<li><a href="${pageContext.request.contextPath }/JobsWorkerslist.action?currentPage=${JobsWorkersList.totalPage}">尾页</a></li>
					</ul>
				</c:if>
				<c:if test="${flag3==1}">
					<ul class="pagination">
						<li><a href="${pageContext.request.contextPath }/JobsWorkerslistByWno.action?currentPage=1&wno=${wno}" >首页</a></li>
						<c:if test="${JobsWorkersList.currentPage!=1}">
							<li>
								<a href="${pageContext.request.contextPath }/JobsWorkerslistByWno.action?currentPage=${JobsWorkersList.currentPage-1}&wno=${wno}" aria-label="Previous">
									<span aria-hidden="true">&laquo;</span>
								</a>
							</li>
						</c:if>
						<c:forEach begin="1" end="${JobsWorkersList.totalPage}" var="pageNum">
							<li <c:if test="${pageNum==JobsWorkersList.currentPage}">class="active"</c:if> ><a href="${pageContext.request.contextPath }/JobsWorkerslistByWno.action?currentPage=${pageNum}&wno=${wno}">${pageNum}</a></li>
						</c:forEach>
						<c:if test="${JobsWorkersList.currentPage!=JobsWorkersList.totalPage}">
							<li>
								<a href="${pageContext.request.contextPath }/JobsWorkerslistByWno.action?currentPage=${JobsWorkersList.currentPage+1}&wno=${wno}" aria-label="Next">
									<span aria-hidden="true">&raquo;</span>
								</a>
							</li>
						</c:if>
						<li><a href="${pageContext.request.contextPath }/JobsWorkerslistByWno.action?currentPage=${JobsWorkersList.totalPage}&wno=${wno}">尾页</a></li>
					</ul>
				</c:if>
				<c:if test="${flag3==2}">
					<ul class="pagination">
						<li><a href="${pageContext.request.contextPath }/JobsWorkerslistByJdept.action?currentPage=1&jdept=${jdept}" >首页</a></li>
						<c:if test="${JobsWorkersList.currentPage!=1}">
							<li>
								<a href="${pageContext.request.contextPath }/JobsWorkerslistByJdept.action?currentPage=${JobsWorkersList.currentPage-1}&jdept=${jdept}" aria-label="Previous">
									<span aria-hidden="true">&laquo;</span>
								</a>
							</li>
						</c:if>
						<c:forEach begin="1" end="${JobsWorkersList.totalPage}" var="pageNum">
							<li <c:if test="${pageNum==JobsWorkersList.currentPage}">class="active"</c:if> ><a href="${pageContext.request.contextPath }/JobsWorkerslistByJdept.action?currentPage=${pageNum}">${pageNum}</a></li>
						</c:forEach>
						<c:if test="${JobsWorkersList.currentPage!=JobsWorkersList.totalPage}">
							<li>
								<a href="${pageContext.request.contextPath }/JobsWorkerslistByJdept.action?currentPage=${JobsWorkersList.currentPage+1}&jdept=${jdept}" aria-label="Next">
									<span aria-hidden="true">&raquo;</span>
								</a>
							</li>
						</c:if>
						<li><a href="${pageContext.request.contextPath }/JobsWorkerslistByJdept.action?currentPage=${JobsWorkersList.totalPage}&jdept=${jdept}">尾页</a></li>
					</ul>
				</c:if>
			</nav>
		</div>
	</div>
	<!--添加业务模态框-->
	<form class="form-horizontal" role="form" id="form_data" style="margin: 20px;">
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
						<button type="button" class="btn btn-primary" onclick="addSubmit()">
							提交
						</button><span id="tip"> </span>
					</div>
				</div><!-- /.modal-content -->
			</div><!-- /.modal -->
		</div>
	</form>

	<!--修改业务模态框-->
	<form class="form-horizontal" role="form" id="form_data2" style="margin: 20px;">
		<div class="modal fade" id="updateUserModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel2" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
							&times;
						</button>
						<h4 class="modal-title" id="myModalLabel2">
							修改员工职位(人事调动)
						</h4>
					</div>
					<div class="modal-body">
						<form class="form-horizontal" role="form">
							<div class="form-group">
								<label for="wno2" class="col-sm-3 control-label">员工</label>
								<div class="col-sm-9">
									<select class="form-control" name="wno" id="wno2">
										<c:forEach items="${Workers2}" var="worker">
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

							<div class="form-group">
								<div class="col-sm-9">
									<input type="hidden" class="form-control" id="id" name="id" value="">
								</div>
							</div>


						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭
						</button>
						<button type="submit" class="btn btn-primary" onclick="changeSubmit()">
							提交
						</button><span id="tip2"></span>
					</div>
				</div><!-- /.modal-content -->
			</div><!-- /.modal -->
		</div>
	</form>

</div>
</body>
</html>