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

import com.entity.Course;
import com.entity.Student;
import com.entity.Teacher;
import com.hibernate.util.HibernateUtil;

/**
 * Servlet implementation class CourseDetailServlet
 */
@WebServlet("/StudentMain")
public class StudentDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentDetailServlet() {
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
		out.println(".main {height:400px;   width:95%;margin:auto;background-color:white;text-align:Center; font-family:cursive; font-size:120%;}");
	    out.print(".p6{height: 30px;   width: 80%; margin:auto;   background-color:white;   text-align:Center; font-family:cursive; font-size:120%;}");
	    out.print("</style></head><body bgcolor=#7575FF>");
	    out.println("<div class=p1></div>");
	    out.println("<div class=p2><b>LEARNING ACADEMY</b></div><br>");
	    out.println("<div class=p4><b>Welcome:</b>"+name + "      " +"<a href=Logout>Logout</a></div><br/>");
	    out.println("<div class=p3>");
	    //out.print("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp&nbsp;&nbsp;&nbsp;&nbsp;&nbsp&nbsp;&nbsp;&nbsp;&nbsp;&nbsp");
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
	    
	    SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
		Session session = sessionFactory.openSession();
		String hql="from Student";
		List<Student> student=session.createQuery(hql).list();
		if(student.size()>=1)
		{
			out.println("<div class=main>");
			out.print("<br><table border=2 align=center>");
			out.print("<tr>");
			out.print("<th align=center width=100px>Student Id</th>");
			out.print("<th align=center width=200px>First Name</th>");
			out.print("<th align=center width=200px>Last Name</th>");
			out.print("<th align=center width=200px>Education</th>");
			out.print("<th align=center width=250px>Address</th>");
			out.print("<th align=center width=200px>Phone_No</th>");
			out.print("</tr>");
			for(Student s:student)
			{
				out.print("<tr>");
				out.print("<td align=center width=100px>"+ s.getStudent_Id() +"</td>");
				out.print("<td align=center width=200px>"+ s.getFirst_Name()+"</td>");
				out.print("<td align=center width=200px>"+ s.getLast_Name()+"</td>");
				out.print("<td align=center width=200px>"+ s.getEducation()+"</td>");
				out.print("<td align=center width=250px>"+ s.getAddress()+"</td>");
				out.print("<td align=center width=200px>"+ s.getPhoneNo()+"</td>");
				out.print("<td align=center width=100px><a href=EditStudent?sid="+s.getStudent_Id() + ">Edit</a></td>");
				out.print("<td align=center width=100px><a href=DeleteStudent?sid="+s.getStudent_Id() + ">Delete</a></td>");
				out.print("</tr>");
			}
			out.print("</table>");
			out.print("<br><a href=AddStudent>Are You want to add Student?</a>");
			String error=(String) hsession.getAttribute("Error");
			if(error!=null)
			{
				out.print("<br><font style=color:red>"+ error + "</font>");
				hsession.setAttribute("Error",null);
			}
			out.print("</div>");
			out.print("<div class=p5><marquee><b>Project By: Pai Najranabanu Yakub (M.C.A)</b></marquee></div>");
		}
		else
		{
			out.print("<br><div class=p6>");
			out.print("<font style=color:red> No Student Details Found </font> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
			out.print("<a href=AddStudent>Are You want to add Student?</a></div>");
			out.print("<div class=p7><marquee><b>Project By: Pai Najranabanu Yakub (M.C.A)</b></marquee></div>");
		}
		
		out.print("</body>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
