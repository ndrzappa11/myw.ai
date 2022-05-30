<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="API.API_4" import="API.API_1" import="API.API_3" import="API.API_2"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>test api 4</title>
</head>
<body>

	<h2><font color="red">FUNZIONA</font></h2>
	<%API_3 prova = new API_3();%>
	<%
	//api3 utilizzata per SSALVARE i dati nel database
	//api4 utilizzata per GUARDARE i dati nel database
	prova = (API_3) request.getSession().getAttribute("test2");
	API_2 test = (API_2) request.getSession().getAttribute("testLogin");
	%>
	<table>
		<tr>
			<td>ID MACCHINARIO</td>
			<td>--></td>
			<td>&nbsp;&nbsp;&nbsp;</td>
			<td><%=prova.getId_macchinario()%></td>
		</tr>
		<tr>
			<td>ID USER</td>
			<td>--></td>
			<td>&nbsp;&nbsp;&nbsp;</td>
			<td><%=test.getIdU(test)%></td>
		</tr>
		<tr>
			<td>DATA INIZIO</td>
			<td>--></td>
			<td>&nbsp;&nbsp;&nbsp;</td>
			<td><%=prova.getData_inizio()%></td>
		</tr>
		<tr>
			<td>DATA FINE</td>
			<td>--></td>
			<td>&nbsp;&nbsp;&nbsp;</td>
			<td><%=prova.getData_fine()%></td>
		</tr>
		<tr>
			<td>SELEZIONE</td>
			<td>--></td>
			<td>&nbsp;&nbsp;&nbsp;</td>
			<td><%=prova.getSelezione()%></td>
		</tr>
		<tr>
			<td>MASSIMALE</td>
			<td>--></td>
			<td>&nbsp;&nbsp;&nbsp;</td>
			<td><%=prova.getMassimale()%></td>
		</tr>
		<tr>
			<td>PREMIO</td>
			<td>--></td>
			<td>&nbsp;&nbsp;&nbsp;</td>
			<td><%=prova.getPremio()%></td>
		</tr>
		<tr>
			<td>DURATA</td>
			<td>--></td>
			<td>&nbsp;&nbsp;&nbsp;</td>
			<td><%=prova.getDurata()%></td>
		</tr>
		<tr>
			<td>ID POLIZZA</td>
			<td>--></td>
			<td>&nbsp;&nbsp;&nbsp;</td>
			<td><%=prova.getId_Polizza(test.getIdU(test), prova.getId_macchinario()) %></td>
		</tr>
		<tr>
			<td>PAGAMENTO</td>
			<td>--></td>
			<td>&nbsp;&nbsp;&nbsp;</td>
			<td><%=prova.getPag()%></td>
		</tr>
	</table>
	<br><br><br>
	
	<br>
	<a href="ManagementServlet?whatsend=api5" target="_center"> <input
		type="button" value="PAGAMENTI"></a>&nbsp;
	<br><br><br>
	<a href="ManagementServlet?whatsend=home" target="_center"> <input
		type="button" value="HOME"></a>&nbsp;
	
</body>
</html>