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
	items="${BOOK}"
	var="BOOK">
	<div class="content">
		<img src="${BOOK.image}">
		<div>
			<p class="title">
				<a
					href="${BOOK.link}"
					target="_NEW"> ${BOOK.title} </a>
			</p>
			<p class="desc">
				<strong>요약 : </strong>${BOOK.description}</p>
			<p class="author">
				<strong>저자 : </strong>${BOOK.author}
			</p>
			<p class="publisher">
				<strong>출판사 : </strong>${BOOK.publisher}
			</p>
			<p class="price">
				<strong>정가 : </strong>${BOOK.price}
			</p>
			<p class="discount">
				<strong>할인가 : </strong>${BOOK.discount}
			</p>
			<p class="pubdate">
				<strong>출간일 : </strong>${BOOK.pubdate}
			</p>
			
			

		</div>
	</div>
</c:forEach>