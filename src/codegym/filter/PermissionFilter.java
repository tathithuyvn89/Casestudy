package codegym.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebFilter(filterName = "PermissionFilter", urlPatterns = "/home")
public class PermissionFilter implements Filter {
    public static final int ROLE_ADMIN = 1;
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        HttpSession session = request.getSession();
        Object o = session.getAttribute("ROLE");
        if (o != null) {
            int role = Integer.parseInt(o.toString());

            if (role!= ROLE_ADMIN) {
                chain.doFilter(req, resp);
            } else {
                response.sendRedirect("./product_manage");
//                PrintWriter writer = resp.getWriter();
//                writer.write("Khong co quyen thuc hien thao tac nay!");
            }
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("view/login.jsp");
            dispatcher.forward(request, response);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}