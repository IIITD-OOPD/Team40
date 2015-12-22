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
<div id="result">
           <c:out value="${requestScope.message}"></c:out>
        </div>

<form action="upload" name="uploadxml" method="post" enctype="multipart/form-data">

upload xml: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<input type="file" name="file" size="30" />
<input type="submit" value="Upload File" />
</form>

<jsp:include page="print.jsp"></jsp:include>
<form action="takeInput" method = "post">
<% if((Integer)request.getSession().getAttribute("size")!=0) 
{
int size = (Integer)request.getSession().getAttribute("size");
%>
<table><%
String j;
for(int i = 0 ; i < size ; i++) 
   { j="I"+(i+1);
  // System.out.println(j+"I am string");
   %>
   
   <tr>
   <td> <input type="checkbox" name="id" value="<%=j%>" /></td>
   </tr>
   
	<%}} %>   
   <tr>
   <td><input type="submit" value="submit"/> </td></tr>
   </table>
   
   </form>
<jsp:include page="draw.jsp"></jsp:include>
<%List<int[]> outputList = null; 
outputList=(List<int[]>)request.getAttribute("list"); 
if(outputList!=null) 
{
for(int i=0;i<outputList.size();i++)
{
    out.println(outputList.get(i));
}
}
%>

</body>
</html>