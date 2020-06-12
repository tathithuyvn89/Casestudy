package codegym.dao;

import codegym.model.Order;
import codegym.model.Product;

import java.sql.SQLException;
import java.util.List;

public interface IOrderDao {
    void saveOrder(Order order) throws SQLException;
//    void saveProduct (Product product);
//    Product selectById(int id);
//    boolean removeProduct(int id);
//    boolean updateProduct(Product product);
//    List<Product> searchByName(String productName);
//    List<Product> groupByMaker(String maker);
}
