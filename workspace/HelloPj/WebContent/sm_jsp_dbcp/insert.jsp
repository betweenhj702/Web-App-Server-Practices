<%@page contentType="text/html;charset=utf-8" import="java.sql.*, javax.sql.*" %>
<jsp:useBean id="dbcp" class="soo.dbcp.DbcpBean" scope="application"/>

<%
		String writer = request.getParameter("writer");
		String email = request.getParameter("email");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		
		DataSource ds = dbcp.getDs();
		Connection con=null;
		PreparedStatement pstmt=null;
		String sql = "insert into BOARD values(BOARD_SEQ.nextval, ?, ?, ?, ?, SYSDATE)";
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, writer);
			pstmt.setString(2, email);
			pstmt.setString(3, subject);
			pstmt.setString(4, content);
			pstmt.executeUpdate();
		}catch(SQLException se){
		}finally{
			try{
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException se){
			}
		}
		response.sendRedirect("list.jsp");
%>