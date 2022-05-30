<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="API.API_2"
    import="API.ManagementServlet"
   
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HOME</title>
<style><%@include file="CSSfile.css"%></style> 
</head>
<body>

    <!-- 
	<div class="loader">
		<span></span> <span></span> <span></span> <span></span>
	</div>
    -->
	<%
	API_2 test = (API_2) request.getSession().getAttribute("testLogin");
	%>
	<h1>
		<font color="blue"> <%if(test != null){ %> CIAO &nbsp;<%=test.getNome() %>
			<%}else{ %> HOME <%} %>
		</font>
	</h1>
	<br>
	<h2>
		<%if(test ==null){ %>
		<a href="ManagementServlet?whatsend=login" target="_center"
			type="button"> Login</a>&nbsp;
		<%}else{ %>
		<a href="ManagementServlet?whatsend=form" target="_center"
			type="button"> Inserisci Nuovo Macchinario</a>&nbsp; <br>
		<br> <a href="ManagementServlet?whatsend=macchinari"
			target="_center" type="button"> Controlla le tue polizze</a>&nbsp; <br>
		<br> <a href="ManagementServlet?whatsend=logout" target="_center"
			type="button"> Logout</a>&nbsp;
		<%} %>
	</h2>
</body>

</html>