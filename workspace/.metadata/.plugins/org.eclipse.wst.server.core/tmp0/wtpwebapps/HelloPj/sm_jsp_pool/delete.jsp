<%@page contentType="text/html;charset=utf-8" import="java.sql.*" %>
<jsp:useBean id="pool" class="soo.db.ConnectionPoolBean" scope="application"/>

<%
		int seq = -1;
		String seqStr = request.getParameter("seq");
		
        if(seqStr != null){
			seqStr = seqStr.trim();
			if(seqStr.length() != 0){
				try{
					seq = Integer.parseInt(seqStr);
				}catch(NumberFormatException ne){
					response.sendRedirect("content.jsp");
				}
			}else{
				response.sendRedirect("content.jsp");
			}
		}else{
			response.sendRedirect("content.jsp");
		}

		Connection con=null;
		PreparedStatement pstmt=null;
		String sql = "delete from BOARD where SEQ=?";
		try{
			con = pool.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, seq);
			pstmt.executeUpdate();
			response.sendRedirect("list.jsp");
		}catch(SQLException se){
		}finally{
			try{
				if(pstmt != null) pstmt.close();
				if(con != null) pool.returnConnection(con);
			}catch(SQLException se){
			}
		}
%>