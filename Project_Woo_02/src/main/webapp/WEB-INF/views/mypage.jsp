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

</head>
<link
	href="${rootPath}/static/css/mypage.css?ver=2021-07-20-001"
	rel="stylesheet" />
<style>
tr.hid {
	display: none;
}
</style>
<body>
	<figure class="clip">
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
		<form
			class="mem_up"
			method="POST">
			<fieldset>
				<table class="joinTable">
					<tr>
						<th class="first"><label for="user_name">이름</label></th>
						<td><input
								type="text"
								id="us_name"
								class="textForm"
								value="${MEM.us_name}"
								readonly="readonly"
								name="us_name" />
					</tr>
					<tr>
						<th class="first"><label for="user_id">ID</label></th>
						<td><input
								id="us_id"
								class="textForm"
								value="${MEM.us_id}"
								readonly="readonly" /></td>
					</tr>
					<tr>
						<th><label for="user_pwd">PW</label></th>
						<td><input
								type="password"
								id="us_pw"
								class="textForm"
								placeholder="수정할 비밀번호 입력"
								name="us_pw"
								value="${MEM.us_pw}" /></td>
					</tr>
					<tr>
						<th><label for="user_pwd_check">PW(check)</label></th>
						<td><input
								type="password"
								id="us_pw_ch"
								class="textForm"
								placeholder="수정할 비밀번호를 재확인..." /></td>
					</tr>
					<tr class="hid">
						<th>지역선택</th>
						<td><input
								id="us_city"
								class="textForm"
								value="${MEM.us_city}"
								readonly="readonly" /></td>
					</tr>
					<tr class="hid">
						<th>상세주소</th>
						<td><input
								class="textForm"
								id="us_dist"
								name="us_dist"
								readonly="readonly"
								value="${MEM.us_dist}" /></td>
					</tr>
				</table>
				<div>
					<button
						id="btn_update"
						type="button">정보수정</button>
					<button
						id="btn_del"
						type="button">회원탈퇴</button>
					<button
						id="btn_home"
						type="button">HOME</button>
				</div>
			</fieldset>
		</form>
	</section>
</body>
<script>
   let user_id = document.querySelector("#us_id")
   let user_pw = document.querySelector("#us_pw")
   let user_pw_ch = document.querySelector("#us_pw_ch")
   let btn_home = document.querySelector("button#btn_home")
   let btn_update = document.querySelector("button#btn_update")
   let btn_delete = document.querySelector("button#btn_del")
   
   if(btn_update) {
   btn_update.addEventListener("click",()=>{
      if(user_pw.value === ""){
      alert("비밀번호를 입력해주세요")
      user_pw.focus();
      return false
      }
      if(user_pw_ch.value !== user_pw.value){
      alert("비밀번호가 일치하지 않습니다.")
      user_pw_ch.focus();
      return false
      } else {
         document.querySelector("form.mem_up").submit();
      }
       
   })
   }
   if(btn_home) {
      btn_home.addEventListener("click",()=> {
         location.href="${rootPath}"
      })
   }
   if(btn_delete) {
	      btn_delete.addEventListener("click", ()=> {
	         if(confirm("정말 탈퇴하시겠습니까?") ){
	            location.href="${rootPath}/mypage/delete?us_id="+"${MEM.us_id}"
	         }
	      })
	   }
</script>
</html>