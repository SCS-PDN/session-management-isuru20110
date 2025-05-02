import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class EnrollServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            response.sendRedirect("login.html");
            return;
        }

        String courseId = request.getParameter("courseId");

        Course course = null;
        if (courseId.equals("101")) {
            course = new Course("101", "Mathematics", "Dr. Smith");
        } else if (courseId.equals("102")) {
            course = new Course("102", "Physics", "Prof. Brown");
        } else if (courseId.equals("103")) {
            course = new Course("103", "Chemistry", "Dr. Green");
        }

        if (course != null) {
            List<Course> enrolled = (List<Course>) session.getAttribute("enrolled");
            if (enrolled == null) {
                enrolled = new ArrayList<>();
            }

            boolean alreadyEnrolled = false;
            for (Course c : enrolled) {
                if (c.getCourseId().equals(courseId)) {
                    alreadyEnrolled = true;
                    break;
                }
            }

            if (!alreadyEnrolled) {
                enrolled.add(course);
                session.setAttribute("enrolled", enrolled);
            }
        }

        response.sendRedirect("DashboardServlet?message=Course+Enrolled+Successfully");
    }
}
