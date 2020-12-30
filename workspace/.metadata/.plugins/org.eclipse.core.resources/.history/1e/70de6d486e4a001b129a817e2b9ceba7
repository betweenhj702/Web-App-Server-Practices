<%@page contentType="text/html;charset=utf-8" import="java.sql.*, javax.sql.*" %>
<jsp:useBean id="dbcp" class="soo.dbcp.DbcpBean" scope="application"/>

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
		DataSource ds = dbcp.getDs();
        Connection con=null;
		PreparedStatement pstmt1=null;
		ResultSet rs = null;
		String sql1 = "select * from BOARD where SEQ = ?";
		try{
			con = ds.getConnection();
			pstmt1 = con.prepareStatement(sql1);
			pstmt1.setInt(1, seq);
			rs = pstmt1.executeQuery();
			rs.next();
			String writer = rs.getString(2);
			String email = rs.getString(3);
			String subject = rs.getString(4);
			String content = rs.getString(5);
%>
<head>
<meta charset=utf-8>
<title>Simple Board with jsp and dbcp</title>
<style>table,th,td{border:1px solid black;border-collapse:collapse}th,td{padding:5px}a{text-decoration:none}</style>
<script src="../js/check.js"></script>
<script language=javascript>function check(){for(var c=0;c<document.input.elements.length;c++){var e=document.input.elements[c];if(e.value==""){alert("모든 값을 입력 하셔야 합니다. ");return false}}var a=input.writer.value;a=trim(a);pass=checkByteLen(a,15);if(!pass){alert("이름 15바이트이하");input.writer.focus();return false}var d=input.email.value;d=trim(d);pass=checkByteLen(d,20);if(!pass){alert("이메일 20바이트이하");input.email.focus();return false}var b=input.subject.value;b=trim(b);pass=checkByteLen(b,20);if(!pass){alert("제목 20바이트이하");input.subject.focus();return false}var f=input.content.value;f=trim(f);pass=checkByteLen(f,400);if(!pass){alert("글 400바이트이하");input.writer.focus();return false}document.input.submit()};</script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
function f(){
input.email.value = "";
input.subject.value = "";
$("#ta").text("");
input.email.focus();
}
</script>
</head>
<body onload=input.writer.focus()>
<font color=gray size=4 face=휴먼편지체>
<center>
<hr width=600 size=2 color=gray noshade>
<h3> Simple Board with jsp and dbcp </h3>
<font color=gray size=3 face=휴먼편지체>
<a href=list.do>리스트</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<a href=../>인덱스</a>
</font>
<hr width=600 size=2 color=gray noshade>
</center>
<form name=input method=post action= update.jsp>
<input type="hidden" name="seq" value="<%=seq%>">
<table border=0 width=600 align=center cellpadding=3 cellspacing=1 bordercolor=gray>
<tr>
<td width=30% align=center>WRITER</td>
<td><input type=text name=writer value="<%=writer%>" size=60></td>
</tr>
<tr>
<td align=center>EMAIL</td>
<td><input type=text name=email value="<%=email%>" size=60></td>
</tr>
<tr>
<td align=center>SUBJECT</td>
<td><input type=text name=subject value="<%=subject%>" size=60></td>
</tr>
<tr>
<td align=center>CONTENT</td>
<td><textarea id=ta name=content style=width:98% rows=8 cols=60><%=content%></textarea></td>
</tr>
<tr>
<td colspan=2 align=center>
<input type=button value=수정 onclick=check()>
<input type=button value="다시입력" onclick=f()>
</td>
</tr>
</table>
<hr width=600 size=2 color=gray noshade>
</form>
</font>
</body>
<%
	}catch(SQLException se){
	}finally{
		try{
			if(rs != null) rs.close();
			if(pstmt1 != null) pstmt1.close();
			if(con != null) con.close();
		}catch(SQLException se){
		}
	}
%>
