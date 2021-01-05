package login.mvc.control;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import login.mvc.model.LoginService;
import mvc.domain.Member;

@WebServlet("/login/login.do")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	
	public void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String m = request.getParameter("m");
		System.out.println("???");
		if(m != null) {
			m = m.trim();
			if(m.equals("form")) {
				form(request, response);
			}else if(m.equals("check")) {
				check(request, response);
			}else if(m.equals("logout")) {
				logout(request, response);
			}
		}else {
			response.sendRedirect("../");
		}
	}
	private void form(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String view = "login_form.jsp";
		response.sendRedirect(view);
	}
	
	private void check(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String pwd = request.getParameter("pwd");
		
		LoginService service = LoginService.getInstance();
		Member member = service.checkS(email, pwd);
		if(member == null) {
			request.setAttribute("email", email);
			
			String view = "login_fail.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(view);
			rd.forward(request, response);
		}
		if(!member.isFlag()) {
			request.setAttribute("pwd", pwd);
			
			String view = "login_fail.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(view);
			rd.forward(request, response);
		}else {
			HttpSession session = request.getSession();
			session.setAttribute("member", member);
			
			String view = "../index.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(view);
			rd.forward(request, response);
		}
		
	}
	private void logout(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("member");
		String view = "../";
		response.sendRedirect(view);
	}
}
