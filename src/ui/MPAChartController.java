package ui;

import drawTool.Executor;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

public class MPAChartController {

    @FXML
    Label fakeAlarmLabel;
    @FXML
    Label pc1Label;
    @FXML
    Label pc2Label;
    @FXML
    Label passDetectionLabel;
    @FXML
    Label classificationErrorLabel;
    @FXML
    Slider probabilitySlider;
    @FXML
    Button buttonStart;
    @FXML
    LineChart<Number,Number> lineChart;

    private Executor executor;
    private double pc1;
    private double pc2;

    public void initialize() {
        probabilitySlider.valueProperty().addListener(
                (o, old, newValue) -> observe(newValue));
        pc1=0;
        pc2=1;
        executor = new Executor(lineChart);
    }

    private void observe(Number newValue) {
        pc1 = Math.round(newValue.doubleValue() * 100.0) / 100.0;
        pc2 = 1 - pc1;
        pc1Label.setText(formatProbability(pc1));
        pc2Label.setText(formatProbability(pc2));
    }

    public void buttonStartOnAction() {
        executor.execute(pc1, pc2);
        fakeAlarmLabel.setText(formatError(executor.getFakeAlarm()));
        passDetectionLabel.setText(formatError(executor.getPassDetection()));
        classificationErrorLabel.setText(formatError(executor.getClassificationError()));
    }

    private static String formatProbability(double probability){
        return String.format("%1.2f", probability);
    }

    private static String formatError(double error){
        return String.format("%1.5f",error);
    }

}
