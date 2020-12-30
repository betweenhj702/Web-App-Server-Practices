<%@page contentType="text/html;charset=utf-8" import="soo.mv.model.SmDTO,soo.mv.model.SmDAO" %>
<jsp:useBean id="dto" class="soo.mv.model.SmDTO"/>
<jsp:setProperty name="dto" property="*"/>

<%
		String seqStr = request.getParameter("seq");
		int seq = -1;
		if(seqStr != null){
			seqStr = seqStr.trim();
			if(seqStr.length() != 0){
				try{
					seq = Integer.parseInt(seqStr);
				}catch(NumberFormatException ne){
					response.sendRedirect("list.jsp");
				}
			}else{
				response.sendRedirect("list.jsp");
			}
		}else{
			response.sendRedirect("list.jsp");
		}
		
		SmDAO smDao = (SmDAO) application.getAttribute("smDao");
		smDao.update(seq, dto);

		response.sendRedirect("content.jsp?seq="+seq);
%>