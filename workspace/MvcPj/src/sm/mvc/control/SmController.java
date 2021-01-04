package sm.mvc.control;

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.domain.Board;
import sm.mvc.model.SmService;
import sm.mvc.vo.ListResult;

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
		HttpSession session = request.getSession();
		String cpStr = request.getParameter("cp");
		String psStr = request.getParameter("ps");
		int cp = 1;
		if(cpStr == null) {
			Object cpObj = session.getAttribute("cp");
			if(cpObj != null) {
				cp = (Integer)cpObj;
			}
		}else {
			cpStr = cpStr.trim();
			cp = Integer.parseInt(cpStr);
		}
		session.setAttribute("cp", cp);
		
		int ps = 3;
		if(psStr == null) {
			Object psObj = session.getAttribute("ps");
			if(psObj != null) {
				ps = (Integer)psObj;
			}
		}else {
			psStr = psStr.trim();
			int psParam = Integer.parseInt(psStr);
			
			Object psObj = session.getAttribute("ps");
			if(psObj != null) {
				int psSession = (Integer)psObj;
				if(psSession != psParam) {
					cp = 1;
					session.setAttribute("cp", cp);
				}
			}else {
				if(ps != psParam) {
					cp = 1;
					session.setAttribute("cp", cp);
				}
			}
			
			ps = psParam;
		}
		session.setAttribute("ps", ps);

		SmService service = SmService.getInstance();
		ListResult listResult = service.getListResult(cp, ps);

			//System.out.println(listResult.getTotalCount());
		if(listResult == null){
			String msg = "sqlException";
			goMsgAlert(msg, request, response);
		}
		
		request.setAttribute("listResult", listResult);
		if(listResult.getList().size() == 0 && cp>1) {
			response.sendRedirect("sm.do?cp="+(cp-1));
		}else {
			String view = "list.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(view);
			rd.forward(request, response);
		}
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
				goMsgAlert(msg, request, response);
			}
			request.setAttribute("selectCon", selectCon);
	
			String view = "content.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(view);
			rd.forward(request, response);
		
		}else {
			String msg = "wrongAccess";
			goMsgAlert(msg, request, response);
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
				goMsgAlert(msg, request, response);
			}else{
				String msg = "sqlException";
				goMsgAlert(msg, request, response);
			}
		}else{
			String msg = "wrongAccess";
			goMsgAlert(msg, request, response);
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
			goMsgAlert(msg, request, response);
		}else{
			String msg = "sqlException";
			goMsgAlert(msg, request, response);
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
				goMsgAlert(msg, request, response);
			}
			request.setAttribute("selUpCon", selUpCon);
			
			String view = "upcontent.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(view);
			rd.forward(request, response);

		}else{
			String msg = "wrongAccess";
			goMsgAlert(msg, request, response);
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
			int result = service.updateS(dto);
			if(result != -1) {
				request.setAttribute("seq", seq);
				String msg = "update";
				goMsgAlert(msg, request, response);
			}else {
				String msg = "sqlException";
				goMsgAlert(msg, request, response);
			}
		}else{
			String msg = "wrongAccess";
			goMsgAlert(msg, request, response);
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
	private void goMsgAlert(String msg, HttpServletRequest request,HttpServletResponse response)
	throws IOException, ServletException{
		request.setAttribute("msg", msg);
		String view = "msgAlert.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}
}
