import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class LoginServlet extends HttpServlet {
    private Map<String, String> users = new HashMap<>();

    public void init() {
        users.put("yourname01", "yourpass01");
        users.put("testuser02", "testpass02");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (users.containsKey(username) && users.get(username).equals(password)) {
            HttpSession session = request.getSession();
            session.setAttribute("username", username);

            Cookie cookie = new Cookie("username", username);
            cookie.setMaxAge(3600); // 1 hour
            response.addCookie(cookie);

            response.sendRedirect("DashboardServlet");
        } else {
            response.sendRedirect("login.html");
        }
    }
}
