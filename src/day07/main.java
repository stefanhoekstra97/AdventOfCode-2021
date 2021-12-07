package day07;

import org.apache.commons.math3.stat.descriptive.moment.Mean;
import org.apache.commons.math3.stat.descriptive.rank.Median;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class main {

    public static void main(String[] args) throws IOException {

        int[] input = Arrays.stream(
                Files.readString(Paths.get("F://AdventOfCode-2021/Inputfiles/daySeven.txt")).split(","))
                .mapToInt(Integer::parseInt).toArray();
        int median = getMedian(input);

        int dist = cumulativeDistanceFromValue(input, median);
        System.out.println("Normal distance = " + dist);

        int mean = (int) Math.round(getMean(input));
        System.out.println("Mean value rounded= " + mean);

        int fuelCostBad = increasingFuelCostCalculation(input, mean -1 );
        System.out.println("possible alignment: " + fuelCostBad);

        int fuelCostGood = increasingFuelCostCalculation(input, mean);
        System.out.println("possible alignment = " + fuelCostGood);
    }

    public static int getMedian(int[] inputNumbers) {
        Median med = new Median();
        double medValue = med.evaluate(Arrays.stream(inputNumbers).mapToDouble(Double::valueOf).toArray());
        return (int) (medValue);
    }

    public static double getMean(int[] numbers) {
        Mean m = new Mean();
        double mean = m.evaluate(Arrays.stream(numbers).mapToDouble(Double::valueOf).toArray());
        return mean;
    }

    public static int cumulativeDistanceFromValue(int[] input, int value) {
        int totalDist = 0;

        for (int num : input) {
            totalDist += Math.abs(num - value);
        }

        return totalDist;
    }


    public static int increasingFuelCostCalculation(int[] input, int value) {
        int sumOfFuel = 0;

        for (int num : input) {
            int N = Math.abs(num - value);
            sumOfFuel += (int) (((long) (N * (N + 1))) / 2);

        }

        return sumOfFuel;
    }

}
