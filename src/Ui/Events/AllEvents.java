package Ui.Events;

import Ui.MainPage.MainScreenController;
import Ui.SignupLoginMain.LoginBox;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import  javafx.scene.text.*;
import javafx.scene.layout.CornerRadii;
import javafx.geometry.Insets;
import javafx.scene.shape.Circle;
import javafx.scene.control.Button;
import Exception.MyException;

import java.io.IOException;
import java.sql.SQLException;
//import MainPage.MainScreenController;

public class AllEvents {
    /*
        mainEventStage ==> Page consisting of Main PlayList and Places near your Playlist

    */
   public static Stage mainEventStage = new Stage();

    public void ALLEventsInterface(){

/////////////////////////////////////////////////////////////////////UI WALA PART/////////////////////////////////////////////////////////////////////////////////////////
        ///////////Main continer/////////////////
        AnchorPane root = new AnchorPane();
        root.setMaxHeight(720);
        root.setMaxWidth(1280);
        ////////////////////////////////////////


        /////////////////////////////////////////
        int MainPainX = 230;
        int TopMargin = 120;
        /////////////////////////////////////////


        ////////////////////EVENTS/////////////////////////////////////////////
        Text EVI = new Text("EVENTS");
        EVI.setFill(Color.WHITE);
        EVI.setFont(Font.font("ConcertResources/SERIO___.TTF", 30));
        EVI.setX(190+80);
        EVI.setY(130);
        ///////////////////////////////////////////////////////////////////////

        //////////////////Logo///////////////////////////////////////////////////////////
        ImageView Logo = new ImageView(new Image("ConcertResources/logo.png"));
        Logo.setX(20);
        Logo.setY(30);
        Logo.setFitHeight(TopMargin);
        Logo.setFitWidth(230);
        /////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////Main Central PlayList//////////////////////////////
        ListView<Rectangle> listViewCen = new ListView<>();
        ObservableList<Rectangle> items = FXCollections.observableArrayList();
        //Adding images to the ListView
        for( int i = 0; i<=8 ;i+=1 ){
            String s = String.format("ConcertResources/concert%d.jpg",i);
            ImagePattern Imp = new ImagePattern(new Image(s));
            Rectangle I = new Rectangle();
            I.setFill(Imp);
            I.setArcWidth(70);
            I.setArcHeight(70);
            I.setY(120);
            if( i != 0 )
                I.setX(MainPainX);
            else
                I.setX(MainPainX+i*910);
            I.setHeight(320);
            I.setWidth(500);
            items.add(I);
        }
        listViewCen.setItems(items);
        listViewCen.setPrefSize(1180,400);
        listViewCen.setMaxWidth(1180);
        listViewCen.setMaxHeight(400);
        listViewCen.setOrientation(Orientation.HORIZONTAL);
        listViewCen.setLayoutX(MainPainX);
        listViewCen.setLayoutY(TopMargin);
        ////////////////////////////////////////////////////////////////////////////////////////////


        /////////////////////////Bottom Small PlayList//////////////////////////////////////////////////////////////////
        ListView<Rectangle> listViewNear = new ListView<>();
        ObservableList<Rectangle> itemsL = FXCollections.observableArrayList();//new ImageView(new Image("ConcertResources/concert0.jpg")), new ImageView(new Image("ConcertResources/concert0.jpg")), new ImageView(new Image("ConcertResources/concert0.jpg")), new ImageView(new Image("ConcertResources/concert0.jpg")) );

        //Adding images to the ListView
        for( int i = 0; i<=25 ;i+=1 ){
            String s = String.format("ConcertResources/FindFest/PLlist%d.jpg",i);
            if( i == 12 || i == 13 )
                s = String.format("ConcertResources/FindFest/PLlist%d.png",i);
            //System.out.println(s);
            ImagePattern ImP = new ImagePattern(new Image(s));
            Rectangle I = new Rectangle();
            I.setFill(ImP);
            I.setY(570);
            I.setArcWidth(15);
            I.setArcHeight(15);

            if( i != 0 )
                I.setX(MainPainX);
            else
                I.setX(MainPainX+i*205);
            I.setHeight(120);
            I.setWidth(200);
            itemsL.add(I);
        }

        listViewNear.setItems(itemsL);
        listViewNear.setPrefSize(1180,150);
        listViewNear.setMaxWidth(1180);;
        listViewNear.setMaxHeight(150);
        listViewNear.setOrientation(Orientation.HORIZONTAL);
        listViewNear.setLayoutX(MainPainX);
        listViewNear.setLayoutY(570);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ///////////////////////Find Places near you Text Widget/////////////////////////////////////////////////////////
        Text FindPlaces = new Text("Find Places near You...");
        FindPlaces.setFill(Color.WHITE);
        FindPlaces.setX(MainPainX+150);
        FindPlaces.setY(550);
        FindPlaces.setScaleX(3);
        FindPlaces.setScaleY(2);
        FindPlaces.setFont(Font.font("Verdana", 12));
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        /////////////////////Left Side(HOME, BOOK EVENT)///////////////////////////////////////
        Button Home =  new Button(" HOME ");
        Home.setLayoutX(60);
        Home.setLayoutY(170);
        Home.setPrefHeight(39);
        Home.setMinWidth(153);



        Button BookEvents =  new Button("BOOK EVENT");
        BookEvents.setLayoutX(60);
        BookEvents.setLayoutY(170+51.2);
        BookEvents.setPrefHeight(39);
        BookEvents.setMinWidth(153);
        ///////////////////////////////////////////////////////////////////////////////////////

        /////////////////////////Arrow For Main Central PlayList/////////////////////////
        Button LeftArrow = new Button("< ");
        LeftArrow.setShape(new Circle(1.5));
        LeftArrow.setLayoutX(MainPainX);
        LeftArrow.setLayoutY(100+listViewCen.getMaxHeight()/2);

        Button RightArrow = new Button(" >");
        RightArrow.setShape(new Circle(1.5));
        RightArrow.setLayoutX(1180+70);
        RightArrow.setLayoutY(100+listViewCen.getMaxHeight()/2);
        ////////////////////////////////////////////////////////////////////////////////

        /////////////////////////Arrow For Near you PlayList//////////////////////////////
        Button LeftArrowNP = new Button("< ");
        LeftArrowNP.setShape(new Circle(1.5));
        LeftArrowNP.setLayoutX(MainPainX);
        LeftArrowNP.setLayoutY(550+listViewNear.getMaxHeight()/2);

        Button RightArrowNP = new Button(" >");
        RightArrowNP.setShape(new Circle(1.5));
        RightArrowNP.setLayoutX(1180+70);
        RightArrowNP.setLayoutY(550+listViewNear.getMaxHeight()/2);
        //////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////UI WALA PART ENDS/////////////////////////////////////////////////////////////////////////////////////////


        ///////////////////////////Event Listeners(All Arrow Navigation Listeners)///////////////////////////////////////////
        int startCent = 0;
        int[] CurrentCent = {0};
        int EndCent = 8;
        int startNear = 0;
        int[] CurrentNear = {0};
        int EndNear = 25;


        LeftArrow.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //listViewCen.getEditingIndex();
                if( CurrentCent[0] != startCent ){
                    CurrentCent[0] -= 1;
                    listViewCen.scrollTo(CurrentCent[0]);
                }
            }});

        RightArrow.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //listViewCen.getEditingIndex();
                if( CurrentCent[0] != EndCent ){
                    CurrentCent[0] += 1;
                    listViewCen.scrollTo(CurrentCent[0]);
                }
            }});

        LeftArrowNP.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //listViewCen.getEditingIndex();
                if( CurrentNear[0] != 0 ){
                    CurrentNear[0] -= 1;
                    listViewNear.scrollTo(CurrentNear[0]);
                }
            }});

        RightArrowNP.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //listViewCen.getEditingIndex();
                if( CurrentNear[0] != EndNear ){
                    CurrentNear[0] += 1;
                    listViewNear.scrollTo(CurrentNear[0]);
                }
            }});

        /////////////////////////////////////////////////////////////////////////////////////

        ///////////////////////////////EventListener(Home And BookEvent)///////////////////////////////////////////
        Home.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mainEventStage.close();
            }});

        BookEvents.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               SelectShow s = new SelectShow();
                try {
                    s.Interface();
                } catch (MyException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }});

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////

/////////////////////////////////////////////////////////////////////UI WALA PART/////////////////////////////////////////////////////////////////////////////////////////

        root.getStylesheets().add(getClass().getResource("Event.css").toExternalForm());

        /////////////////////////////Redesigning Arrow Keys///////////////////////////////////////
        LeftArrow.setStyle("-fx-background-color: #FFFFFF;");//White Background After CSS Styling
        RightArrow.setStyle("-fx-background-color: #FFFFFF;");
        LeftArrowNP.setStyle("-fx-background-color: #FFFFFF;");
        RightArrowNP.setStyle("-fx-background-color: #FFFFFF;");
        //////////////////////////////////////////////////////////////////////////////////////////

        root.setStyle("-fx-background-image: url('Resources/bg.jpg');  -fx-graphic-size:1280px 720px ;");
        root.getChildren().addAll(Home,BookEvents,Logo,listViewCen, listViewNear,FindPlaces,LeftArrow,RightArrow,LeftArrowNP,RightArrowNP,EVI);
        root.setBackground(new Background(new BackgroundFill(Color.color(12/255,12/255,12/255,1), CornerRadii.EMPTY, Insets.EMPTY)));
        Scene scene = new Scene(root, 1280, 720);

        mainEventStage.setTitle("BAJAO");
        mainEventStage.setScene(scene);
        mainEventStage.show();

/////////////////////////////////////////////////////////////////////UI WALA PART ENDS/////////////////////////////////////////////////////////////////////////////////////////

    }


}
