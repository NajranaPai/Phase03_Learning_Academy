package com.hibernate.util;
import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import com.entity.Admin;
import com.entity.Course;
import com.entity.Student;
import com.entity.StudentCourse;
import com.entity.Subject;
import com.entity.Teacher;
import com.entity.TeacherSubject;

public class HibernateUtil {
	private static SessionFactory sessionFactory;

	public static SessionFactory buildSessionFactory() {
		if (sessionFactory == null) {
			try {
				Configuration configuration = new Configuration();

				// Hibernate settings equivalent to hibernate.cfg.xml's properties
				Properties properties = new Properties();
				properties.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
				properties.put(Environment.URL, "jdbc:mysql://localhost:3306/learningacademy");
				properties.put(Environment.USER, "root");
				properties.put(Environment.PASS, "root");
				properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5InnoDBDialect");

				properties.put(Environment.SHOW_SQL, "true");

				properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

				properties.put(Environment.HBM2DDL_AUTO, "update");

				configuration.setProperties(properties);
				configuration.addAnnotatedClass(Admin.class);	 
				configuration.addAnnotatedClass(Course.class);	
				configuration.addAnnotatedClass(Subject.class);	
				configuration.addAnnotatedClass(Teacher.class);
				configuration.addAnnotatedClass(Student.class);
				configuration.addAnnotatedClass(TeacherSubject.class);
				configuration.addAnnotatedClass(StudentCourse.class);
				
				ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
						.applySettings(configuration.getProperties()).build();
				 
				sessionFactory = configuration.buildSessionFactory(serviceRegistry);
				return sessionFactory;

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return sessionFactory;
	}
}
