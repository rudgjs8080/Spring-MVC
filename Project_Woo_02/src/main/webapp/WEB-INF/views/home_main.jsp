<%@ page
	language="java"
	contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib
	uri="http://java.sun.com/jsp/jstl/core"
	prefix="c"%>
<c:set
	var="rootPath"
	value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>W O O</title>
</head>
<link
	href="${rootPath}/static/css/home.css?ver=2021-07-20-001"
	rel="stylesheet" />
	
<body>

	<c:if test="${not empty MEMVO}">
		<%@ include file="/WEB-INF/views/home_A.jsp"%>
	</c:if>
	<c:if test="${empty MEMVO}">
		<%@ include file="/WEB-INF/views/home_B.jsp"%>
	</c:if>
	
</body>

</html>