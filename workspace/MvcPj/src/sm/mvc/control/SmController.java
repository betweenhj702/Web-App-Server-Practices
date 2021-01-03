package sm.mvc.control;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.domain.Board;
import sm.mvc.model.SmService;

@WebServlet("/sm/sm.do")
public class SmController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		
		String m = request.getParameter("m");
		if(m != null){
			m = m.trim();
			if(m.equals("selectCon")){
				selectCon(request, response);
			}else if(m.equals("delete")){
				delete(request, response);
			}else if(m.equals("write")){
				write(response);
			}else if(m.equals("insert")){
				insert(request, response);
			}else if(m.equals("selUpCon")){
				selUpCon(request,response);
			}else if(m.equals("update")){
				update(request,response);
			}else{
				list(request, response);
			}
		}else{
			list(request, response);
		}
	}
	private void list(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException{
		
		SmService service = SmService.getInstance();
		ArrayList<Board> list = service.listS();
		if(list == null){
			String msg = "sqlException";
			goMsgAlert(msg);
		}
		request.setAttribute("list", list);

		String view = "list.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}

	private void selectCon(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException{
		int seq = 0;
		String seqStr = request.getParameter("seq");
		seq = check(seqStr);
		
		if(seq != -1) {
			SmService service = SmService.getInstance();
			Board selectCon = service.selectConS(seq);
			if(selectCon == null){
				String msg = "sqlException";
				goMsgAlert(msg);
			}
			request.setAttribute("selectCon", selectCon);
	
			String view = "content.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(view);
			rd.forward(request, response);
		
		}else {
			String msg = "wrongAccess";
			goMsgAlert(msg);
		}
	}
  
	private void delete(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException{
		int seq = -1;
		String seqStr = request.getParameter("seq");
		seq = check(seqStr);
		if(seq != -1){
			SmService service = SmService.getInstance();
			int result = service.deleteS(seq);
			if(result != -1){
				String msg = "delete";
				goMsgAlert(msg);
			}else{
				String msg = "sqlException";
				goMsgAlert(msg);
			}
		}else{
			String msg = "wrongAccess";
			goMsgAlert(msg);
		}
	}
	
	private void write(HttpServletResponse response) throws ServletException, IOException{
		String view = "write_jsp.jsp";
		response.sendRedirect(view);
	}

	private void insert(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException{
		String writer = request.getParameter("writer");
		String email = request.getParameter("email");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		Board dto = new Board(-1, writer, email, subject, content, null);
		
		SmService service = SmService.getInstance();
		int result = service.insertS(dto);
		if(result != -1){
			String msg = "insert";
			goMsgAlert(msg);
		}else{
			String msg = "sqlException";
			goMsgAlert(msg);
		}
	}

	private void selUpCon(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException{
		int seq = -1;
		String seqStr = request.getParameter("seq");
		
		seq = check(seqStr);
		if(seq != -1){
			SmService service = SmService.getInstance();
			Board selUpCon = service.selUpConS(seq);
			if(selUpCon == null){
				String msg = "sqlException";
				goMsgAlert(msg);
			}
			request.setAttribute("selUpCon", selUpCon);
			
			String view = "upcontent.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(view);
			rd.forward(request, response);

		}else{
			String msg = "wrongAccess";
			goMsgAlert(msg);
		}
	}

	private void update(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException{
		String seqStr = request.getParameter("seq");
		String writer = request.getParameter("writer");
		String email = request.getParameter("email");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		
		int seq = -1;
		seq = check(seqStr);
		if( seq != -1){
			Board dto = new Board(seq, writer, email, subject, content);
			
			SmService service = SmService.getInstance();
			service.updateS(dto);
			response.sendRedirect("sm.do");
		
		}else{
			String msg = "wrongAccess";
			goMsgAlert(msg);
		}
	}
	
	//����
	private int check(String seqStr){
		if(seqStr != null){
			seqStr = seqStr.trim();
			if(seqStr.length() != 0){
				try{
					int seq = Integer.parseInt(seqStr);
					return seq;
				}catch(NumberFormatException ne){	
				}
			}else{
			}
		}else{
		}
		return -1;
	}
	private void goMsgAlert(String msg){
		request.setAttribute("msg", msg);
		String view = "msgAlert.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}
}
