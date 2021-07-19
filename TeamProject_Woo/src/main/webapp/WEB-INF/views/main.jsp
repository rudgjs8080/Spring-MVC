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
<html>
<head>
<meta charset="UTF-8">
<title>나의 홈페이지</title>
</head>
<link
	href="${rootPath}/static/css/main.css?ver=2021-07-02-002"
	rel="stylesheet" />
<body>
	<div>
		<div class="container">
			<div class="item-1">
				<div class="log_join">
					<button class="logout">logout</button>
					<button class="mypage">MyPage</button>
				</div>
				<div class="header">
					<h1 style="font-size: 90px; text-align: center">W O O</h1>
					<h4 style="font-size: 32px; text-align: center">WEATHER & OOTD</h4>
				</div>
			</div>
			<div class="item-2">
				<div
					class="w_1"
					style="border: 1px solid black;">
					<p>기온</p>
				</div>
				<div
					class="w_2"
					style="border: 1px solid black;">
					<img src="${rootPath}/static/images/1.png" />
				</div>
				<div
					class="w_3"
					style="border: 1px solid black;">위치시간</div>
				<div class="w_4">
					<div class="w_4_a">
						<div>온도</div>
						<div>
							<img src="${rootPath}/static/images/온도계.png" />
						</div>
						<div>21</div>
					</div>
					<div class="w_4_a">
						<div>온도</div>
						<div>
							<img src="${rootPath}/static/images/온도계.png" />
						</div>
						<div>21</div>
					</div>
					<div class="w_4_a">
						<div>온도</div>
						<div>
							<img src="${rootPath}/static/images/온도계.png" />
						</div>
						<div>21</div>
					</div>
					<div class="w_4_a">
						<div>온도</div>
						<div>
							<img src="${rootPath}/static/images/온도계.png" />
						</div>
						<div>21</div>
					</div>
				</div>
			</div>
			<div class="item-3">
				<figure class="snip1200">
					<div>
						<img
							class="clothes"
							src="${rootPath}/static/images/clothes.png"
							alt="clothes"
							style="padding-top: 3%" />
						<p style="margin: 10px; font-size: 20px">OOTD 추천을 원하시면 화면에 커서를
							올려주세요</p>
					</div>
					<figcaption class="o">
						<div class="w_data_o">

							<div class="w_data_o_text">
								OOTD 추천을 원하시면<br> 로그인을 해주세요
							</div>

						</div>
						<div class="heading_o">
							<h1>
								<span> OOTD</span>
							</h1>
						</div>
					</figcaption>
				</figure>
			</div>
		</div>
		<div class="item-4">3</div>
	</div>
</body>
<script>

let logout = document.querySelector("button.logout")
let mypage = document.querySelector("button.mypage")

if(logout){
	logout.addEventListener("click", (e) =>{
		location.href = "${rootPath}"
	})
}

</script>
</html>