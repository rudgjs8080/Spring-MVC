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
<title>회원가입</title>
</head>
<link href="${rootPath}/static/css/join.css?ver=2021-07-09-001"
	rel="stylesheet" />
<body>
	<figure class="snip1432">
		<figcaption>
			<div>
				<h1>Sign Up</h1>
				<h1>회원가입</h1>
			</div>
			<div>
				<h4>WEATHER & OOTD</h4>
			</div>
		</figcaption>
	</figure>
	<section id="join_wrap">
		<form method="POST" class="form_join">
			<fieldset>
				<table class="joinTable">
					<tr>
						<th class="first"><label for="user_name">이름</label></th>
						<td><input type="text" id="us_name" class="textForm"
							placeholder="이름을 입력..." name="us_name" />
					</tr>
					<tr>
						<th class="first"><label for="us_id">ID</label></th>
						<td><input type="text" id="us_id" class="textForm"
							placeholder="ID를 입력..." name="us_id" />
							<button id="id_check" type="button">중복확인</button></td>
					</tr>
					<tr>
						<th><label for="user_pwd">PW</label></th>
						<td><input type="password" id="us_pw" class="textForm"
							placeholder="비밀번호를 입력..." name="us_pw" /></td>
					</tr>
					<tr>
						<th><label for="user_pwd_check">PW(check)</label></th>
						<td><input type="password" id="us_pw_ch" class="textForm"
							placeholder="비밀번호를 재확인..." /></td>
					</tr>
					<tr>
						<th>지역선택</th>
						<td><select name="us_city" class="textForm">
								<option>선택</option>
								<option>서울</option>
								<option>대전</option>
								<option>대구</option>
								<option>부산</option>
								<option>광주</option>
								<option>경기도</option>
								<option>강원도</option>
								<option>충청북도</option>
								<option>충청남도</option>
								<option>전라북도</option>
								<option>전라남도</option>
								<option>경상북도</option>
								<option>경상남도</option>
						</select></td>
					</tr>
					<tr>
						<th>상세주소</th>
						<td><input class="textForm" name="us_dist" /></td>
					</tr>
				</table>
				<button type="button" class="btn_join" id="btn_join">가입하기</button>
				<button type="button" id="btn_home">홈으로</button>
			</fieldset>
		</form>
	</section>
</body>
<script>
let user_name = document.querySelector("input#us_name")
let user_id = document.querySelector("input#us_id")
let user_pwd = document.querySelector("input#us_pw")
let user_pwd_ch = document.querySelector("input#us_pw_ch")
let btn_home = document.querySelector("button#btn_home")
let btn_join = document.querySelector("button#btn_join")
let btn_idcheck = document.querySelector("button#id_check")
let check = "0"

// btn_home을 클릭하면 home으로 이동
if(btn_home) {
	btn_home.addEventListener("click",()=> {
		location.href="${rootPath}"
	})
}
if(btn_idcheck) {
	btn_idcheck.addEventListener("click", ()=>{
		let json = {user_id}
		let jsonString = JSON.stringify(json);
		fetch("${rootPath}/idcheck/" + user_id.value)
		.then(res=>res.text())
		.then(result=>{
		if(result == "1"){
		alert("이미 사용중인 아이디 입니다")
		user_id.focus();
		user_id.value=""
		return false;
		} else {
		alert("사용할 수 있는 아이디 입니다")
		check = "1";
		}
		})
	})
}

// btn_join을 클릭하면
if(btn_join) {
	btn_join.addEventListener("click",()=>{
		if(user_name.value === "") {
			alert("이름을 입력하세요")
			user_name.focus();
			return false
		}
		if(user_id.value === ""){
		alert("ID를 입력하세요")
		user_id.focus();
		return false
		}
		if(user_pwd.value === ""){
		alert("비밀번호를 입력해주세요")
		user_pwd.focus();
		return false
		}
		if(user_pwd_ch.value !== user_pwd.value){
		alert("비밀번호가 일치하지 않습니다.")
		user_pwd_ch.focus();
		return false
		}
		if(check === "0") {
			alert("ID 중복확인을 해주세요")
			user_id.focus();
			return false
		} else {
			document.querySelector("form.form_join").submit();
		}
	    
	})
}





    /*document.addEventListener("DOMContentLoaded", () => {
      document.querySelector("button").addEventListener("click", (ev) => {
        alert("회원가입을 환영합니다!");
        alert("상세 날씨 페이지로 이동합니다");
        document.location.href =
          "http://127.0.0.1:5501/weather_ootd/logout.html";
      });
    });
    document.addEventListener("DOMContentLoaded", () => {
      document
        .querySelector("select.textForm")
        .addEventListener("change", (ev) => {
          alert("상세주소를 선택해주세요");

          window.open(
             회원가입 팝업창 생성 

            "popup.html",
            "popup01",
            "width=500, height=400, fullscreen=yes",
            "scrollbars= 0, toolbar=0, menubar=no"
          );
        });
    });*/
  </script>
</html>