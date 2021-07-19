<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>로그인</title>
</head>
<link href="${rootPath}/static/css/login.css?ver=2021-07-09-001"
	rel="stylesheet" />
<style>
div.msg {
	width: 50%;
	height: 10px;
	background-color: red;
	font-size: 10px;
}
</style>
<body>
	<form id="login" method="POST">
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
						<td><input type="text" name="us_id" class="user"
							placeholder="ID를 입력해주세요" /></td>
					</tr>
					<tr>
						<th><label>PW</label></th>
						<td><input type="password" name="us_pw" class="user"
							placeholder="PW를 입력해주세요" /></td>
					</tr>
				</table>
				<button id="btn_login" type="button">Login</button>
				<button id="btn_join" type="button">Join Us</button>
				<button id="btn_home" type="button">Home</button>
			</fieldset>
		</section>
	</form>
</body>
<script>
let form = document.querySelector("#login") // form#login 지정
let btn_join = document.querySelector("button#btn_join") // 회원가입 버튼 지정
let btn_home = document.querySelector("button#btn_home") // 홈 버튼 지정
let btn_login= document.querySelector("button#btn_login") // 로그인 버튼 지정
let input_id = document.querySelector("input[name='us_id']") // id input box 지정
let input_pw = document.querySelector("input[name='us_pw']") // pw input box 지정
let msg_error = document.querySelector("div.msg") // div msg class지정


// btn_login을 눌렀을때
if(btn_login) { 
	btn_login.addEventListener("click", ()=> {
		
		let us_id = input_id.value // id input box에 담긴 값 'us_id'에 담기
		let us_pw = input_pw.value // pw input box에 담긴 값 'us_pw'에 담기
		if(us_id === "") { // 'us_id'에 담긴 값이 없으면
			alert("사용자 ID를 입력하세요.")
			input_id.focus() // id input box에 cursor 지정
			return false // 정지
		}
		if(us_pw === "") { // 'us_pw'에 담긴 값이 없으면
			alert("사용자 PW를 입력하세요.")
			input_pw.focus() // pw input box에 cursor 지정
			return false // 정지
		}
		// id, pw 모두 입력해서 if문을 모두 통과하면
		form.submit() // form 내에 있는 데이터 name에 담아 전송
	})
}

// btn_join을 눌렀을때
if(btn_join) {
	btn_join.addEventListener("click", ()=> {
		location.href="${rootPath}/join" // join 페이지로 이동
	})
}
if(btn_home) {
	btn_home.addEventListener("click", ()=> {
		location.href="${rootPath}" // home 페이지로 이동
	})
}


let msg = "${MSG}" //MemberService에서 지정한 MSG 변수에 담긴 값을 msg라는 변수에 선언해 담는다
if(msg === "NOT_USERID") {
	msg_error.innerText = "사용자 ID가 존재하지 않습니다."
	msg_error.classList.add("view")
} else if (msg === "NEQ_PASS") {
	msg_error.innerText = "비밀번호가 일치하지 않습니다."
	msg_error.classList.add("view")
}

</script>
</html>