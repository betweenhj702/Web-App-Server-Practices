package sm.mvc.control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.domain.Board;

@WebServlet("sm/sm.do")
public class SmController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void service(HttpServletRequest requset, HttpServletResponse response)
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
			}else if(m.equals("upSelCon")){
				upSelCon(request,response);
			}else if(m.equals("update")){
				update(request,response);
			}else{
				list(request, response);
			}
		}else{
			list(requset, response);
		}
	}
	private void list(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException{
		
		SmService service = SmService.getInstance();
		ArrayList<Board> list = service.listS();
		request.setAttribute("list", list);

		String view = "list.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}

	private void selectCon(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException{
		int seq = -1;
		String seqStr = request.getParameter("seq");
		if(seqStr != null){
			seqStr = seqStr.trim();
			if(seqStr.length() != 0){
				try{
					seq = Integer.parseInt(seqStr);
				}catch(NumberFormatException ne){
					//에러처리
				}
			}else{
				//에러처리
			}
		}else{
			//에러처리
		}
		SmService service = SmService.getInstance();
		Board selectCon = service.selectConS(seq);
		request.setAttribute("selectCon", selectCon);

		String view = "content.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}
  
	private void delete(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException{
		int seq = -1;
		String seqStr = request.getParameter("seq");
		if(seqStr != null){
			seqStr = seqStr.trim();
			if(seqStr.length() != 0){
				try{
					seq = Integer.parseInt(seqStr);
				}catch(NumberFormatException ne){
					//에러처리
				}
			}else{
				//에러처리
			}
		}else{
			//에러처리
		}
		
		SmService service = SmService.getInstance();
		service.deleteS(seq);
		//알럿?
		response.sendRedirect("sm.do");
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
		service.insertS(dto);
		//알럿?
		response.sendRedirect("sm.do");
	}

	private void upSelCon(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException{
		int seq = -1;
		String seqStr = request.getParameter("seq");
		if(seqStr != null){
			seqStr = seqStr.trim();
			if(seqStr.length() != 0){
				try{
					seq = Integer.parseInt(seqStr);
				}catch(NumberFormatException ne){
					//에러처리
				}
			}else{
				//에러처리
			}
		}else{
			//에러처리
		}
		
		SmService service = SmService.getInstance();
		Board upSelCon = service.upSelConS(seq);
		request.setAttribute("upSelCon", upSelCon);
		//알럿?
		String view = "upcontent.jsp"
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}

	private void update(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException{
		String seqStr = request.getParameter("seq");
		String writer = request.getParameter("writer");
		String email = request.getParameter("email");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		//메소드 처리하자 check();
		int seq = -1;
		if(seqStr != null){
			seqStr = seqStr.trim();
			if(seqStr.length() != 0){
				try{
					seq = Integer.parseInt(seqStr);
				}catch(NumberFormatException ne){
					//에러처리
				}
			}else{
				//에러처리
			}
		}else{
			//에러처리
		}
		Board dto = new Board(seq, writer, email, subject, content);
		
		SmService service = SmService.getInstance();
		service.updateS(dto);

		String view = "content.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}
}
