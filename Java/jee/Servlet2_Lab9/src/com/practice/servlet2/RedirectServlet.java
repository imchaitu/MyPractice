package com.practice.servlet2;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RedirectServlet
 */
public class RedirectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RedirectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = request.getParameter("page");
		if(page == null || page.trim().length() == 0) {
			RequestDispatcher rd = null;
			rd = request.getRequestDispatcher("error.html");
			rd.forward(request, response);
			return ;
		}
		if(page.startsWith("www")) {
			page = "http://" + page;
		}
		response.sendRedirect(page);
	}

}
