package drawTool;

import javafx.scene.chart.LineChart;
import method.algorithm.ProbabilityAlgorithm;
import method.algorithm.ProbabilityException;
import method.randomGenerator.RandomGenerator;

import static method.algorithm.ProbabilityAlgorithm.*;
import static method.randomGenerator.RandomGenerator.*;

public class Executor {

    private ChartDrawer drawer;
    private ProbabilityAlgorithm algorithm;
    private RandomGenerator generator;

    public Executor(LineChart<Number, Number> chart) {
        algorithm = new ProbabilityAlgorithm();
        drawer = new ChartDrawer(chart);
        generator = new RandomGenerator();
    }

    public void execute(double pc1, double pc2) {
        drawer.clear();

        algorithm.calculateErrors(pc1, pc2,
                procDensity(200, 500, pc1),
                procDensity(400, 700, pc2));
        procDelta();
    }

    public double getFakeAlarm() {
        try {
            return algorithm.getFakeAlarm();
        } catch (ProbabilityException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public double getPassDetection() {
        try {
            return algorithm.getPassDetection();
        } catch (ProbabilityException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public double getClassificationError() {
        try {
            return algorithm.getClassificationError();
        } catch (ProbabilityException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private double[] procDensity(double min, double max, double probability) {
        double[] density = calculateGaussian(
                generator.getPoints(COUNT, min, max), probability, COUNT);
        drawer.drawGraphic(density);
        return density;
    }

    private void procDelta() {
        try {
            drawer.drawDelta(algorithm.getDelta()
                    , algorithm.getMin()
                    , algorithm.getMax());
        } catch (ProbabilityException e) {
            e.printStackTrace();
        }
    }
}
