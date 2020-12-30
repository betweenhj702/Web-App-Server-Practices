<%@page contentType="text/html;charset=utf-8" import="java.sql.*" %>
<%!
	Connection con;
	Statement stmt;

	public void jspInit(){
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:JAVA";
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, "servlet", "java");
			stmt = con.createStatement();
		}catch(ClassNotFoundException cnfe){
		}catch(SQLException se){
		}
	}
	public void jspDestroy(){ 
        try{
			if(stmt != null) stmt.close();
			if(con != null) con.close();
		}catch(SQLException se){
		}
	}
%>

<head>
<meta charset='utf-8'>
<title>Simple Board with JSP  </title>
<style>table,th,td{border:1px solid black;border-collapse:collapse}th,td{padding:5px}a{text-decoration:none}</style>
</head>
<body>
<center>
<hr width='600' size='2' noshade>
<h2>Simple Board with JSP</h2>
&nbsp;&nbsp;&nbsp;
<a href='write_jsp.html'>글쓰기</a>
&nbsp;&nbsp;&nbsp;
<a href='../index.html'>인덱스</a>
<hr width='600' size='2' noshade>
</center>
<table border='1' width='600' align='center' cellpadding='2'>
<tr>
<th align='center' width='10%'>글번호</th>
<th align='center' width='15%'>작성자</th>
<th align='center' width='30%'>이메일</th>
<th align='center' width='30%'>글제목</th>
<th align='center' width='15%'>날짜</th>
</tr>

<%
		ResultSet rs = null;
		String sql = "select * from BOARD order by SEQ desc";
		try{
			rs = stmt.executeQuery(sql);
			boolean flag = false;
			while(rs.next()){
				flag = true;
				int seq = rs.getInt(1);
				String writer = rs.getString(2);
				String email = rs.getString(3);
				String subject = rs.getString(4);
				Date rdate = rs.getDate(6);
%>
				<tr>
				<td align='center'><%=seq%></td>
				<td align='center'><%=writer%></td>
				<td align='center'><%=email%></td>
				<td align='center'>
				<a href='content.jsp?seq=<%=seq%>'><%=subject%></a>
				</td>
				<td align='center'><%=rdate%></td>
				</tr>
<%
			}
			if(!flag){
					out.println("<tr>");
					out.println("<td colspan='5' align='center'>데이터 없음</a>");
					out.println("</tr>");
				}
			}catch(SQLException se){
			}finally{
				try{
					if(rs != null) rs.close();
				}catch(SQLException se){}
			}
%>

</body>
