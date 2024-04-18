<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<link href="<c:url value="/styles/main.css" />" type="text/css"
	rel="stylesheet" />
<script language="javascript" type="text/javascript"
	src="<c:url value="/scripts/My97DatePicker/WdatePicker.js" />"></script>
<title>编辑用户</title>
<base href="<%=basePath%>" />
</head>
<body>
	<div class="main">
		<h2 class="title">
			<span>编辑用户</span>
		</h2>
		<form:form action="user/editSave" method="post" modelAttribute="user">
			<fieldset>
				<legend>用户</legend>
				<table cellpadding="5" cellspacing="8">
					<tr>
						<td><label for="user_name">姓名：</label></td>
						<td><form:input path="user_name" size="40" /></td>
						<td><form:errors path="user_name" cssClass="error"></form:errors></td>
					</tr>
					<tr>
						<td><label for="user_sex">性别：</label></td>
						<td><form:input path="user_sex" size="10" /></td>
						<td><form:errors path="user_sex" cssClass="error"></form:errors></td>
					</tr>
					<tr>
						<td><label for="user_birthday">出生日期：</label></td>
						<td><form:input path="user_birthday" size="40" class="Wdate"
								onClick="WdatePicker()" /></td>
						<td><form:errors path="user_birthday" cssClass="error"></form:errors></td>
					</tr>
					<tr>
						<td><label for="user_email">邮箱：</label></td>
						<td><form:input path="user_email" size="40" /></td>
						<td><form:errors path="user_email" cssClass="error"></form:errors></td>
					</tr>
				</table>
				<p>
					<form:hidden path="user_id" />
					<input type="submit" value="保存" class="btn out">
				</p>
			</fieldset>
			<!--<form:errors path="*"></form:errors> -->
		</form:form>
		<p style="color: red">${message}</p>
		<p>
			<a href="<c:url value="/user/list" />" class="abtn out">返回列表</a>
		</p>
	</div>
</body>
</html>