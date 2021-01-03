/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.40
 * Generated at: 2021-01-01 06:12:07 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.sm;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class write_005fjsp_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = null;
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    if (!javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      final java.lang.String _jspx_method = request.getMethod();
      if ("OPTIONS".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        return;
      }
      if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSP들은 오직 GET, POST 또는 HEAD 메소드만을 허용합니다. Jasper는 OPTIONS 메소드 또한 허용합니다.");
        return;
      }
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html;charset=utf-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("  <head>\r\n");
      out.write("    <meta charset=\"utf-8\">\r\n");
      out.write("    <title>Simple Board with JSP and dbcp in MVC</title>\r\n");
      out.write("    <style>\r\n");
      out.write("\t\ttable, th, td {\r\n");
      out.write("\t\t   border: 1px solid black;\r\n");
      out.write("\t\t   border-collapse: collapse;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tth, td {\r\n");
      out.write("\t\t   padding: 5px;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\ta { text-decoration:none }\r\n");
      out.write("\t</style>\r\n");
      out.write("\t<script src=\"../js/check.js\"></script>\r\n");
      out.write("\t<script language=\"javascript\">\r\n");
      out.write("\t   function check()\r\n");
      out.write("\t   {\r\n");
      out.write("\t       for(var i=0; i<document.input.elements.length; i++)\r\n");
      out.write("\t\t   {\r\n");
      out.write("\t\t      var inputElem = document.input.elements[i]\r\n");
      out.write("\t\t\t  if(inputElem.value == \"\")\r\n");
      out.write("\t\t\t  {\r\n");
      out.write("\t\t\t     alert(\"모든 값을 입력 하셔야 합니다. \");\r\n");
      out.write("\t\t\t\t return false;\r\n");
      out.write("\t\t\t  }\r\n");
      out.write("\t\t   }\r\n");
      out.write("\t\t\tvar writerval = input.writer.value;\r\n");
      out.write("\t\t\twriterval = trim(writerval);\r\n");
      out.write("\t\t\tpass = checkByteLen(writerval, 21);\r\n");
      out.write("\t\t\tif(!pass){\r\n");
      out.write("\t\t\t\talert(\"이름 7자이하\");\r\n");
      out.write("\t\t\t\tinput.writer.focus();\r\n");
      out.write("\t\t\t\treturn false;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\tvar emailval = input.email.value;\r\n");
      out.write("\t\t\temailval = trim(emailval);\r\n");
      out.write("\t\t\tpass = checkByteLen(emailval, 30);\r\n");
      out.write("\t\t\tif(!pass){\r\n");
      out.write("\t\t\t\talert(\"이메일 10자이하\");\r\n");
      out.write("\t\t\t\tinput.email.focus();\r\n");
      out.write("\t\t\t\treturn false;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\tvar subjectval = input.subject.value;\r\n");
      out.write("\t\t\tsubjectval = trim(subjectval);\r\n");
      out.write("\t\t\tpass = checkByteLen(subjectval, 30);\r\n");
      out.write("\t\t\tif(!pass){\r\n");
      out.write("\t\t\t\talert(\"제목 10자이하\");\r\n");
      out.write("\t\t\t\tinput.subject.focus();\r\n");
      out.write("\t\t\t\treturn false;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\tvar contentval = input.content.value;\r\n");
      out.write("\t\t\tcontentval = trim(contentval);\r\n");
      out.write("\t\t\tpass = checkByteLen(contentval, 600);\r\n");
      out.write("\t\t\tif(!pass){\r\n");
      out.write("\t\t\t\talert(\"글 200자이하\");\r\n");
      out.write("\t\t\t\tinput.writer.focus();\r\n");
      out.write("\t\t\t\treturn false;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t   document.input.submit();\r\n");
      out.write("       }\r\n");
      out.write("\t</script>\r\n");
      out.write("  </head>\r\n");
      out.write("  <body onload=\"input.writer.focus()\">\r\n");
      out.write("\t<font color=\"gray\" size='4' face=\"휴먼편지체\">\r\n");
      out.write("    <center>\r\n");
      out.write("\t   <hr width=\"600\" size='2' color=\"gray\" noshade>\r\n");
      out.write("\t      <h3> Simple Board with JSP and dbcp in MVC</h3>\r\n");
      out.write("\t\t  \t<font color=\"gray\" size=\"3\" face=\"휴먼편지체\">\r\n");
      out.write("\t\t\t<a href='sm.do'>리스트</a>\r\n");
      out.write("\t\t\t&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\r\n");
      out.write("\t\t\t<a href='../'>인덱스</a>\r\n");
      out.write("\t\t\t</font>\r\n");
      out.write("\t   <hr width=\"600\" size=\"2\" color=\"gray\" noshade>\r\n");
      out.write("\t</center>\r\n");
      out.write("\r\n");
      out.write("\t<form name=\"input\" method=\"post\" action=\"sm.do?m=insert\">\r\n");
      out.write("\t   <table border=\"0\" width=\"600\" align=\"center\"  cellpadding=\"3\" cellspacing=\"1\" bordercolor=\"gray\">\r\n");
      out.write("\t      <tr>\r\n");
      out.write("\t\t     <td width=\"30%\" align=\"center\">WRITER</td>\r\n");
      out.write("\t\t\t <td><input type=\"text\" name=\"writer\" size=\"60\"></td>\r\n");
      out.write("\t\t  </tr>\r\n");
      out.write("\t\t  <tr>\r\n");
      out.write("\t\t     <td align=\"center\">EMAIL</td>\r\n");
      out.write("\t\t\t <td><input type=\"text\" name=\"email\" size=\"60\"></td>\r\n");
      out.write("\t\t  </tr>\r\n");
      out.write("          <tr>\r\n");
      out.write("\t\t     <td align=\"center\">SUBJECT</td>\r\n");
      out.write("\t\t\t <td><input type=\"text\" name=\"subject\" size=\"60\"></td>\r\n");
      out.write("\t\t  </tr>\r\n");
      out.write("\t\t  <tr>\r\n");
      out.write("\t\t     <td align=\"center\">CONTENT</td>\r\n");
      out.write("\t\t\t <td><textarea  name=\"content\" style=\"width:98%\" rows=\"8\" cols=\"60\"></textarea></td>\r\n");
      out.write("\t\t  </tr>\r\n");
      out.write("\t\t  <tr>\r\n");
      out.write("\t\t     <td colspan=\"2\" align=\"center\">\r\n");
      out.write("\t\t\t    <input type=\"button\" value=\"전송\" onclick=\"check()\">\r\n");
      out.write("\t\t\t\t<input type=\"reset\" value=\"다시입력\" onclick=\"input.writer.focus()\">\r\n");
      out.write("\t\t\t </td>\r\n");
      out.write("\t\t  </tr>\r\n");
      out.write("\t   </table>\r\n");
      out.write("\t   <hr width=\"600\" size=\"2\" color=\"gray\" noshade>\r\n");
      out.write("\t</form>\r\n");
      out.write("\t</font>\r\n");
      out.write("  </body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}