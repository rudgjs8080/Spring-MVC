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
<style>
form {
	
}

fieldset {
	margin: 0 auto;
	border-radius: 10px;
	padding: 0.7rem;
	border: 1px solid green;
	padding-left: 10%;
	width: 50%;
}

fieldset div {
	margin-top: 10px;
}

input {
	padding: 10px;
	border: 1px solid green;
	border-radius: 15px;
}

textarea {
	border: 1px solid green;
	border-radius: 15px;
}

legend {
	font-size: 40px;
}
label{
text-align: left;}
</style>

<form
	method="POST"
	enctype="multipart/form-data">
	<fieldset>
		<legend>이미지 등록</legend>
		<div>
			<label>작성자 : </label>
			<input
				name="g_writer"
				value="${CMD.g_writer}">
		</div>
		<div>
			<label>작성일자 : </label>
			<input
				name="g_date"
				value="${CMD.g_date}">
		</div>
		<div>
			<label>작성시각 : </label>
			<input
				name="g_time"
				value="${CMD.g_time}">
		</div>
		<div>
			<label>제목 : </label>
			<input name="g_subject">
		</div>
		<div>
			<label>내용 : </label>
			<textarea name="g_content"></textarea>
		</div>
		<div>
			<label>대표이미지 : </label>
			<input
				type="file"
				name="one_file">
		</div>
		<div>
			<label>갤러리 : </label>
			<input
				type="file"
				multiple="multiple"
				name="m_file">
		</div>
		<!-- vo나 dto를 만들 때 type이 file로 된 것들의 name을 변수로
	사용해서는 안된다 Ex) one_file, m_file -->
		<button>전송</button>
	</fieldset>
</form>
