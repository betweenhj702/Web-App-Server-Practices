<%@page contentType="text/html;charset=utf-8" %>

<%
	String msg = (String) request.getAttribute("msg");
%>
<script language="javascript">
	var msg = "<%=msg%>";
	if(msg == "wrongAccess"){
		alert("올바른 접근이 아닙니다");
	}else if(msg == "delete"){
		alert("삭제되었습니다");
	}else if(msg == "sqlException"){
		alert("데이터베이스 오류");
	}
	location.href="sm.do";
</script>