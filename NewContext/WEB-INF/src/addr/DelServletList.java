package soo.sv.addr;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.*;
import java.sql.*;

public class DelServletList extends HttpServlet{
	Connection con;
	PreparedStatement pstmt;
	
	public void init(){
		//System.out.println("init()");
		String url = "jdbc:oracle:thin:@localhost:1521:JAVA";
		String sql = "delete from ADDRESS where SEQ = ?";
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, "servlet","java");
			pstmt = con.prepareStatement(sql);
		}catch(ClassNotFoundException cnfe){
		}catch(SQLException se){}
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
			res.setContentType("text/html;charset=utf-8");
			PrintWriter pw = res.getWriter();
			pw.println("<html>");
			try{	
				pstmt.setInt(1,seq);
				int i = pstmt.executeUpdate();
				if(i>0){
					pw.println("<script>");
						pw.println("alert('���������ϴ�');");
					//	pw.println("location.href='addr.do'");
					pw.println("</script>");
				}
			}catch(SQLException e){
				pw.println("<script>");
					pw.println("alert('��������');");
					//pw.println("location.href='addr.do'");
				pw.println("</script>");
			}catch(NumberFormatException ne){
			}
			pw.println("</html>");

			//��� ��쿡 ���� �������� ���ư��� ��. ���ư��� 2������� 1)��ũ��Ʈ�� ó�� 2)sendRedirect�� ó��
			res.sendRedirect("list.do");
	}
	public void destroy(){
		try{
			if(pstmt!=null) pstmt.close();
			if(con !=null) con.close();
		}catch(SQLException se){}
	}
}
