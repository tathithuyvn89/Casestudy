package codegym.dao;

import codegym.model.Login;
import codegym.model.User;
import codegym.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoginDao {

    private static final String SELECT_FROM_USERS ="SELECT password from users where username=? and password=?;";
    private static  final String SELECT_ALL_USERS= "SELECT * from users where username =?";
    private List<User>users = new ArrayList<>();
    public boolean validate(Login login) {
        boolean status= false;
        try {
            Connection connection = JDBCUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FROM_USERS);
            preparedStatement.setString(1,login.getUsername());
            preparedStatement.setString(2,login.getPassword());
            ResultSet resultSet = preparedStatement.executeQuery();
            status = resultSet.next();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return  status;
    }
    public int doPermission( String username){
        int role =-1;
        try {
            Connection connection = JDBCUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);
            preparedStatement.setString(1,username);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
               role= resultSet.getInt("role");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return role;
    }
}
