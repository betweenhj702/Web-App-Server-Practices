<%@page contentType="text/html;charset=utf-8" import="mvc.domain.Board" %>

<meta charset='utf-8'>
<style>table,th,td{border:1px solid black;border-collapse:collapse}th,td{padding:5px}a{text-decoration:none}</style>
<center>
<hr width='600' size='2' noshade>
<h2>Simple Board with JSP and dbcp in MVC </h2>
&nbsp;&nbsp;&nbsp;
<a href='sm.do?m=write'>글쓰기</a>
<hr width='600' size='2' noshade>
<table border='1' width='600' align='center' cellpadding='3'>

<%
	Board selectCon = (Board) request.getAttribute("selectCon");
%>
<tr>
<td width='100' align='center'>글번호</td>
<td><%=selectCon.getSeq()%></td>
</tr>
<tr>
<td align='center'>글쓴이</td>
<td><%=selectCon.getWriter()%></td>
</tr>
<tr>
<td align='center'>이메일</td>
<td><%=selectCon.getEmail()%></td>
</tr>
<tr>
<td align='center'>글제목</td>
<td><%=selectCon.getSubject()%></td>
</tr>
<tr>
<td align='center'>글내용</td>
<td><%=selectCon.getContent()%></td>
</tr>
</table>
<hr width='600' size='2' noshade>
<b>
<a href='sm.do?m=upSelCon&seq=<%=selectCon.getSeq()%>'>수정</a>
|
<a href='sm.do?m=delete&seq=<%=selectCon.getSeq()%>'>삭제</a>
|
<a href='sm.do'>목록</a>
</b>
<hr width='600' size='2' noshade>
</center>

