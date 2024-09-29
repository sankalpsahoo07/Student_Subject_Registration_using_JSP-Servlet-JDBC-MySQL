package Admin.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Admin.dao.ThirdSemARVRDao;
import Admin.model.AddCourseModel;


@WebServlet("/ThirdSem_AR_VRCtrl")
public class ThirdSem_AR_VRCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ThirdSem_AR_VRCtrl() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
		String CourseCode = request.getParameter("CourseCode");
		String CourseName = request.getParameter("CourseName");
		String Credits = request.getParameter("Credits");
		String CourseType = request.getParameter("CourseType");
		String AcademicYear = request.getParameter("AcademicYear");
		
		AddCourseModel course = new AddCourseModel();
		
		course.setCourseCode(CourseCode);
		course.setCourseName(CourseName);
		course.setCredits(Credits);
		course.setCourseType(CourseType);
		course.setAcademicYear(AcademicYear);
		
		ThirdSemARVRDao dao = new ThirdSemARVRDao();
		RequestDispatcher rd=null;
		
		int i=0;
		
		try {
			i=dao.addCourse(course);
			
			if(i!=0) {
				rd = request.getRequestDispatcher("CourseSuccess.jsp");
				request.setAttribute("status", course);
			}else {
				rd=request.getRequestDispatcher("ThirdSem_ar_vr.jsp");
				//request.setAttribute("ErrorMessage", "Invalid credentials. Please try again.");

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		rd.forward(request, response);
	}

}
