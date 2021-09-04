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
import com.entity.Teacher;
import com.entity.TeacherSubject;
import com.hibernate.util.HibernateUtil;

/**
 * Servlet implementation class CourseDetailServlet
 */
@WebServlet("/EditTeacherSubject")
public class TeacherSubjectUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TeacherSubjectUpdateServlet() {
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
		out.println(".main {height:400px;   width:80%;margin:auto;background-color:white;text-align:Center; font-family:cursive; font-size:120%;}");
	    out.print(".p6{height: 30px;   width: 80%; margin:auto;   background-color:white;   text-align:Center; font-family:cursive; font-size:120%;}");
	    out.print(".b{font-family:cursive; font-size:120%;  width:200px; height: 30px; text-align:Center; font-family:cursive; }");
	    out.print(".txt{font-family:cursive; font-size:100%;text-align:Center;}");
	    out.print("</style></head><body bgcolor=#7575FF><form action=EditTeacherSubject method=post>");
	    out.println("<div class=p1></div>");
	    out.println("<div class=p2><b>LEARNING ACADEMY</b></div><br>");
	    out.println("<div class=p4><b>Welcome:</b>"+name + "      " +"<a href=Logout>Logout</a></div><br/>");
	    out.println("<div class=p3>");
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
		String hql="from TeacherSubject";
		List<TeacherSubject> teachersubject=session.createQuery(hql).list();
		int tid=Integer.parseInt(request.getParameter("tsid"));
		out.print("<tr>");
		out.print("<th align=center width=100px>No</th>");
		out.print("<th align=center width=250px>Teacher Name</th>");
		out.print("<th align=center width=250px>Course Name</th>");
		out.print("<th align=center width=250px>Subject Name</th>");
		out.print("</tr>");		
		for(TeacherSubject ts:teachersubject)
		{
		  if(ts.getSrno()==tid)
		  {
			    out.print("<tr>");
				out.print("<td align=center><input class=txt type=text name=\"srno\" style=\"height:30px; width:150px;\" value="+ tid +"></td>");
				out.print("<td align=center width=250px><select class=txt name=\"tname\"style=\"height:30px; width:250px;\">");
				hql="From Teacher";
				List<Teacher> tname=session.createQuery(hql).list();
				for(Teacher t2:tname)
				{ 
					if(t2.getTeacher_Id().equals(ts.getTeacher_Id()))
					  out.print("<option>" + t2.getFirst_Name() + " " +t2.getLast_Name() + "</option>");
				}
				for(Teacher t2:tname)
				{ 
					if(!t2.getTeacher_Id().equals(ts.getTeacher_Id()))
					 out.print("<option>" + t2.getFirst_Name() + " " +t2.getLast_Name() + "</option>");
				}
				out.print("</select></td><td align=center width=250px><select class=txt name=\"cname\"style=\"height:30px; width:250px;\">");
				hql="From Course";
				List<Course> cname=session.createQuery(hql).list();
				for(Course c: cname)
				{
					if(c.getCourse_Id().equals(ts.getCourse_Id()))
					  out.print("<option>"+ c.getCourse_Name()  +"</option>");
				}
				for(Course c: cname)
				{
					if(!c.getCourse_Id().equals(ts.getCourse_Id()))
					  out.print("<option>"+ c.getCourse_Name()  +"</option>");
				}
				out.print("</select></td><td align=center width=250px><select class=txt name=\"sname\"style=\"height:30px; width:250px;\">");
				hql="From Subject";
				List<Subject> subject=session.createQuery(hql).list();
				for(Subject s: subject)
				{
					if(s.getSubject_Id().equals(ts.getSubject_Id()))
					 out.print("<option>"+ s.getSubject_Name()  +"</option>");
				}
				for(Subject s: subject)
				{
					if(!s.getSubject_Id().equals(ts.getSubject_Id()))
					 out.print("<option>"+ s.getSubject_Name()  +"</option>");
				}
				out.print("</select></td>");
				out.print("<td colspan=2 align=center><input type=submit value=Update class=b></td>");
				out.print("</tr>");
		  }
		  else
		  {
			  out.print("<tr>");
				out.print("<td align=center>"+ ts.getSrno() +"</td>");
				hql="From Teacher where Teacher_Id=:Teacher_Id";
				List<Teacher> tname=session.createQuery(hql).setParameter("Teacher_Id",ts.getTeacher_Id()).list();
				for(Teacher t2:tname)
				{
					out.print("<td align=center>"+ t2.getFirst_Name() + "  " +t2.getLast_Name() +"</td>");
				}
				hql="From Course Where Course_Id=:Course_Id";
				List<Course> cname=session.createQuery(hql).setParameter("Course_Id", ts.getCourse_Id()).list();
				for(Course c: cname)
				{
					out.print("<td align=center>"+ c.getCourse_Name()  +"</td>");
				}
				hql="From Subject Where Subject_Id=:Subject_Id";
				List<Subject> subject=session.createQuery(hql).setParameter("Subject_Id",ts.getSubject_Id()).list();
				for(Subject s: subject)
				{
					out.print("<td align=center>"+ s.getSubject_Name()  +"</td>");
				}
				out.print("<td align=center width=100px><a href=EditTeacherSubject?tsid="+ ts.getSrno()+ ">Edit</a></td>");
				out.print("<td align=center width=100px><a href=DeleteTeacherSubject?tsid="+ts.getSrno() + ">Delete</a></td>");
				out.print("</tr>");
				
		  }
		}
		
		
		out.print("</table>");
		
		String error=(String) hsession.getAttribute("Error");
		if(error!=null)
		{
			out.print("<font style=color:red>"+ error + "</font>");
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
		int srno=Integer.parseInt(request.getParameter("srno"));
		String tname=request.getParameter("tname");
		String cname=request.getParameter("cname");
		String sname=request.getParameter("sname");
		String tid=null,sid=null,cid=null;
		if(cname.isEmpty() ||sname.isEmpty() || tname.isEmpty()  )
		{
			hsession.setAttribute("Error", "Please Select Mandatory details");
			response.sendRedirect("TeacherSubjectMain");
		}
		else
		{
			SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
			Session session = sessionFactory.openSession();
			String[] name=tname.split("\\s",2);
			String fname=name[0];
			String lname=name[1];
			String hql="From Teacher Where First_Name=:First_Name and Last_Name=:Last_Name";
			List<Teacher> teacher=session.createQuery(hql).setParameter("First_Name", fname).setParameter("Last_Name",lname).list();
			for(Teacher t2:teacher)
			{
				tid=t2.getTeacher_Id();
			}
			
			hql="From Course Where Course_Name=:Course_Name";
			List<Course> course=session.createQuery(hql).setParameter("Course_Name", cname).list();
			for(Course c: course)
			{
			 cid=c.getCourse_Id();
			}
			hql="From Subject Where Subject_Name=:Subject_Name";
			List<Subject> subject=session.createQuery(hql).setParameter("Subject_Name",sname).list();
			for(Subject s: subject)
			{
			 sid=s.getSubject_Id();
			}
			hql="From TeacherSubject where Teacher_Id=:Teacher_Id and Course_Id=:Course_Id and Subject_Id=:Subject_Id";
			List<TeacherSubject> teachersubject=session.createQuery(hql).setParameter("Teacher_Id", tid).setParameter("Course_Id", cid).setParameter("Subject_Id", sid).list();
			if(teachersubject.size()==0)
			{
				Transaction tx=session.beginTransaction();
				TeacherSubject ts=new TeacherSubject();
				ts.setSrno(srno);
				ts.setTeacher_Id(tid);
				ts.setCourse_Id(cid);
				ts.setSubject_Id(sid);
				session.update(ts);
				tx.commit();
				session.close();
				response.sendRedirect("TeacherSubjectMain");
			}
			else
			{
				hsession.setAttribute("Error", "Subject is already assign to teacher please check");
				response.sendRedirect("TeacherSubjectMain");
			}
			
				
		}
	}

}
