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
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<link
	href="${rootPath}/static/css/mypage.css?ver=2021-07-09-002"
	rel="stylesheet" />
<script>
document.addEventListener("DOMContentLoaded", ()=>{
	document.querySelector("button.btn_home").addEventListener("click",()=>{
		let tagName = e.target.tagName;
		if(tagName === "DIV"){
			let menuText = e.target.textContent;
			if(menuText === "HOME"){
				document.location.href = "redirect:/"
			}
		}
	})
})
</script>
<body>
	<figure class="snip1432">
		<figcaption>
			<div>
				<h1>Changing Information</h1>
				<h1>회원정보수정</h1>
			</div>
			<div>
				<h4>WEATHER & OOTD</h4>
			</div>
		</figcaption>
		<a href="#"></a>
	</figure>
	<section id="join_wrap">
		<form>
			<fieldset>
				<table class="joinTable">
					<tr>
						<th class="first"><label for="user_id">ID</label></th>
						<td><input
								type="text"
								id="user_id"
								class="textForm"
								placeholder="ID값 담기" /></td>
					</tr>
					<tr>
						<th><label for="user_pwd">PW</label></th>
						<td><input
								type="password"
								id="user_pwd"
								class="textForm"
								placeholder="수정할 비밀번호 입력" /></td>
					</tr>
					<tr>
						<th><label for="user_pwd_check">PW(check)</label></th>
						<td><input
								type="password"
								id="user_pwd"
								class="textForm"
								placeholder="수정할 비밀번호를 재확인..." /></td>
					</tr>
					<tr>
						<th>지역선택</th>
						<td><select
							name="user_country"
							class="textForm">
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
						<td><input
								class="textForm"
								placeholder="수정한 주소값 담기" /></td>
					</tr>
				</table>
				<div>
					<button class="btn_update">정보수정</button>
					<button class="btn_home">HOME</button>
				</div>
			</fieldset>
		</form>
	</section>
</body>
</html>
