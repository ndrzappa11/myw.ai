<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="API.API_1" import="API.API_4"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>dati inseriti</title>
</head>
<body>

<%
	//pag utilizzata per richiedere la polizza che più piace 
	//e il tipo di pagamento desiderato
%>

	<%
	API_1 test = new API_1();
	%>
	<%
	test = (API_1) request.getSession().getAttribute("test");
	%>
	<%
	int id = test.getIDMacchinario();
	%>
	<%
	String matricola = test.getMatricola();
	%>
	<%
	String durata = test.getDurata();
	%>
	<%
	String tip = test.getTip();
	%>
	<%
	double val = test.getValore();
	%>
	<%
	String as = test.getAss_gar();
	%>
	<%
	int[] id_polizze = test.getId_polizza();
	%>
	<%
	double[] premio = test.getPremio();
	%>
	<%
	double[] massimale = test.getMassimale();
	%>
	<h3> aggiungere un allert che indichi se è loggato o meno un utente</h3>
	<h3>la pagina dopo specifica una polizza attiva</h3>
	<form name="secForm" action="ManagementServlet" method="post">
		<table>
			<tr>
				<td>
					<h2>DATI INSERITI</h2>
				</td>
			</tr>
			<tr>
				<td>id</td>
				<td>-->&nbsp;</td>
				<td></td>
				<td><%=id%></td>
			</tr>
			<tr>
				<td>matricola</td>
				<td>--> &nbsp;</td>
				<td></td>
				<td><%=matricola%></td>
			</tr>
			<tr>
				<td>durata</td>
				<td>-->&nbsp;</td>
				<td></td>
				<td><%=durata%></td>
			</tr>
			<tr>
				<td>tipologia</td>
				<td>-->&nbsp;</td>
				<td></td>
				<td><%=tip%></td>
			</tr>
			<tr>
				<td>valore</td>
				<td>-->&nbsp;</td>
				<td></td>
				<td><%=val%></td>
			</tr>
			<tr>
				<td>tipo di polizza</td>
				<td>-->&nbsp;</td>
				<td></td>
				<td><%=as%></td>
			</tr>
			<tr>
				<td>TIPO DI PAGAMENTO</td>
				<td><select name="pag">
						<option value="annuo" selected="selected">annuale</option>
						<option value="pay_to_use">pay to use</option>
				</select></td>
			</tr>
		</table>
		<br> <br> <br> <br> OFFERTE<br> <br>
		<%
			test.genera_offerta();
			%>
		<form name="secondoform" action="ManagementServlet" method="post">
			<table>
				<tr>
					<td>ID</td>
					<td>PREMIO</td>
					<td>MASSIMALE</td>

				</tr>
				<%
				if (id_polizze[0] > -1) {
				%>
				<tr>
					<td><%=id_polizze[0]%></td>
					<td></td>
					<td><%=premio[0]%></td>
					<td></td>
					<td><%=massimale[0]%></td>
					<td></td>
					<td><input type="radio" name="offerta" value="<%=id_polizze[0]%>" /></td>
				</tr>
				<%
				} else {
				%>
				<tr>NESSUNA POLIZZA ADEGUATA TI SUGGERIAMO
				</tr>
				<%}
				if (id_polizze[1] > -1) {
				%>
				<tr>
					<td><%=id_polizze[1]%></td>
					<td></td>
					<td><%=premio[1]%></td>
					<td></td>
					<td><%=massimale[1]%></td>
					<td></td>
					<td><input type="radio" name="offerta" value="<%=id_polizze[1]%>" /></td>
				</tr>
				<%
				}
				if (id_polizze[2] > -1) {
				%>
				<tr>
					<td><%=id_polizze[2]%></td>
					<td></td>
					<td><%=premio[2]%></td>
					<td></td>
					<td><%=massimale[2]%></td>
					<td></td>
					<td><input type="radio" name="offerta" value="<%=id_polizze[2]%>" /></td>
				</tr>

				
				<%
				}
				%>
			</table>
			<br> <br> <br> <br> vuoi continure ? ti aggrada
			la proposta? <input name="whatsend" value="prop" type="hidden">
			<input type="submit" value="avanti">
		</form>




	</form>
	
	<br>
	<br>
	<br>
	<a href="ManagementServlet?whatsend=home" target="_center"> <input
		type="button" value="HOME"></a>&nbsp;



</body>
</html>