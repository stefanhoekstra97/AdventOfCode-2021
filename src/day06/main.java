package day06;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class main {

    public static void main(String[] args) {
        File input = new File("F://AdventOfCode-2021/Inputfiles/daySix.txt");

        Scanner scnr = null;
        try {
            scnr = new Scanner(input);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String initialState = scnr.next();
        int[] initialFish = Arrays.stream(initialState.split(",")).mapToInt(Integer::parseInt).toArray();


        fishCounter counter = new fishCounter(initialFish);

        counter.progressXDays(18);
        System.out.println(counter.countCurrentFish());

        counter.progressXDays(80-18);
        System.out.println(counter.countCurrentFish());


        counter.progressXDays(256-80);
        System.out.println(counter.countCurrentFish());
    }
}
