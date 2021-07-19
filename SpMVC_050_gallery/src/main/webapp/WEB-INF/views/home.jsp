<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>나의 홈페이지</title>
<script src="https://kit.fontawesome.com/58b5d9133d.js" crossorigin="anonymous"></script>

<style>
* {
	box-sizing: border-box;
	margin: 1px;
	padding: 0;
}

a {
	text-decoration: none;
	color: black;
}

a:hover {
	color: red;
}

.input_img {
	text-align: right;
}

div.head {
	padding-top: 25px;
	height: 100px;
	background-color: #ffff00;
	margin-bottom: 20px;
	vertical-align: middle;
	text-align: center;
	height: 100px;
}

div.item_1 {
	margin: 0 auto;
}

.nav {
	margin-bottom: 30px
}
</style>
</head>
<body>
	<div class="head">
		<h1>내 갤러리</h1>
	</div>

	<div class="nav"><%@ include
			file="/WEB-INF/views/include/include_nav.jspf"%></div>

	<div class="main">
		<c:choose>
			<c:when test="${BODY eq 'GA-INPUT'}">
				<%@ include file="/WEB-INF/views/gallery/input.jsp"%>
			</c:when>
			<c:when test="${BODY eq 'GA-LIST'}">
				<%@ include file="/WEB-INF/views/gallery/list.jsp"%>
			</c:when>
			<c:when test="${BODY eq 'GA-DETAIL'}">
				<%@ include file="/WEB-INF/views/gallery/detail.jsp"%>
			</c:when>
			<c:when test="${BODY eq 'GA-DETAIL-V2'}">
				<%@ include file="/WEB-INF/views/gallery/detail2.jsp"%>
			</c:when>
			<c:when test="${BODY eq 'JOIN'}">
				<%@ include file="/WEB-INF/views/member/join.jsp"%>
			</c:when>
			<c:when test="${BODY eq 'LOGIN' }">
				<%@ include file="/WEB-INF/views/member/login.jsp"%>
			</c:when>
			<c:otherwise>
				<a href="${rootPath}/gallery/input">이미지 등록</a>
			</c:otherwise>
		</c:choose>
		<c:forEach items="${FILES}" var="FILE">
			<a href="${rootPath}/files/${FILE}" target="_NEW"> <img
				src="${rootPath}/files/${FILE}" width="200px;" height="100px;" /></a>
		</c:forEach>
	</div>
</body>
<script>
	let main_nav = document.querySelector(".d_nav")
	
	if(main_nav) {
		main_nav.addEventListener("click", (e)=>{
			let menu = e.target
			if(menu.tagName === "DIV"){
				if(menu.id === "join"){
					location.href = "${rootPath}/member/join"
				} else if(menu.id === "login"){
					location.href = "${rootPath}/member/login"
				} else if(menu.id === "logout"){
					location.href = "${rootPath}/member/logout"
				} else if(menu.id === "image_create"){
					location.href = "${rootPath}/gallery/input"
				} else if(menu.id === "home"){
					location.href = "${rootPath}/"
				}
			}
		})
	}
</script>
</html>