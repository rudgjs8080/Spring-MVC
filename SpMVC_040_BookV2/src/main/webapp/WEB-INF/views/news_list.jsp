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
<c:forEach
	items="${NEWS_LIST}"
	var="NEWS">
	<div class="content">

		<div>
			<p class="title">
				<a href="${NEWS.link}">${NEWS.title}</a>
			</p>
			<p class="description">
				<strong>요약 : </strong>${NEWS.description}
			</p>
			
			<button class="insert">내 서재등록</button>
		</div>
	</div>
</c:forEach>