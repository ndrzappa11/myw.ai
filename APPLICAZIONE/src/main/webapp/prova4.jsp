<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="API.API_5"
    import="API.API_4"
    import="API.pagamenti"
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>api5</title>
</head>
<body>
	test<br>
	<%API_5 test = new API_5(); %>
	<%int k = test.numPagamenti(21); %>
	
	
	
	<%pagamenti[] pag = new pagamenti[k]; %>
	<%pag = test.getPagamenti(21); %>
	<br>
	<%for(int i=0; i < k; i++){ %>
	<br>
	<%=pag[i].getData() %>
	<%} %>

	
</body>
</html>