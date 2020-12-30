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
			//�ٹ������ ���� �Ķ����, �����Ͱ� ���ܸ� ��������
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
			//Statement ����κ�, �����˶�â
			
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
					pw.println("alert('���������ϴ�');");	
				}
			}catch(SQLException e){
				pw.println("alert('��������');");			
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

			//��� ��쿡 ���� �������� ���ư��� ��. ���ư��� 2������� 1)��ũ��Ʈ�� ó�� 2)sendRedirect�� ó��
			res.sendRedirect("list.do");
	}
	
}
