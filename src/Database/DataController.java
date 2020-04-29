package Database;
import Core.*;
import Exception.*;
import Ui.BookEvents.BookEventController;
import Ui.MainPage.MainScreenController;
import Ui.Search.M;
import com.sun.glass.ui.EventLoop;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class DataController {
    public Connection connection;

    public void updateUserData(ArrayList<String> editedData, User user) throws MyException{
        try {
            DBConnection con = new DBConnection();
            connection = con.getConnection();
            if(connection == null) throw new ConnectionInvalidException("Connection not Establised");
            Statement stmt = connection.createStatement();



            String s1="";
            String s2="";
            String s3="";
            String s4="";

            for (int i=0;i<editedData.size();i++){
                if(i==0){
                    if(!editedData.get(i).equals("null")){
                        s1 = "UPDATE USER SET name = '" + editedData.get(i) + "' Where user_id = " + user.getUser_id();
                        stmt.executeUpdate(s1);
                        user.setName(editedData.get(i));
                    }
                }
                else if(i==1){
                    if(!editedData.get(i).equals("null")){
                        s2 = "UPDATE USER SET country = '" + editedData.get(i) + "' Where user_id = " + user.getUser_id() ;
                        stmt.executeUpdate(s2);
                        user.setCountry(editedData.get(i));
                    }
                }
                else if(i==2){
                    if(!editedData.get(i).equals("null")){
                        s3 = "UPDATE USER SET email = '" + editedData.get(i) + "' Where user_id = " + user.getUser_id() ;
                        stmt.executeUpdate(s3);
                        user.setEmail(editedData.get(i));
                    }
                }
                else if(i==4){
                    if(!editedData.get(i).equals("null")){
                        s4 = "UPDATE USER SET DOB = '" + editedData.get(i) + "' Where user_id = " + user.getUser_id() ;
                        stmt.executeUpdate(s4);
                        user.setDOB(editedData.get(i));
                    }
                }
                System.out.println(editedData.get(i));
            }

//
            System.out.println("SUCCESS");

        }catch (SQLException e){
            System.out.println("ERROR");
            e.printStackTrace();
        }
    }

    public ArrayList<M> getSingleColumnFromTable(String tableName, String columnName) throws MyException{

        ArrayList<M> data = null;


        try {
            data = new ArrayList<M>();
            DBConnection con = new DBConnection();
            connection = con.getConnection();
            if(connection == null) throw new ConnectionInvalidException("Connection not Establised");
            Statement stmt = connection.createStatement();
            List<String> allTables = getAllTables();
            if(!allTables.contains(tableName.toUpperCase())) throw new InvalidTableNameException(tableName + "is not a table in database");
            String query = "Select " + columnName +  " from " + tableName.toUpperCase();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()) {
                M m = new M();
                m.key=tableName;
                m.value=rs.getString(1);
                data.add(m);
            }


        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return data;
    }

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

    public ArrayList<UserPlaylist> getAllPlaylists(User user) throws ConnectionInvalidException, CannotAddPlaylsitException {
        if(user == null) return  null;
        try {
            ArrayList<UserPlaylist> list = new ArrayList<>();
            DBConnection con = new DBConnection();
            connection = con.getConnection();
            if (connection == null) throw new ConnectionInvalidException("Connection not Establised");
            Statement stmt = connection.createStatement();
            String query = "SELECT * FROM USER_PLAYLISTS where user_id = " + user.getUser_id();
            System.out.println(query);
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                int playlist_id = rs.getInt("playlist_id");
                int user_id  = rs.getInt("user_id");
                String name = rs.getString("name");
//                int song_num  = rs.getInt("song_num");
                UserPlaylist up = new UserPlaylist(user_id, playlist_id,name,0);
                list.add(up);
            }
            System.out.println("Successfull");
            connection.close();
            return list;
        }
        catch (SQLException e){
            e.printStackTrace();
            throw  new CannotAddPlaylsitException("Error getting all the playlists ");
        }
    }

    public Connection getConnection(){
        DBConnection db = new DBConnection();
        return db.getConnection();
    }

    public ArrayList<ArrayList<String>> ExecuteQuery(String query) throws MyException {
        ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();

        try {
            DBConnection con = new DBConnection();
            connection = con.getConnection();
            if (connection == null) throw new ConnectionInvalidException("Connection not Establised");
            Statement stmt = connection.createStatement();

            List<String> allTables = getAllTables();
            for (String table : allTables)
                System.out.println(table);

            ResultSet rs = stmt.executeQuery(query);
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();

            while (rs.next()) {
                ArrayList<String> map = new ArrayList<>();
                for (int i = 1; i <= columnsNumber; i++) {
                    map.add(rs.getString(i));
                }
                data.add(map);
            }
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    public void getTicket(User user, ArrayList<String>  list ) throws ConnectionInvalidException {
        System.out.println(" ------------------- Event Selected -----------------");
        for(int i=0;i<list.size();i++){
            System.out.print(list.get(i) + " ");
        }
        System.out.println();
        System.out.println("by user --- >"+ user.getUser_id() + ", " + user.getName());
        System.out.println();


        int live_perf_id = Integer.parseInt(list.get(7));
        int artist_id = Integer.parseInt(list.get(0));
        String artistName = list.get(1);
        String venue = list.get(12);
        String DateTime = list.get(13);
        double amount = 0;
        double wallet = 0;


        try{
            DBConnection db = new DBConnection();
            connection = db.getConnection();
            Statement stmt = connection.createStatement();
            String query = "select * from TICKETS where live_perf_id = " + live_perf_id;
            ResultSet rs = stmt.executeQuery(query);
            rs.next();
            amount = rs.getFloat("amount");
            query = "select * from USER_WALLET where user_id = " + MainScreenController.getUser().getUser_id();
            ResultSet rs2 = stmt.executeQuery(query);
            rs2.next();
            wallet = rs2.getFloat("amount");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println("Live Performance ID : "  + live_perf_id);
        System.out.println("Artist ID : " + artist_id);
        System.out.println("Artist Name : "  + artistName);
        System.out.println("Venue : " + venue);
        System.out.println("Date Time : " + DateTime);
        System.out.println("Amount " + amount);
        System.out.println("Wallet " + wallet);


        amount = (int)amount;
        BookEventController bec = new BookEventController();
        bec.setAmount(amount);
        bec.setArtistName(artistName);
        bec.setDateTime(DateTime);
        bec.setVenue(venue);
        bec.setWalletbalance(wallet);
        try {
            bec.loadWindow();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void makeTransaction(User user, double amount) throws ConnectionInvalidException {
        DBConnection db = new DBConnection();
        connection = db.getConnection();
        try{
            if (connection == null) throw new ConnectionInvalidException("Connection not Establised");

            Statement stmt  = connection.createStatement();
            String query = "select * from USER_WALLET where user_id = " + user.getUser_id();
//            String query = "update  USER_WALLET  set amount = amount - " + amount + " where user_id = " + user.getUser_id();
            ResultSet rs = stmt.executeQuery(query);
            double wallet = rs.getInt("amount");


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}