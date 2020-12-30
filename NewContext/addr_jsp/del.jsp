<%@page contentType="text/html;charset=utf-8" import="java.sql.*"%>
<%!
	Connection con;
	PreparedStatement pstmt;
	public void jspInit(){
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
	public void jspDestroy(){
		try{
			if(pstmt!=null) pstmt.close();
			if(con !=null) con.close();
		}catch(SQLException se){}
	}
%>
<%
		String seqStr = request.getParameter("seq");
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
		response.setContentType("text/html;charset=utf-8");
		out.println("<script>");
		try{	
			pstmt.setInt(1,seq);
			int i = pstmt.executeUpdate();
			if(i>0){				
				out.println("alert('지워졌습니다');");
			}
		}catch(SQLException e){
			out.println("alert('삭제실패');");
				
			
		}catch(NumberFormatException ne){
		}
		out.println("location.href='list.jsp'");
		out.println("</script>");

		//모든 경우에 원래 페이지로 돌아가면 됨. 돌아가는 2가지방법 1)스크립트로 처리 2)sendRedirect로 처리
		//res.sendRedirect("list.do");
%>

