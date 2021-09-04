package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Properties;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.entity.Admin;
import com.hibernate.util.HibernateUtil;

import javax.mail.*;
import javax.mail.internet.*;

/**
 * Servlet implementation class ForgotPasswordServlet
 */
@WebServlet("/forgot")
public class ForgotPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ForgotPasswordServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		String email=request.getParameter("email");
		String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		String pwd,name = null;
		if(email.isBlank())
		{
			request.getRequestDispatcher("ForgotPassword.html").include(request, response);
			out.println("<br/><br/><div class=p4><font style=color:red>Please Enter Email Address:</font></div>");
		}
		else if(!email.matches(regex))
		{
			request.getRequestDispatcher("ForgotPassword.html").include(request, response);
			out.println("<br/><br/><div class=p4><font style=color:red> Enter Email Address is not Valid..,Please enter Valid one..</font></div>");
		}
		else
		{
			SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
			org.hibernate.Session session = sessionFactory.openSession();
			String hql="from Admin where email=:email";
			List<Admin> a=session.createQuery(hql).setParameter("email",email).list();
			if(a.size()==0)
			{
				request.getRequestDispatcher("ForgotPassword.html").include(request, response);
				out.println("<br/><br/><div class=p4><font style=color:red> Email Address does not found please <a href=Admin_Add.html>Sign Up</a></font></div>");
			}
			else
			{
			 for(Admin admin:a)
			  {
        	    name=admin.getFname() + " " + admin.getLname();
			    pwd=admin.getPassword(); 
			   
			    String sender="paimasternaaz786@gmail.com"; // mention your email address from that you have to send
			    String spwd=""; // I removed my password please mention your email address and password
			   
			    Properties proper = System.getProperties();
			    proper.put("mail.smtp.auth", "true"); 
			    proper.put("mail.smtp.starttls.enable","true");
			    proper.put("mail.smtp.host", "smtp.gmail.com");
			    proper.put("mail.smtp.port", "587"); 
			    
			    
			    javax.mail.Session s=javax.mail.Session.getDefaultInstance(proper,    
			            new javax.mail.Authenticator() {    
			            protected PasswordAuthentication getPasswordAuthentication() {    
			            return new PasswordAuthentication(sender,spwd);  
			            }});
			    try 
			    {    
			      Message message=new MimeMessage(s);
			      message.setFrom(new InternetAddress("paimasternaaz786@gmail.com"));
			      message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(email));
			      message.setSubject("Learning Academy");
			      message.setText("\nDear "+ name + "\n\n\t Your Learning Academy Password is : " + pwd);
			      Transport.send(message);
			      
			      request.getRequestDispatcher("ForgotPassword.html").include(request, response);
				  out.println("<br/><br/><div class=p4><font style=color:red>Password has been sent to your email Address please check..<a href=Admin_Login.html>Login</a></font></div>");
				
			    }catch(MessagingException e) {throw new RuntimeException(e);}   
			  }
			}
		}
	}
}
			     
			   
			  
		 