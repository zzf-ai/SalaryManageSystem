<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>员工个人信息列表</title>

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

		function exam() {
			// JSON字符串转换JSON对象
			var data = JSON.parse("{\"wno\":\"\"}");
			data.wno=$('#wno').val();
			$.ajax({
				type:"post",
				url:"${pageContext.request.contextPath}/ajaxCheck3.action",
				data:"json",
				data:{'param':JSON.stringify(data)},
				success:function(data){
					console.log(data.toString());
					if(data.toString()=='true'){
						document.getElementById("error1").style.display="none";
						addSubmit();
					}
					else{
						document.getElementById("error1").style.display="block";
					}
				}
			});
		}

		function exam1() {
			var data = JSON.parse("{\"wno\":\"\"}");
			data.wno=$('#wno2').val();

			var d=$('#wno2').val();
			var t=$('#wno3').val();
			$.ajax({
				type:"post",
				url:"${pageContext.request.contextPath }/ajaxCheck3.action",
				data:"json",
				data:{'param':JSON.stringify(data)},
				success:function(data){
					console.log(data.toString());
					if(data.toString()=='true'){
						document.getElementById("error2").style.display="none";
						changeSubmit();
					}
					else{
						if(d==t) {
							document.getElementById("error2").style.display = "none";
							changeSubmit();
						}
						else
							document.getElementById("error2").style.display="block";
					}
				}
			});
		}

		function frmSubmit() {
			var s=document.getElementsByName("wnoArray");
			var c='';
			for(var i=0;i<s.length;i++){
				if(s[i].checked==true){
					c=c+"1";
				}
			}
			if(c.length==0){
				alert("请选择再删除！")
			}
			else{
				delubmit();
			}
		}
		function delubmit() {
			// var s=document.getElementsByName("wnoArray");
			var checkID=[];
			$("input[name='wnoArray']:checked").each(function(i){
				checkID[i] = $(this).val();
			});
			//返回字符串，用text
			$.ajax({
				type:'post',
				url:'${pageContext.request.contextPath }/workersdelete.action',
				data:{"wnoArray":checkID},
				traditional: true,
				dataType:'text',
				success:function(data){
					console.log(data)
					if(data=='true'){
						ShowSuccess("删除成功")
						window.location.reload();
					}else {
						ShowFailure("删除失败")
						window.location.reload();
					}
				}
			});
		}

		function changeSubmit() {
			/*var data=JSON.stringify($("#form_data2").serialize());
			alert(data)*/
			$.ajax({
				type:'post',
				url:'${pageContext.request.contextPath }/workersupdate.action',
				data:$("#form_data2").serialize(),
				success:function(data){
					console.log(data)
					if(data=='true'){
						ShowSuccess("修改成功")
						window.location.reload();
					}else {
						ShowFailure("修改失败")
						window.location.reload();
					}
				}
			})
		}

		function addSubmit() {
			var currntpage='${workerList.currentPage}';
			var datasize = '${workerList.datas.size()}';
			var pageSize='${workerList.pageSize}';
			var totalpage='${workerList.totalPage}';

			if (datasize == pageSize && currntpage == totalpage) {
				currntpage = parseInt(currntpage) + 1;
			} else if (datasize == pageSize && currntpage != totalpage) {
				currntpage = parseInt(totalpage) + 1;
			}
			$.ajax({
				type:'post',
				url:'${pageContext.request.contextPath }/workersinsert.action',
				data:$("#form_data").serialize(),
				success:function(data){
					console.log(data);
					if(data=='true'){
						ShowSuccess("添加成功");
						//window.location.reload();

						window.location.href='${pageContext.request.contextPath }/workerslist.action?currentPage='+currntpage;
					}else {
						ShowFailure("添加失败")
						window.location.reload();
					}
				}
			})
		}

		//按姓名关键字查找
		function findByWname(a) {
			var s='wname='+document.getElementById('text').value;
			a.href='${pageContext.request.contextPath }/workerslistbyname.action?'+s;
		}

		//全部查找
		function findAll(a) {
			a.href='${pageContext.request.contextPath }/workerslist.action?';
		}
		//按工号查找
		function findByWno(a) {
			var s='wno='+document.getElementById('text').value;
			a.href='${pageContext.request.contextPath }/workerslistbyno.action?'+s;
		}
		//修改模态框
		$(function () { $('#updateUserModal').on('hide.bs.modal', function () {
			// 关闭时清空edit状态为add
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
				var wid = a.data('wid'), wno = a.data('wno'), wname = a.data('wname'), wsex = a.data('wsex'),wnative = a.data('wnative'),wphone = a.data('wphone');
				var modal = $(this)
				modal.find('#wid2').val(wid);
				modal.find('#wno2').val(wno);
				modal.find('#wno3').val(wno);
				modal.find('#wname2').val(wname);
				if (wsex == '女') {
					document.getElementById('women2').checked = true;
				} else {
					document.getElementById('man2').checked = true;
				}
				modal.find('#wnative2').val(wnative);
				modal.find('#wphone2').val(wphone);
			});
		});
</script>
</head>
<body id="show-refectory-html">
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<a class="navbar-brand" href="#">简易版人事&工资信息管理系统</a>
			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li><a href="${pageContext.request.contextPath}/toLeader.action">主页 <span class="sr-only">(current)</span></a></li>
					<li class="active"><a href="${pageContext.request.contextPath }/workerslist.action">员工个人信息管理</a></li>
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
				</ul>
			</div><!-- /.navbar-collapse -->
		</div><!-- /.container-fluid -->
	</nav>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12">
				<h3>
					员工个人信息列表
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
								<input placeholder="输入姓名关键字或工号" class="input-medium search-query" type="text" id="text" value="${wname!=null?wname:wno}" /> <a class="btn btn-primary" onclick="findByWname(this)">按关键字查找</a>
								<a class="btn btn-primary" onclick="findByWno(this)">按工号查找</a>
								<a class="btn btn-primary" onclick="findAll(this)">全部查找</a>
							</form>
						</div>
						<form method="post" id="form1" name="form1">
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
										性别
									</th>
									<th>
										籍贯
									</th>
									<th>
										手机号
									</th>
									<th>
										操作 &nbsp;| &nbsp; <a class="btn btn-info" href="#" onclick="javascript:frmSubmit();">多选删除</a>&nbsp;&nbsp;
										<c:if test="${flag1==0}">
											<button type="button" class="btn btn-warning" data-toggle="modal" data-target="#addUserModal">添加</button>
										</c:if>
									</th>
								</tr>
								</thead>
								<tbody>
								<c:forEach items="${workerList.datas}" var="worker">
									<tr>
										<td><input type="checkbox" name="wnoArray"
												   value="${worker.wno }"></td>
										<td>${worker.wno }</td>
										<td>${worker.wname }</td>
										<td>${worker.wsex }</td>
										<td>${worker.wnative }</td>
										<td>${worker.wphone }</td>
										<td>
											<button type="button" class="btn btn-success" data-toggle="modal" data-target="#updateUserModal"
													data-wid="${worker.wid }" data-wno="${worker.wno }" data-wname="${worker.wname}"
											data-wsex="${worker.wsex }" data-wnative="${worker.wnative }" data-wphone="${worker.wphone }">
											修改
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
				当前第${workerList.currentPage}页，总共${workerList.totalPage}页，总共${workerList.totalCount}条记录
			</div>
		</div>
		<!-- 分页条信息 -->
		<div class="row">
			<div class="col-md-12 text-center">
				<nav aria-label="Page navigation">
					<c:if test="${flag1==0}">
						<ul class="pagination">
							<li><a href="${pageContext.request.contextPath }/workerslist.action?currentPage=1" >首页</a></li>
							<c:if test="${workerList.currentPage!=1}">
								<li>
									<a href="${pageContext.request.contextPath }/workerslist.action?currentPage=${workerList.currentPage-1}" aria-label="Previous">
										<span aria-hidden="true">&laquo;</span>
									</a>
								</li>
							</c:if>

							<c:forEach begin="1" end="${workerList.totalPage}" var="pageNum">
								<li <c:if test="${pageNum==workerList.currentPage}">class="active"</c:if> ><a href="${pageContext.request.contextPath }/workerslist.action?currentPage=${pageNum}">${pageNum}</a></li>
							</c:forEach>
							<c:if test="${workerList.currentPage!=workerList.totalPage}">
								<li>
									<a href="${pageContext.request.contextPath }/workerslist.action?currentPage=${workerList.currentPage+1}" aria-label="Next">
										<span aria-hidden="true">&raquo;</span>
									</a>
								</li>
							</c:if>
							<li><a href="${pageContext.request.contextPath }/workerslist.action?currentPage=${workerList.totalPage}">尾页</a></li>
						</ul>
					</c:if>
					<c:if test="${flag1==1}">
						<ul class="pagination">
							<li><a href="${pageContext.request.contextPath }/workerslistbyname.action?currentPage=1&wname=${wname}" >首页</a></li>
							<c:if test="${workerList.currentPage!=1}">
								<li>
									<a href="${pageContext.request.contextPath }/workerslistbyname.action?currentPage=${workerList.currentPage-1}&wname=${wname}" aria-label="Previous">
										<span aria-hidden="true">&laquo;</span>
									</a>
								</li>
							</c:if>
							<c:forEach begin="1" end="${workerList.totalPage}" var="pageNum">
								<li <c:if test="${pageNum==workerList.currentPage}">class="active"</c:if> ><a href="${pageContext.request.contextPath }/workerslistbyname.action?currentPage=${pageNum}&wname=${wname}">${pageNum}</a></li>
							</c:forEach>
							<c:if test="${workerList.currentPage!=workerList.totalPage}">
								<li>
									<a href="${pageContext.request.contextPath }/workerslistbyname.action?currentPage=${workerList.currentPage+1}&wname=${wname}" aria-label="Next">
										<span aria-hidden="true">&raquo;</span>
									</a>
								</li>
							</c:if>
							<li><a href="${pageContext.request.contextPath }/workerslistbyname.action?currentPage=${workerList.totalPage}&wname=${wname}">尾页</a></li>
						</ul>
					</c:if>
					<c:if test="${flag1==2}">
						<ul class="pagination">
							<li><a href="${pageContext.request.contextPath }/workerslistbyno.action?currentPage=1&wname=${wno}" >首页</a></li>
							<c:if test="${workerList.currentPage!=1}">
								<li>
									<a href="${pageContext.request.contextPath }/workerslistbyno.action?currentPage=${workerList.currentPage-1}&wno=${wno}" aria-label="Previous">
										<span aria-hidden="true">&laquo;</span>
									</a>
								</li>
							</c:if>
							<c:forEach begin="1" end="${workerList.totalPage}" var="pageNum">
								<li <c:if test="${pageNum==workerList.currentPage}">class="active"</c:if> ><a href="${pageContext.request.contextPath }/workerslist.action?currentPage=${pageNum}">${pageNum}</a></li>
							</c:forEach>
							<c:if test="${workerList.currentPage!=workerList.totalPage}">
								<li>
									<a href="${pageContext.request.contextPath }/workerslistbyno.action?currentPage=${workerList.currentPage+1}&wno=${wno}" aria-label="Next">
										<span aria-hidden="true">&raquo;</span>
									</a>
								</li>
							</c:if>
							<li><a href="${pageContext.request.contextPath }/workerslistbyname.action?currentPage=${workerList.totalPage}&wno=${wno}">尾页</a></li>
						</ul>
					</c:if>
				</nav>
			</div>
		</div>




		<!--添加业务模态框-->
		<form method="post" class="form-horizontal" role="form" name="form_data" id="form_data" style="margin: 20px;">
			<div class="modal fade" id="addUserModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
								&times;
							</button>
							<h4 class="modal-title" id="myModalLabel">
								员工个人信息
							</h4>
						</div>
						<div class="modal-body">
							<form class="form-horizontal" role="form">
								<div class="form-group">
									<label for="wno" class="col-sm-3 control-label">工号</label>
									<div class="col-sm-6">
										<input type="text" class="form-control" id="wno" name="wno" value=""
											   placeholder="工号" maxlength="9">
									</div>
									<div class="col-md-3">
										<span style="display:none; color: red" id="msg3"></span>
										<span style="display:none; color: red" id="error1">工号已存在</span>
									</div>
								</div>

								<div class="form-group">
									<label for="wname" class="col-sm-3 control-label">姓名</label>
									<div class="col-sm-6">
										<input type="text" class="form-control" name="wname" value="" id="wname"
											   placeholder="姓名">
									</div>
								</div>

								<div class="form-group">
									<label for="wsex" class="col-sm-3 control-label">性别</label>
									<div class="col-sm-6">
										<input type="radio" name="wsex" id="man" value="男" checked>男
										&nbsp;&nbsp;
										<input type="radio" name="wsex" id="woman" value="女">女
									</div>
								</div>
								<div class="form-group">
									<label for="wnative" class="col-sm-3 control-label">籍贯</label>
									<div class="col-sm-6">
										<input type="text" class="form-control" name="wnative" value="" id="wnative"
											   placeholder="籍贯">
									</div>
								</div>

								<div class="form-group">
									<label for="wphone" class="col-sm-3 control-label">手机号</label>
									<div class="col-sm-6">
                                        <input type="text"  class="form-control"  name="wphone" value="" id="wphone"
										   placeholder="手机号">
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
		<form method="post" class="form-horizontal" role="form" id="form_data2" name="form_data2" style="margin: 20px;">
			<div class="modal fade" id="updateUserModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel2" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
								&times;
							</button>
							<h4 class="modal-title" id="myModalLabel2">
								修改员工个人信息
							</h4>
						</div>
						<div class="modal-body">
							<form class="form-horizontal" role="form">
								<div class="form-group">
									<div class="col-sm-6">
										<input type="hidden" class="form-control" id="wid2" name="wid" value="">
									</div>
								</div>

								<div class="form-group">
									<label for="wno2" class="col-sm-3 control-label">工号</label>
									<div class="col-sm-6">
										<input type="text" class="form-control" id="wno2" name="wno" value="${worker.wno }"
											   placeholder="工号" maxlength="9">
									</div>
									<div class="col-md-3">
										<span style="display:none; color: red" id="msg4"></span>
										<span style="display:none; color: red" id="error2">工号已存在</span>
									</div>
								</div>

								<div class="form-group">
									<div class="col-sm-6">
										<input type="hidden" class="form-control" id="wno3" value="">
									</div>
								</div>

								<div class="form-group">
									<label for="wname2" class="col-sm-3 control-label">姓名</label>
									<div class="col-sm-6">
										<input type="text" class="form-control" name="wname" value="${worker.wname }" id="wname2"
											   placeholder="姓名">
									</div>
								</div>

								<div class="form-group">
									<label for="wsex2" class="col-sm-3 control-label">性别</label>
									<div class="col-sm-6">
										<input type="radio" name="wsex" id="man2" value="男" <c:if test="${worker.wsex eq '男'}"> checked="checked"</c:if>>男
										&nbsp;&nbsp;
										<input type="radio" name="wsex" id="women2" value="女" <c:if test="${worker.wsex eq '女'}"> checked="checked"</c:if>>女
									</div>
								</div>
								<div class="form-group">
									<label for="wnative2" class="col-sm-3 control-label">籍贯</label>
									<div class="col-sm-6">
										<input type="text" class="form-control" name="wnative" value="${worker.wnative }" id="wnative2"
											   placeholder="籍贯">
									</div>
								</div>

								<div class="form-group">
									<label for="wphone2" class="col-sm-3 control-label">手机号</label>
									<div class="col-sm-6">
										<input type="text"  class="form-control"  name="wphone" value="${worker.wphone }" id="wphone2"
											   placeholder="手机号">
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