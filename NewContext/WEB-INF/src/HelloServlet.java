package soo.sv;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.*;

public class HelloServlet extends HttpServlet {
	//�����κ��� ��û�ް�(req �����ϴ� �޼ҵ�
	public void service(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
		//System.out.println("HelloServlet service()");
		
		//�����ϴ� �κ� >> html ������ ���⼭ pw�� ���ؼ� �ۼ��Ѵ�.
		res.setContentType("text/html;charset=utf-8");
		PrintWriter pw = res.getWriter(); //���������� ����� ��½�Ʈ�� ��ü
		
		pw.println("<meta charset='UTF-8'>");
		pw.println("<body align='center'>");
		pw.println("<h2> Hello Servlet </h2>");
		
		pw.println("<br><br>�ȳ� ����");
		pw.println("<br><br>");

		pw.println("<a href='./'>index</a>");
		pw.println("</body>");
		
		

	}
}
