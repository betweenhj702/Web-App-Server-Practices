package sdo.sv.smpool;

import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.sql.*;
import soo.db.ConnectionPoolBean;

public class SmServletUpdatePool extends HttpServlet 
{
	public ConnectionPoolBean getPool(){
        try{
			ServletContext application = getServletContext();
			ConnectionPoolBean pool = (ConnectionPoolBean)application.getAttribute("pool");
			if(pool == null){
				pool = new ConnectionPoolBean();
				application.setAttribute("pool", pool);
			}
			return pool;
		}catch(ClassNotFoundException cnfe){
			return null;	
		}catch(SQLException se){
			return null;
		}
	}
	public void service(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String seqStr = req.getParameter("seq");
		String writer = req.getParameter("writer");
		String email = req.getParameter("email");
		String subject = req.getParameter("subject");
		String content = req.getParameter("content");

		int seq = -1;
		if(seqStr != null){
			seqStr = seqStr.trim();
			if(seqStr.length() != 0){
				try{
					seq = Integer.parseInt(seqStr);
				}catch(NumberFormatException ne){
					res.sendRedirect("list.do");
				}
			}else{
				res.sendRedirect("list.do");
			}
		}else{
			res.sendRedirect("list.do");
		}
		
		ConnectionPoolBean pool = null; 
		Connection con=null;
		PreparedStatement pstmt=null;
		String sql = "update BOARD set WRITER=?, EMAIL=?, SUBJECT=?, CONTENT=? where SEQ=?";
		try{
			pool = getPool();
			con = pool.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, writer);
			pstmt.setString(2, email);
			pstmt.setString(3, subject);
			pstmt.setString(4, content);
			pstmt.setInt(5, seq);
			pstmt.executeUpdate();
			res.sendRedirect("sel.do?seq="+seq);
		}catch(SQLException se){
		}finally{
			try{
				if(pstmt != null) pstmt.close();
				if(con != null) pool.returnConnection(con);
			}catch(SQLException se){}
		}	
	}
}