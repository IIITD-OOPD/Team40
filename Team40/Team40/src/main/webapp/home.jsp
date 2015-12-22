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
 <div id="res">
      <c:out value="${requestScope.message}"></c:out>
 
 </div>

<form action="upload" name="uploadxml" method="post" enctype="multipart/form-data">

upload xml: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<input type="file" name="file" size="30" />
<input type="submit" value="Upload File" />
</form>

<jsp:include page="print.jsp"></jsp:include>

</body>
</html>