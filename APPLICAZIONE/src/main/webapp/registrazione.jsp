<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>registrazione</title>
</head>
<body>


	<form name="regist" action="ManagementServlet" method="post">
		<table>
		<tr>
				<td>nome:</td>
				<td><input name="nome" value="" type="text" maxlength="16"></td>
			</tr>
			<tr>
				<td>cognome:</td>
				<td><input name="cognome" value="" type="text" maxlength="16"></td>
			</tr>
			<tr>
				<td>email:</td>
				<td><input name="email" value="" type="text" maxlength="16"></td>
			</tr>
			<tr>
				<td>pass:</td>
				<td><input name="pass" value="" type="text" maxlength="16"></td>
			</tr>
			<tr>
				<td>telefono:</td>
				<td><input name="telefono" value="" type="text" maxlength="16"></td>
			</tr>
			<tr>
				<td>data nascita:</td>
				<td><input name="nascita" value="" type="Date" maxlength="16"></td>
			</tr>
			
		</table>
		<br> <input name="whatsend" value="regist" type="hidden">
		<input type="submit" value="Registrati">
	</form>
	<br>

	<br>
	<br>
	<a href="ManagementServlet?whatsend=home" target="_center"> <input
		type="button" value="HOME"></a>&nbsp;

</body>
</html>