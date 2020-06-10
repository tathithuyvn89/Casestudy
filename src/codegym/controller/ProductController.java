package codegym.controller;

import codegym.dao.ProductDaoImpl;
import codegym.model.Item;
import codegym.model.Order;
import codegym.model.Product;

import javax.print.attribute.standard.OrientationRequested;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ProductController",urlPatterns = "/home")
public class ProductController extends HttpServlet {
    private ProductDaoImpl productDao;

    @Override
    public void init() throws ServletException {
       productDao= new ProductDaoImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action==null){
            action="";
        }
        switch (action) {
            case "addcard":
                addCard(request, response);
                break;
            default:
                break;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     String action = request.getParameter("action");
     if (action==null){
         action="";
     }
     switch (action){
         case "addcard":
             showForm(request,response);
             break;

         case "list":
             listProduct(request,response);
             break;
         default:
             RequestDispatcher requestDispatcher= request.getRequestDispatcher("view/login.jsp");
             requestDispatcher.forward(request,response);
             break;
     }
    }

    private void showForm(HttpServletRequest request, HttpServletResponse response) {

//        HttpSession session = request.getSession();
//        Object o = session.getAttribute("order");
//        Order order;
//        if (o==null){
//            order = new Order();
//        } else {
//            order= (Order) o;
//        }
//        request.setAttribute("order",order.getItems());
//        RequestDispatcher requestDispatcher= request.getRequestDispatcher("view/card_items.jsp");
//        try {
//            requestDispatcher.forward(request,response);
//        } catch (ServletException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        int quantity=1;
        int id;
        if(request.getParameter("productid")!=null){
            id=Integer.parseInt(request.getParameter("productid"));
            Product product= productDao.selectById(id);
//            if (product!=null){
//                quantity=Integer.parseInt(request.getParameter("quantity"));
//            }
            HttpSession session = request.getSession();
            if(session.getAttribute("order")==null){
                Order order = new Order();
                List<Item> items = new ArrayList<>();
                Item item= new Item();
                item.setProduct(product);
                item.setQuantity(quantity);
                item.setPrice(product.getPrice());
                items.add(item);
                order.addItem(item);
                session.setAttribute("order",order);
                request.setAttribute("listitem",order.getItems());
            } else {
                Order order = (Order) session.getAttribute("order");
                List<Item> listItem = order.getItems();
                boolean check=false;
                for (Item item: listItem){
                    if(item.getProduct().getId()==product.getId()){
                        item.setQuantity(item.getQuantity()+quantity);
                        check=true;

                    }
                }
                if (check==false){
                    Item item = new Item();
                    item.setQuantity(quantity);
                    item.setProduct(product);
                    item.setPrice(product.getPrice());
                    listItem.add(item);
                }

                order.setItems(listItem);
                session.setAttribute("order",order);
                request.setAttribute("listitem",order.getItems());

            }

            RequestDispatcher requestDispatcher = request.getRequestDispatcher("view/card_items.jsp");
            try {
                requestDispatcher.forward(request,response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            try {
                response.sendRedirect("view/card_items.jsp");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void addCard(HttpServletRequest request, HttpServletResponse response) {
//        HttpSession session = request.getSession();
//        Object o = session.getAttribute("order");
//        Order order;
//        if (o == null) {
//            order = new Order();
//        } else {
//            order = (Order) o;
//        }
//
//        int id = Integer.parseInt(request.getParameter("id"));
//
//        Product product = productDao.selectById(id);
//        Item oderItem= new Item();
//        oderItem.setProduct(product);
//        oderItem.setPrice(product.getPrice());
//        oderItem.setQuantity(1);
//        order.addItem(oderItem);
//        session.setAttribute("order", order);
////        request.setAttribute("order",order.getItems());
//        RequestDispatcher requestDispatcher= request.getRequestDispatcher("view/card_items.jsp");
//        try {
//            requestDispatcher.forward(request,response);
//        } catch (ServletException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }




    }

    private void listProduct(HttpServletRequest request, HttpServletResponse response) {
        List<Product> productList= productDao.findAll();

        try {

            request.setAttribute("listProduct",productList);
            RequestDispatcher requestDispatcher= request.getRequestDispatcher("view/home.jsp");
            requestDispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
