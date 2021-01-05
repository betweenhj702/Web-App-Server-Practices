<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%
   String sId = session.getId();
   out.println("세션ID: " + sId);
%>