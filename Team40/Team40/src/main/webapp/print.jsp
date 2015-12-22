<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
    <%@ taglib uri="http://java.sun.com/jstl/sql" prefix="sql"%>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<sql:setDataSource var="snapshot" driver="com.mysql.jdbc.Driver" url="jdbc:mysql://localhost:3306/digitalCircuits" user="root" password="root"/>
<sql:query dataSource="${snapshot}" var="result">
select * from circuits;
</sql:query>
<form action="chooseCircuit" method="get">
<table border="0" width="30%">
<tr>
   <th>circuits</th>
   
</tr>
<c:forEach var="row" items="${result.rows}">
<tr><c:set var="circuitName"  value="${row.c_name}"></c:set>
	<td><input type="checkbox" name="circuit_name" value="<c:out value="${circuitName}"/>"><c:out value="${circuitName}"/></td>
   	
    
</tr>
</c:forEach>
<tr><td><input type="submit" value="Submit" ></td></tr>
</table>
</form>

</body>
</html>