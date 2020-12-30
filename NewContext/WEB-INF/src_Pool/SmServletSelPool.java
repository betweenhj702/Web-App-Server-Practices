package sdo.sv.smpool;

import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.sql.*;
import soo.db.ConnectionPoolBean;

public class SmServletSelPool extends HttpServlet 
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
		
		req.setCharacterEncoding("utf-8");
		int seq = -1;
		String seqStr = req.getParameter("seq");
		
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

		res.setContentType("text/html;charset=utf-8");
		PrintWriter pw = res.getWriter();

		pw.println("<meta charset='utf-8'>");
		pw.println("<style>table,th,td{border:1px solid black;border-collapse:collapse}th,td{padding:5px}a{text-decoration:none}</style>");
		pw.println("<center>");
		pw.println("<hr width='600' size='2' noshade>");
		pw.println("<h2>Simple Board with Servlet using POOL </h2>");
		pw.println("&nbsp;&nbsp;&nbsp;");
		pw.println("<a href='write.html'>글쓰기</a>");	//링크 html
		pw.println("<hr width='600' size='2' noshade>");
		pw.println("<table border='1' width='600' align='center' cellpadding='3'");
		
		ConnectionPoolBean pool = null;
		Connection con=null;
		PreparedStatement pstmt=null;
		String sql = "select * from BOARD where SEQ=?";
		ResultSet rs = null;
		try{
			pool = getPool();
			con = pool.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, seq);
			rs = pstmt.executeQuery();
			rs.next();
			String writer = rs.getString(2);
			String email = rs.getString(3);
			String subject = rs.getString(4);
			String content = rs.getString(5);
			//
			pw.println("<tr>");
			pw.println("<td width='100' align='center'>글번호</td>");
			pw.println("<td>"+seq+"</td>");
			pw.println("</tr>");
			pw.println("<tr>");
			pw.println("<td align='center'>글쓴이</td>");
			pw.println("<td>"+writer+"</td>");
			pw.println("</tr>");
			pw.println("<tr>");
			pw.println("<td align='center'>이메일</td>");
			pw.println("<td>"+email+"</td>");
			pw.println("</tr>");
			pw.println("<tr>");
			pw.println("<td align='center'>글제목</td>");
			pw.println("<td>"+subject+"</td>");
			pw.println("</tr>");
			pw.println("<tr>");
			pw.println("<td align='center'>글내용</td>");
			pw.println("<td>"+content+"</td>");
			pw.println("</tr>");
			pw.println("</table>");
			pw.println("<hr width='600' size='2' noshade>");
			pw.println("<b>");
			pw.println("<a href='upsel.do?seq="+seq+"'>수정</a>");	//링크 서블릿
			pw.println("|");
			pw.println("<a href='del.do?seq="+seq+"'>삭제</a>");		//링크 서블릿
			pw.println("|");
			pw.println("<a href='list.do'>목록</a>");
			pw.println("</b>");
			pw.println("<hr width='600' size='2' noshade>");
			pw.println("</center>");
			//
		}catch(SQLException se){
			System.out.println("@");
		}finally{
			try{
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) pool.returnConnection(con);
			}catch(SQLException se){}
		}
	}
}

