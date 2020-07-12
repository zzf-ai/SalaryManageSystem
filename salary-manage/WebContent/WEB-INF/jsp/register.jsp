<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>注册页面</title>
<meta http-equiv=Content-Type content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath}/css/style.css"
	   type=text/css rel=stylesheet>

<script src="https://code.jquery.com/jquery-3.4.1.min.js">

</script>
<meta content="MSHTML 6.00.2600.0" name=GENERATOR>
<script>
// 判断是登录账号和密码是否为空
function check(){
    var usercode = $("#usercode").val();
    var password = $("#password").val();
    var password2 = $("#password2").val();
    if(usercode=="" || password==""){
    	$("#message").text("账号或密码不能为空！");
        return false;
    }  
    else if(password2==""){
    	$("#message").text("请确认密码！");
        return false;
    }  
    else if(password!=password2){
    	$("#message").text("两次密码不相同！");
        return false;
    }  
    return true;
}

</script>
</head>
<body leftMargin=0 topMargin=0 marginwidth="0" marginheight="0"
	background="${pageContext.request.contextPath}/images/rights.jpg">

<div ALIGN="center">
<table border="0" width="1140px" cellspacing="0" cellpadding="0"
                                                           id="table1">
	<tr>
		<td height="93"></td>
		<!-- <td></td> -->
	</tr>
	<tr>
   <td class="login_msg" width="400" height="400" align="center">
	 <!-- margin:0px auto; 控制当前标签居中 -->
	 <fieldset style="width: auto; margin: 0px auto;">
		  <legend>
		     <font style="font-size:15px" face="宋体">
		                   注册账户
		     </font>
		  </legend> 
		<font color="red">
			 <%-- 提示信息--%>
			 <span id="message">${msg}</span>
		</font>
		<%-- 提交后的位置：/WEB-INF/jsp/customer.jsp--%>
		<form name="reg" action="${pageContext.request.contextPath }/Register.action" onsubmit="return check()" method="post">
                      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br /><br />
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;账&nbsp;号：<input id="usercode" type="text" name="usercode" />
          <br /><br />
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;密&nbsp;码：<input id="password" type="password" name="password" />
          <br /><br />
           	确认密码：<input id="password2" type="password" name="password2" />
          <br /><br />
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <center><input type="submit" value="注册" />
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <input type="reset" value="重置" />
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <input type="button" value="返回" onclick="location.href='${pageContext.request.contextPath }/login.action'" /></center>
		 </form>
	 </fieldset>
	</td>
	</tr>
</table>
</div>
</body>
</html>
