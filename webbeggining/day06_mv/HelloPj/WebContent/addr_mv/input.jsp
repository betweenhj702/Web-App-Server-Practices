<%@page contentType="text/html;charset=utf-8" %>

<meta charset="utf-8">
<script src="../js/trim.js"></script>
<script language="javascript"> 
    function check(){
		var nameval = f.name.value;
		nameval = trim(nameval);
		if(nameval.length == 0){
			alert("이름을 넣어주세요");
			f.name.value = "";
			f.name.focus();
			return false;
		}else{
			pass = checkByteLen(nameval, 10);
			if(!pass){
				alert("이름이 너무 길어요");
				f.name.focus();
				return false;
			}
		}
		
		var addrval = f.addr.value;
		addrval = trim(addrval);
		if(addrval.length == 0){
			alert("주소를 넣어주세요");
			f.addr.value = "";
			f.addr.focus();
			return false;
		}else{
			pass = checkByteLen(addrval, 20);
			if(!pass){
				alert("주소가 너무 길어요");
				f.addr.focus();
				return false;
			}
		}

		f.submit();
	}

    function checkByteLen(str, size){
        var byteLen = getByteLen(str);
		if(byteLen <= size){
			return true;
		}else{
			return false;
		}
	}
	function getByteLen(str){
	   return str.replace(/[\0-\x7f]|([0-\u07ff]|(.))/g,"$&$1$2").length;
    }
</script>
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
<body onload="document.f.name.focus()">
<center>
   <h1>
		Address Input JSP in Model1
   </h1>
   <form name="f" action="insert.jsp" method="post">
       <table border="1" width="300" height="200">
	      <tr>
		     <td width="30%" colspan="2" align="center"><h2>입력폼</h2></td> 
		  </tr>
		  <tr>
		     <th width="30%">이름</th> 
			 <td><input name="name" align="center" size="20" align="center"></td>
		  </tr>
		  <tr>
		     <th width="30%">주소</th> 
			 <td><input name="addr" size="20" align="center"></td>
		  </tr>
		  <tr>
		     <td colspan="2" align="center">
			     <input type="button" value="전송" onclick="check()"/>
				 <input type="reset" value="취소"/>
			 </td> 
		  </tr>
	   </table>
   </form>
</center>
</body>

