<%@page contentType="text/html;charset=utf-8" import="java.sql.*" %>
<%!
	Connection con;
	PreparedStatement pstmt;

	public void jspInit(){ 
	    String url = "jdbc:oracle:thin:@127.0.0.1:1521:JAVA";
		String sql = "insert into BOARD values(BOARD_SEQ.nextval, ?, ?, ?, ?, SYSDATE)";
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
<%
		request.setCharacterEncoding("utf-8");
		String writer = request.getParameter("writer");
		String email = request.getParameter("email");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		try{
			pstmt.setString(1, writer);
			pstmt.setString(2, email);
			pstmt.setString(3, subject);
			pstmt.setString(4, content);
			pstmt.executeUpdate();
		}catch(SQLException se){
		}
		response.sendRedirect("list.jsp");
%>