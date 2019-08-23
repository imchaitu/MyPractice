package com.practice.servlet3;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HelloServlet
 */
@WebServlet(name = "helloServ", urlPatterns = { "/hello.jlc" }, initParams = {
		@WebInitParam(name = "email", value = "hello@jlc.com") })
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ServletConfig cfg = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HelloServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		this.cfg = config;
		System.out.println("** init() method of HelloServlet **");
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("**service() method of HelloServlet **");
		String fnm = request.getParameter("fname");
		String phn = request.getParameter("phone");
		Writer out = response.getWriter();
		response.setContentType("text/html");
		out.write("<h1> Response from HelloServlet ");
		out.write("<hr/> Request Parameters ");
		out.write("<br/>Fname : " + fnm);
		out.write("<br/>Phone : " + phn);
		out.write("<hr/>Servlet Config Parameters");
		String eml = cfg.getInitParameter("email");
		out.write("<br/>" + cfg);
		out.write("<br/>Email : " + eml);
		out.write("<hr/>Servlet Context Parameters");
		ServletContext ctx = cfg.getServletContext();
		String web = ctx.getInitParameter("website");
		out.write("<br/>" + ctx);
		out.write("<br/>Web : " + web);
	}

}
