<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="API.API_2"
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>post registrazione</title>
</head>
<body>
	<h1> CREATO NUOVO USER</h1> <br>
	<%
	API_2 prova = (API_2) request.getSession().getAttribute("testLogin");
	%>
	<table>
		<tr>
			<td>ID </td>
			<td>--></td>
			<td>&nbsp;&nbsp;&nbsp;</td>
			<td><%=prova.getId()%></td>
		</tr>
		<tr>
			<td>nome</td>
			<td>--></td>
			<td>&nbsp;&nbsp;&nbsp;</td>
			<td><%=prova.getNome()%></td>
		</tr>
		<tr>
			<td>cognome</td>
			<td>--></td>
			<td>&nbsp;&nbsp;&nbsp;</td>
			<td><%=prova.getCognome()%></td>
		</tr>
		<tr>
			<td>email</td>
			<td>--></td>
			<td>&nbsp;&nbsp;&nbsp;</td>
			<td><%=prova.getEmail()%></td>
		</tr>
		<tr>
			<td>nascita</td>
			<td>--></td>
			<td>&nbsp;&nbsp;&nbsp;</td>
			<td><%=prova.getNascita()%></td>
		</tr>
		<tr>
			<td>telefono</td>
			<td>--></td>
			<td>&nbsp;&nbsp;&nbsp;</td>
			<td><%=prova.getTelefono()%></td>
		</tr>
		<tr>
			<td>inserimento</td>
			<td>--></td>
			<td>&nbsp;&nbsp;&nbsp;</td>
			<td><%=prova.getInserimento()%></td>
		</tr>
		
	</table>
	<br><br><br>
	
	<br>

	<br><br><br>
	<a href="ManagementServlet?whatsend=home" target="_center"> <input
		type="button" value="HOME"></a>&nbsp;
	
</body>
</html>