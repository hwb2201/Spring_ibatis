<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!--  XmlHttpRequest(순수ajax)를 이용한 방법 -->
<script src="resources/jquery-2.1.0.js" type="text/javascript"></script>
<script src="resources/comment.js" type="text/javascript"></script>

<!-- JQuery Ajax를 이용한 방법 -->


<title>글보기</title>
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


	<form name="contentForm" method="post" action="/bbs2/replyForm.ktds">
		<input type="hidden" name="pageNum" value="${pageNum}"> <input
			type="hidden" id="article_num" value="${article.article_num}">
		<input type="hidden" name="depth" value="${article.depth}"> <input
			type="hidden" name="group_id" value="${article.group_id}"> <input
			type="hidden" name="pos" value="${article.pos}">
		<table width="600" border="0" cellspacing="1" cellpadding="2"
			align="center">

			<tr height="39" align="center">
				<td colspan="2" class="bgcolor1"><b>글보기</b></td>
			</tr>

			<tr>
				<td class="bgcolor1">글쓴이</td>
				<td class="bgcolor2">&nbsp;${article.id}</td>
			</tr>
			<tr>
				<td class="bgcolor1">조회수</td>
				<td class="bgcolor2">&nbsp;${article.hit}</td>
			</tr>

			<tr>
				<td class="bgcolor1">글제목</td>
				<td class="bgcolor2"><input type="text" name="title" size="20"
					value="${article.title}" readonly></td>
			</tr>

			<tr>
				<td class="bgcolor1">파일</td>
				<td class="bgcolor2">&nbsp; <a
					href="/bbs2/download.ktds?fname=${article.fname}">${article.fname}</a></td>
			</tr>

			<tr>
				<td class="bgcolor1">내용</td>
				<td class="bgcolor2"><textarea name="content" rows="10" cols=40
						readonly>${article.content}</textarea>
			</tr>

			<tr>
				<c:if test="${id != null}">
					<td colspan='2' align="right" class="bgcolor1"><input
						type="submit" value="답글"> <c:if
							test="${id == article.id }">
							<input type="button" value="수정"
								onclick='document.location.href="/bbs2/modifyForm.ktds?article_num=${article.article_num}&pageNum=${pageNum}"'>
							<input type="button" value="삭제"
								onclick='document.location.href="/bbs2/delete.ktds?article_num=${article.article_num}&pageNum=${pageNum}"'>
						</c:if> <c:if test="${id != article.id }">
							<input type="button" value="수정" disabled="disabled">
							<input type="button" value="삭제" disabled="disabled">
						</c:if> <input type="button" value="글 목록"
						onclick='document.location.href="/bbs2/list.ktds?pageNum=${pageNum}"'>
					</td>
				</c:if>

				<c:if test="${id == null}">
					<td colspan='2' align="right" class="bgcolor1"><c:if
							test="${id != article.id }">
							<input type="submit" value="답글" disabled="disabled">
							<input type="button" value="수정" disabled="disabled">
							<input type="button" value="삭제" disabled="disabled">
							<input type="button" value="글 목록"
								onclick='document.location.href="/bbs2/list.ktds?pageNum=${pageNum}"'>
						</c:if></td>
				</c:if>
			</tr>
		</table>
	</form>

	<div align="center" width="600">
		<input type="button" value="댓글보기" id='comm_show'> <input
			type="button" value="댓글창" id='btnwrite'>
		<div id='write_show'>
			<form name='commentForm'>
				<table width='600' border='1'>
					<tr>
						<td class='bgcolor1' width=100>댓글</td>
						<td class='bgcolor2'><textarea class='textcomment' rows='5'
								cols='40'></textarea> <input type='button' value='전송'
							class='writer'></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<!--  댓글 뿌려주는 div 영역	 -->
	<div id="show_comment" align="center"></div>
</body>
</html>
