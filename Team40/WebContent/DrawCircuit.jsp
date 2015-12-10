

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" 
    import="oopd.Team40.model.Gate,java.util.ArrayList,java.util.HashMap,oopd.Team40.model.Circuit" %>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="style.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

</head>
<body>

<c:set var="circuit" value='${requestScope.ckt}'/>
<c:out value="${circuit.getName()}"/>

<% Circuit circuit = (Circuit)request.getAttribute("ckt");
System.out.print(circuit.getName()); 
ArrayList<Gate> gate = (ArrayList<Gate>)circuit.getGates();
for(int i = 0 ; i < gate.size();i++)
{  HashMap<String,Integer> in = (HashMap<String,Integer>)gate.get(i).getInput();


	}
%>

<c:forEach begin="1" end="20" varStatus="outerLoop">
    <div id="${outerLoop.count}">
       <c:forEach begin="1" end="40" varStatus="innerLoop">
         <div id="${innerLoop.count}"> </div>
        
       </c:forEach>
    </div>
</c:forEach> 

</body>
</html>