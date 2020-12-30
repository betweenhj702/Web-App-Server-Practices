<%@page contentType="text/html;charset=utf-8" import="soo.mv.model.AddrDAO"%>


<%
    int seq = -1;
	String seqStr = request.getParameter("seq");
	if(seqStr != null){
		seqStr = seqStr.trim();
		if(seqStr.length() != 0){
			try{
				seq = Integer.parseInt(seqStr);
			}catch(NumberFormatException ne){
			}
		}
	}
	
	AddrDAO addrDao = (AddrDAO) application.getAttribute("addrDao");
	addrDao.delete(seq);
	response.sendRedirect("list.jsp");
%>