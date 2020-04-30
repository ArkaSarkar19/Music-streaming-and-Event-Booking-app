package Ui.Search;

import Core.User;
import Database.DBConnection;
import Database.DataController;
import Ui.Player.PlayerController;
import Ui.SearchFilter.SearchFilterController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SearchPageController {

    public static String SearchFilterString = "null";

    public VBox vbox1;
    public VBox vbox2;
    public Stage searchPage;
    public Scene scene;
    public Button goHomeButton;
    public Button searchButton;
    public AnchorPane ap;

    //All the data related to the search query is stored in this arraylist.
    public ArrayList<M> finalResult;
    public ArrayList<M> data;
    public ArrayList<M> temp;

    ArrayList<Button> bl1;
    ArrayList<Button> bl2;

    public Button b1;
    public Button b2;

//    public TextField queryText;


    public void loadWindow(User user){
        try{
            SearchFilterString = "null";
            searchPage = new Stage();
            Parent profile = FXMLLoader.load(SearchPageController.class.getResource("SearchPage.fxml"));
            searchPage.initModality(Modality.APPLICATION_MODAL);
            scene = new Scene(profile);
            searchPage.setScene(scene);
            searchPage.setTitle("Browse");
            searchPage.setResizable(false);
            searchPage.show();

            goHomeButton = (Button)scene.lookup("#goHomeButton") ;
            goHomeButton.setOnAction(actionEvent -> {
                 searchPage.close();
            });

            searchButton = (Button)scene.lookup("#searchButton") ;
            searchButton.setOnAction(actionEvent -> {
                data = new ArrayList<M>();
                finalResult = new ArrayList<M>();
                String query = "";
                TextField queryText = (TextField)scene.lookup("#queryText");

                //query is stored
                query = queryText.getText();
                query = query.replaceAll("\\s", "");

                System.out.println(queryText.getText());
//                queryText.setText("");
                DataController dc = new DataController();
                String tableName = "";
                String columnName = "";

                vbox1 = (VBox) scene.lookup("#vbox1");
                vbox1.getChildren().clear();
                vbox1.setSpacing(10);

                vbox2 = (VBox) scene.lookup("#vbox2");
                vbox2.getChildren().clear();
                vbox2.setSpacing(10);

                ArrayList<Label> labelsList = new ArrayList<Label>();
                ArrayList<Button> buttonsList = new ArrayList<Button>();

                if(SearchFilterString.equals("null")){

                    try {
                        temp = dc.getSingleColumnFromTable("ALL_SONGS","title");
                        tempToData(temp,data);
                        temp = dc.getSingleColumnFromTable("ALL_ARTISTS","name");
                        tempToData(temp,data);
                        temp = dc.getSingleColumnFromTable("USER_PLAYLISTS","name");
                        tempToData(temp,data);

                        for (int i=0;i<data.size();i++){
                            M m = data.get(i);
                            if(isSubstring(query.toLowerCase(),m.value.replaceAll("\\s", "").toLowerCase())){
                                System.out.println(m.key + " " + m.value);
                                M mm = new M();
                                mm.key = m.key;
                                mm.value = m.value;
                                finalResult.add(mm);
                            }

                        }


                    } catch (Exception e) {
                        System.out.println("Error");
                    }

                }
                else if(SearchFilterString.equals("songs")){
                    try {
                        temp = dc.getSingleColumnFromTable("ALL_SONGS","title");
                        tempToData(temp,data);

                        for (int i=0;i<data.size();i++){
                            M m = data.get(i);
                            if(isSubstring(query.toLowerCase(),m.value.replaceAll("\\s", "").toLowerCase())){
                                System.out.println(m.key + " " + m.value);
                                M mm = new M();
                                mm.key = m.key;
                                mm.value = m.value;
                                finalResult.add(mm);
                            }

                        }

                    } catch (Exception e) {
                        System.out.println("Error");
                    }
                }

                else if(SearchFilterString.equals("artists")){
                    try {
                        temp = dc.getSingleColumnFromTable("ALL_ARTISTS","name");
                        tempToData(temp,data);

                        for (int i=0;i<data.size();i++){
                            M m = data.get(i);
                            if(isSubstring(query.toLowerCase(),m.value.replaceAll("\\s", "").toLowerCase())){
                                System.out.println(m.key + " " + m.value);
                                M mm = new M();
                                mm.key = m.key;
                                mm.value = m.value;
                                finalResult.add(mm);
                            }

                        }

                    } catch (Exception e) {
                        System.out.println("Error");
                    }
                }

                else if(SearchFilterString.equals("playlists")){
                    try {
                        temp = dc.getSingleColumnFromTable("USER_PLAYLISTS","name");
                        tempToData(temp,data);

                        for (int i=0;i<data.size();i++){
                            M m = data.get(i);
                            if(isSubstring(query.toLowerCase(),m.value.replaceAll("\\s", "").toLowerCase())){
                                System.out.println(m.key + " " + m.value);
                                M mm = new M();
                                mm.key = m.key;
                                mm.value = m.value;
                                finalResult.add(mm);
                            }

                        }

                    } catch (Exception e) {
                        System.out.println("Error");
                    }
                }

                bl1 = new ArrayList<Button>();
                bl2 = new ArrayList<Button>();

                for (int h=0;h<finalResult.size();h++){
                    if(h<10) {
                        System.out.println(finalResult.get(h).value);
                        Button b = new Button();
                        b.setText(finalResult.get(h).value);
//                        Label l = new Label(finalResult.get(h).value);
                        b.setId(finalResult.get(h).key + h);
                        bl1.add(b);

                    }
                    else if(h>=10 && h<20){
                        System.out.println(finalResult.get(h).value);
                        Button b = new Button(finalResult.get(h).value);
//                        Label l = new Label(finalResult.get(h).value);
                        b.setId(finalResult.get(h).key + h);
                        bl2.add(b);
                    }

                }

                buttonsFunctionality(bl1,bl2,user);

//                vbox.getChildren().addAll(labelsList);
                vbox1.getChildren().addAll(bl1);
                vbox2.getChildren().addAll(bl2);

                data.clear();
                finalResult.clear();
//                labelsList.clear();
                buttonsList.clear();
                bl1.clear();
                bl2.clear();
//                if(!vbox.getChildren().isEmpty()){
//                    vbox.getChildren().clear();
//                }

            });

        }
        catch (Exception e){
            System.out.println("Error while opening search window.");
            e.printStackTrace();
        }


    }

    public void buttonsFunctionality(ArrayList<Button> bl1,ArrayList<Button> bl2,User user){
        for (int i=0;i<bl1.size();i++){
            b1 = bl1.get(i);
            bl1.get(i).setOnAction(actionEvent -> {

                OnButtonClickController c = new OnButtonClickController();
                c.loadWindow(b1.getText(),user);

            });
        }
        for (int j=0;j<bl2.size();j++){
            b2 = bl2.get(j);
            bl2.get(j).setOnAction(actionEvent -> {

                OnButtonClickController c = new OnButtonClickController();
                c.loadWindow(b2.getText(),user);

            });
        }
    }



    public Boolean isSubstring(String s1, String s2)
    {
        int M = s1.length();
        int N = s2.length();

        /* A loop to slide pat[] one by one */
        for (int i = 0; i <= N - M; i++) {
            int j;

            /* For current index i, check for
            pattern match */
            for (j = 0; j < M; j++)
                if (s2.charAt(i + j) != s1.charAt(j))
                    break;

            if (j == M)
                return true;
        }

        return false;
    }

    public void tempToData(ArrayList<M> temp, ArrayList<M> data){
        for (int i=0;i<temp.size();i++){
            M m = temp.get(i);
            data.add(m);
        }
    }

    public void handlesFilterButton(){
        SearchFilterController searchFilterController = new SearchFilterController();
        SearchFilterString = searchFilterController.loadWindow();
//        SearchFilterString = "songs";
    }

    public void filter(String str){

    }

//    public void handlesSearchButton(){
//        TextField queryText = (TextField)scene.lookup("#queryText");
//        queryText.setText("");
//        System.out.println(queryText.getText());
//
//    }

  public void handleTopArtists(){
        vbox1.getChildren().clear();
        DBConnection db = new DBConnection();
        Connection connection = db.getConnection();
        try{
            Statement stmt = connection.createStatement();
            String query = "select name, (1 + (select count(*) from ALL_ARTISTS A where A.popularity>B.popularity)) as Ranking " +
                    "from ALL_ARTISTS B " +
                    "order by Ranking limit 10";
            System.out.println(query);
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                Button b = new Button();
                b.setText(rs.getInt("Ranking") + " " + rs.getString("name"));
                vbox1.getChildren().add(b);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void handleTopSongs(){
        vbox1.getChildren().clear();
        DBConnection db = new DBConnection();
        Connection connection = db.getConnection();
        try{
            Statement stmt = connection.createStatement();
            String query = "select title, name, (1 + (select count(*) from ALL_SONGS A where A.likes>B.likes)) as Ranking "+
            "from ALL_SONGS B, ALL_ARTISTS "+
            "where B.artist_id = ALL_ARTISTS.artist_id "+
            "order by Ranking limit 10";
            System.out.println(query);
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                Button b = new Button();
                b.setText(rs.getInt("Ranking") + " " + rs.getString("title"));
                vbox1.getChildren().add(b);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
