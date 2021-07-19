<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<%@ include file="/WEB-INF/views/include/head.jspf"%>
<style>
.container {
	display: flex;
	flex-wrap: wrap;
	width: 50%;
	margin: 10px auto;
	border: 1px solid black;
	border-radius: 5px;
	padding: 3px
	
}

.item {
	flex: 1 1 22%;
	border: 1px solid black;
	margin:1px;
}
.list_table{
display: table;
width:50%;
margin: 10px auto;
text-align: center;
}
.list_table td{
border-bottom: 1px solid black;
}

</style>
<body>
	<%@ include file="/WEB-INF/views/include/header.jspf"%>
	<div class="container">
		<div class="item">학번</div>
		<div class="item">학번</div>
		<div class="item">전공</div>
		<div class="item">전공</div>
		<div class="item">이름</div>
		<div class="item">이름</div>
		<div class="item">학년</div>
		<div class="item">학년</div>
	</div>
	<table class="list_table">
		<tr class="Li_main_tr">
			<td>No.</td>
			<td>과목명</td>
			<td>점수</td>
		</tr>
		<tr class="Li_list_tr">
			<td>No.</td>
			<td>과목명</td>
			<td>점수</td>
		</tr>
		<tr class="Li_total_tr">
			<td>No.</td>
			<td>과목명</td>
			<td>점수</td>
		</tr>
	</table>

</body>
</html>