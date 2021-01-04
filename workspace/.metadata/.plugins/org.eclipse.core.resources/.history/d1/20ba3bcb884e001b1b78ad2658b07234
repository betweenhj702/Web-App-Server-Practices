<%@page contentType="text/html;charset=utf-8" %>

<%
	String msg = (String) request.getAttribute("msg");
	int seq = -1;
	if(request.getAttribute("seq") != null){
		seq = (int) request.getAttribute("seq");
	}
%>
<script language="javascript">
	var msg = "<%=msg%>";
	if(msg == "wrongAccess"){
		alert("올바른 접근이 아닙니다");
		location.href="sm.do";
	}else if(msg == "delete"){
		alert("삭제되었습니다");
		location.href="sm.do";
	}else if(msg == "sqlException"){
		alert("데이터베이스 오류");
		location.href="sm.do";
	}else if(msg == "insert"){
		alert("데이터베이스에 입력완료");
		location.href="sm.do";
	}else if(msg == "update"){
		alert("수정 완료");
		location.href="sm.do?m=selectCon&seq=<%=seq%>";
	}
</script>