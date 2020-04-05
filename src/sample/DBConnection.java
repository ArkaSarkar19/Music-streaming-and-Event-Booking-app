package sample;
import java.sql.*;
public class DBConnection {
    private String host = "jdbc:mysql://www.remotemysql.com";
    private String user = "8WS34TaNi5";
    private String passowrd = "fKJYiyhXGc";

    public Connection getconnction(){
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(host, user,passowrd);
            System.out.println("Connection made "  + connection);
            LoginBox.getLogin();
        }
        catch (Exception  e ){
            System.out.println("An error occurred while connecting to DATABASE");
            System.out.println(e);
        }
        return connection;
    }
}
