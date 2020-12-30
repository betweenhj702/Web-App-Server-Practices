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
		//Statement ����κ�, �����˶�â
		response.setContentType("text/html;charset=utf-8");
		out.println("<script>");
		try{	
			pstmt.setInt(1,seq);
			int i = pstmt.executeUpdate();
			if(i>0){				
				out.println("alert('���������ϴ�');");
			}
		}catch(SQLException e){
			out.println("alert('��������');");
				
			
		}catch(NumberFormatException ne){
		}
		out.println("location.href='list.jsp'");
		out.println("</script>");

		//��� ��쿡 ���� �������� ���ư��� ��. ���ư��� 2������� 1)��ũ��Ʈ�� ó�� 2)sendRedirect�� ó��
		//res.sendRedirect("list.do");
%>

