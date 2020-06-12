package codegym.dao;

import codegym.model.Item;
import codegym.model.Order;
import codegym.model.Product;
import codegym.model.User;
import codegym.utils.JDBCUtils;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderImpl implements IOrderDao{
    private final static String ADD_INTO_ORDERDETAIL= "{CALL insertOrderDetail(?,?,?,?)}";
    private final static String ADD_INTO_ORDERS= "{CALL addOrder(?)}";


    private  Order order= new Order();
    private  User user= new User();
    private List<Product> productList = new ArrayList<>();
    private List<Item> items= new ArrayList<>();

    @Override
    public void saveOrder(Order order) throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        CallableStatement call= connection.prepareCall(ADD_INTO_ORDERDETAIL);
       for (int i =0; i<order.getItems().size();i++){
           call.setInt(1,order.getUser().getId());
           call.setInt(2,order.getItems().get(i).getId());
           call.setInt();
       }

    }
}
