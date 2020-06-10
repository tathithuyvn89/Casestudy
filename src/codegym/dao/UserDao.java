package codegym.dao;

import codegym.model.User;
import codegym.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    private static final String INSERT_INTO_USERS =
            "INSERT INTO users (fullname,username,password,email)" +
            "values"+ "(?,?,?,?);";
//    private static final String UPDATE_USER_SQL = "UPDATE USERS SET fullname=?,username=?,password=?" +
//            "where username=?;";
    private static final String SELECT_USERS_BY_USERNAME="select fullname,username,password from users where username=?";
    private List<User>users = new ArrayList<>();
    public void insert(User user) {
        Connection connection = JDBCUtils.getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_USERS);
            preparedStatement.setString(1, user.getFullname());
            preparedStatement.setString(2, user.getUsername());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getEmail());
           preparedStatement.executeUpdate() ;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
//    public boolean update(User user){
//        boolean rowUpdated= false;
//        Connection connection = (Connection) JDBCConnector.getInstance();
//        try {
//            PreparedStatement preparedStatement= connection.prepareStatement(UPDATE_USER_SQL);
//            preparedStatement.setString(1, user.getFullname());
//            preparedStatement.setString(2, user.getUsername());
//            preparedStatement.setString(3, user.getPassword());
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//    }
    public  User getUserByUsername( String username){
        User user= null;
        Connection connection = JDBCUtils.getConnection();
        try {
            PreparedStatement preparedStatement= connection.prepareStatement(SELECT_USERS_BY_USERNAME);
            preparedStatement.setString(1,username);

            ResultSet resultSet= preparedStatement.executeQuery();
            while (resultSet.next()){
                String fullname= resultSet.getString("fullname");
                String email= resultSet.getString("email");
                String password= resultSet.getString("password");
                user= new User(fullname,username,email,password);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }
}
