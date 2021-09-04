package com.servlets;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/Logout")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogoutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("Text/Html");
		PrintWriter out =response.getWriter();
		HttpSession hsession=request.getSession();
		String name=(String) hsession.getAttribute("AdminName");
		out.print("<html><head><title>Learning Academy</title>");
		out.print("<style>.p1 {height:10px;   width:100%;}");
		out.print(".p2{height: 40px;   width: 100%;   background-color:white;   text-align:Center; font-family:cursive; font-size:170%;}");
		out.print(".p3{height: 30px;   width: 70%; margin:auto;   background-color:white;   text-align:Center; font-family:cursive; font-size:120%;}");
		out.print("</style></head><body bgcolor=#7575FF>");
	    out.println("<div class=p1></div>");
	    out.println("<div class=p2><b>Thank You For Using Learning Academy Website..</b></div><br>");
	    out.println("<div class=p3><a href=Admin_Login.html>Again Login</a></div>");
	    out.println("</body>");
         
	    
	    hsession.invalidate();
	    
	}

}
