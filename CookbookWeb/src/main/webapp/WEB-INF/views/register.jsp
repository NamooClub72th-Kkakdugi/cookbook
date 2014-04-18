<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
#main{
	position: relative;
	table-align:center;
	margin : 5px auto;
	width:512px;
}
</style>
<title>조리법 등록</title>
<link href="./register.css" rel="stylesheet" type="text/css" />

</head>
<body>
<div id="main">
	<h1>조리법 등록</h1>

	<form action="${ctx}/recipe/add" method="POST" enctype="multipart/form-data">
		<table border="1">
			<tr>
				<th>조리명</th>
				<td><input type="text" name="name" /></td>
			</tr>
			<tr>
				<th>재료</th>
				<td><textarea rows="2" name="ingredients"></textarea></td>
			</tr>
			<tr>
				<th>조리과정</th>
				<td><textarea rows="2" name="procedure"></textarea></td>
			</tr>
			<tr>
				<th>요리사진</th>
				<td><input type="file" name="imageFile" /></td>
			</tr>
		</table>
		<input type="submit" value="등록" />
	</form>
</div>
</body>
</html>