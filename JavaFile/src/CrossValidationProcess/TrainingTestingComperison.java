package CrossValidationProcess;

import AHPalgorithm.AHPcalculation;
import AHPalgorithm.AHPprocessImplementation;
import MainPackage.BookNumber;
import MainPackage.Processing;
import MultiVariableRegression.MultipleLinearRegression;
import ObjectOriented.AHPcriteriaWeight;
import ObjectOriented.PriorityData;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.input.ContextMenuEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class TrainingTestingComperison extends Application {



    PriorityData[] priorityData;
    AHPcriteriaWeight ahPcriteriaWeight;

    int numberOfBooks;
    int iterator;
    Processing processing = new Processing();
    BookNumber bookNumber = new BookNumber();
    MultipleLinearRegression multipleLinearRegression = new MultipleLinearRegression();

    AHPcalculation ahPcalculation = new AHPcalculation();
    AHPprocessImplementation ahPprocessImplementation = new AHPprocessImplementation();
    int positionIndicatorSet1 = 0;
    int positionIndicatorSet2 = 0;
    int positionIndicatorSet3 = 0;
    int positionIndicatorSet4 = 0;
    int minimumRange;

    double [] averageTrainingData = new double[200];
    double [] TrainingData1 = new double[200];
    double [] TrainingData2 = new double[200];
    double [] TrainingData3 = new double[200];
    double [] TrainingData4 = new double[200];

    @Override
    public void start(Stage primaryStage){
        Button back = new Button("Back");
        Button exit = new Button("Exit");


        back.setTranslateX(0);
        back.setTranslateY(650);
        exit.setTranslateX(1100);
        exit.setTranslateY(650);


        back.setOnAction(actionEvent -> {
            TestingSet testingSet = new TestingSet();

            try {
                testingSet.start(primaryStage);
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








        Button scatterChartComperison = new Button("ScatterChart Comperison");
        Button lineChartComperison = new Button("LineChart Comperison");
        Button stackedAreaChartComperison = new Button("StackedAreaChart Comperison");


        scatterChartComperison.setTranslateX(500);
        scatterChartComperison.setTranslateY(250);
        lineChartComperison.setTranslateX(500);
        stackedAreaChartComperison.setTranslateX(500);
        stackedAreaChartComperison.setTranslateY(350);
        lineChartComperison.setTranslateY(450);



        scatterChartComperison.setOnAction(actionEvent -> {
            TrainingTestingComperison trainingTestingComperison = new TrainingTestingComperison();
            try {
                trainingTestingComperison.startScatterChart(primaryStage);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });

        lineChartComperison.setOnAction(actionEvent -> {
            TrainingTestingComperison trainingTestingComperison = new TrainingTestingComperison();
            try {
                trainingTestingComperison.startLineChart(primaryStage);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });

        stackedAreaChartComperison.setOnAction(actionEvent -> {
            TrainingTestingComperison trainingTestingComperison = new TrainingTestingComperison();
            try {
                trainingTestingComperison.startStackedAreaChart(primaryStage);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });


        setStyle(scatterChartComperison);
        setStyle(lineChartComperison);
        setStyle(stackedAreaChartComperison);


        scatterChartComperison.setPrefSize(350, 80);
        lineChartComperison.setPrefSize(380, 80);
        stackedAreaChartComperison.setPrefSize(350, 80);


        Image image = new Image("libraryBackground9.jpg");
        Canvas canvas = new Canvas(1500,950);
        Group group = new Group();
        group.getChildren().addAll(canvas,exit,back,scatterChartComperison,
                lineChartComperison,stackedAreaChartComperison);

        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        graphicsContext.drawImage(image,0,0);


        Scene scene1 = new Scene(group,1500,950);


        primaryStage.setScene(scene1);
        primaryStage.setTitle("Books Statistics");
        primaryStage.setFullScreen(true);
        primaryStage.show();

    }




    public void startScatterChart(Stage primaryStage) throws IOException {
        Button back = new Button("Back");
        Button exit = new Button("Exit");


        back.setTranslateX(0);
        back.setTranslateY(650);
        exit.setTranslateX(1100);
        exit.setTranslateY(650);


        back.setOnAction(actionEvent -> {
            TrainingTestingComperison trainingTestingComperison = new TrainingTestingComperison();
            try {
                trainingTestingComperison.start(primaryStage);
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


        priorityData = processing.fileReaderMethods();
        numberOfBooks = bookNumber.bookNumberFindingMethods();
        priorityData = multipleLinearRegression.multipleLinearRegressionMethods(priorityData, numberOfBooks);

        CategoryAxis categoryAxis = new CategoryAxis();
        categoryAxis.setLabel("Book Class Category");

        NumberAxis numberAxis = new NumberAxis();
        numberAxis.setLabel("Numbers of Book");


        ScatterChart ScatterChart = new ScatterChart(categoryAxis, numberAxis);

        XYChart.Series series1 = new XYChart.Series();
        XYChart.Series series2 = new XYChart.Series();


        series1.setName("Training Set 1 ");
        series2.setName("Training Set 2 ");


        //   series1.setName("Training Set 1 ");
        for (iterator = 0; iterator < numberOfBooks; iterator++) {
            if (priorityData[iterator].bookData.bookId.substring(13, 14).contains("1") ||
                    priorityData[iterator].bookData.bookId.substring(13, 14).contains("6")) {
                TrainingData1[positionIndicatorSet1] = priorityData[iterator].getMLRweight();
                positionIndicatorSet1++;
            }
        }

        // series2.setName("Training Set 2 ");
        for (iterator = 0; iterator < numberOfBooks; iterator++) {
            if (priorityData[iterator].bookData.bookId.substring(13, 14).contains("2") ||
                    priorityData[iterator].bookData.bookId.substring(13, 14).contains("7")) {
                TrainingData2[positionIndicatorSet2] = priorityData[iterator].getMLRweight();
                positionIndicatorSet2++;

            }
        }
        //  series3.setName("Training Set 3 ");
        for (iterator = 0; iterator < numberOfBooks; iterator++) {
            if (priorityData[iterator].bookData.bookId.substring(13, 14).contains("3") ||
                    priorityData[iterator].bookData.bookId.substring(13, 14).contains("8")) {
                TrainingData3[positionIndicatorSet3] = priorityData[iterator].getMLRweight();
                positionIndicatorSet3++;

            }
        }
        //   series4.setName("Training Set 4 ");
        for (iterator = 0; iterator < numberOfBooks; iterator++) {
            if (priorityData[iterator].bookData.bookId.substring(13, 14).contains("4") ||
                    priorityData[iterator].bookData.bookId.substring(13, 14).contains("9")) {
                TrainingData4[positionIndicatorSet4] = priorityData[iterator].getMLRweight();
                positionIndicatorSet4++;

            }
        }
minimumRange = Math.min(positionIndicatorSet1,positionIndicatorSet2);
        minimumRange = Math.min(minimumRange,positionIndicatorSet3);
        minimumRange = Math.min(minimumRange,positionIndicatorSet4);

        for(iterator=0;iterator<minimumRange;iterator++){

            averageTrainingData[iterator]  = .25 * (TrainingData1[iterator]+TrainingData2[iterator]+
                    TrainingData3[iterator]+TrainingData4[iterator]);

            series1.getData().add(new XYChart.Data(String.valueOf(iterator),averageTrainingData[iterator]));

        }

        int positionIndicatorSetPro=0;
        for (iterator = 0; iterator < numberOfBooks; iterator++) {
            if (priorityData[iterator].bookData.bookId.substring(13, 14).contains("5") ||
                    priorityData[iterator].bookData.bookId.substring(13, 14).contains("0")) {
                positionIndicatorSetPro++;

                series2.getData().add(new XYChart.Data(String.valueOf(positionIndicatorSetPro), priorityData[iterator].getMLRweight()));
            }
        }


        ScatterChart.getData().add(series1);
        ScatterChart.getData().add(series2);


        ScatterChart.setTranslateX(10);
        ScatterChart.setTranslateY(25);
        ScatterChart.setPrefSize(1350, 800);

        ContextMenu contextMenu = new ContextMenu();
        MenuItem scatterChartView = new MenuItem("ScatterChart View");
        MenuItem lineChartView = new MenuItem("LineChart View");
        MenuItem stackedAreaView = new MenuItem("StackedArea View");



        scatterChartView.setOnAction((event) -> {
            TrainingTestingComperison trainingTestingComperison = new TrainingTestingComperison();
            try {
                trainingTestingComperison.startScatterChart(primaryStage);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });

        lineChartView.setOnAction((event) -> {
            TrainingTestingComperison trainingTestingComperison = new TrainingTestingComperison();
            try {
                trainingTestingComperison.startLineChart(primaryStage);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
        stackedAreaView.setOnAction((event) -> {
            TrainingTestingComperison trainingTestingComperison = new TrainingTestingComperison();
            try {
                trainingTestingComperison.startStackedAreaChart(primaryStage);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });


        contextMenu.getItems().addAll(scatterChartView, lineChartView, stackedAreaView);

        ScatterChart.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {

            @Override
            public void handle(ContextMenuEvent event) {

                contextMenu.show(ScatterChart, event.getScreenX(), event.getScreenY());
            }
        });



        //  Image image = new Image("libraryBackground9.jpg");
        Canvas canvas = new Canvas(1500, 950);
        Group group = new Group();
        group.getChildren().addAll(canvas, ScatterChart, exit, back);

        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        // graphicsContext.drawImage(image,0,0);


        Scene scene1 = new Scene(group, 1500, 950);


        primaryStage.setScene(scene1);
        primaryStage.setTitle("Books Statistics");
        primaryStage.setFullScreen(true);
        primaryStage.show();

    }



    public void startLineChart(Stage primaryStage) throws Exception{

    }


    public void startStackedAreaChart(Stage primaryStage) throws Exception{

    }
    public Button setStyle(Button button)
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
