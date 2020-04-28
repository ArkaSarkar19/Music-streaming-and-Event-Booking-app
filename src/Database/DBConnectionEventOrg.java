package Database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnectionEventOrg {
    private String host = "jdbc:mysql://bajao.crgxyeyepevg.us-east-1.rds.amazonaws.com/BAJAO";
    private String user = "EventOrg";
    private String passowrd = "1234";

    public Connection getConnection(){
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(host, user,passowrd);
            System.out.println("Connection made "  + connection);
        }
        catch (Exception  e ){
            System.out.println("An error occurred while connecting to musical proffesional DATABASE");
            System.out.println(e);
        }
        return connection;
    }
}
