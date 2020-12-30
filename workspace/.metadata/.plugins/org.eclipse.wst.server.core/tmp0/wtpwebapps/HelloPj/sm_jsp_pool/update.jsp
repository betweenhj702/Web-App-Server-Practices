<%@page contentType="text/html;charset=utf-8" import="java.sql.*" %>
<jsp:useBean id="pool" class="soo.db.ConnectionPoolBean" scope="application"/>

<%
		request.setCharacterEncoding("utf-8");
		String seqStr = request.getParameter("seq");
		String writer = request.getParameter("writer");
		String email = request.getParameter("email");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");

		int seq = -1;
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
		Connection con=null;
		PreparedStatement pstmt=null;
		String sql = "update BOARD set WRITER=?, EMAIL=?, SUBJECT=?, CONTENT=? where SEQ=?";
		try{
			con = pool.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, writer);
			pstmt.setString(2, email);
			pstmt.setString(3, subject);
			pstmt.setString(4, content);
			pstmt.setInt(5, seq);
			pstmt.executeUpdate();
			response.sendRedirect("content.jsp?seq="+seq);
		}catch(SQLException se){
		}finally{
			try{
				if(pstmt != null) pstmt.close();
				if(con != null) pool.returnConnection(con);
			}catch(SQLException se){
			}
		}
%>