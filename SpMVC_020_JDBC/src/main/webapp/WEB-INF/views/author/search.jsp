<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<%@ include file="/WEB-INF/views/include/include_head.jspf"%>
<style>
</style>
<body>
	<section class="search_sec">
		<table>
			<tr>
				<th>저자코드</th>
				<th>저자명</th>
				<th>전화번호</th>
				<th>주소</th>
				<th>주요장르</th>
			</tr>
			<c:choose>
				<c:when test="${empty AUTHORS}">
					<tr>
						<td colspan="5">데이터가 없음</td>
				</c:when>
				<c:otherwise>
					<c:forEach items="${AUTHORS}" var="AU" varStatus="seq">
						<tr data-acode="${AU.au_code}" class="search_author">
							<td>${AU.au_code}</td>
							<td>${AU.au_name}</td>
							<td>${AU.au_tel}</td>
							<td>${AU.au_addr}</td>
							<td>${AU.au_genre}</td>
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</table>
	</section>
</body>
<script>
document.querySelector("button.btn_insert.author").addEventListener("click", () =>{
	location.href = "${rootPath}/author/insert";
	
})


</script>








</html>