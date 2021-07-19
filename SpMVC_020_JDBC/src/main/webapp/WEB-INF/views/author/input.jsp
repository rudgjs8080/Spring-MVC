<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<%@ include file="/WEB-INF/views/include/include_head.jspf"%>
<style>

</style>
<body>
	<%@ include file="/WEB-INF/views/include/include_header.jspf"%>
	<section class="main_sec">
	<form method="POST">
		<fieldset>
			<legend>저자정보 등록</legend>
			<div>
				<label>저자코드</label> <input name="au_code" id="au_code"
					placeholder="저자코드를 입력하세요">
			</div>
			<div>
				<label>저자명</label> <input name="au_name" id="au_name" placeholder="저자명을 입력하세요">
			</div>
			<div>
				<label>전화번호</label> <input name="au_tel" id="au_tel" placeholder="전화번호를 입력하세요">
			</div>
			<div>
				<label>주소</label> <input name="au_addr" id="au_addr" placeholder="주소를 입력하세요">
			</div>
			<div>
				<label>주요장르</label> <input name="au_genre" id="au_genre"
					placeholder="주요장르를 입력하세요">
			</div>
		</fieldset>

		<div class="btn_box">
			<button type="button" class="btn_save author">저자등록</button>
		</div>
		<div class="btn_box">
			<button type="reset" class="btn_reset author">신규작성</button>
		</div>
		<div class="btn_box">
			<button type="button" class="btn_list author">리스트 바로가기</button>
		</div>
	</form>
</section>
	<%@ include file="/WEB-INF/views/include/include_footer.jspf"%>
</body>
</html>