<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>로그인</title>
</head>
<body>
	<div style="width: 300px; border: 1px solid #cccccc; margin: 10 0 0 0">
		<form method="post" action="/bbs2/login.ktds">
			<input type="hidden" name="logined" value="${logined}">
			<table align="center">
				<tr>
					<td>ID:</td>
					<td><input type="text" name="id"></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input type="password" name="password"></td>
				</tr>
				<tr>
					<td><input type="submit" value="로그인"></td>
					<td><input type="reset" value="취소">
					<input type="button" value="회원가입"></td>
				</tr>
			</table>

		</form>
	</div>
</body>
</html>