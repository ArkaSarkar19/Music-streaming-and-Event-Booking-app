package Ui.Events;

import Database.DataController;
import Exception.MyException;
import Ui.MainPage.MainScreenController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import Exception.*;

//////////////////////////////It is just for test///////////////////////////////////////////


public class SelectShow {

    //////////////////////////Respective Column name to extract the Relevant Index's Features//////////////////////////////////////////
    String[] ColumnNameAndIndex = {"artist_id" ,"Artist_name", "song_num", "popularity", "perf_data", "sponsors" ,"ratings", "live_perf_id", "address", "name", "email", "contact", "venue", "date_time"};
    Stage window = new Stage();
   public static Button bookevent  ;
    ;
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private StackPane function(ArrayList<String> strings, int horizontalGap, int verticalGap, int Index ) {
        StackPane AnP = new StackPane();
        AnP.setLayoutX(horizontalGap+500);
        AnP.setLayoutY(verticalGap);
        AnP.setMaxHeight(300);
        AnP.setMaxWidth(900);

        int Ind = Index;
        Index %= 26;

        String S = String.format("ConcertResources/imagi/imag%d.jpg",Index);
        ImagePattern ImP = new ImagePattern(new Image(S));
        Rectangle I = new Rectangle();
        I.setFill(ImP);
        I.setX(horizontalGap+500+10);
        I.setY(verticalGap+10);
        I.setWidth(200);
        I.setHeight(250);
        I.setArcHeight(10);
        I.setArcWidth(10);


        VBox v = new VBox();
        v.setLayoutX(horizontalGap+500+600);
        v.setLayoutY(verticalGap);
        v.setMinWidth(500);
        v.setMaxWidth(500);


        Text t = new Text("Event"+" : "+ (Ind+1));
        t.setFill(Color.WHITE);
        t.setFont(Font.font("Verdana", FontWeight.BOLD,20));
        v.getChildren().add(t);

        int count = 0;
        for( String s : strings ){
            t = new Text(ColumnNameAndIndex[count]+" : "+s);
            t.setFill(Color.WHITE);
            t.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 12));
            v.getChildren().add(t);
            count += 1;
        }
        HBox h = new HBox(4);
        AnP.opacityProperty();
        h.getChildren().addAll(I,v);
        AnP.getChildren().addAll(h);
        AnP.setStyle("-fx-background-image: url('Resources/bg.jpg'); -fx-graphic-size:300px 400px ;");
        return AnP;
    }


    public void Interface() throws MyException, SQLException {
        window.setTitle("BAJAO");
        AnchorPane root = new AnchorPane();
        StackPane[] AnP = {new StackPane()};

///////////////////////////////////////////////////////////!!!  !!!////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////Extracting Important Data for Events(Containing Artist_Names,Event name, Date of event for sorting)///////////////////////////////////////////
        DataController db = new DataController();
        ArrayList<ArrayList<String>>[] Live_PERFROM_DATA_TIME_VENUE = new ArrayList[]{db.ExecuteQuery("SELECT DISTINCT A.artist_id,A.name,A.song_num,A.popularity,P.perf_data, P.sponsors, P.ratings, P.live_perf_id, AD.address, AD.name, AD.email, AD.contact, S.venue, S.date_time FROM ALL_ARTISTS A, ADVERTISERS AD, LIVE_PERFORMANCES P,SCHEDULE S WHERE A.artist_id = P.artist_id AND AD.advertiser_id = P.advertiser_id AND S.live_perf_id = P.live_perf_id")};
        boolean[] flag = {false};

        ArrayList<ArrayList<String>> FilteredList = new ArrayList<ArrayList<String>>();
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


        /////////////////Extracting All Places where Events would take place(Lexo-Sorted)//////////
        ArrayList<String> AllPlaces = new ArrayList<>();

        int AllPlaces_index =0;
        for( int i=0 ; i<ColumnNameAndIndex.length ; i+=1 )
            if( ColumnNameAndIndex[i].equals("venue") )
                AllPlaces_index = i;

        for( int i=0 ; i<Live_PERFROM_DATA_TIME_VENUE[0].size() ; i+=1 )
            if(  !AllPlaces.contains(Live_PERFROM_DATA_TIME_VENUE[0].get(i).get(AllPlaces_index)) )
                AllPlaces.add(Live_PERFROM_DATA_TIME_VENUE[0].get(i).get(AllPlaces_index));

        Collections.sort(AllPlaces);

        //////////////////////////////////////////////////////////////////////////////////////////

        ///////////////////////Extracting All Artist Names(Lexo-Sorted)///////////////////////////////
        ArrayList<String> AllArtists = new ArrayList<>();

        int Artist_index =0;
        for( int i=0 ; i<ColumnNameAndIndex.length ; i+=1 )
            if( ColumnNameAndIndex[i].equals("Artist_name") )
                Artist_index = i;

        for( int i=0 ; i<Live_PERFROM_DATA_TIME_VENUE[0].size() ; i+=1 )
            if(  !AllArtists.contains(Live_PERFROM_DATA_TIME_VENUE[0].get(i).get(Artist_index)) )
                AllArtists.add(Live_PERFROM_DATA_TIME_VENUE[0].get(i).get(Artist_index));

        Collections.sort(AllArtists);

        ////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////Extracting Dates of Event(Lexo-Sorted)///////////////////////////////
        ArrayList<String> AllDates = new ArrayList<>();

        int AllDates_index = 0;
        for( int i=0 ; i<ColumnNameAndIndex.length ; i+=1 )
            if( ColumnNameAndIndex[i].equals("date_time") )
                AllDates_index = i;

        for( int i=0 ; i<Live_PERFROM_DATA_TIME_VENUE[0].size() ; i+=1 )
            if( !AllDates.contains(Live_PERFROM_DATA_TIME_VENUE[0].get(i).get(AllDates_index)) )
                AllDates.add(Live_PERFROM_DATA_TIME_VENUE[0].get(i).get(AllDates_index));

        Collections.sort(AllDates);

        /////////////////////////////////////////////////////////////////////////////////////////

/////////////////////////////////////////////////////////!!!!!!!!!!!!!!!!!!!!!!!!!/////////////////////////////////////////////////////////////////////////////////////////////


        /////////////////////////////////////LEFT MARGIN/////////////////////////////////////////////////////////////
        int HorizontalGap = 190;
        int VerticalGap = 60;
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ///////////////////////////////////////List View/////////////////////////////////////////////////////////////
        ListView<Text> listViewWithPlaces = new ListView<>();
        ObservableList<Text> items = FXCollections.observableArrayList();

        /////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////MenuButton for Places////////////////////////////////////////////
        MenuButton PlacesMenuButton = new MenuButton("Places");
        PlacesMenuButton.setLayoutX(HorizontalGap);
        PlacesMenuButton.setLayoutY(VerticalGap);

        for( int i = 0; i < AllPlaces.size(); i+=1 ){
            CheckMenuItem Places = new CheckMenuItem(AllPlaces.get(i));
            Places.setOnAction(e -> {
                if ( ((CheckMenuItem)e.getSource()).isSelected() ) {
                    FilteredList.clear();

                    for (int i12 = 0; i12 < Live_PERFROM_DATA_TIME_VENUE[0].size(); i12 += 1)
                        if (Live_PERFROM_DATA_TIME_VENUE[0].get(i12).get(12).equals(((CheckMenuItem) e.getSource()).getText())) {
                            FilteredList.add(Live_PERFROM_DATA_TIME_VENUE[0].get(i12));
                        }

                    flag[0] = true;

                    if( flag[0] == true) {
                        int Event = 1;
                        items.clear();
                        for (int k = 0; k < FilteredList.size(); k += 1) {
                            Text t = new Text("Event " + Event);
                            t.setFill(Color.WHITE);
                            t.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 12));
                            items.add(t);
                            Event += 1;
                        }

                        listViewWithPlaces.setItems(items);
                        listViewWithPlaces.setMaxWidth(200);
                        listViewWithPlaces.setMaxHeight(380);
                        listViewWithPlaces.setLayoutX(HorizontalGap);
                        listViewWithPlaces.setLayoutY(VerticalGap+30);

                        if( root.getChildren().contains(listViewWithPlaces) ){
                            root.getChildren().remove(listViewWithPlaces);
                        }
                        root.getChildren().add(listViewWithPlaces);

                        listViewWithPlaces.setOnMouseClicked(new EventHandler<MouseEvent>() {

                            @Override
                            public void handle(MouseEvent event) {

                                AnP[0] = function( FilteredList.get(listViewWithPlaces.getSelectionModel().getSelectedIndex()),HorizontalGap,VerticalGap ,listViewWithPlaces.getSelectionModel().getSelectedIndex()) ;

                                if( root.getChildren().contains(AnP[0]) ){
                                    root.getChildren().remove(AnP[0]);
                                }
                                root.getChildren().add(AnP[0]);
                            }
                        });
                    }
                }
            });
            PlacesMenuButton.getItems().add(Places);
        }
        /////////////////////////////////////////////////////////////////////////////////////////////////////


        ////////////////////////////////////MenuButton for Artists////////////////////////////////////////////
        MenuButton AllArtistMenuButton = new MenuButton("Artists");
        AllArtistMenuButton.setLayoutX(HorizontalGap+100);
        AllArtistMenuButton.setLayoutY(VerticalGap);

        for( int i = 0; i < AllArtists.size(); i+=1 ){
            CheckMenuItem Artists = new CheckMenuItem(AllArtists.get(i));
            int finalArtist_index = Artist_index;
            Artists.setOnAction(e -> {
                if ( ((CheckMenuItem)e.getSource()).isSelected() ) {
                    FilteredList.clear();
                    for (int i1 = 0; i1 < Live_PERFROM_DATA_TIME_VENUE[0].size(); i1 += 1)
                        if ( Live_PERFROM_DATA_TIME_VENUE[0].get(i1).get(finalArtist_index).equals( ((CheckMenuItem) e.getSource()).getText()) ) {
                            FilteredList.add(Live_PERFROM_DATA_TIME_VENUE[0].get(i1));
                        }
                    flag[0] = true;

                    if( flag[0] == true) {
                        items.clear();
                        listViewWithPlaces.getItems().clear();
                        int Event = 1;
                        for (int k = 0;  k < FilteredList.size(); k += 1) {
                            Text t = new Text("Event " + Event);
                            t.setFill(Color.WHITE);
                            t.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 12));
                            items.add(t);
                            Event += 1;
                        }

                        listViewWithPlaces.setItems(items);
                        listViewWithPlaces.setMaxWidth(200);
                        listViewWithPlaces.setMaxHeight(400);
                        listViewWithPlaces.setLayoutX(HorizontalGap);
                        listViewWithPlaces.setLayoutY(VerticalGap+30);

                        if( root.getChildren().contains(listViewWithPlaces) ){
                            root.getChildren().remove(listViewWithPlaces);
                        }
                        root.getChildren().add(listViewWithPlaces);

                        listViewWithPlaces.setOnMouseClicked(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {

                                AnP[0] = function( FilteredList.get(listViewWithPlaces.getSelectionModel().getSelectedIndex()),HorizontalGap,VerticalGap ,listViewWithPlaces.getSelectionModel().getSelectedIndex()) ;

                                if( root.getChildren().contains(AnP[0]) ){
                                    root.getChildren().remove(AnP[0]);
                                }
                                root.getChildren().add(AnP[0]);
                            }
                        });
                    }
                }
            });
            AllArtistMenuButton.getItems().add(Artists);
        }
        /////////////////////////////////////////////////////////////////////////////////////////////////////


        ////////////////////////////////////TextField for Date(From to To)////////////////////////////////////////////
        Label From = new Label("FROM");
        Label To = new Label("TO");
        From.setLayoutX(HorizontalGap+200);
        From.setLayoutY(VerticalGap-30);
        To.setLayoutX(HorizontalGap+300);
        To.setLayoutY(VerticalGap-30);

        TextField FromDate = new TextField("YYYY-MM-DD");
        TextField ToDate = new TextField("YYYY-MM-DD");

        FromDate.setLayoutX(HorizontalGap+200);
        FromDate.setLayoutY(VerticalGap);
        ToDate.setLayoutX(HorizontalGap+300);
        ToDate.setLayoutY(VerticalGap);

        Button Date = new Button("Lock Date");
        Date.setLayoutX(HorizontalGap+300);
        Date.setLayoutY(VerticalGap+30);
        Date.setMinWidth(170);
        Date.setMinHeight(39.5);

        bookevent = new Button("Book Event");
        bookevent.setLayoutX(HorizontalGap+300);
        bookevent.setLayoutY(VerticalGap+80);
        bookevent.setMinWidth(170);
        bookevent.setMinHeight(39.5);

        TextField eventName = new TextField();
        eventName.setPromptText("Event Name");
        eventName.opacityProperty().setValue(.5);
        eventName.setLayoutX(HorizontalGap+300);
        eventName.setLayoutY(VerticalGap+160);
        eventName.setMinWidth(170);
        eventName.setMinHeight(39.5);

        bookevent.setOnAction(actionEvent -> {
            for(int i=0;i<FilteredList.size();i++){
                for(int j=0;j<FilteredList.get(i).size();j++){
                    System.out.print(FilteredList.get(i).get(j) + " ");
                }
                System.out.println();

            }
            System.out.println(eventName.getText());
            int index = Integer.parseInt(eventName.getText()) - 1 ;
            DataController db2 = new DataController();
            try {
                db2.getTicket(MainScreenController.getUser(),FilteredList.get(index));
            } catch (ConnectionInvalidException e) {
                e.printStackTrace();
            }
            window.close();
            AllEvents.mainEventStage.close();
        });


        int finalAllDates_index = AllDates_index;
        Date.setOnAction(e -> {
            if ( !From.getText().isEmpty() && !ToDate.getText().isEmpty() ) {
                FilteredList.clear();
                for (int i = 0; i < Live_PERFROM_DATA_TIME_VENUE[0].size(); i += 1) {
                       if (Live_PERFROM_DATA_TIME_VENUE[0].get(i).get(finalAllDates_index).compareTo(FromDate.getText()) >= 0 && Live_PERFROM_DATA_TIME_VENUE[0].get(i).get(finalAllDates_index).compareTo(ToDate.getText()) <= 0) {
                        FilteredList.add(Live_PERFROM_DATA_TIME_VENUE[0].get(i));
                    }
                }

                flag[0] = true;

                if( flag[0] == true) {
                    items.clear();
                    listViewWithPlaces.getItems().clear();
                    int Event = 1;
                    for (int k = 0;  k < FilteredList.size(); k += 1) {
                        Text t = new Text("Event " + Event);
                        t.setFill(Color.WHITE);
                        t.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 12));
                        items.add(t);
                        Event += 1;
                    }

                    listViewWithPlaces.setItems(items);
                    listViewWithPlaces.setMaxWidth(200);
                    listViewWithPlaces.setMaxHeight(400);
                    listViewWithPlaces.setLayoutX(HorizontalGap);
                    listViewWithPlaces.setLayoutY(VerticalGap+30);

                    if( root.getChildren().contains(listViewWithPlaces) ){
                        root.getChildren().remove(listViewWithPlaces);
                    }
                    root.getChildren().add(listViewWithPlaces);

                    listViewWithPlaces.setOnMouseClicked(new EventHandler<MouseEvent>() {

                        @Override
                        public void handle(MouseEvent event) {

                            AnP[0] = function( FilteredList.get(listViewWithPlaces.getSelectionModel().getSelectedIndex()),HorizontalGap,VerticalGap ,listViewWithPlaces.getSelectionModel().getSelectedIndex()) ;

                            if( root.getChildren().contains(AnP[0]) ){
                                root.getChildren().remove(AnP[0]);
                            }
                            root.getChildren().add(AnP[0]);
                        }
                    });
                }
            }
        });
        /////////////////////////////////////////////////////////////////////////////////////////////////////


        /////////////////////////////////Showing the Screen with Places////////////////////////
        if( flag[0] == false ) {
            root.setStyle("-fx-background-image: url('Resources/bg.jpg'); -fx-graphic-size:1280px 720px ;");
            root.getChildren().addAll(AllArtistMenuButton,PlacesMenuButton, From, To, FromDate ,ToDate ,Date, bookevent,eventName);
        }
        ///////////////////////////////////////////////////////////////////////////////////////
        root.getStylesheets().add(getClass().getResource("SelectShow.css").toExternalForm());

        Scene s = new Scene(root,1280,720);

        window.setScene(s);
        window.setResizable(false);
        window.show();
    }
}
