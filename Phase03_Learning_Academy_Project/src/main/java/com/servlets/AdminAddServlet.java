package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.entity.Admin;
import com.hibernate.util.HibernateUtil;

/**
 * Servlet implementation class AdminAdd
 */
@WebServlet("/admin_add")
public class AdminAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminAddServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String email = request.getParameter("email");
		String password = request.getParameter("pwd");
		String mobile=request.getParameter("mobile");
		String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		int n=0;
		if(email.isBlank() || password.isBlank())
		{
			request.getRequestDispatcher("Admin_Add.html").include(request, response);
			out.println("<br/><br/><div class=p4><font style=color:red> Email and Password field are mandatory....</font></div>");
		}
		else if(!email.matches(regex))
		{
			request.getRequestDispatcher("Admin_Add.html").include(request, response);
			out.println("<br/><br/><div class=p4><font style=color:red> Enter Email Address is not Valid..,Please enter Valid one..</font></div>");
		}
		else
		{
			SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
			Session session = sessionFactory.openSession();
			String hql="Select * from Admin where email=:email";
			Query q=session.createSQLQuery(hql);
			q.setParameter("email", email);
			List l =q.list();
			n=l.size();
			if(n==0)
			{
				Transaction tx = session.beginTransaction();
				Admin a=new Admin(fname,lname,email,password,mobile);
				session.save(a);
				tx.commit();	
				response.sendRedirect("Admin_Login.html");
			}
			else
			{
				request.getRequestDispatcher("Admin_Add.html").include(request, response);
				out.println("<br/><br/><div class=p4><font style=color:red>Already have an account?</font>");
				out.print("<a href=Admin_Login.html> Login </a></div>");
			}
			session.close();
		}
	}

}
