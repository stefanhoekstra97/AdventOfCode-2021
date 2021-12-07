package day02;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class dayTwo {

    public static void main(String[] args) {
        File input = new File("F://AdventOfCode-2021/Inputfiles/dayTwo.txt");
        Scanner scnr = null;
        try {
            scnr = new Scanner(input);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        int horizontalMovement = 0;
        int verticalMovement = 0;
        int aim = 0;

        while (scnr.hasNextLine()) {
            String line = scnr.nextLine();

            if(line.contains("forward")) {
                int mutation = Integer.parseInt(line.substring("forward ".length()));
                horizontalMovement += mutation;
                verticalMovement += aim * mutation;

            } else if(line.contains("down")) {
                int mutation = Integer.parseInt(line.substring("down ".length()));
//                verticalMovement += mutation;
                aim += mutation;


            } else if(line.contains("up")){
                int mutation = Integer.parseInt(line.substring("up ".length()));
//                verticalMovement -= mutation;


                aim -= mutation;
            }

        }
        System.out.println(horizontalMovement * verticalMovement);

        return;
    }
}
