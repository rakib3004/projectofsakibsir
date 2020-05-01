package UserInterfacePackage;

import AHPalgorithm.AHPcalculation;
import AHPalgorithm.AHPprocessImplementation;
import MainPackage.BookNumber;
import MainPackage.Processing;
import ObjectOriented.AHPcriteriaWeight;
import ObjectOriented.PriorityData;
import RankingAlgorithmFx.StatisticsFX;
import TableViewPackage.PRA_TableViewFX;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class ChooseType extends Application {
    PriorityData[] priorityData;
    AHPcriteriaWeight ahPcriteriaWeight;

    int numberOfBooks;
    Processing processing = new Processing();
    BookNumber bookNumber = new BookNumber();

    AHPcalculation ahPcalculation = new AHPcalculation();
    AHPprocessImplementation ahPprocessImplementation = new AHPprocessImplementation();

    @Override
    public void start(Stage primaryStage) throws IOException {

        Button writerWise = new Button("Writer Wise");

        writerWise.setTranslateX(550);
        writerWise.setTranslateY(200);
        writerWise.setOnAction(actionEvent -> {

            try {

                WriterWiseFX writerWiseFX = new WriterWiseFX();
                writerWiseFX.start(primaryStage);
            }

            catch (Exception exception) {
                exception.printStackTrace();
            }

        });
        writerWise.setPrefSize(230, 80);
        setStyle(writerWise);


Button classWise = new Button("Class Wise");

        classWise.setTranslateX(550);
        classWise.setTranslateY(300);
        classWise.setOnAction(actionEvent -> {

            try {
                ClassWiseFX classWiseFX = new ClassWiseFX();
                classWiseFX.start(primaryStage);
            }

            catch (Exception exception) {
                exception.printStackTrace();
            }

        });
        classWise.setPrefSize(230, 80);
        setStyle(classWise);


Button selfWise = new Button("Self Wise");

        selfWise.setTranslateX(550);
        selfWise.setTranslateY(400);
        selfWise.setOnAction(actionEvent -> {

            try {

                SelfWiseFX selfWiseFX = new SelfWiseFX();
                selfWiseFX.start(primaryStage);
            }

            catch (Exception exception) {
                exception.printStackTrace();
            }

        });
        selfWise.setPrefSize(230, 80);
        setStyle(selfWise);


Button timeWise = new Button("Time Wise");

        timeWise.setTranslateX(550);
        timeWise.setTranslateY(500);
        timeWise.setOnAction(actionEvent -> {

            try {


                TimeWiseFX timeWiseFX = new TimeWiseFX();
                timeWiseFX.start(primaryStage);
            }

            catch (Exception exception) {
                exception.printStackTrace();
            }

        });
        timeWise.setPrefSize(230, 80);
        setStyle(timeWise);


Button addBook = new Button("Add Book");

        addBook.setTranslateX(550);
        addBook.setTranslateY(600);
        addBook.setOnAction(actionEvent -> {

            try {
               AddBookFX addBookFX = new AddBookFX();
               addBookFX.start(primaryStage);
            }

            catch (Exception exception) {
                exception.printStackTrace();
            }

        });
        addBook.setPrefSize(230, 80);
        setStyle(addBook);



        Button back = new Button("Back");
        Button exit = new Button("Exit");
        back.setOnAction(actionEvent -> {
            StatisticsFX statisticsFX = new StatisticsFX();

            try {
                statisticsFX.start(primaryStage);
            } catch (Exception exception) {
                exception.printStackTrace();
            }

        });

        exit.setOnAction(actionEvent -> {
            System.exit(0);


        });
        setStyle(exit);
        setStyle(back);

        back.setPrefSize(200, 80);
        exit.setPrefSize(200, 80);

        back.setTranslateX(0);
        back.setTranslateY(650);
        exit.setTranslateX(1100);
        exit.setTranslateY(650);



        Image image = new Image("libraryBackground23.jpg");
        Canvas canvas = new Canvas(1500,950);
        Group group = new Group();
        group.getChildren().addAll(canvas,addBook,exit,back,writerWise,
                classWise,selfWise,timeWise);

        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        graphicsContext.drawImage(image,0,0);

        Scene scene1 = new Scene(group,1500,950);


        primaryStage.setScene(scene1);
        primaryStage.setTitle("Books Statistics");
        primaryStage.setFullScreen(true);
        primaryStage.show();
    }
    public Button setStyle( Button button)
    {
        button.setStyle("-fx-padding: 8 15 15 15;\n" +
                "    -fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;\n" +
                "    -fx-background-radius: 8;\n" +
                "    -fx-background-color: \n" +
                "        linear-gradient(from 0% 93% to 0% 100%, #8d9092 0%, #717375 100%),\n" +
                "        #8d9092,\n" +
                "        #717375,\n" +
                "        radial-gradient(center 50% 50%, radius 100%, #ffffff, #a1a3a6);\n" +
                "    -fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );\n" +
                "    -fx-font-weight: bold;\n" +
                "    -fx-font-size: 2.1em;");
        return  button;
    }
}