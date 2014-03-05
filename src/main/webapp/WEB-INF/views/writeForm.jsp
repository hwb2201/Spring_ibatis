<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="resources/jquery-2.1.0.js" type="text/javascript"></script>
<script>
	function inputCheck() {
		if ($('textarea[name=content]').val()=='') {
			alert("내용을 입력하세요.");
			$('textarea[name=content]').focus();
			return false;
		}
		if ($('input[name=title]').val() == "") {
			alert("제목을 입력하세요.");
			$('input[name=title]').focus();
			return false;
		}
	}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>글쓰기</title>

<!-- css 속성 정의-->
<style type="text/css">
.bgcolor1 {
	width: 150px;
	height: 35px;
	font-size: 14;
	text-align: center;
	background-color: #B7F0B1
}

.bgcolor2 {
	width: 230px;
	height: 35px;
	font-size: 13;
	text-align: left;
	background-color: #EDFFE7
}
</style>
</head>
<body>


	<form name="writeForm" method="post" action="/bbs2/write.ktds" enctype="multipart/form-data" onsubmit="return inputCheck()">
		<table width="800" border="0" cellspacing="1" cellpadding="2"
			align="center">

			<tr height="39" align="center">
				<td colspan="3" class="bgcolor1"><b>글쓰기</b></td>
			</tr>

			<tr>
				<td class="bgcolor1">글제목</td>
				<td class="bgcolor2"><input type="text" name="title" size="20"></td>
			</tr>

			<tr>
				<td class="bgcolor1">작성자</td>
				<td class="bgcolor2">&nbsp;${id}</td>
			</tr>

			<tr>
				<td class="bgcolor1">파일</td>
				<td class="bgcolor2"><input type="file" name="uploadFile" size="20"></td>
			</tr>

			<tr>
				<td class="bgcolor1">내용</td>
				<td class="bgcolor2"><textarea name="content" rows="10" cols=40></textarea>
			</tr>

			<tr>
				<td colspan="3" align="center" class="bgcolor1"><input
					type="submit" value="글 등록하기">&nbsp;&nbsp;&nbsp;&nbsp; <input
					type="reset" value="다시작성"> <input type="button" value="취소"
					onclick='document.location.href="/bbs2/list.ktds"'></td>
			</tr>
		</table>
	</form>
</body>
</html>
