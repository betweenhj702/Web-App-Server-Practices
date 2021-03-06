<%@page contentType="text/html;charset=utf-8" import="java.util.*, mvc.domain.Board, sm.mvc.vo.ListResult" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset='utf-8'>
<title>Simple Board with JSTL+EL in MVC</title>
<style>table,th,td{border:1px solid black;border-collapse:collapse}th,td{padding:5px}a{text-decoration:none}</style>
</head>
<body>
<center>
<hr width='600' size='2' noshade>
<h2>Simple Board with JSTL+EL in MVC</h2>
&nbsp;&nbsp;&nbsp;
<a href='sm.do?m=write'>글쓰기</a>
&nbsp;&nbsp;&nbsp;
<a href='../'>인덱스</a>

<hr width='600' size='2' noshade>
<table border='1' width='600' align='center' cellpadding='2'>
<tr>
<th align='center' width='10%'>글번호</th>
<th align='center' width='15%'>작성자</th>
<th align='center' width='30%'>이메일</th>
<th align='center' width='30%'>글제목</th>
<th align='center' width='15%'>날짜</th>
</tr>

	<c:if test="${empty listResult.list}">
       <TR align='center' noshade>
          <TD colspan="5">데이터가 없음</TD>
       </TR>
   	</c:if>
   	<c:forEach items="${listResult.list}" var="board"> 
			<tr>
			<td align='center'>${board.seq}</td>
			<td align='center'>${board.writer}</td>
			<td align='center'>${board.email}</td>
			<td align='center'>
			<a href='sm.do?m=selectCon&seq=${board.seq}'>${board.subject}</a>
			</td>
			<td align='center'>${board.rdate}</td>
			</tr>
	</c:forEach>
</table>

<hr width='600' size='2' color='gray' noshade>

<font color='gray' size='3' face='휴먼편지체'>
    (총페이지수 : ${listResult.totalPageCount} )
    &nbsp;&nbsp;&nbsp;
    
	<c:forEach begin="1" end="${listResult.totalPageCount}" var="i">
		<a href="sm.do?cp=${i}">
			<c:choose>
               <c:when test="${i==cp}">
                  <strong>${i}</strong>
               </c:when>
	           <c:otherwise>
	              ${i}
	           </c:otherwise>
           </c:choose>
		</a>&nbsp;
	</c:forEach>
  
    ( TOTAL : ${listResult.totalCount} )

    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
     페이지 싸이즈 : 
    <select id="psId" name="ps" onchange="f(this)">
    		
   		   <option value="3" <c:if test="${ps == 3}">selected</c:if> >3</option>
	       <option value="5" <c:if test="${ps == 5}">selected</c:if> >5</option>
	       <option value="10"<c:if test="${ps == 10}">selected</c:if> >10</option>
    </select>
    
    <script language="javascript">
       function f(select){
           //var el = document.getElementById("psId");
           var ps = select.value;
           //alert("ps : " + ps);
           location.href="sm.do?ps="+ps;
       }
    </script>
</font>
<hr width='600' size='2' color='gray' noshade>
</center>
</body>
</html>