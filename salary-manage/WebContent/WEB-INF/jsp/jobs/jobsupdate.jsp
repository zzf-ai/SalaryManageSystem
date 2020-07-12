<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改职位信息</title>
</head>
<body>
	<form action="${pageContext.request.contextPath }/jobsupdate.action"
		id="form1" name="form1" method="post">
		<table align="center" width="500" border="1" cellspacing="0"
			cellpadding="0" style="border-collapse: collapse;"
			bordercolor="#0099FF">
			<tr>
				<td width="116" height="30" align="right" valign="middle">职位号:</td>
				<td width="378" align="left" valign="middle"><input type="text"
					name="jno" readonly="readonly" id="jno" value="${jobs.jno }"></td>
			</tr>
			<tr>
				<td width="116" height="30" align="right" valign="middle">职位名称:</td>
				<td width="378" align="left" valign="middle"><input type="text"
					name="jname" id="jname" value="${jobs.jname }"></td>
			</tr>

			<tr>
				<td width="116" height="30" align="right" valign="middle">基本工资:</td>
				<td width="378" align="left" valign="middle"><input type="text"
					name="jsalary" id="jsalary" value="${jobs.jsalary }"></td>
			</tr>
			<tr>
				<td height="30" align="right" valign="middle">&nbsp;</td>
				<td align="left" valign="middle"><input type="submit"
					name="button" value="提交"> <input type="reset"
					name="button2" id="button2" value="重置"></td>
			</tr>

		</table>
	</form>
</body>
</html>