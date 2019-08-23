package com.practice.servlet2;

import java.io.IOException;
import java.io.Writer;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestServlet
 */
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	static int count = 0;
       
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
		count++;
		System.out.println("-- service --:" + count);
		Writer out = response.getWriter();
		Date dt = new Date();
		out.write("<h1>" + dt);
		if(count <= 10) {
			response.setHeader("Refresh",  "3");
		}else {
			response.setHeader("Refresh", "1; URL=http://www.google.com");
		}
	}

}
