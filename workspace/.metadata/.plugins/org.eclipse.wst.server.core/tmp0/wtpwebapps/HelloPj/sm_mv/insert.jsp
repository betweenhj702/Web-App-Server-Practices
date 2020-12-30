<%@page contentType="text/html;charset=utf-8" import="soo.mv.model.SmDTO, soo.mv.model.SmDAO" %>
<jsp:useBean id="dto" class="soo.mv.model.SmDTO"/>
<jsp:setProperty name="dto" property="*"/>

<%

		SmDAO smDao = (SmDAO) application.getAttribute("smDao");
		smDao.insert(dto);

		response.sendRedirect("list.jsp");
%>