import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class DashboardServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            response.sendRedirect("login.html");
            return;
        }

        List<Course> courses = new ArrayList<>();
        courses.add(new Course("101", "Mathematics", "Dr. Smith"));
        courses.add(new Course("102", "Physics", "Prof. Brown"));
        courses.add(new Course("103", "Chemistry", "Dr. Green"));

        request.setAttribute("courses", courses);

        List<Course> enrolled = (List<Course>) session.getAttribute("enrolled");
        if (enrolled == null) {
            enrolled = new ArrayList<>();
        }
        request.setAttribute("enrolledCourses", enrolled);

        String message = request.getParameter("message");
        if (message != null) {
            request.setAttribute("message", message);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("dashboard.jsp");
        dispatcher.forward(request, response);
    }
}
