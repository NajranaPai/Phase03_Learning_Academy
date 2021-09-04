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
import com.entity.Student;
import com.entity.StudentCourse;
import com.entity.Subject;
import com.entity.Teacher;
import com.entity.TeacherSubject;
import com.hibernate.util.HibernateUtil;

/**
 * Servlet implementation class CourseDetailServlet
 */
@WebServlet("/AddStudentAssignment")
public class StudentCourseAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentCourseAddServlet() {
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
	    out.print("</style></head><body bgcolor=#7575FF><form action=AddStudentAssignment method=post>");
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
		 
		String hql="from StudentCourse";
		List<StudentCourse> studentcourse=session.createQuery(hql).list();
		
		out.print("<tr>");
		out.print("<th align=center width=100px>No</th>");
		out.print("<th align=center width=250px>Student Name</th>");
		out.print("<th align=center width=250px>Course Name</th>");
		out.print("</tr>");	
		for(StudentCourse sc:studentcourse)
		{
			out.print("<tr>");
			out.print("<td align=center>"+ sc.getSrno() +"</td>");
			hql="From Student where Student_Id=:Student_Id";
			List<Student> student=session.createQuery(hql).setParameter("Student_Id",sc.getStudent_Id()).list();
			for(Student s:student)
			{
				out.print("<td align=center>"+ s.getFirst_Name() + "  " +s.getLast_Name() +"</td>");
			}
			hql="From Course Where Course_Id=:Course_Id";
			List<Course> cname=session.createQuery(hql).setParameter("Course_Id", sc.getCourse_Id()).list();
			for(Course c: cname)
			{
				out.print("<td align=center>"+ c.getCourse_Name()  +"</td>");
			}
			 
			out.print("<td align=center width=100px><a href=EditStudentAssignment?scid="+ sc.getSrno()+ ">Edit</a></td>");
			out.print("<td align=center width=100px><a href=DeleteStudentAssignment?scid="+sc.getSrno() + ">Delete</a></td>");
			out.print("</tr>");
		}
		
		out.print("<tr>");
		out.print("<td align=center><input class=txt type=text name=\"srno\" style=\"height:30px; width:150px;\" value="+studentcourse.size()+1+"></td>");
		out.print("<td align=center width=250px><select class=txt name=\"sname\"style=\"height:30px; width:250px;\"><option> Select Student </option>");
		hql="From Student";
		 
		List<Student> student=session.createQuery(hql).list();
		for(Student s:student)
		{
			out.print("<option>" + s.getFirst_Name() + " " +s.getLast_Name() + "</option>");
		}
		out.print("</select></td><td align=center width=250px><select class=txt name=\"cname\"style=\"height:30px; width:250px;\"><option> Select Course </option>");
		hql="From Course";
		List<Course> cname=session.createQuery(hql).list();
		for(Course c: cname)
		{
			out.print("<option>"+ c.getCourse_Name()  +"</option>");
		}
		 
		out.print("</select></td>");
		out.print("<td colspan=2 align=center><input type=submit value=Assign class=b></td>");
		out.print("</tr>");
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
		String sname=request.getParameter("sname");
		String cname=request.getParameter("cname");
		 
		String cid=null,sid=null;
		if(cname.equals("Select Course") ||sname.equals("Select Student") )
		{
			hsession.setAttribute("Error", "Please Select Mandatory details");
			response.sendRedirect("AddStudentAssignment");
		}
		else
		{
			SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
			Session session = sessionFactory.openSession();
			String[] name=sname.split("\\s",2);
			String fname=name[0];
			String lname=name[1];
			String hql="From Student Where First_Name=:First_Name and Last_Name=:Last_Name";
			List<Student> student=session.createQuery(hql).setParameter("First_Name", fname).setParameter("Last_Name",lname).list();
			for(Student s:student)
			{
				sid=s.getStudent_Id();
			}
			
			hql="From Course Where Course_Name=:Course_Name";
			List<Course> course=session.createQuery(hql).setParameter("Course_Name", cname).list();
			for(Course c: course)
			{
			 cid=c.getCourse_Id();
			}
			 
			 hql="from StudentCourse where Sr_No=:Sr_No or Student_Id=:Student_Id and Course_Id=:Course_Id";
			List<StudentCourse> studentcourse=session.createQuery(hql).setParameter("Sr_No",srno).setParameter("Student_Id",sid).setParameter("Course_Id",cid).list();
			if(studentcourse.size()==0)
			{
				Transaction tx=session.beginTransaction();
				StudentCourse sc=new StudentCourse();
				sc.setSrno(srno);
				sc.setStudent_Id(sid);;
				sc.setCourse_Id(cid);				 
				session.save(sc);
				tx.commit();
				session.close();
				response.sendRedirect("StudentCourseMain");
			}
			else
			{
				hsession.setAttribute("Error", "Student have already assign same course please check");
				response.sendRedirect("AddStudentAssignment");
			}
			
				
		}
	}

}
