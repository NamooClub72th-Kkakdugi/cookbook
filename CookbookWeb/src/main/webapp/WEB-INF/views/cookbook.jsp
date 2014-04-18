<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />


<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<link rel="shortcut icon" href="${ctx}/resources/images/favicon.ico">

<title>요리책</title>




<!-- Bootstrap core CSS -->
<link href="${ctx}/resources/css/bootstrap.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="${ctx}/resources/css/jumbotron-narrow.css" rel="stylesheet">
</head>

<body>
	<div class="container">
		<div class="header">
			<ul class="nav nav-pills pull-right">
				<li class="active"><a href="${ctx}/main">Home</a></li>
			</ul>
			<h3 class="text-muted">Cookbook</h3>
		</div>

		<div class="jumbotron">
			<h1>${cookbook.name}</h1>
			<p class="lead">나무소리 요리책과 함게 맛있는 요리를 즐기세요. 여러분의 요리비법을 다른 사람과 공유하세요.</p>
			<p>
				<a class="btn btn-lg btn-success" href="${ctx}/recipe/add">조리법 공유</a>
			</p>
		</div>

		<div class="row marketing">
			<c:forEach var="recipe" items="${cookbook.recipes}">
				<div class="col-lg-6">
					<h4><button type="button" class="btn btn-info">${recipe.name}</button></h4>
					<p>
						<a href="${ctx}/recipe/${recipe.name}"><img	src="${ctx}/recipe/${recipe.name}/image"></a>
					</p>
				</div>
			</c:forEach>
			<div class="footer">
				<address>
					<strong>나무소리요리사</strong><br>
					서울특별시 구로구 오류2동 137-1<br>
					럭키빌라 101호 (우 : 152-896)<br>
					<abbr title="Phone">Phone :</abbr> (010) 2270-8598<br>
				</address>

				<address>
					<strong>Kane Juseong Lee</strong><br>
					 <a href="mailto:#">wntjd1211@nate.com</a><br>
				</address>
				<p>&copy; NamoosoriChef 2014</p>
			</div>

		</div>
		<!-- /container -->
	</div>


	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
</body>
</html>
