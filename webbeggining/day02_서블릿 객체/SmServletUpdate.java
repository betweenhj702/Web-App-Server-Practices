package sdo.sv.sm;

import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.sql.*;

public class SmServletUpdate extends HttpServlet 
{
	Connection con;
	PreparedStatement pstmt1;

	public void init(){ 
	    String url = "jdbc:oracle:thin:@127.0.0.1:1521:JAVA";
		String sql1 = "select * from BOARD where SEQ = ?";
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, "servlet", "java");
			pstmt1 = con.prepareStatement(sql1);
		}catch(ClassNotFoundException cnfe){
		}catch(SQLException se){
		}
	}
	public void service(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
		
		
		
	}
	public void destroy(){ 
        try{
			if(pstmt1 != null) pstmt1.close();
			if(con != null) con.close();
		}catch(SQLException se){
		}
	}
}