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
<html lang="ko">
<head>
<meta charset="UTF-8" />
<meta
	http-equiv="X-UA-Compatible"
	content="IE=edge" />
<meta
	name="viewport"
	content="width=device-width, initial-scale=1.0" />
<title>로그인</title>
</head>
<link
	href="${rootPath}/static/css/login.css?ver=2021-07-09-001"
	rel="stylesheet" />
<body>
	<form
		id="login"
		method="POST">
		<figure class="logo">
			<figcaption>
				<h1>Log In</h1>
				<h1>로그인</h1>
				<h4>WEATHER & OOTD</h4>
			</figcaption>
		</figure>
		<section>
			<fieldset>
				<table class="input">
					<div class="msg">${MSG}</div>
					<tr>
						<th><label>ID</label></th>
						<td><input
								type="text"
								name="us_id"
								class="user"
								placeholder="ID를 입력해주세요" /></td>
					</tr>
					<tr>
						<th><label>PW</label></th>
						<td><input
								type="password"
								name="us_pw"
								class="user"
								placeholder="PW를 입력해주세요" /></td>
					</tr>
				</table>
				<button id="btn_login">LogIn</button>
				<button id="btn_join">Join Us</button>
			</fieldset>
		</section>
	</form>
</body>
</html>
<script>
let msg_error = document.querySelector("div.msg")
let MSG = "${MSG}"
if(MSG === "LOGIN_FAIL"){
	msg_error.innerText = "로그인에 실패하셨습니다 다시 시도해주세요"
	msg_error.classList.add("view")
}
</script>