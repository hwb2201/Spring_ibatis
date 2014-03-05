<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>게시판</title>
</head>

<body>

	<c:if test="${id!=null}">
		<%@ include file="Logined.jsp"%>
	</c:if>

	<c:if test="${id==null}">
		<c:if test="${result!=null}">${result}</c:if>
		<%@ include file="LoginPage.jsp"%>
	</c:if>

	<center>
		<b>글목록(전체 글:${count})</b><br>
		<table width="700">
			<tr>
				<td align="right"><a href="/bbs2/writeForm.ktds">글쓰기</a>&nbsp;</td>
			</tr>
		</table>

		<c:if test="${count == 0}">
			<table width="700" border="1" cellpadding="0" cellspacing="0">
				<tr>
					<td align="center">게시판에 저장된 글이 없습니다.</td>
				</tr>
			</table>
		</c:if>

		<table border="1" width="700" cellpadding="2" cellspacing="2"
			align="center">
			<tr height="30">
				<td align="center" width="50">번 호</td>
				<td align="center" width="250">제 목</td>
				<td align="center" width="100">작성자</td>
				<td align="center" width="150">작성일</td>
				<td align="center" width="50">조 회</td>
			</tr>

			<c:forEach var="article" items="${articleList}">
				<tr height="30">
					<td align="center" width="50"><c:out
							value="${article.article_num}" /></td>
					<td width="250">
						<c:if test="${article.depth > 0}">
							<img src="images/level.gif" width="${10 * article.depth}" height="16">
							<img src="images/re.gif">
						</c:if> 
						<c:if test="${article.depth == 0}">
							<img src="images/level.gif" width="0" height="16">
						</c:if> <a href="/bbs2/content.ktds?article_num=${article.article_num}&pageNum=${pageNum}">${article.title}</a> <c:if test="${article.hit >= 20}">
							<img src="images/hot.gif" border="0" height="16">
						</c:if></td>
					<td align="center" width="100">${article.id}</td>
					<td align="center" width="150">${article.write_date}</td>
					<td align="center" width="50">${article.hit}</td>
				</tr>
			</c:forEach>
			<tr>

				<td colspan="5" align="center" height="40">${pageCode}</td>

			</tr>
		</table>
	</center>
</body>
</html>