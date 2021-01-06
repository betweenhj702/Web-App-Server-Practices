package smfile.mvc.control;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import file.mvc.model.FileSet;
import mvc.domain.Board;
import sm.mvc.model.SmService;
import sm.mvc.vo.ListResult;

@WebServlet("/sm_file/sm.do")
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
			}else if(m.equals("download")) {
				download(request,response);
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
			del(request,response);
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
	public void del(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String fname =request.getParameter("fname");
		if(fname != null) fname = fname.trim();
		File f = new File(FileSet.FILE_DIR, fname);
		if(f.exists()) f.delete();
		
	}
	private void write(HttpServletResponse response) throws ServletException, IOException{
		String view = "write_jsp.jsp";
		response.sendRedirect(view);
	}

	private void insert(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException{
		
		//
		File fSaveDir = new File(FileSet.FILE_DIR);
		if(!fSaveDir.exists()) fSaveDir.mkdirs();
		
		MultipartRequest mr = new MultipartRequest(request,
                FileSet.FILE_DIR,
                1*1024*1024,
                "utf-8",
                new DefaultFileRenamePolicy());
		
		String writer = mr.getParameter("writer");
		String email = mr.getParameter("email");
		String subject = mr.getParameter("subject");
		String content = mr.getParameter("content");
		String fname = mr.getFilesystemName("fname");
		String ofname = mr.getOriginalFileName("fname");
		File f = new File(fSaveDir, fname);
		long fsize = f.length();
		//
		
		Board dto = new Board(-1, writer, email, subject, content, null, fname,
				ofname, fsize);
		
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
		//
		File fSaveDir = new File(FileSet.FILE_DIR);
		if(!fSaveDir.exists()) fSaveDir.mkdirs();
		
		MultipartRequest mr = new MultipartRequest(request,
                FileSet.FILE_DIR,
                1*1024*1024,
                "utf-8",
                new DefaultFileRenamePolicy());
		
		String seqStr = mr.getParameter("seq");
		String writer = mr.getParameter("writer");
		String email = mr.getParameter("email");
		String subject = mr.getParameter("subject");
		String content = mr.getParameter("content");
		String fname = mr.getFilesystemName("fname");
		String ofname = mr.getOriginalFileName("fname");
		File f = new File(fSaveDir, fname);
		long fsize = f.length();
		//
		
		
		
		int seq = -1;
		seq = check(seqStr);
		if( seq != -1){
			Board dto = new Board(seq, writer, email, subject, content, null, fname,
					ofname, fsize);
			
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
	public void download(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String fname = new String(request.getParameter("fname"));
		File file = new File(FileSet.FILE_DIR +"/"+fname);

		response.setContentType("application/octet-stream"); 

		String Agent=request.getHeader("USER-AGENT");
		if( Agent.indexOf("MSIE") >= 0 ){
			int i = Agent.indexOf( 'M', 2 );

			String IEV = Agent.substring( i + 5, i + 8 );

			if( IEV.equalsIgnoreCase("5.5") ){
				response.setHeader("Content-Disposition", "filename=" + new String(fname.getBytes("utf-8"), "8859_1") );
			}else{
				response.setHeader("Content-Disposition", "attachment;filename="+new String(fname.getBytes("utf-8"),"8859_1"));
			}
		}else{
			response.setHeader("Content-Disposition", "attachment;filename=" + new String(fname.getBytes("utf-8"), "8859_1") );
		}

		byte b[] = new byte[1024];
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		OutputStream os = null;
		BufferedOutputStream bos = null;
		
		if( file.isFile()){ 
			try{ 
				fis = new FileInputStream(file);//Node 
				bis = new BufferedInputStream(fis); //Filter 
				
				os = response.getOutputStream(); //Node
				bos = new BufferedOutputStream(os); //Filter   

				int read = 0;

				while( ( read = bis.read( b ) ) != -1 ){  
					bos.write(b,0,read);
				}

				bos.flush();
				
			}catch( Exception e ){
				
			}finally {
				try {
					if(bis != null) bis.close();
					if(bos != null) bos.close();
					if(fis != null) fis.close();
					if(os != null) os.close();
				}catch(IOException ie) {}
			}
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
