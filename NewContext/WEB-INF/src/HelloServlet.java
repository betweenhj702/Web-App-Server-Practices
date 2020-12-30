package soo.sv;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.*;

public class HelloServlet extends HttpServlet {
	//서버로부터 요청받고(req 반응하는 메소드
	public void service(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
		//System.out.println("HelloServlet service()");
		
		//반응하는 부분 >> html 문서를 여기서 pw를 통해서 작성한다.
		res.setContentType("text/html;charset=utf-8");
		PrintWriter pw = res.getWriter(); //웹브라우져와 연결된 출력스트림 객체
		
		pw.println("<meta charset='UTF-8'>");
		pw.println("<body align='center'>");
		pw.println("<h2> Hello Servlet </h2>");
		
		pw.println("<br><br>안녕 서블릿");
		pw.println("<br><br>");

		pw.println("<a href='./'>index</a>");
		pw.println("</body>");
		
		

	}
}
