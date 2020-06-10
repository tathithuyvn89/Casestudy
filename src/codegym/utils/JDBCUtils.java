package codegym.utils;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;

public class JDBCUtils {
    private  static String jdbcURL = "jdbc:mysql://localhost:3306/casestudy_module3";
    private static String jdbcUsername= "root";
    private static String jdbcPassword="12345";
    public static Connection getConnection(){
        Connection connection= null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection= DriverManager.getConnection(jdbcURL,jdbcUsername,jdbcPassword);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }
    public static void printSQLException(SQLException ex){
        for(Throwable e: ex){
            if (e instanceof SQLException){
                e.printStackTrace(System.err);
                System.err.println("SQLState: "+((SQLException)e).getSQLState());
                System.err.println("Error Code: "+((SQLException)e).getErrorCode());
                System.err.println("Message: "+e.getMessage());
                Throwable t= ex.getCause();
                while (t!=null){
                    System.out.println("Cause: "+t);
                    t=t.getCause();
                }
            }
        }
    }
    public static Date getSQLDate(LocalDate date){
        return Date.valueOf(date);
    }
    public static LocalDate getUtilDate(Date sqlDate){
        return sqlDate.toLocalDate();
    }
}
