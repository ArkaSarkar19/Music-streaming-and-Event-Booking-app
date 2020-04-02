package com.example.bajao;
import java.sql.*;
public class DBConnection {

    public java.sql.Connection getConnection(){
          java.sql.Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("remotemysql.com", "8WS34TaNi5","fKJYiyhXGc");
        }
        catch (Exception  e ){ System.out.println(e);}
        return connection;
    }
}
