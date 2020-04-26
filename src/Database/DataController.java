package Database;
import Core.*;
import Exception.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class DataController {
    public Connection connection;

    public List<Map<String,String>> getData(String tableName) throws MyException{
        List<Map<String,String>> data  = null;
        if(tableName == null) throw new InvalidTableNameException("Enter a valid table Name");
        try{
            DBConnection con = new DBConnection();
            connection = con.getConnection();
            if(connection == null) throw new ConnectionInvalidException("Connection not Establised");
            Statement stmt = connection.createStatement();
            List<String> allTables = getAllTables();
            if(!allTables.contains(tableName.toUpperCase())) throw new InvalidTableNameException(tableName + "is not a table in database");
            String query = "Select * from" + tableName.toUpperCase();
            ResultSet rs = stmt.executeQuery(query);
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            while(rs.next()){
                Map<String, String> map = new HashMap<String,String>();
                for(int i=1;i<columnsNumber;i++){
                    String columnName = rsmd.getColumnName(i);
                    map.put(columnName,rs.getString(i));
                }
                data.add(map);
            }
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
    public List<String> getAllTables(){
        List<String> data  = new ArrayList<String>();
        try{
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("Show tables");
            while(rs.next()) {
                data.add(rs.getString(1).toUpperCase());

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    public   User validateLogin(String email, String password) throws MyException{
        try{
            DBConnection con = new DBConnection();
            connection = con.getConnection();
            if(connection == null) throw new ConnectionInvalidException("Connection not Establised");
            Statement stmt = connection.createStatement();
            String query = "Select * from USER as T  where email = '" + email + "' and '" + password + "' = (select password from USER_AUTH as S where S.user_id = T.user_id )";
            System.out.println(query);
            ResultSet rs = stmt.executeQuery(query);
            rs.last();
//            System.out.println("number of rows"+ rs.getRow());
            if(rs.getRow() == 0) throw new InvalidUsernamePassowordException("Invalid USERNAME or PASSWORD");
            User user = new User(rs.getInt("user_id"),rs.getString("name"),rs.getString("country"),rs.getString("email"),rs.getString("DOB"),rs.getString("gender"));

            connection.close();
            System.out.println("Login");
            return user;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void checkUser(String name, String email) throws  MyException{
        try{
            DBConnection con = new DBConnection();
            connection = con.getConnection();
            if(connection == null) throw new ConnectionInvalidException("Connection not Establised");
            Statement stmt = connection.createStatement();
            String query = "Select COUNT(*) from USER where binary email = '" + email +"'";
            System.out.println(query);
            ResultSet rs = stmt.executeQuery(query);
            rs.next();
            if(rs.getInt("COUNT(*)") == 1) throw new UserExistsException("User Exists");
            connection.close();

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addUser(User user, UserAuth userAuth) throws MyException, SQLException {
        DBConnection con = new DBConnection();
        connection = con.getConnection();
        if(connection == null) throw new ConnectionInvalidException("Connection not Establised");
        Statement stmt = connection.createStatement();
        String query = "insert into USER values("+user.getUser_id()+",'" + user.getName() + "','" + user.getCountry() + "','" + user.getEmail() + "','" + user.getDOB() + "','" + user.getGender() + "')";
        System.out.println(query);
        stmt.executeUpdate(query);
        query = "insert into USER_AUTH values(" + userAuth.getUser_id() + ",'" + userAuth.getPassword() + "')";
        System.out.println(query);
        stmt.executeUpdate(query);
        System.out.println("Successfull");
        connection.close();
    }

    public void addNewPlaylist(UserPlaylist userPlaylist) throws CannotAddPlaylsitException, ConnectionInvalidException {
        try {
            DBConnection con = new DBConnection();
            connection = con.getConnection();
            if (connection == null) throw new ConnectionInvalidException("Connection not Establised");
            Statement stmt = connection.createStatement();
            String query = "insert into USER_PLAYLISTS values(" + userPlaylist.getPlaylist_id() + "," + userPlaylist.getUser_id()  + " ,'" + userPlaylist.getName().toString() + "'," + userPlaylist.getSong_num() + ")";
            System.out.println(query);
            stmt.executeUpdate(query);
            System.out.println("Successfull");
            connection.close();
        }
        catch (SQLException e){
            e.printStackTrace();
            throw  new CannotAddPlaylsitException("Cannot add playlist");
        }
    }
}