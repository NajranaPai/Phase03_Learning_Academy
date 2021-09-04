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

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.entity.Course;
import com.entity.Subject;
import com.hibernate.util.HibernateUtil;

/**
 * Servlet implementation class CourseDetailServlet
 */
@WebServlet("/EditSubject")
public class SubjectUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubjectUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("Text/Html");
		PrintWriter out =response.getWriter();
		HttpSession hsession=request.getSession();
		String name=(String) hsession.getAttribute("AdminName");
		
		out.print("<html><head><title>Learning Academy</title>");
		out.print("<style>.p1 {height:10px;   width:100%;}");
		out.print(".p2{height: 40px;   width: 100%;   background-color:white;   text-align:Center; font-family:cursive; font-size:170%;}");
		out.print(".p3{height: 30px;   width: 80%; margin:auto;   background-color:white;   text-align:Center; font-family:cursive; font-size:120%;}");
		out.print(".p4 {height:30px;   width:20%; margin-left:1200px; background-color:white;text-align:Center; font-family:cursive; font-size:120%;}");
		out.print(".p5{height: 30px;   width: 80%; margin:auto; margin-top:130px;  background-color:white; text-align:Center; font-family:cursive; font-size:120%;font-color:black;}");
		out.print(".p7{height: 30px;   width: 80%; margin:auto; margin-top:490px;  background-color:white; text-align:Center; font-family:cursive; font-size:120%;font-color:black;}");
		out.println(".main {height:400px;   width:70%;margin:auto;background-color:white;text-align:Center; font-family:cursive; font-size:120%;}");
	    out.print(".p6{height: 30px;   width: 70%; margin:auto;   background-color:white;   text-align:Center; font-family:cursive; font-size:120%;}");
	    out.print(".b{font-family:cursive; font-size:120%;  width:200px; height: 30px; text-align:Center; font-family:cursive; }");
	    out.print(".txt{font-family:cursive; font-size:100%;text-align:Center;}");
	    out.print("</style></head><body bgcolor=#7575FF><form action=EditSubject method=post>");
	    out.println("<div class=p1></div>");
	    out.println("<div class=p2><b>LEARNING ACADEMY</b></div><br>");
	    out.println("<div class=p4><b>Welcome:</b>"+name + "      " +"<a href=Logout>Logout</a></div><br/>");
	    out.println("<div class=p3>");
	   // out.print("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp&nbsp;&nbsp;&nbsp;&nbsp;&nbsp&nbsp;&nbsp;&nbsp;&nbsp;&nbsp");
	    out.print("<a href=CourseMain>Courses</a>");
	    out.print("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp&nbsp;&nbsp;&nbsp;&nbsp;&nbsp&nbsp;&nbsp;&nbsp;&nbsp;&nbsp");
	    out.print("<a href=SubjectMain>Subjects</a>");
	    out.print("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp&nbsp;&nbsp;&nbsp;&nbsp;&nbsp&nbsp;&nbsp;&nbsp;&nbsp;&nbsp");
	    out.print("<a href=TeacherMain>Teachers</a>");
	    out.print("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp&nbsp;&nbsp;&nbsp;&nbsp;&nbsp&nbsp;&nbsp;&nbsp;&nbsp;&nbsp");
	    out.print("<a href=StudentMain>Student</a>");
	    out.print("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp&nbsp;&nbsp;&nbsp;&nbsp;&nbsp&nbsp;&nbsp;&nbsp;&nbsp;&nbsp");
	    out.print("<a href=TeacherSubjectMain>TeacherSubjectAssign</a>");
	    out.print("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp&nbsp;&nbsp;&nbsp;&nbsp;&nbsp&nbsp;&nbsp;&nbsp;&nbsp;&nbsp");
	    out.print("<a href=StudentCourseMain>StudentCourseAssign</a>");
	    out.println("</div><br>");
	    out.println("<div class=main>");
		out.print("<br><table border=2 align=center>");
	    SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
		Session session = sessionFactory.openSession();
		String hql="from Subject";
		List<Subject> subject=session.createQuery(hql).list();
		out.print("<tr>");
		out.print("<th align=center width=150px>Subject Id</th>");
		out.print("<th align=center width=250px>Subject Name</th>");
		out.print("<th align=center width=250px>Course Detail</th>");
		out.print("</tr>");
		String sid=request.getParameter("sid");
		for(Subject s:subject)
		{
		 if(s.getSubject_Id().equals(sid))
		 {
		  out.print("<tr>");
		  out.print("<td align=center width=150px><input class=txt type=text name=\"sid\" style=\"height:30px; width:150px;\" value=\""+s.getSubject_Id()+"\"></td>");
		  out.print("<td align=center width=150px><input class=txt type=text name=\"sname\" style=\"height:30px; width:250px;\" value=\""+s.getSubject_Name()+"\"></td>");
		  hql="from Course";
		  List<Course> course=session.createQuery(hql).list();
		   for(Course c:course)
			{
			 if(c.getCourse_Id().equals(s.getCourse_Id()))
			  {
				 out.print("<td align=center width=150px><select class=txt name=\"cname\"style=\"height:30px; width:250px;\"><option>"+c.getCourse_Name()+"</option>");
			  }
			}
		   for(Course c:course)
			{
			 if(!c.getCourse_Id().equals(s.getCourse_Id()))
			  {
				 out.print("<option>"+c.getCourse_Name()+"</option>");
			  }
			}
			 
		  out.print("</select></td>");
		  out.print("<td colspan=2 align=center><input type=submit value=Update class=b></td></tr>");
		 }
		 else
		 {
  		  out.print("<tr>");
		  out.print("<td align=center width=150px>"+ s.getSubject_Id() +"</td>");
		  out.print("<td align=center width=250px>"+ s.getSubject_Name()+"</td>");
		  hql="from Course where Course_Id=:Course_Id";
		  List<Course> course=session.createQuery(hql).setParameter("Course_Id", s.getCourse_Id()).list();
		   for(Course c:course)
			 {
			   out.print("<td align=center width=250px>"+ c.getCourse_Name()+"</td>");
			 }
		  out.print("<td align=center width=100px><a href=EditSubject?sid="+ s.getSubject_Id()+ ">Edit</a></td>");
		  out.print("<td align=center width=100px><a href=DeleteSubject?sid="+ s.getSubject_Id()+ ">Delete</a></td>");
		  out.print("</tr>");
		 }
		}			
		 
		out.print("</table>");
		
		String error=(String) hsession.getAttribute("Error");
		if(error!=null)
		{
			out.print("<br><font style=color:red>"+ error + "</font>");
			hsession.setAttribute("Error",null);
		}
		out.print("</div>");
		out.print("<div class=p5><marquee><b>Project By: Pai Najranabanu Yakub (M.C.A)</b></marquee></div>");
		out.print("</form></body>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("Text/Html");
		PrintWriter out =response.getWriter();
		HttpSession hsession=request.getSession();
		String sid=request.getParameter("sid");
		String sname=request.getParameter("sname");
		String cname=request.getParameter("cname");
		
		if(sid.isBlank() || sname.isEmpty() || cname.equals("Select Course") )
		{
			hsession.setAttribute("Error", "Mandatory Details are missing.., please Enter");
			response.sendRedirect("EditSubject");
		}
		else
		{
			SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
			Session session = sessionFactory.openSession();
			String hql="from Course where Course_Name=:Course_Name";
			List<Course> course=session.createQuery(hql).setParameter("Course_Name", cname).list();
			for(Course c:course) {cname=c.getCourse_Id(); }
			hql="from Subject where Subject_Name=:Subject_Name and Course_Id=:Course_Id";
			List<Subject> subject=session.createQuery(hql).setParameter("Subject_Name", sname).setParameter("Course_Id", cname).list();
			if(subject.size()==0)
			{
				Transaction tx=session.beginTransaction();
				Subject s=new Subject();
				s.setSubject_Id(sid);
				s.setSubject_Name(sname);				
				s.setCourse_Id(cname);
				session.update(s);
				tx.commit();
				session.close();
				response.sendRedirect("SubjectMain");
			}
			else
			{
				hsession.setAttribute("Error", "Please Check Subject Details are already exist..");
				response.sendRedirect("EditSubject");
			}
				
		}
	}

}
