package com.practice.servlet2;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestServlet
 */
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. Request parameters
		String un = request.getParameter("uname");
		String pw = request.getParameter("password");
		
		//2. Display request parameters
		PrintWriter out = response.getWriter();
		out.println("<h1> Username : " + un);
		out.println("<br/>Password : " + pw);
		out.println("<hr/>");
		out.println("Request Headers");
		
		//3. Request Headers
		Enumeration e = request.getHeaderNames();
		while(e.hasMoreElements()) {
			String hn = e.nextElement().toString();
			String hv = request.getHeader(hn);
			out.println("<br/>" + hn + " : " + hv);
		}
		out.println("<hr/>");
		out.println("Locale Info");
		
		//4. Locales supported by Browser.
		out.println("<br/> request.getLocale() : " + request.getLocale());
		out.println("<hr/>");
		out.println("Other Info");
		
		//5. Other information from Request.
		out.println("<br/> METHOD: " + request.getMethod());
		out.println("<br/> Request URI: " + request.getRequestURI());
		out.println("<br/> Request URL: " + request.getRequestURL());
		out.println("<br/> Protocol: " + request.getProtocol());
		out.println("<br/> Content Len: " + request.getContentLength());
		out.println("<br/> Content Type: " + request.getContentType());
		out.println("<br/> Remote Addr: " + request.getRemoteAddr());
		out.println("<br/> Remote Port: " + request.getRemotePort());
		out.println("<br/> Remote Host: " + request.getRemoteHost());
		out.println("<br/> Server Port: " + request.getServerPort());
		out.println("<br/> Server Name: " + request.getServerName());
		out.println("<br/> QueryString(): " + request.getQueryString());
		out.println("<br/> req.getServletPath(): " + request.getServletPath());
		out.println("<br/> req.getContextPath(): " + request.getContextPath());
	}

}
