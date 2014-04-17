<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>조리법 등록</title>
</head>
<body>

	<h1>조리법 등록</h1>

	<form action="${ctx}/recipe/add" method="POST" enctype="multipart/form-data">
		<table border="1">
			<tr>
				<td>조리명</td>
				<td><input type="text" name="name" /></td>
			</tr>
			<tr>
				<td>재료</td>
				<td><textarea name="ingredients"></textarea></td>
			</tr>
			<tr>
				<td>조리과정</td>
				<td><textarea name="procedure"></textarea></td>
			</tr>
			<tr>
				<td>요리사진</td>
				<td><input type="file" name="imageFile" /></td>
			</tr>
		</table>
		
		<input type="submit" value="등록" />
	</form>

</body>
</html>