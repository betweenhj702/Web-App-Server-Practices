<%@page contentType="text/html;charset=utf-8" import="soo.mv.model.SmDTO" %>
<jsp:setProperty name="dtd" property="*"/>

<%

		SmDAO smDao = (SmDAO) application.getAttribute("smDao");
		smDao.insert(dto);

		response.sendRedirect("list.jsp");
%>