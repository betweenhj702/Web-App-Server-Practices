<%@page contentType="text/html;charset=utf-8" %>

<%
		int seq = -1;
		String seqStr = request.getParameter("seq");
		
        if(seqStr != null){
			seqStr = seqStr.trim();
			if(seqStr.length() != 0){
				try{
					seq = Integer.parseInt(seqStr);
				}catch(NumberFormatException ne){
					response.sendRedirect("content.jsp");
				}
			}else{
				response.sendRedirect("content.jsp");
			}
		}else{
			response.sendRedirect("content.jsp");
		}
		
		SmDAO smDao = (SmDAO) application.getAttribute("smDao");
		smDao.delete(seq);
		response.sendRedirect("list.jsp");
%>