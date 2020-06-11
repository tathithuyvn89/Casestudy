package codegym.controller;

import codegym.dao.LoginDao;
import codegym.dao.UserDao;
import codegym.model.Login;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LoginController", urlPatterns = "/login")
public class LoginController extends HttpServlet {
    private LoginDao loginDao;

    @Override
    public void init() throws ServletException {
        loginDao= new LoginDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username= request.getParameter("username");
        String password= request.getParameter("password");
        Login login= new Login(username,password);
        if (loginDao.validate(login)){
            HttpSession session= request.getSession();
            session.setAttribute("IS_lOGGINED",true);
            session.setAttribute("ROLE",loginDao.doPermission(username));
            session.setAttribute("USERNAME",loginDao.findNameByUsername(username));
            response.sendRedirect("./home?action=list");

        }else {
            RequestDispatcher requestDispatcher= request.getRequestDispatcher("view/login.jsp");
            request.setAttribute("NOTIFICATION", "Bạn đã nhập username hoac mat khau sai");
            requestDispatcher.forward(request,response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("view/login.jsp");
        requestDispatcher.forward(request,response);

    }
}
