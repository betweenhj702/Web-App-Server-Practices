<%@page contentType="text/html;charset=utf-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>Simple Board with FILE in MVC</title>
    <style>
		table, th, td {
		   border: 1px solid black;
		   border-collapse: collapse;
		}
		th, td {
		   padding: 5px;
		}
		a { text-decoration:none }
	</style>
	<script src="../js/check.js"></script>
	<script language="javascript">
	   function check()
	   {
	       for(var i=0; i<document.input.elements.length; i++)
		   {
		      var inputElem = document.input.elements[i]
			  if(inputElem.value == "")
			  {
			     alert("모든 값을 입력 하셔야 합니다. ");
				 return false;
			  }
		   }
			var writerval = input.writer.value;
			writerval = trim(writerval);
			pass = checkByteLen(writerval, 21);
			if(!pass){
				alert("이름 7자이하");
				input.writer.focus();
				return false;
			}
			var emailval = input.email.value;
			emailval = trim(emailval);
			pass = checkByteLen(emailval, 30);
			if(!pass){
				alert("이메일 10자이하");
				input.email.focus();
				return false;
			}
			var subjectval = input.subject.value;
			subjectval = trim(subjectval);
			pass = checkByteLen(subjectval, 30);
			if(!pass){
				alert("제목 10자이하");
				input.subject.focus();
				return false;
			}
			var contentval = input.content.value;
			contentval = trim(contentval);
			pass = checkByteLen(contentval, 600);
			if(!pass){
				alert("글 200자이하");
				input.writer.focus();
				return false;
			}
		   document.input.submit();
       }
	</script>
  </head>
  <body onload="input.writer.focus()">
	<font color="gray" size='4' face="휴먼편지체">
    <center>
	   <hr width="600" size='2' color="gray" noshade>
	      <h3> Simple Board with FILE in MVC</h3>
		  	<font color="gray" size="3" face="휴먼편지체">
			<a href='sm.do'>리스트</a>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<a href='../'>인덱스</a>
			</font>
	   <hr width="600" size="2" color="gray" noshade>
	</center>

	<form name="input" method="post" action="sm.do?m=insert" enctype="multipart/form-data">
	   <table border="0" width="600" align="center"  cellpadding="3" cellspacing="1" bordercolor="gray">
	      <tr>
		     <td width="30%" align="center">WRITER</td>
			 <td><input type="text" name="writer" size="60"></td>
		  </tr>
		  <tr>
		     <td align="center">EMAIL</td>
			 <td><input type="text" name="email" size="60"></td>
		  </tr>
          <tr>
		     <td align="center">SUBJECT</td>
			 <td><input type="text" name="subject" size="60"></td>
		  </tr>
		  <tr>
		     <td align="center">FILE</td>
			 <td> <input type="file" name="fname"></td>
		  </tr>
		  <tr>
		     <td align="center">CONTENT</td>
			 <td><textarea  name="content" style="width:98%" rows="8" cols="60"></textarea></td>
		  </tr>
		  <tr>
		     <td colspan="2" align="center">
			    <input type="button" value="전송" onclick="check()">
				<input type="reset" value="다시입력" onclick="input.writer.focus()">
			 </td>
		  </tr>
	   </table>
	   <hr width="600" size="2" color="gray" noshade>
	</form>
	</font>
  </body>
</html>