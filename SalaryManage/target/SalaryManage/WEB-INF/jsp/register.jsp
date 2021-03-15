<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>注册页面</title>
<meta http-equiv=Content-Type content="text/html; charset=utf-8">
	<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
	<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
	<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
	<meta content="MSHTML 6.00.2600.0" name=GENERATOR>
<script>
// 判断是登录账号和密码是否为空
function check(){
    var usercode = $("#usercode").val();
    var password = $("#password").val();
    var confirm = $("#confirm").val();
    var wno=$("#wno").val();
	if(wno==""){
		$("#msg3").text("请绑定工号！");
		$("#msg3").show().delay(1000).hide(0);
		return false;
	}
    else if(usercode==""){
		$("#msg").text("账号不能为空！");
		$("#msg").show().delay(1000).hide(0);
        return false;
    }  
    else if(usercode==null){
    	$("#msg1").text("密码不能为空！");
		$("#msg1").show().delay(1000).hide(0);
        return false;
    }
    else if(password==null||password==""){
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
//异步验证账号
function exam() {
	//alert($('#usercode').val())
	var data = JSON.parse("{\"usercode\":\"\"}");
	data.usercode=$('#usercode').val();

	$.ajax({
		type:"post",
		url:"${pageContext.request.contextPath }/ajaxCheck.action",
		data:"json",
		data:{'param':JSON.stringify(data)},
		success:function(data){
			console.log(data.toString());
			if(data.toString()=='true'){
				document.getElementById("error").style.display="none";
			}
			else{
				document.getElementById("error").style.display="block";
			}
		}
	});
}

//异步验证工号
function exam2() {
	// JSON字符串转换JSON对象
	var data = JSON.parse("{\"wno\":\"\"}");
	data.wno=$('#wno').val();

	$.ajax({
		type:"post",
		url:"${pageContext.request.contextPath }/ajaxCheck2.action",
		data:"json",
		data:{'param':JSON.stringify(data)},
		success:function(data){
			console.log(data.toString());
			if(data.toString()=='false'){
				document.getElementById("error1").style.display="none";
				document.getElementById("error3").style.display="none";
			}
			else if(data.toString()=='not'){
				document.getElementById("error1").style.display="none";
				document.getElementById("error3").style.display="block";
			}
			else{
				document.getElementById("error3").style.display="none";
				document.getElementById("error1").style.display="block";
			}
		}
	});
}
function toLogin() {
	window.location.href = "login.action";
}
</script>
</head>
<body>
<div id="signupbox" style="margin-top:50px" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
	<div class="panel panel-info">
		<div class="panel-heading">
			<div class="panel-title">注册</div>
			<div style="float:right; font-size: 85%; position: relative; top:-10px"><a id="signinlink" href="#" onclick="toLogin()">登录</a></div>
		</div>
		<div class="panel-body" >
			<form id="signupform" class="form-horizontal" role="form" method="post" action="${pageContext.request.contextPath}/Register.action" onsubmit="return check()">
				<div id="signupalert" style="display:none" class="alert alert-danger">
					<p>Error:</p>
					<span></span>
				</div>
				<div class="form-group">
					<label for="wno" class="col-md-3 control-label">绑定工号</label>
					<div class="col-md-6">
						<input type="text" class="form-control" id="wno" name="wno" placeholder="输入工号" onblur="exam2()">
					</div>
					<div class="col-md-3">
						<span style="display:none; color: red" id="msg3"></span>
						<span style="display:none; color: red" id="error1">工号已被绑定</span>
						<span style="display:none; color: red" id="error3">工号不存在</span>
					</div>
				</div>
				<div class="form-group">
					<label for="usercode" class="col-md-3 control-label">账号</label>
					<div class="col-md-6">
						<input type="text" class="form-control" id="usercode" name="usercode" placeholder="输入账号" onblur="exam()">
					</div>
					<div class="col-md-3">
						<span style="display:none; color: red" id="msg"></span>
						<span style="display:none; color: red" id="error">账号已存在</span>
					</div>
				</div>
				<div class="form-group">
					<label for="password" class="col-md-3 control-label">密码</label>
					<div class="col-md-6">
						<input type="password" class="form-control" id="password" name="password" placeholder="输入密码(不超过8位)" maxlength="8">
					</div>
					<div class="col-md-3">
						<span style="display:none; color: red" id="msg1"></span>
					</div>
				</div>
				<div class="form-group">
					<label for="confirm" class="col-md-3 control-label">确认密码</label>
					<div class="col-md-6">
						<input type="password" class="form-control" id="confirm" name="confirm" placeholder="确认密码" maxlength="8">
					</div>
					<div class="col-md-3">
						<span style="display:none; color: red" id="msg2"></span>
					</div>
				</div>
				<div class="form-group">
					<div class="col-md-6">
						<input type="hidden" class="form-control" id="authority" name="authority" value="普通员工">
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