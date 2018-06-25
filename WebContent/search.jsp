<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.mysql.jdbc.Driver" %>   
<%@ page import="java.sql.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<jsp:useBean id="search" class="servlets.Search" scope = 'page'>
	<jsp:setProperty name="search" property="content"/>
</jsp:useBean>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="stylesNew.css" type="text/css" rel="stylesheet"/>
<title>Search</title>
</head>
<body>
<div>
      <a href="home.jsp" >Back to home page </a> </br></br>
      
      <a href="angu.html">Transportation Suggestion</a>
</div>


<%   
String key = search.getContent();

String driverName="com.mysql.jdbc.Driver";   
String userName="wenxin";   
String userPasswd="123654";   
String dbName="wenxin";  

String url="jdbc:mysql://localhost/"+dbName+"?user="+userName+"&password="+userPasswd;   
Class.forName("com.mysql.jdbc.Driver").newInstance();   
Connection conn=DriverManager.getConnection(url);   
Statement stmt = conn.createStatement();   

String sql="SELECT * FROM commodity where description like '%" + key +"%'" ; 
ResultSet rs = stmt.executeQuery(sql); 
if (!rs.next()){%>
<b>No Result...</b>	
<% }else{%>


	<table border="2" bgcolor="lightyellow" align="center">
	<tr>
            <th>Description</th>
            <th>Price</th>
            <th>Location</th>
            <th>Contact</th>
   	</tr>
    <tr>
            <td><%= rs.getString("description") %></td>
            <td><%= rs.getString("price") %></td>
            <td><%= rs.getString("location") %></td>
            <td><%= rs.getString("email") %></td>
   </tr>
  </table>

	
<%}%>




</body>
</html>