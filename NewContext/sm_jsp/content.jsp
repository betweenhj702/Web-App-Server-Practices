<%@page contentType="text/html;charset=utf-8" import="java.sql.*" %>
<%!
	Connection con;
	PreparedStatement pstmt;

	public void jspInit(){ //서블릿 로딩 ( by 첫번째 요청) 
		System.out.println("init()수행");
	    String url = "jdbc:oracle:thin:@127.0.0.1:1521:JAVA";
		String sql = "select * from BOARD where SEQ=?";
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, "servlet", "java");
			pstmt = con.prepareStatement(sql);
		}catch(ClassNotFoundException cnfe){
		}catch(SQLException se){
		}
	}
	public void jspDestroy(){ 
        try{
			if(pstmt != null) pstmt.close();
			if(con != null) con.close();
		}catch(SQLException se){
		}
	} 
%>

<meta charset='utf-8'>
<style>table,th,td{border:1px solid black;border-collapse:collapse}th,td{padding:5px}a{text-decoration:none}</style>
<center>
<hr width='600' size='2' noshade>
<h2>Simple Board with JSP </h2>
&nbsp;&nbsp;&nbsp;
<a href='write_jsp.html'>글쓰기</a>
<hr width='600' size='2' noshade>
<table border='1' width='600' align='center' cellpadding='3'
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
	ResultSet rs = null;
	try{
		System.out.println(seq);
		pstmt.setInt(1, seq);
		rs = pstmt.executeQuery();
		rs.next();
		String writer = rs.getString(2);
		String email = rs.getString(3);
		String subject = rs.getString(4);
		String content = rs.getString(5);
%>
<tr>
<td width='100' align='center'>글번호</td>
<td><%=seq%></td>
</tr>
<tr>
<td align='center'>글쓴이</td>
<td><%=writer%></td>
</tr>
<tr>
<td align='center'>이메일</td>
<td><%=email%></td>
</tr>
<tr>
<td align='center'>글제목</td>
<td><%=subject%></td>
</tr>
<tr>
<td align='center'>글내용</td>
<td><%=content%></td>
</tr>
</table>
<hr width='600' size='2' noshade>
<b>
<a href='upcontent.jsp?seq=<%=seq%>'>수정</a>
|
<a href='delete.jsp?seq=<%=seq%>'>삭제</a>
|
<a href='list.jsp'>목록</a>
</b>
<hr width='600' size='2' noshade>
</center>
<%
	}catch(SQLException se){
		System.out.println("@");
	}finally{
		try{
			if(rs != null) rs.close();
		}catch(SQLException se){}
	}
%>
