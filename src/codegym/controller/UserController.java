package codegym.controller;

import codegym.dao.UserDao;
import codegym.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UserController",urlPatterns = "/register")
public class UserController extends HttpServlet {
    private UserDao userDao;

    @Override
    public void init() throws ServletException {
        userDao= new UserDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//       user=null;
        String fullname= request.getParameter("fullname");
        String username= request.getParameter("username");
        String email= request.getParameter("email");
        String password1= request.getParameter("password1");
        String passwordFinal= request.getParameter("passwordFinal");
        if (password1.equals(passwordFinal)){
          User  user= new User(fullname,username,password1,email);
           userDao.insert(user);
            response.sendRedirect("view/login.jsp");
        } else {
            RequestDispatcher requestDispatcher= request.getRequestDispatcher("view/register.jsp");
            request.setAttribute("NOTIFICATION", "Bạn đã nhập 2 lần mật khẩu không giống nhau");
            requestDispatcher.forward(request,response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher= request.getRequestDispatcher("view/register.jsp");
        requestDispatcher.forward(request,response);
    }
}
