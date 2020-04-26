package Database;
import java.sql.*;
public class DBConnection {
    private String host = "jdbc:mysql://bajao.crgxyeyepevg.us-east-1.rds.amazonaws.com/BAJAO";
    private String user = "admin";
    private String passowrd = "admin123";

    public Connection getConnection(){
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(host, user,passowrd);
            System.out.println("Connection made "  + connection);
        }
        catch (Exception  e ){
            System.out.println("An error occurred while connecting to DATABASE");
            System.out.println(e);
        }
        return connection;
    }
}
