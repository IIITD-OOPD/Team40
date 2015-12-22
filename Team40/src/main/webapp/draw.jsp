<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   
    <%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %> 
   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="style.css">

</head>
<body>
<c:set var="possition" value="${requestScope.possition}"></c:set>
<c:set var="gates" value="${requestScope.gates}"></c:set>
<c:set var="i" value="0"></c:set>
<c:forEach begin="1" end="20" var="outerLoop">
<div class="row">

<c:forEach begin="1" end="40" var="innerLoop">
<c:set var="j" value="${i*40+innerLoop}"></c:set>
<div class="cell" id="${j}">

<c:forEach var="hash" items="${possition}">
	<c:set var="key" value="${hash.key}"></c:set>
	<c:set var="value" value="${hash.value}"></c:set>
	<c:if test="${j==key}">
	   
	   <c:forEach var="gate" items="${gates}">
	       	<c:set var="gatekey" value="${gate.key}"></c:set>
			<c:set var="gatevalue" value="${gate.value}"></c:set>
	     <c:if test="${value==gatekey}">
	     
	     <c:set var="image" value="${gatevalue}.png"></c:set>
	     <img id="theImg" src="<c:url value="images/${image}"/>" />
	     </c:if>
	   
	   
	   </c:forEach>
	</c:if>
         
    </c:forEach>


</div>

</c:forEach>
<c:set var="i" value="${i+1}"></c:set>
</div>
</c:forEach>


</body>
</html>