<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<%@ include file="/WEB-INF/views/include/include_head.jspf"%>
<style>
form#join_form {
	width: 400px;
	padding: 60px;
	background-color: green;
	text-align: center;
	margin: 100px auto;
	border-radius: 40px;
}

form#join_form h2 {
	color: white;
	font-weight: 500;
}

form#join_form input {
	outline: 0;
	display: block;
	width: 200px;
	margin: 20px auto;
	padding: 15px 10px;
	color: white;
	border-radius: 25px;
	background: none;
	border: 2px solid #3498db;
	text-align: center;
}

form#join_form input:focus {
	width: 280px;
	border-color: #2ecc71;
	transition: 0.5s;
}

form#join_form button {
	border: 0;
	outline: none;
	background: none;
	display: block;
	margin: 20px auto;
	text-align: center;
	border: 1px solid #2ecc71;
	color: white;
	border-radius: 30px;
	cursor: pointer;
	width: 200px;
}

form#join_form button:hover {
	
}
</style>
<body>
	<%@ include file="/WEB-INF/views/include/include_header.jspf"%>

	<section id="join">
		<form id="join_form" method="POST">
			<h2>로그인</h2>
			<input name="m_username" placeholder="사용자 ID" /> 
			<input type="password" name="m_password" placeholder="비밀번호" />
			<input type="password" name="m_password" placeholder="비밀번호" />
			<button>로그인</button>

		</form>
	</section>

	<%@ include file="/WEB-INF/views/include/include_footer.jspf"%>
</body>
<script>
document.querySelector("button.btn_book_insert").addEventListener("click", () =>{
	location.href = "${rootPath}/books/insert";
	
})


</script>
</html>