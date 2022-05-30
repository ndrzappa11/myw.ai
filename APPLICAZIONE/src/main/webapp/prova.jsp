<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="API.API_1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>inserimento dati</title>
</head>
<body>

<%//pagina utilizzata per richiedere i dati per l'api_1 %>

	<form name="primoForm" action="ManagementServlet" method="post">
		<table>
			<tr>
				<td><h2>FORNIRE DATI MACCHINARIO</h2></td>
			</tr>

			<tr>
				<td>matricola:</td>
				<td><input name="matricola" value="" type="text" maxlength="16"></td>
			</tr>

			<tr>
				<td>valore:</td>
				<td><input name="valore" value="1" type=number step="any"></td>
			</tr>
			<tr>
				<td>durata:</td>
				<td><select name="durata">
						<option value="tre" selected="selected">tre mesi</option>
						<option value="sei">sei mesi</option>
						<option value="dodici">dodici mesi</option>
						<option value="ventqua">ventiquattro mesi</option>
				</select></td>

			</tr>
			<tr>
				<td>tipologia</td>
				<td><select name="tip">
						<option value="ven" selected="selected">verniciatura</option>
						<option value="eco">ecografia</option>
						<option value="elettr">elettrodomestici</option>
						<option value="altro">altro ...</option>
				</select></td>
			</tr>
			<tr>
				<td>assicurazione e/o garanzia</td>
				<td><select name="ass_gar">
						<option value="ass" selected="selected">assicurazione</option>
						<option value="gar">garanzia</option>
						<option value="ass_gar">assicurazione e garanzia</option>
				</select></td>
			</tr>
		</table>
		<br> <input name="whatsend" value="api_1" type="hidden"> <input
			type="submit" value="invia">
	</form>
	<br>
	<a href="ManagementServlet?whatsend=home" target="_center"> <input
		type="button" value="HOME"></a>&nbsp;
</body>
</html>