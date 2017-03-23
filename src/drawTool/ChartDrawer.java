package drawTool;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;

import java.util.*;

public class ChartDrawer {

    private LineChart<Number, Number> lineChart;


    public ChartDrawer(LineChart<Number, Number> lineChart) {
        this.lineChart = lineChart;
        clear();
    }

    void clear() {
        lineChart.getData().clear();
    }

    void drawDelta(double delta,double min,double max) {
        Series<Number, Number> series = new Series<>();
        series.getData().add(new Data<>(delta, min));
        series.getData().add(new Data<>(delta, max));
        lineChart.getData().add(series);
    }

    void drawGraphic(double[] pointsY) {
        Series<Number, Number> series = new Series<>();
        series.getData().addAll(getDataList(pointsY));
        lineChart.getData().add(series);
    }

    private List<Data<Number, Number>> getDataList(double[] pointsY) {
        List<Data<Number, Number>> dataList = new ArrayList<>(pointsY.length);
        for (int i = 0; i < pointsY.length; i++) {
            dataList.add(new Data<>(i, pointsY[i]));
        }
        return dataList;
    }

}
