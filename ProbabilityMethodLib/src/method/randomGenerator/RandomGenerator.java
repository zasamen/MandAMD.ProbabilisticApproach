package method.randomGenerator;

import java.util.Random;

public class RandomGenerator {

    public static final double DEFAULT_MIN = 0;
    public static final double DEFAULT_MAX = 1;
    public static final int COUNT = 1000;
    private Random generator;

    public RandomGenerator() {
        generator = new Random();
    }

    public double[] getPoints() {
        return getPoints(COUNT, DEFAULT_MIN, DEFAULT_MAX);
    }

    public double[] getPoints(int count, double min, double max) {
        double[] points = new double[count];

        for (int i = 0; i < count; i++) {
            points[i] = min + (max - min) * generator.nextDouble();
        }
        return points;
    }
}
