package Ui.Search;

import Database.DataController;
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

import java.util.ArrayList;

public class SearchPageController {

    public static String SearchFilterString = "null";

    public VBox vbox;
    public Stage searchPage;
    public Scene scene;
    public Button goHomeButton;
    public Button searchButton;
    public AnchorPane ap;

    //All the data related to the search query is stored in this arraylist.
    public ArrayList<M> finalResult;
    public ArrayList<M> data;
    public ArrayList<M> temp;

//    public TextField queryText;


    public void loadWindow(){
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

                vbox = (VBox) scene.lookup("#vbox");
                vbox.getChildren().clear();
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

                for (int h=0;h<finalResult.size();h++){
                    if(h<21) {
                        System.out.println(finalResult.get(h).value);
                        Label l = new Label(finalResult.get(h).value);
                        l.setId(finalResult.get(h).key + h);
                        labelsList.add(l);
                    }

                }

                vbox.getChildren().addAll(labelsList);

                data.clear();
                finalResult.clear();
                labelsList.clear();
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


}
