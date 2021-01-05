<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MvcPj index</title>
</head>
<body>
	<div style="text-align:center">
		<h1>MvcPj INDEX(한글)</h1>
		<p>
		<c:if test="${!member.flag or empty member}">
			<a href="addr/addr.do">주소록(MVC)</a><br/>
		 	<a href="sm/sm.do?cp=1&ps=3">게시판(MVC)</a><br/><br/>
		 	<a href="login/login.do?m=form">로그인</a><br/><br/>
		 	<a href="scope/session_test.jsp">세션ID확인</a><br/>
		 </c:if>
		 <c:if test="${member.flag}">
		 	<a href="../addr/addr.do">주소록(MVC)</a><br/>
		 	<a href="../sm/sm.do?cp=1&ps=3">게시판(MVC)</a><br/><br/>
		 	<a href="login.do?m=logout">로그아웃</a>
		 		${member.email}&nbsp;&nbsp;&nbsp;${member.name}&nbsp;&nbsp;&nbsp;${member.phone}
		 		<br/><br/>
		 	<a href="../scope/session_test.jsp">세션ID확인</a><br/>
		 </c:if>
		 	
		 	
		 		
		 	
		 	
		</p>
		</div>
</body>
</html>