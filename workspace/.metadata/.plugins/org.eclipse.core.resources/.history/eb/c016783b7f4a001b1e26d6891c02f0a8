<%@page contentType="text/html;charset=utf-8" import="soo.mv.model.SmDTO" %>

<meta charset='utf-8'>
<style>table,th,td{border:1px solid black;border-collapse:collapse}th,td{padding:5px}a{text-decoration:none}</style>
<center>
<hr width='600' size='2' noshade>
<h2>Simple Board with JSP and dbcp in MODEL1 </h2>
&nbsp;&nbsp;&nbsp;
<a href='write_jsp.jsp'>글쓰기</a>
<hr width='600' size='2' noshade>
<table border='1' width='600' align='center' cellpadding='3'>

<%
		int seq = -1;
		String seqStr = request.getParameter("seq");
		if(seqStr != null){
			seqStr = seqStr.trim();
			if(seqStr.length() != 0){
				try{
					seq = Integer.parseInt(seqStr);
				}catch(NumberFormatException ne){
					response.sendRedirect("list.jsp");
				}
			}else{
				response.sendRedirect("list.jsp");
			}
		}else{
			response.sendRedirect("list.jsp");
		}
		
		SmDAO smDao = (SmDAO) application.getAttribute("smDao");
		SmDTO dto = smDao.selectCon(seq);
		
%>
<tr>
<td width='100' align='center'>글번호</td>
<td><%=dto.seq%></td>
</tr>
<tr>
<td align='center'>글쓴이</td>
<td><%=dto.writer%></td>
</tr>
<tr>
<td align='center'>이메일</td>
<td><%=dto.email%></td>
</tr>
<tr>
<td align='center'>글제목</td>
<td><%=dto.subject%></td>
</tr>
<tr>
<td align='center'>글내용</td>
<td><%=dto.content%></td>
</tr>
</table>
<hr width='600' size='2' noshade>
<b>
<a href='upcontent.jsp?seq=<%=dto.seq%>'>수정</a>
|
<a href='delete.jsp?seq=<%=dto.seq%>'>삭제</a>
|
<a href='list.jsp'>목록</a>
</b>
<hr width='600' size='2' noshade>
</center>

