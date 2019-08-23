package com.practice.servlet2;

import java.io.IOException;
import java.io.Writer;

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
     * Default constructor. 
     */
    public LoginServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String unm = request.getParameter("username");
		String pwd = request.getParameter("password");
		String msg = "";
		
		if(unm.equals(pwd)) {
			msg = "<h1>Login Success<br/>Welcome to JLC Home page";
		}else {
			msg = "<h1>Login Failed<br/>Invalid Username or Password";
		}
		
		Writer out = response.getWriter();
		out.write(msg);
	}

}
