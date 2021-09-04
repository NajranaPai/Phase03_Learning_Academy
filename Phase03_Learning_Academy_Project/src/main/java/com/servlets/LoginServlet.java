package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.entity.Admin;
import com.hibernate.util.HibernateUtil;
 
 
/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
 	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		String email=request.getParameter("email");
		String pwd=request.getParameter("pwd");
		String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		int n=0;
		HttpSession hsession=request.getSession();
		if(email.isBlank() || pwd.isBlank())
		{
			request.getRequestDispatcher("Admin_Login.html").include(request, response);
			out.println("<br/><br/><div class=p4><font style=color:red> Either Email or Password are missing.. Please enter..</font></div>");
		}
		else if(!email.matches(regex))
		{
			request.getRequestDispatcher("Admin_Login.html").include(request, response);
			out.println("<br/><br/><div class=p4><font style=color:red> Enter Email Address is not Valid..,Please enter Valid one..</font></div>");
		}
		else
		{
			SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
			Session session = sessionFactory.openSession();
			String hql="from Admin where email=:email and password=:password";
			  
			/*Query q=session.createQuery(hql);
			q.setParameter("email", email);
			q.setParameter("password", pwd);
			List l=q.list();
			n=l.size();*/
			List<Admin> a=session.createQuery(hql).setParameter("email",email).setParameter("password",pwd).list();
			System.out.print(n);
			if(a.size()>=1)
			{  
				for(Admin admin:a)
				  {
					String name=admin.getFname() + " " + admin.getLname();
					hsession.setAttribute("AdminName", name);
					hsession.setAttribute("Error",null);
					response.sendRedirect("HomePage");
				  }
			}
			else
			{
				 
				request.getRequestDispatcher("Admin_Login.html").include(request, response);
				out.println("<br/><br/><div class=p4><font style=color:red>Please Enter Valid Credential..</font></div>");
			}
			session.close();
		}
		 
	}
}
