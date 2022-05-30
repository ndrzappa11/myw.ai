<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="API.API_2" import="API.API_5"
	import="API.API_4" import="API.pagamenti"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>lista polizze</title>
</head>
<body>
	<a href="ManagementServlet?whatsend=home" target="_center"> <input
		type="button" value="HOME"></a>&nbsp;
	<%
	API_2 user = (API_2) request.getSession().getAttribute("testLogin");
	%>
	<%
	API_4 prova = new API_4();
	%>
	<h1>
		<font color="blue"> <%=user.getNome()%>
		</font>
	</h1>
	<br>
	<br>
	<%=prova.numPolizze(user.getIdU(user))%>
	<%
	int k = prova.numPolizze(user.getIdU(user));
	%>
	<%
	API_4[] api = new API_4[k];
	%>
	<%
	api = prova.getPolizze(user.getIdU(user));
	%>
	<br>
	<%
	for (int i = 0; i < k; i++) {
	%>
	<br>
	<form name="form assistenza" action="ManagementServlet" method="post">
		<table>
			<tr>
				<td>polizza numero</td>
				<td><%=i + 1%></td>
			</tr>
			<tr>
				<td>id macchinario</td>
				<td><%=api[i].getId_macchinario()%></td>
			</tr>
			<tr>
				<td>id polizza</td>
				<td><%=api[i].getId_polizza()%></td>
			</tr>
			<tr>
				<td>data inizio</td>
				<td><%=api[i].getData_inizio()%></td>
			</tr>
			<tr>
				<td>data fine</td>
				<td><%=api[i].getData_fine()%></td>
			</tr>
			<tr>
				<td>massimale</td>
				<td><%=api[i].getMassimale()%></td>
			</tr>
			<tr>
				<td>premio</td>
				<td><%=api[i].getPremio()%></td>
			</tr>
			<tr>
				<td>pagamento</td>
				<td><%=api[i].getPag()%></td>
			</tr>
			<tr>
				<td></td>
			</tr>
		</table>
		<input name="whatsend" value="pagamenti" type="hidden">
					<input name="api" value="<%=api[i]%>" type="hidden"> <input
					type="submit" value="PAGAMENTI">
		<br>
		<input name="whatsend" value="assistenza" type="hidden">
					<input name="api" value="<%=api[i]%>" type="hidden"> <input
					type="submit" value="ASSISTENZA">
	</form>
	<br>
	<br>
	<%
	}
	%>


	<br>
	<br>



</body>
</html>