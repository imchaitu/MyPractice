package com.practice.servlet2;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("** service of TestServlet");
		String unm = request.getParameter("uname");
		String pwd = request.getParameter("password");
		Writer out = response.getWriter();
		out.write("<h1>Hi, Welcome to JLC");
		String page = "";
		boolean check = true;
		if (unm == null || unm.trim().isEmpty()) {
			page = "/WEB-INF/required.html";
			check = false;
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		}else if(pwd == null || pwd.trim().isEmpty()) {
			page = "/WEB-INF/required.html";
			check = false;
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		}
		
		if(check) {
			if(unm.equals(pwd)) {
				page = "/WEB-INF/home.html";
				RequestDispatcher rd = request.getRequestDispatcher(page);
				rd.forward(request, response);
			}else {
				page = "/WEB-INF/error.html";
				RequestDispatcher rd = request.getRequestDispatcher(page);
				rd.forward(request, response);
			}
		}
		
		out.write("<h1>Again, Welcome to JLC");
		System.out.println("** service completed - LAST STATEMENT**");
	}

}
