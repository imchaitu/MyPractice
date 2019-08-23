package com.practice.Servlet3;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet(name="regServlet", urlPatterns= {"/register.jlc"})
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			System.out.println("service()");
			
			//1. Collect the data
			String fn = request.getParameter("fname");
			String em = request.getParameter("email");
			String ph = request.getParameter("phone");
			String ge = request.getParameter("gender");
			String ti = request.getParameter("timings");
			String co = request.getParameter("course");
			String cous[] = request.getParameterValues("course");
			String re = request.getParameter("remarks");
			
			//2. Process the data (store in db)
			String msg = "Your Enquiry has been added successfully";
			System.out.println("Full Name : " + fn);
			System.out.println("Email ID : " + em);
			System.out.println("Phone No : " + ph);
			System.out.println("Gender : " + ge);
			System.out.println("Timings : " + ti);
			System.out.println("Course : " + co);
			System.out.println("All Courses");
			if(cous != null) {
				for (String c : cous) {
					System.out.println(c);
				}
			}
			System.out.println("Remarks : " + re);
			
			//3. Send response
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<h1>Java Learning Center</h1>");
			out.println("<h1>" + msg + "</h1>");
			out.println("<h1>Full name : " + fn);
			out.println("<h1>Email ID : " + em);
			out.println("<h1>Phone No : " + ph);
			out.println("<h1>Gender : " + ge);
			out.println("<h1>Timings : " + ti);
			out.println("<h1>Course : " + co);
			out.println("<h1>All Courses ");
			if(cous != null) {
				for (String c : cous) {
					out.println("<br/>" + c);
				}
			}
			out.println("<h1>Remarks : " + re);
	}

}
