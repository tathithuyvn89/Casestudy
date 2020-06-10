package codegym.dao;

import codegym.model.Product;
import codegym.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements IProductDao {
    private  static final String SELECT_ALL_FROM_PRODUCTS= "SELECT * FROM PRODUCTS;";
    private static final  String INSERT_NEW_PRODUCT= "insert into products(name,price,description,img,maker)"+
            " values " +"(?,?,?,?,?);";
    private static final String UPDATE_PRODUCTS= "update products set name=?,price=?,description=?,img=?,maker=?"+
            " where id=?";
    private static final String SELECT_PRODUCTS_BY_ID="select name,price,description,img,maker from products"+
            " where id=?";
    private  static final String DELETE_PRODUCT_BY_ID="delete from products where id=?";
    private static final String FIND_PRODUCT_BY_NAME ="SELECT * FROM products where name like ?;";

private List<Product> products = new ArrayList<>();
    @Override
    public List<Product> findAll() {
        products.clear();
        Connection connection= JDBCUtils.getConnection();
        try {
            PreparedStatement preparedStatement= connection.prepareStatement(SELECT_ALL_FROM_PRODUCTS);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id =resultSet.getInt("id");
                String name= resultSet.getString("name");
                double price= resultSet.getDouble("price");
                String description=resultSet.getString("description");
                String img=resultSet.getString("img");
                String maker=resultSet.getString("maker");
                products.add(new Product(id,name,price,description,img,maker));

            }
        } catch (SQLException throwables) {
            JDBCUtils.printSQLException(throwables);
        }
        return products;
    }

    @Override
    public void saveProduct(Product product) {
        Connection connection= JDBCUtils.getConnection();
        try {
            PreparedStatement preparedStatement= connection.prepareStatement(INSERT_NEW_PRODUCT);
            preparedStatement.setString(1,product.getName());
            preparedStatement.setDouble(2,product.getPrice());
            preparedStatement.setString(3,product.getDescription());
            preparedStatement.setString(4,product.getImg());
            preparedStatement.setString(5,product.getMaker());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            JDBCUtils.printSQLException(throwables);
        }


    }

    @Override
    public Product selectById(int id) {
        Product product=null;
        Connection connection= JDBCUtils.getConnection();
        try {
            PreparedStatement preparedStatement= connection.prepareStatement(SELECT_PRODUCTS_BY_ID);
            preparedStatement.setInt(1,id);
            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                String name= resultSet.getString("name");
                Double price= resultSet.getDouble("price");
                String description= resultSet.getString("description");
                String img= resultSet.getString("img");
                String maker= resultSet.getString("maker");
                product= new Product(id,name,price,description,img,maker);
            }
        } catch (SQLException throwables) {
            JDBCUtils.printSQLException(throwables);
        }
        return product;
    }


    @Override
    public boolean removeProduct(int id) {
        boolean rowDeleted=false;
        Connection connection= JDBCUtils.getConnection();
        try {
            PreparedStatement preparedStatement= connection.prepareStatement(DELETE_PRODUCT_BY_ID);
            preparedStatement.setInt(1,id);
            rowDeleted = preparedStatement.executeUpdate()>0;
        } catch (SQLException throwables) {
            JDBCUtils.printSQLException(throwables);
        }
        return rowDeleted;


    }

    @Override
    public boolean updateProduct(Product product) {
        boolean rowUpdated = false;
        Connection connection = JDBCUtils.getConnection();
        try {
            PreparedStatement preparedStatement= connection.prepareStatement(UPDATE_PRODUCTS);
            preparedStatement.setString(1,product.getName());
            preparedStatement.setDouble(2,product.getPrice());
            preparedStatement.setString(3,product.getDescription());
            preparedStatement.setString(4,product.getImg());
            preparedStatement.setString(5,product.getMaker());
            preparedStatement.setInt(6,product.getId());
            rowUpdated = preparedStatement.executeUpdate()>0;
        } catch (SQLException throwables) {
            JDBCUtils.printSQLException(throwables);
        }
        return rowUpdated;
    }


    @Override
    public List<Product> searchByName(String productName) {
        products.clear();
        Connection connection= JDBCUtils.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_PRODUCT_BY_NAME);
            preparedStatement.setString(1,"%"+productName+"%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double price= resultSet.getDouble("price");
                String description= resultSet.getString("description");
                String img = resultSet.getString("img");
                String maker = resultSet.getString("maker");
                products.add(new Product(id,name,price,description,img,maker));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return products;
    }
}

