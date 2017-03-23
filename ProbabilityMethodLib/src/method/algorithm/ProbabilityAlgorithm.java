package method.algorithm;

import javafx.util.Pair;
import org.jetbrains.annotations.Contract;

import static java.lang.Math.*;

public class ProbabilityAlgorithm {

    private double fakeAlarm;
    private double passDetection;
    private double classificationError;
    private boolean calculated = false;
    private double min;
    private double max;
    private int delta;
    private boolean startWithFirstSet;


    public double getFakeAlarm() throws ProbabilityException {
        if (!calculated) {
            throw new ProbabilityException("Errors not Calculated");
        }
        return fakeAlarm;
    }

    public double getPassDetection() throws ProbabilityException {
        if (!calculated) {
            throw new ProbabilityException("Errors not Calculated");
        }
        return passDetection;
    }

    public double getClassificationError() throws ProbabilityException {
        if (!calculated) {
            throw new ProbabilityException("Errors not Calculated");
        }
        return classificationError;
    }

    public double getMin() throws ProbabilityException {
        if (!calculated) {
            throw new ProbabilityException("Errors not Calculated");
        }
        return min;
    }

    public double getMax() throws ProbabilityException {
        if (!calculated) {
            throw new ProbabilityException("Errors not Calculated");
        }
        return max;
    }

    public int getDelta() throws ProbabilityException {
        if (!calculated) {
            throw new ProbabilityException("Errors not Calculated");
        }
        return delta;
    }

    public void calculateErrors(double probC1, double probC2, double[] set1, double[] set2) {

        setMinAndMax(set1, set2);

        delta = calculateDeltaPointNumber(set1, set2);

        if (startWithFirstSet) {
            calcFaceAlarmAndPassDetection(set1, set2);
        } else {
            calcFaceAlarmAndPassDetection(set2, set1);
        }

        classificationError = fakeAlarm + passDetection;

        calculated = true;
    }

    private void calcFaceAlarmAndPassDetection(double[] set1,double[] set2) {
        fakeAlarm = calculateFakeAlarm(set1);
        passDetection = calculatePassDetection(set2);
    }

    @Contract(pure = true)
    private double calculateFakeAlarm(double[] set) {
        double result = 0;
        for (int i = 0; i < delta; i++) {
            result += set[i];
        }
        return result;
    }

    @Contract(pure = true)
    private double calculatePassDetection(double[] set) {
        double result = 0;
        for (int i = delta; i < set.length; i++) {
            result += set[i];
        }
        return result;
    }


    public static double[] calculateGaussian
            (double[] randoms, double probability, int count) {
        double mu = calculateMathExpectation(randoms);
        double sigma = calculateStandardDeviation(randoms, mu);
        double[] result = new double[count];
        for (int i = 0; i < count; i++) {
            result[i] = exp((-0.5) * sqr(((double) 0 + i - mu) / sigma))
                    / (sigma * sqrt(PI * 2)) * probability;
        }
        return result;
    }

    private int calculateDeltaPointNumber
            (double[] firstSet, double[] secondSet) {
        int count = min(firstSet.length, secondSet.length);
        double previous = abs(firstSet[0] - secondSet[0]);
        double current = abs(firstSet[1] - secondSet[1]);
        for (int i = 2; i < count; i++) {
            double next = abs(firstSet[i] - secondSet[i]);
            if (current <= previous && current <= next) {
                startWithFirstSet = firstSet[i - 1] < secondSet[i - 1];
                return i;
            }
            previous = current;
            current = next;
        }
        return count;
    }

    @Contract(pure = true)
    private static double calculateMathExpectation(double[] numbers) {
        double mu = 0;
        for (double number : numbers) {
            mu += number;
        }
        return mu / numbers.length;
    }


    private static double calculateStandardDeviation(double[] numbers, double mathExpectation) {
        double sigma = 0;
        for (double number : numbers) {
            sigma += Math.pow(number - mathExpectation, 2);
        }
        return sqrt(sigma / numbers.length);
    }

    private static double sqr(double a) {
        return Math.pow(a, 2);
    }


    private void setMinAndMax(double[] array1, double[] array2) {
        Pair<Double, Double> pair1 = getMinMaxPair(array1);
        Pair<Double, Double> pair2 = getMinMaxPair(array2);
        min = min(pair1.getKey(), pair2.getKey());
        max = max(pair1.getValue(), pair2.getValue());
    }

    private Pair<Double,Double> getMinMaxPair(double[] array) {
        double min = 0;
        double max = 0;
        for (double d : array) {
            if (d < min) min = d;
            else if (d > max) max = d;
        }
        return new Pair<>(min, max);
    }
}
