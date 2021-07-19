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
<style>
div.main_list {
	width: 50%;
	margin: 0 auto;
	display: grid;
	grid-template-columns: 1fr 1fr;
	grid-gap: 50px;
}
div.item_1 {
	display: flex;
	flex-direction: column;
	flex: auto;
	padding: 20px;
	border: 0.5px solid blue;
	border-radius: 30px;
}

div.img img {
	max-width: 400px;
	max-height: 200px;
}

.img h3 {
	text-align: center;
}
</style>
<%@ include file="/WEB-INF/views/include/include_gallery_search.jspf" %>
<div class="main_list">
<c:forEach
	items="${GALLERYS}"
	var="GA">
	<div class="item_1">
		<div class="img">
			<h3>대표이미지</h3>
			<c:if test="${empty GA.g_image}">
				<img src="${rootPath}/files/NoImage.png" width="100%">
			</c:if>
			<c:if test="${not empty GA.g_image}">
				<img src="${rootPath}/files/${GA.g_image}">	
			</c:if>
			
		</div>
		<div class="content">
			<h3>
				제목 : <a href="${rootPath}/gallery/detail2/${GA.g_seq}">${GA.g_subject}</a>
			</h3>
			<p>내용 : ${GA.g_content}</p>
		</div>
	</div>
</c:forEach>
</div>
<%@ include file="/WEB-INF/views/include/include_page_nav.jspf"%>
