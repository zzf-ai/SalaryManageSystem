<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>员工工资信息列表</title>
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
		$(function () {
			$('#datetimepicker2').datetimepicker({
				format: 'YYYY-MM',
				locale: moment.locale('zh-cn'),
			});
		});
		function gsubmitAll() {
			//返回字符串，用text
			$.ajax({
				type:'post',
				url:'${pageContext.request.contextPath }/WSalarysGrantAll.action',
				traditional: true,
				success:function(data){
					console.log(data)
					if(data=='true'){
						alert("发放成功")
						window.location.reload();
					}else {
						alert("发放失败")
						window.location.reload();
					}
				}
			});
		}
		function gsubmit() {
			// var s=document.getElementsByName("wnoArray");
			var checkID=[];
			$("input[name='wsidArray']:checked").each(function(i){
				checkID[i] = $(this).val();
			});
			//返回字符串，用text
			$.ajax({
				type:'post',
				url:'${pageContext.request.contextPath }/WSalarysGrant.action',
				data:{"wsidArray":checkID},
				traditional: true,
				dataType:'text',
				success:function(data){
					console.log(data)
					if(data=='true'){
						alert("发放成功")
						window.location.reload();
					}else {
						alert("发放失败")
						window.location.reload();
					}
				}
			});
		}
		function frmSubmit() {
			var s=document.getElementsByName("wsidArray");
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
		function grantSubmit() {
			var s=document.getElementsByName("wsidArray");
			var c='';
			for(var i=0;i<s.length;i++){
				if(s[i].checked==true){
					c+=s;
				}
			}
			if(c.length==0){
				alert("请选择！")
			}
			else{
				gsubmit();
			}
		}
		//查找全部
		function findAll(a) {
			a.href='WSalaryslist.action';
		}
		//按部门查找
		function findByJdept(a) {
			var s='jdept='+$('#text').val();
			a.href='WSalaryslistbyJdept.action?'+s;
		}
		function nextPage(a) {
			var s=$.cookie('input_value');
			a.href="${pageContext.request.contextPath }/WSalaryslistbySettleDate.action?currentPage=${WSalaryList.currentPage+1}&settledate="+s;
		}

		function prePage(a) {
			var s=$.cookie('input_value');
			a.href="${pageContext.request.contextPath }/WSalaryslistbySettleDate.action?currentPage=${WSalaryList.currentPage-1}&settledate="+s;
		}

		function firstPage(a) {
			var s=$.cookie('input_value');
			a.href="${pageContext.request.contextPath }/WSalaryslistbySettleDate.action?currentPage=1&settledate="+s;
		}

		function endPage(a) {
			var s=$.cookie('input_value');
			a.href="${pageContext.request.contextPath }/WSalaryslistbySettleDate.action?currentPage=${WSalaryList.totalPage}&settledate="+s;
		}
		function onePage(a,t) {
			var s=$.cookie('input_value');
			a.href="${pageContext.request.contextPath }/WSalaryslistbySettleDate.action?currentPage="+t+"&settledate="+s;
		}

		//按月份查询
		function findBySettleDate(a) {
			var s='settledate='+$('#text2').val();
			// $('#text3').attr('value',$('#text2').val());
			$.cookie('input_value',$('#text2').val());
			a.href='WSalaryslistbySettleDate.action?'+s;
		}
		//按工号查询
		function findByWno(a) {
			var s='wno='+$('#text').val();
			a.href='WSalaryslistbyWno.action?'+s;
		}
		//模态框
		$(function () { $('#addUserModal').on('hide.bs.modal', function () {
			// 关闭时清空edit状态为add
			$("#act").val("add");
		})
		});

		//导出excel
		/*function toexcel() {
			window.location.href="${pageContext.request.contextPath }/toexcel";
			$("#addUserModal").modal('hide');
		}*/
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

		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			<c:if test="${USER_SESSION.authority=='财务'}">
				<ul class="nav navbar-nav">
					<li><a href="${pageContext.request.contextPath}/toCustomer.action">主页 <span class="sr-only">(current)</span></a></li>
					<li><a href="${pageContext.request.contextPath }/jobslist.action">公司职位信息查询</a></li>
					<li><a href="${pageContext.request.contextPath }/JobsWorkerslist2.action">员工工资结算</a></li>
					<li  class="active"><a href="${pageContext.request.contextPath }/WSalaryslist.action">员工工资信息管理</a></li>
					<li><a href="${pageContext.request.contextPath }/MySalaryslist.action">我的工资信息</a></li>
				</ul>
			</c:if>
			<c:if test="${USER_SESSION.authority=='公司高层'}">
			<ul class="nav navbar-nav">
				<li><a href="${pageContext.request.contextPath}/toLeader.action">主页 <span class="sr-only">(current)</span></a></li>
				<li><a href="${pageContext.request.contextPath }/workerslist.action">员工个人信息管理</a></li>
				<li><a href="${pageContext.request.contextPath }/jobslist.action">公司职位信息管理</a></li>
				<li><a href="${pageContext.request.contextPath }/JobsWorkerslist.action">人事管理</a></li>
				<li class="active"><a href="${pageContext.request.contextPath }/WSalaryslist.action">员工工资信息查询</a></li>
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
		</div><!-- /.navbar-collapse -->
	</div><!-- /.container-fluid -->
</nav>
<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">
			<h3>
				员工工资信息列表
			</h3>
		</div>
		<div class="span12">
			<div class="col-md-10">
			</div>
			<c:if test="${USER_SESSION.authority=='财务'}">
				<div class="col-md-2">
					<button type="button" class="btn btn-success" onclick="gsubmitAll()">一键发放</button>
					<button id="cal" type="button" class="btn btn-success" data-toggle="modal" data-target="#addUserModal">导出excel表格</button>
				</div>
			</c:if>
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
					<div class="col-md-2"><a class="btn btn-primary" onclick="findBySettleDate(this)">按工资月份查找</a></div>
					<div class="col-md-4">
						<form class="form-search">
							<input placeholder="输入部门关键字或工号" class="input-medium search-query" type="text" id="text" value="${jdept!=null?jdept:wno}" />
							<a class="btn btn-primary" onclick="findByWno(this)">按工号查找</a> <a class="btn btn-primary" onclick="findByJdept(this)">按部门查询</a> <a class="btn btn-primary" onclick="findAll(this)">全部查询</a>
						</form>

					</div>
					<form action="${pageContext.request.contextPath }/WSalarysdelete.action" method="post" id="form1" name="form1">
						<table class="table table-hover" id="#tab">
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
									是否已发放
								</th>
								<th>
									发放日期
								</th>
								<c:if test="${USER_SESSION.authority=='财务'}">
									<th>
										操作 &nbsp;| &nbsp; <a class="btn btn-success" href="#" onclick="grantSubmit();">多选发放</a>&nbsp;&nbsp;
										<a class="btn btn-info" href="#" onclick="javascript:frmSubmit();">多选删除</a>
									</th>
								</c:if>
							</tr>
							</thead>
							<tbody>
							<c:forEach items="${WSalaryList.datas}" var="wsalary">
								<tr>
									<td><input type="checkbox" name="wsidArray"
											   value="${wsalary.wsid}"></td>
									<td>${wsalary.wno }</td>
									<td>${wsalary.wname }</td>
									<td>&nbsp;&nbsp;${wsalary.jno }</td>
									<td>${wsalary.jname }</td>
									<td>${wsalary.jdept }</td>
									<td>${wsalary.jsalary }</td>
									<td>${wsalary.jbonus }</td>
									<td>${wsalary.total }</td>
									<td>${wsalary.settledate }</td>
									<td>${wsalary.isgrant}</td>
									<td><fmt:formatDate value="${wsalary.grantdate}" pattern="yyyy-MM-dd"/></td>
									<c:if test="${USER_SESSION.authority=='财务'}">
										<td>
											<c:if test="${wsalary.isgrant=='否'}">
												<a class="btn btn-success" onclick="javascript:grantSubmit();">发放</a>
											</c:if>
											<c:if test="${wsalary.isgrant=='是'}">
												<a class="btn btn-success" onclick="javascript:grantSubmit();" disabled="disabled">发放</a>
											</c:if>
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
				<c:if test="${flag4==0}">
					<ul class="pagination">
						<li><a href="${pageContext.request.contextPath }/WSalaryslist.action?currentPage=1" >首页</a></li>
						<c:if test="${WSalaryList.currentPage!=1}">
							<li>
								<a href="${pageContext.request.contextPath }/WSalaryslist.action?currentPage=${WSalaryList.currentPage-1}" aria-label="Previous">
									<span aria-hidden="true">&laquo;</span>
								</a>
							</li>
						</c:if>

						<c:forEach begin="1" end="${WSalaryList.totalPage}" var="pageNum">
							<li <c:if test="${pageNum==WSalaryList.currentPage}">class="active"</c:if> ><a href="${pageContext.request.contextPath }/WSalaryslist.action?currentPage=${pageNum}">${pageNum}</a></li>
						</c:forEach>
						<c:if test="${WSalaryList.currentPage!=WSalaryList.totalPage}">
							<li>
								<a href="${pageContext.request.contextPath }/WSalaryslist.action?currentPage=${WSalaryList.currentPage+1}" aria-label="Next">
									<span aria-hidden="true">&raquo;</span>
								</a>
							</li>
						</c:if>
						<li><a href="${pageContext.request.contextPath }/WSalaryslist.action?currentPage=${WSalaryList.totalPage}">尾页</a></li>
					</ul>
				</c:if>
				<c:if test="${flag4==1}">
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
				<c:if test="${flag4==2}">
					<ul class="pagination">
						<li><a href="${pageContext.request.contextPath }/WSalaryslistbyWno.action?currentPage=1&wno=${wno}" >首页</a></li>
						<c:if test="${WSalaryList.currentPage!=1}">
							<li>
								<a href="${pageContext.request.contextPath }/WSalaryslistbyWno.action?currentPage=${WSalaryList.currentPage-1}&wno=${wno}" aria-label="Previous">
									<span aria-hidden="true">&laquo;</span>
								</a>
							</li>
						</c:if>
						<c:forEach begin="1" end="${WSalaryList.totalPage}" var="pageNum">
							<li <c:if test="${pageNum==WSalaryList.currentPage}">class="active"</c:if> ><a href="${pageContext.request.contextPath }/WSalaryslistbyWno.action?currentPage=${pageNum}&wno=${wno}">${pageNum}</a></li>
						</c:forEach>
						<c:if test="${WSalaryList.currentPage!=WSalaryList.totalPage}">
							<li>
								<a href="${pageContext.request.contextPath }/WSalaryslistbyWno.action?currentPage=${WSalaryList.currentPage+1}&wno=${wno}" aria-label="Next">
									<span aria-hidden="true">&raquo;</span>
								</a>
							</li>
						</c:if>
						<li><a href="${pageContext.request.contextPath }/WSalaryslistbyWno.action?currentPage=${WSalaryList.totalPage}&wno=${wno}">尾页</a></li>
					</ul>
				</c:if>
				<c:if test="${flag4==3}">
					<ul class="pagination">
						<li><a href="${pageContext.request.contextPath }/WSalaryslistbyJdept.action?currentPage=1&jdept=${jdept}" >首页</a></li>
						<c:if test="${WSalaryList.currentPage!=1}">
							<li>
								<a href="${pageContext.request.contextPath }/WSalaryslistbyJdept.action?currentPage=${WSalaryList.currentPage-1}&jdept=${jdept}" aria-label="Previous">
									<span aria-hidden="true">&laquo;</span>
								</a>
							</li>
						</c:if>
						<c:forEach begin="1" end="${WSalaryList.totalPage}" var="pageNum">
							<li <c:if test="${pageNum==WSalaryList.currentPage}">class="active"</c:if> ><a href="${pageContext.request.contextPath }/WSalaryslistbyJdept.action?currentPage=${pageNum}&jdept=${jdept}">${pageNum}</a></li>
						</c:forEach>
						<c:if test="${WSalaryList.currentPage!=WSalaryList.totalPage}">
							<li>
								<a href="${pageContext.request.contextPath }/WSalaryslistbyJdept.action?currentPage=${WSalaryList.currentPage+1}&jdept=${jdept}" aria-label="Next">
									<span aria-hidden="true">&raquo;</span>
								</a>
							</li>
						</c:if>
						<li><a href="${pageContext.request.contextPath }/WSalaryslistbyJdept.action?currentPage=${WSalaryList.totalPage}&jdept=${jdept}">尾页</a></li>
					</ul>
				</c:if>
			</nav>
		</div>
	</div>
	<form method="post" action="${pageContext.request.contextPath }/toexcel" class="form-horizontal" role="form" id="form_data" name="form_data" style="margin: 20px;">
		<div class="modal fade" id="addUserModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
							&times;
						</button>
						<h4 class="modal-title" id="myModalLabel">
							选择要打印的月份，默认全部月份
						</h4>
					</div>
					<div class="modal-body">
						<form class="form-horizontal" role="form">
							<div class='input-group date col-md-4' id='datetimepicker2'>
								<input name="settledate" id="settledate" type='text' class="form-control" />
								<span class="input-group-addon">
									<span class="glyphicon glyphicon-calendar"></span>
								</span>
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
</div>
</body>
</html>