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
 <div id="result">
      <c:out value="${requestScope.message}"></c:out>
        </div>

<form action="upload" name="uploadxml" method="post" enctype="multipart/form-data">

upload xml: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<input type="file" name="file" size="30" />
<input type="submit" value="Upload File" />
</form>

<sql:setDataSource var="snapshot" driver="com.mysql.jdbc.Driver" url="jdbc:mysql://localhost:3306/digitalCircuits" user="root" password="root"/>

<sql:query dataSource="${snapshot}" var="result1">
select * from circuits;
</sql:query>
<form action="draw" method="post">
<table border="0" width="30%">
<tr>
   <th>circuits</th>
   
</tr>
<c:forEach var="row" items="${result1.rows}">
<tr>
	<td><input type="checkbox" name="id" value="${row.c_name}"></td>
   	<td><c:out value="${row.c_name}"/></td>
   
</tr>
</c:forEach>
<tr><td><input type="submit" value="Submit" ><td></tr>
</table>
</form>

</body>
</html>