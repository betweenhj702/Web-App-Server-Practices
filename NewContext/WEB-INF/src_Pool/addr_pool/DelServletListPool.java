package soo.sv.addr.pool;

import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.sql.*;
import soo.db.ConnectionPoolBean;

public class DelServletListPool extends HttpServlet{
	
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
		throws ServletException, IOException{
			//겟방식으로 받은 파라미터, 데이터값 예외를 생각해줌
			String seqStr = req.getParameter("seq");
			int seq = -1;
			if(seqStr != null){
				seqStr = seqStr.trim();
				if(seqStr.length() != 0){
					try{
						seq = Integer.parseInt(seqStr);
					}catch(NumberFormatException ne){
					}
				}
			}
			//Statement 실행부분, 삭제알람창
			
			ConnectionPoolBean pool = null;
			Connection con = null;
			Statement stmt = null;
			PreparedStatement pstmt = null;
			String sql = "delete from ADDRESS where SEQ=?";

			res.setContentType("text/html;charset=utf-8");
			PrintWriter pw = res.getWriter();
			pw.println("<html>");
			pw.println("<script>");
			try{	
				pool = getPool();
				con = pool.getConnection();
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1,seq);
				int i = pstmt.executeUpdate();
				if(i>0){
					pw.println("alert('지워졌습니다');");	
				}
			}catch(SQLException e){
				pw.println("alert('삭제실패');");			
			}catch(NumberFormatException ne){
			}finally{
				try{
					if(pstmt != null) pstmt.close();
					if(con != null) pool.returnConnection(con);
				}catch(SQLException se){
				}
			}
			pw.println("location.href='addr.do'");
			pw.println("</script>");
			pw.println("</html>");

			//모든 경우에 원래 페이지로 돌아가면 됨. 돌아가는 2가지방법 1)스크립트로 처리 2)sendRedirect로 처리
			res.sendRedirect("list.do");
	}
	
}
