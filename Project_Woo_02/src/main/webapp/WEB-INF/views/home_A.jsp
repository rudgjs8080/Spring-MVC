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
<script>

document.addEventListener("DOMContentLoaded", ()=>{
	document.querySelector("div.log_join").addEventListener("click", (e)=> {
		let tagName = e.target.tagName;
		
		if(tagName === "BUTTON") {
			let menuText = e.target.textContent;
			 if (menuText === "Logout") {
				document.location.href="${rootPath}/logout"
			} else if(menuText ==="Mypage"){
				location.href="${rootPath}/mypage?us_id=" + "${MEMVO.us_id}"
			}
		}
	})
})
</script>
</head>
<link
	href="${rootPath}/static/css/home_A.css?ver=2021-07-20-003"
	rel="stylesheet" />
<body>
	<div>
		<div class="container">
			<div class="item-1">
				<div class="log_join">
					<button class="logout">Logout</button>
					<button class="mypage">Mypage</button>
				</div>
				<div class="header">
					<h1 style="font-size: 90px; text-align: center">W O O</h1>
					<h4 style="font-size: 32px; text-align: center">WEATHER & OOTD</h4>
				</div>
			</div>
			<div class="item-2">
				<div class="w_1">

					<c:forEach
						items="${TODAY}"
						var="TD">

						<c:choose>
							<c:when
								test="${TD.category == '강수확률' && TD.fcstTime == TIME && TD.fcstValue == '100%'}">
								<div>
									<img src="${rootPath}/static/images/비.png">
								</div>
							</c:when>
							<c:when
								test="${TD.category == '6시간 적설량' && TD.fcstTime == TIME && TD.fcstValue != '없음'}">
								<div>
									<img src="${rootPath}/static/images/눈.png">
								</div>
							</c:when>
							<c:when
								test="${TD.category == '하늘상태' && TD.fcstValue =='맑음' && TD.fcstTime == TIME}">
								<div>
									<img
										src="${rootPath}/static/images/맑음.png"
										width="100px">
								</div>
							</c:when>
							<c:when
								test="${TD.category == '하늘상태' && TD.fcstValue =='구름많음'&&TD.fcstTime == TIME }">
								<div>
									<img
										src="${rootPath}/static/images/구름많음.png"
										width="100px">
								</div>
							</c:when>
							<c:when
								test="${TD.category == '하늘상태' && TD.fcstValue =='흐림' &&TD.fcstTime == TIME}">
								<div>
									<img
										src="${rootPath}/static/images/흐림.png"
										width="100px">
								</div>
							</c:when>
							<c:otherwise>
							</c:otherwise>
						</c:choose>
					</c:forEach>

				</div>
				<div class="w_2">

					<div>${LOCATION.ar_si}&nbsp;${LOCATION.ar_gu}&nbsp;${LOCATION.ar_dong}</div>
					<c:forEach
						items="${TODAY}"
						var="TD">
						<c:if test="${TD.category == '아침 최저기온'}">
							${TD.fcstValue} / 
						</c:if>
						<c:if test="${TD.category == '낮 최고기온'}">
							${TD.fcstValue}
						</c:if>
					</c:forEach>

				</div>
				<div class="w_3">
					<div class="w_3_a">
						<div>기온</div>
						<div>
							<c:forEach
								items="${TODAY}"
								var="TD">
								<c:if test="${TD.fcstTime == TIME && TD.category == '3시간 기온'}">
									<div>${TD.fcstValue}</div>
								</c:if>
							</c:forEach>
						</div>
						<div>
							<img src="${rootPath}/static/images/온도계.png" />
						</div>
					</div>
					<div class="w_3_a">
						<div>습도</div>
						<div>
							<c:forEach
								items="${TODAY}"
								var="TD">
								<c:if test="${TD.fcstTime == TIME && TD.category == '습도'}">
									<div>${TD.fcstValue}</div>
								</c:if>
							</c:forEach>
						</div>
						<div>
							<img src="${rootPath}/static/images/humidity.png" />
						</div>
					</div>
					<div class="w_3_a">
						<div>강수</div>
						<div>
							<c:forEach
								items="${TODAY}"
								var="TD">
								<c:if test="${TD.fcstTime == TIME && TD.category == '강수확률'}">
									<div>${TD.fcstValue}</div>
								</c:if>
							</c:forEach>
						</div>
						<div>
							<img src="${rootPath}/static/images/강수확률.png" />
						</div>
					</div>
					<div class="w_3_a">
						<div>풍향</div>
						<c:forEach
							items="${TODAY}"
							var="TD">
							<c:if test="${TD.fcstTime == TIME && TD.category == '풍향'}">
								<div>${TD.fcstValue}</div>
								<div>
									<img src="${rootPath}/static/images/${TD.fcstValue}.png" />
								</div>
							</c:if>
						</c:forEach>
					</div>
				</div>

				<div class="w_4">
					<div class="w_4_a">오늘<br>날씨</div>
					<c:forEach
						items="${TODAY}"
						var="TD">
						<c:if test="${TD.category == '3시간 기온'}">
							<div class="w_4_a">
								<div>${TD.fcstValue}</div>
								<div>${TD.fcstTime}</div>
							</div>
						</c:if>
					</c:forEach>
				</div>

				<div class="w_5">
					<div class="w_5_a">내일<br>날씨</div>
					<c:forEach
						items="${TOMORROW}"
						var="TM">
						<c:if test="${TM.category == '3시간 기온'}">
							<div class="w_5_a">
								<div>${TM.fcstValue}</div>
								<div>${TM.fcstTime}</div>
							</div>
						</c:if>
					</c:forEach>
				</div>
			</div>
			
			<div class="item-3">
				<figure class="clip_h">
					<div>
						<img class="clothes" src="${rootPath}/static/images/clothes.png"/>
						<p>OOTD 추천을 원하시면 화면에 커서를
							올려주세요</p>
					</div>
					<figcaption class="o">
						<div class="w_data_o">
							<c:choose>
								<c:when test="${SEASON == '봄'}">
									<%@ include file="/WEB-INF/views/spring.jspf"%>
								</c:when>
								<c:when test="${SEASON == '여름'}">
									<%@ include file="/WEB-INF/views/summer.jspf"%>
								</c:when>
								<c:otherwise>
									<%@ include file="/WEB-INF/views/winter.jspf"%>
								</c:otherwise>
							</c:choose>
						</div>
					</figcaption>
				</figure>
			</div>
			
		</div>
	</div>
</body>
</html>