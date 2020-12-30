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
					response.sendRedirect("content.jsp");
				}
			}else{
				response.sendRedirect("content.jsp");
			}
		}else{
			response.sendRedirect("content.jsp");
		}
		DataSource ds = dbcp.getDs();
		Connection con=null;
		PreparedStatement pstmt=null;
		String sql = "delete from BOARD where SEQ=?";
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, seq);
			pstmt.executeUpdate();
			response.sendRedirect("list.jsp");
		}catch(SQLException se){
		}finally{
			try{
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException se){
			}
		}
%>