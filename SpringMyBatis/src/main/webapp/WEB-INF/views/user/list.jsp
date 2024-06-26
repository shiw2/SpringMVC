<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<c:url value="/styles/main.css"/>" type="text/css"
	rel="stylesheet" />
<title>用户管理</title>
</head>
<body>
	<div class="main">
		<h2 class="title">
			<span>用户管理</span>
		</h2>
		<form action="deleteUsers" method="post">
			<table border="1" width="100%" class="tab">
				<tr>
					<th><input type="checkbox" id="chkAll"></th>
					<th>姓名</th>
					<th>性别</th>
					<th>出生日期</th>
					<th>邮箱</th>
					<th>操作</th>
				</tr>
				<c:forEach var="entity" items="${userList}">
					<tr>
						<th><input type="checkbox" name="userId"
							value="${entity.userId}"></th>
						<td>${entity.userName}</td>
						<td>${entity.userGender}</td>
						<td>${entity.userBirth}</td>
						<td>${entity.userEmail}</td>

						<td><a href="edit/${entity.userId}" class="abtn">编辑</a> <a
							href="deleteUserById/${entity.userId}" class="abtn">删除</a></td>
					</tr>
				</c:forEach>
			</table>
			<div id="pager"></div>
			<p>
				<a href="add" class="abtn out">添加</a> 
				<input type="submit" value="批量删除" class="btn out" onclick="return submitForm();" />
				<a href="list?pageNO=${num}" class="abtn out">下一页</a>
			</p>
			<p style="color: red">${message}</p>
			<!--分页 -->
			<script type="text/javascript"
				src="<c:url value="/scripts/jQuery1.11.3/jquery-1.11.3.min.js"/>"></script>
			<link href="<c:url value="/scripts/pagination22/pagination.css"/>"
				type="text/css" rel="stylesheet" />
			<script type="text/javascript"
				src="<c:url value="/scripts/pagination22/jquery.pagination2.2.js"/>"></script>
			<script type="text/javascript">
				$(document).ready(function() {
					//全选/取消全选
					$("#chkAll").click(function() {
						var checked = $("#chkAll").prop("checked");
						$("input[name='user_id']").prop("checked", checked);
					})
				})

				//初始化分页组件
				var count = $
				{
					count
				};
				var size = $
				{
					size
				};
				var pageNO = $
				{
					pageNO
				};
				$("#pager").pagination(count, {
					items_per_page : size,
					current_page : pageNO - 1,
					next_text : "下一页",
					prev_text : "上一页",
					num_edge_entries : 2,
					load_first_page : false,
					callback : handlePaginationClick
				});

				//回调方法
				function handlePaginationClick(new_page_index,
						pagination_container) {
					location.href = "list?pageNO=" + (new_page_index + 1);
				}

				function submitForm() {
					if ($("input[name='userId']:checked").length == 0) {
						alert("请选择要删除的记录！");
						return false;
					}
					return true;
				}
			</script>
		</form>
	</div>
</body>
</html>