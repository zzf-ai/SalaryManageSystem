<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>工资结算</title>
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <link href="https://cdn.bootcss.com/bootstrap-datetimepicker/4.17.47/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
    <script src="https://cdn.bootcss.com/moment.js/2.24.0/moment-with-locales.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap-datetimepicker/4.17.47/js/bootstrap-datetimepicker.min.js"></script>
    <script type="text/javascript">
        $(function () {
            $('#datetimepicker1').datetimepicker({
                format: 'YYYY-MM',
                locale: moment.locale('zh-cn'),
                // defaultDate: "2020-02-08",
            });
        });
        //结算服务
        function f(){
            $('table tr').each(function(){
                var a = $(this).find('td:eq(5)').text();
                var b = $(this).find('td:eq(6)').text();
                var c=parseFloat(a)+parseFloat(b);
                $(this).children('td').eq(7).html(parseFloat(c));
                $("#cal").attr("disabled",true);
            });
        }
        //入库服务
        function p(){
            var obj = JSON.parse("{\"data\":[]}");
            var a=new Array();
            var settlement=$('#settledate').val();
            $('table tr').each(function(i){
                var wsalary=new Object();
                var wno="wno";
                var wname="wname";
                var jno="jno";
                var jname="jname";
                var jdept="jdept";
                var jsalary="jsalary";
                var jbonus="jbonus";
                var total="total";
                var settledate="settledate";

                wsalary[wno]=$(this).find('td:eq(0)').text();
                wsalary[wname]=$(this).find('td:eq(1)').text();
                wsalary[jno]=$(this).find('td:eq(2)').text();
                wsalary[jname]=$(this).find('td:eq(3)').text();
                wsalary[jdept]=$(this).find('td:eq(4)').text();
                wsalary[jsalary]=$(this).find('td:eq(5)').text();
                wsalary[jbonus]=$(this).find('td:eq(6)').text();
                wsalary[total]=$(this).find('td:eq(7)').text();
                wsalary[settledate]=settlement;
                if(wsalary[wno]!=''){
                    a[i]=wsalary;
                    console.log(wsalary);
                }
            });
            console.log(a);
            obj.data=a;
            console.log(JSON.stringify(obj));
            $.ajax({
                type:"post",
                url:"${pageContext.request.contextPath}/WSalarysinsert.action",
                /*contentType : "application/json;charset=UTF-8",*/
                dataType:"json",
                //cache:true,
                data:{'param':JSON.stringify(obj)},
                success:function (data) {
                    if (data.toString()=='true')
                    {
                        alert("入库成功");
                        $("#save").attr("disabled",true);

                    }
                    else{
                        alert("入库失败");
                    }
                }
            })
        };

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
            <ul class="nav navbar-nav">
                <li><a href="${pageContext.request.contextPath}/toCustomer.action">主页 <span class="sr-only">(current)</span></a></li>
                <li><a href="${pageContext.request.contextPath }/jobslist.action">公司职位信息查询</a></li>
                <li class="active"><a href="${pageContext.request.contextPath }/JobsWorkerslist2.action">员工工资结算</a></li>
                <li><a href="${pageContext.request.contextPath }/WSalaryslist.action">员工工资信息管理</a></li>
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
                工资结算
            </h3>
        </div>
        <div class="span12">
            <label>选择日期：</label>

            <!--指定 date标记-->

            <div class='input-group date col-md-2' id='datetimepicker1'>

                <input id="settledate" type='text' class="form-control" />

                <span class="input-group-addon">

                    <span class="glyphicon glyphicon-calendar"></span>

                </span>
            </div>
        </div>
        <div class="span12">
            <div class="col-md-10">
            </div>
            <div class="col-md-2">
                <button id="cal" type="button" class="btn btn-warning" onclick="f()">结算</button>
                <button id="save" type="button" class="btn btn-warning" onclick="p();">入库</button>
            </div>
        </div>
    </div>
    <div class="row-fluid">
        <h5 class="page-header"></h5>
    </div>
    <div class="row-fluid">
        <div class="span12">
            <div class="row-fluid">
                <div class="span12">
                    <form action="${pageContext.request.contextPath }/WSalarysinsert.action" method="post" id="form1" name="form1">
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
                                    应发工资
                                </th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${JobsWorkersList}" var="worker">
                                <tr>
                                    <td>${worker.wno }</td>
                                    <td>${worker.wname }</td>

                                    <c:forEach items="${worker.jobslist}" var="job">
                                        <td>${job.jno}</td>
                                        <td>${job.jname}</td>
                                        <td>${job.jdept }</td>
                                        <td>${job.jsalary}</td>
                                        <td>${job.jbonus}</td>
                                        <td></td>
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
</div>
</body>
</html>