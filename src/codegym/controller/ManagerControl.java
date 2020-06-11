package codegym.controller;

import codegym.dao.ProductDaoImpl;
import codegym.model.Product;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ShopController", urlPatterns = "/product_manage")
public class ManagerControl extends HttpServlet {
    private ProductDaoImpl productDao= new ProductDaoImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action= request.getParameter("action");
        if(action == null){
            action = "";
        }
        switch (action){
            case "create":
                createProduct(request,response);
                break;
            case "edit":
                updateProduct(request,response);
                break;
            case "delete":
                deleteProduct(request,response);
                break;
            case "search":
                resultSearchForm(request, response);
                break;
            default:
                break;
        }


    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) {
        int id= Integer.parseInt(request.getParameter("id"));
        Product product = productDao.selectById(id);
        RequestDispatcher dispatcher;
        if(product==null) {
            dispatcher = request.getRequestDispatcher("404-error.jsp");
        }else {
            productDao.removeProduct(id);
            try {
                response.sendRedirect("./product_manage");
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    private void updateProduct(HttpServletRequest request, HttpServletResponse response) {
        String newName= request.getParameter("newName");
        double newPrice= Double.parseDouble(request.getParameter("newPrice"));
        String newDescription= request.getParameter("newDes");
        String newMaker= request.getParameter("newMaker");
        String newImg=request.getParameter("newImg");
        int id= Integer.parseInt(request.getParameter("id"));
        Product product = productDao.selectById(id);
        RequestDispatcher dispatcher;
        if(product==null) {
            dispatcher = request.getRequestDispatcher("404-error.jsp");
        } else {
            dispatcher= request.getRequestDispatcher("manager/editproduct.jsp");
            request.setAttribute("message","Update product success!!!");
            product.setName(newName);
            product.setPrice(newPrice);
            product.setDescription(newDescription);
            product.setMaker(newMaker);
            product.setImg(newImg);
            productDao.updateProduct(product);
        }
        try {
            dispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void createProduct(HttpServletRequest request, HttpServletResponse response) {
        String name= request.getParameter("productName");
        double price= Double.parseDouble(request.getParameter("productPrice"));
        String description= request.getParameter("productDes");
        String img= request.getParameter("productImg");
        String maker= request.getParameter("productMaker");
        productDao.saveProduct(new Product(name,price,description,img,maker));
        RequestDispatcher dispatcher= request.getRequestDispatcher("manager/createproduct.jsp");
        request.setAttribute("message","Create new product success!!!");
        try {
            dispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action= request.getParameter("action");
        if(action == null){
            action = "";
        }
        switch (action){
            case "create":
                showCreatForm(request, response);
                break;
            case "edit":
                showEditForm(request,response);
                break;
            case "delete":
                showDeleteForm(request,response);
                break;
            case "view":
                viewProductForm(request, response);
                break;

            default:
                listProduct(request, response);
                break;
        }

    }

    private void resultSearchForm(HttpServletRequest request, HttpServletResponse response) {
        String adress= request.getParameter("address");
        String name = request.getParameter("searchname");
        List<Product> productList= productDao.searchByName(name);
        if(productList.size()==0){
            request.setAttribute("NOTFOUNDRESULTSEARCH","Không có kết quả tìm kiếm");
        } else {
            request.setAttribute("products",productList);
        }
        RequestDispatcher dispatcher= request.getRequestDispatcher(adress);
        try {
            dispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void viewProductForm(HttpServletRequest request, HttpServletResponse response) {
        int id= Integer.parseInt(request.getParameter("id"));
        Product product=productDao.selectById(id);
        RequestDispatcher dispatcher;
        if(product==null){
            dispatcher=request.getRequestDispatcher("404-error.jsp");
        }else {
            dispatcher=request.getRequestDispatcher("manager/productview.jsp");
            request.setAttribute("product", product);
        }
        try {
            dispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private void showDeleteForm(HttpServletRequest request, HttpServletResponse response) {
        int id= Integer.parseInt(request.getParameter("id"));
        Product product=productDao.selectById(id);
        RequestDispatcher dispatcher;
        if(product==null){
            dispatcher=request.getRequestDispatcher("404-error.jsp");
        }else {
            dispatcher=request.getRequestDispatcher("manager/deleteproduct.jsp");
            request.setAttribute("product", product);
        }
        try {
            dispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) {
        int id= Integer.parseInt(request.getParameter("id"));
        Product product = productDao.selectById(id);
        RequestDispatcher dispatcher;
        if(product==null){
            dispatcher=request.getRequestDispatcher("404-error.jsp");
        }else {
            dispatcher=request.getRequestDispatcher("manager/editproduct.jsp");
            request.setAttribute("product", product);
        }
        try {
            dispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void showCreatForm(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher= request.getRequestDispatcher("manager/createproduct.jsp");

        try {
            dispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private  void listProduct(HttpServletRequest request, HttpServletResponse response){
        List<Product> productList= productDao.findAll();
        request.setAttribute("products",productList);
        RequestDispatcher dispatcher= request.getRequestDispatcher("manager/product_manage.jsp");
        try {
            dispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}