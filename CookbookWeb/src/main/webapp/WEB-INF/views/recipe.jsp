<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath }" />

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>조리법 - ${recipe.name}</title>
</head>
<body>

<h1>${recipe.name}</h1>

<img src="${ctx}/recipe/${recipe.name}/image" />

<h2>재료</h2>
<ul>
	<c:forEach var="recipeName" items="${recipe.ingredients}">
		<li>${recipeName}</li>
	</c:forEach>
</ul>

<h2>조리과정</h2>
<ul>
	<li>${recipe.procedure}</li>
</ul>
<a href="${ctx}/recipe/${recipe.name}/delete" >삭제</a>

</body>
</html>