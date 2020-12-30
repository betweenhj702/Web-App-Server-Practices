package sdo.sv.smpool;

import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.sql.*;
import soo.db.ConnectionPoolBean;

public class SmServletDelPool extends HttpServlet 
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
		throws ServletException, IOException { //요청될 때마다 
		
		int seq = -1;
		String seqStr = req.getParameter("seq");
		
        if(seqStr != null){
			seqStr = seqStr.trim();
			if(seqStr.length() != 0){
				try{
					seq = Integer.parseInt(seqStr);
				}catch(NumberFormatException ne){
					res.sendRedirect("sel.do");
				}
			}else{
				res.sendRedirect("sel.do");
			}
		}else{
			res.sendRedirect("sel.do");
		}
		ConnectionPoolBean pool = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "delete from BOARD where SEQ=?";
		
		try{
			pool = getPool();
			con = pool.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, seq);
			pstmt.executeUpdate();
			res.sendRedirect("list.do");
		}catch(SQLException se){
		}finally{
			try{
				if(pstmt != null) pstmt.close();
				if(con != null) pool.returnConnection(con);
			}catch(SQLException se){}
		}	
	}

	
}

