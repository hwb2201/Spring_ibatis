<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
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
	<form name="replyForm" method="post" action="/bbs2/reply.ktds" enctype="multipart/form-data">
		<input type="hidden" name="pageNum" value="${pageNum}"> 		
		<input type="hidden" name="depth" value="${depth}"> 
		<input type="hidden" name="group_id" value="${group_id}"> 
		<input type="hidden" name="pos" value="${pos}">
		
		<table width="800" border="0" cellspacing="1" cellpadding="2"
			align="center">

			<tr height="39" align="center">
				<td colspan="3" class="bgcolor1"><b>답글쓰기</b></td>
			</tr>

			<tr>
				<td class="bgcolor1">글제목</td>
				<td class="bgcolor2"><input type="text" name="title" size="20" value="RE]"></td>
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
					type="submit" value="답글 등록하기">&nbsp;&nbsp;&nbsp;&nbsp; 
					<input type="reset" value="다시작성">
					<input type="button" value="답글취소" onclick='document.location.href="/bbs2/content.ktds?article_num=${article_num}&pageNum=${pageNum}"'>
					</td>
			</tr>
		</table>
	</form>
</body>
</html>
