<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath }" />
		<div>오늘날씨</div>
		<c:forEach items="${TODAY}" var="TD">
			<div>${TD.fcstDate}</div>
			<div>${TD.fcstTime}</div>
			<div>${TD.category}</div>
			<c:choose>
				<c:when test="${TD.category == '하늘상태' && TD.fcstValue =='맑음' }">
					<div><img src="${rootPath}/static/icon/size-large/맑음.png" width="100px"></div>
				</c:when>
				<c:when test="${TD.category == '하늘상태' && TD.fcstValue =='구름많음' }">
					<div><img src="${rootPath}/static/icon/size-large/구름많음.png" width="100px"></div>
				</c:when>
				<c:when test="${TD.category == '하늘상태' && TD.fcstValue =='흐림' }">
					<div><img src="${rootPath}/static/icon/size-large/흐림.png" width="100px"></div>
				</c:when>
				<c:otherwise>
					<div>${TD.fcstValue}</div>	
				</c:otherwise>
			</c:choose>
			<div>------------</div>
		</c:forEach>
		<!-- 
		<li>내일날씨=====================================================================</li>
		<c:forEach items="${TOMORROW}" var="TM">
			<li>${TM.fcstDate}</li>
			<li>${TM.fcstTime}</li>
			<li>${TM.category}</li>
			<li>${TM.fcstValue}</li>
		</c:forEach>
		<li>2일뒤날씨=====================================================================</li>
		<c:forEach items="${AFTERTOMORROW}" var="AM">
			<li>${AM.fcstDate}</li>
			<li>${AM.fcstTime}</li>
			<li>${AM.category}</li>
			<li>${AM.fcstValue}</li>
		</c:forEach> -->
