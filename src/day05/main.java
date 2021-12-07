package day05;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class main {

    public static void main(String[] args) {
        File input = new File("F://AdventOfCode-2021/Inputfiles/dayFive.txt");

        Scanner scnr = null;
        try {
            scnr = new Scanner(input);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        grid g = new grid();

        assert scnr != null;
        while (scnr.hasNext()) {

            String xy1 = scnr.next();

            String[] xysplit = xy1.split(",");
            int x1 = Integer.parseInt(xysplit[0]);
            int y1 = Integer.parseInt(xysplit[1]);

            String arrow = scnr.next();


            String xy2 = scnr.next();
            xysplit = xy2.split(",");
            int x2 = Integer.parseInt(xysplit[0]);
            int y2 = Integer.parseInt(xysplit[1]);

            g.addLine(x1, y1, x2, y2);
        }

        g.initializeGrid();
        g.countAllLines();

        g.initializeGrid();
        g.CountIncludingDiagonals();

        System.out.println(g.getOverlappingInGrid());



    }
}
