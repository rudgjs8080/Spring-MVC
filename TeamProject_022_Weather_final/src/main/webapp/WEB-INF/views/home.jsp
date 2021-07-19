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
<title>Insert title here</title>
<script>

document.addEventListener("DOMContentLoaded", ()=>{
	document.querySelector("nav#main_mem").addEventListener("click", (e)=> {
		let tagName = e.target.tagName;
		if(tagName === "LI") {
			let menuText = e.target.textContent;
			if (menuText === "로그인") {
				document.location.href="redirect:/login";
			} else if (menuText === "회원가입") {
				document.location.href="${rootPath}/join"
			}  else if (menuText === "로그아웃") {
				document.location.href="${rootPath}/logout"
			} 
		}
	})
	
	/*
	document.querySelector("li#li_log").addEventListener("click",(e)=>{
		document.location.href="${rootPath}/login"
	})
	document.querySelector("li#li_join").addEventListener("click",(e)=>{
		document.location.href="${rootPath}/join"
	})
	document.querySelector("li#li_logout").addEventListener("click",(e)=>{
		document.location.href="${rootPath}/logout"
	})
	
	*/
	
})
</script>
</head>
<link
	href="${rootPath}/static/css/home.css?ver=2021-07-09-001"
	rel="stylesheet" />
<body>

	<nav id="main_mem">
		<ul>

			<c:if test="${not empty MEMVO}">

			<!-- 	<li id="li_logout">로그아웃</li>
				<li id="li_mypage">${MEMVO.us_name}</li> -->

				<%@ include file="/WEB-INF/views/home_A.jsp"%>
			</c:if>
			<c:if test="${empty MEMVO}">

				<!-- <li id="li_log">로그인</li>
				<li id="li_join">회원가입</li> -->
				<%@ include file="/WEB-INF/views/home_B.jsp"%>
			</c:if>



		</ul>
	</nav>


</body>
</html>