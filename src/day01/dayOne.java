package day01;

import java.io.Console;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class dayOne {
    public static int countIncreases(ArrayList<Integer> list){
        int increases = 0;
        Integer previous = null;

        for (Integer a : list){
            if(previous == null){
                previous = a;
                continue;
            }

            if(previous < a){
                increases++;
            }
            previous = a;
        }
        return increases;
    }


    public static void main(String[] args){
        ArrayList<Integer> list = new ArrayList<>();

        for (String number : args) {
            list.add(Integer.valueOf(number));
        }

        ArrayList<Integer> slidingList = new ArrayList<>();
        for (int i = 0; i + 2 < list.size(); i++) {
            Integer sum = list.get(i) +list.get(i+1) +list.get(i + 2);
            slidingList.add(sum);
        }

        System.out.println("end while");
        System.out.println(countIncreases(slidingList));

    }
}
