<%@page contentType="text/html;charset=utf-8" import="java.util.*, " %>

<head>
<meta charset='utf-8'>
<title>Simple Board with jsp and dbcp in MVC</title>
<style>table,th,td{border:1px solid black;border-collapse:collapse}th,td{padding:5px}a{text-decoration:none}</style>
</head>
<body>
<center>
<hr width='600' size='2' noshade>
<h2>Simple Board with JSP and dbcp in MVC</h2>
&nbsp;&nbsp;&nbsp;
<a href='sm.do?m=write'>글쓰기</a>
&nbsp;&nbsp;&nbsp;
<a href='../'>인덱스</a>
<hr width='600' size='2' noshade>
</center>
<table border='1' width='600' align='center' cellpadding='2'>
<tr>
<th align='center' width='10%'>글번호</th>
<th align='center' width='15%'>작성자</th>
<th align='center' width='30%'>이메일</th>
<th align='center' width='30%'>글제목</th>
<th align='center' width='15%'>날짜</th>
</tr>

<%
		ArrayList<Board> list = (ArrayList<Board>) request.getAttribute("list");
		if( (list.size()) != 0){
			for(Board dto: list){
		
%>
				<tr>
				<td align='center'><%=dto.getSeq()%></td>
				<td align='center'><%=dto.getWriter()%></td>
				<td align='center'><%=dto.getEmail()%></td>
				<td align='center'>
				<a href='sm.do?m=selectCon&seq=<%=dto.getSeq()%>'><%=dto.getSubject()%></a>
				</td>
				<td align='center'><%=dto.getRdate()%></td>
				</tr>
<%
			}
		}else{		
%>
		<tr>
			<td align='center' colspan='5'>데이터가 하나도 없음</td>
		</tr>
<%
		}
%>
</body>
