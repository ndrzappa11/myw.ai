<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login</title>
</head>
<body>

<form name="login" action="ManagementServlet" method="post">
<table>
<tr>
				<td>email:</td>
				<td><input name="email" value="" type="text"
					maxlength="16"></td>
			</tr>
			<tr>
				<td>pass:</td>
				<td><input name="pass" value="" type="password"
					maxlength="16"></td>
			</tr>
</table>
<br>
<input name="whatsend" value="login" type="hidden"> <input
			type="submit" value="login">
	</form>
	<br>
	<a href="ManagementServlet?whatsend=regist" target="_center"> <input
		type="button" value="Registrati"></a>&nbsp;
	<br><br>
	<a href="ManagementServlet?whatsend=home" target="_center"> <input
		type="button" value="HOME"></a>&nbsp;
</body>
</html>