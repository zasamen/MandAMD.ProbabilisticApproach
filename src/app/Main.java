package app;

import ui.MPAChartController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(MPAChartController.class.getResource("chart.fxml"));
        Parent root = loader.load();
        ((MPAChartController)loader.getController()).initialize();
        primaryStage.setTitle("Probabilistic approach");
        primaryStage.setScene(new Scene(root, 1200, 650));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
