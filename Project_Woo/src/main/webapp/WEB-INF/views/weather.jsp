<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
    <c:set var="rootPath" value="${pageContext.request.contextPath }" />
<div>
	<ul>
		 <li>테스트1</li>
		 <c:forEach items="${WEATHER}" var="wt">
			<li>${wt.fcstDate}</li>
			<li>${wt.fcstTime}</li>
			<li>${wt.category}</li>
			<li>${wt.fcstValue}</li>
		</c:forEach>
	</ul>
</div>