package day09;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class grid {

    ArrayList<basin> basinList = new ArrayList<>();
    gridPoint[][] grid = new gridPoint[100][100];
    private int maxX;
    private int maxY;


    public grid() {
        String[] input = new String[0];
        try {
            input = Files.readString(Paths.get("F://AdventOfCode-2021/Inputfiles/dayNine.txt")).split("\r\n");
        } catch (IOException e) {
            e.printStackTrace();
        }


        for ( int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[i].toCharArray().length; j++) {
                gridPoint p = new gridPoint(i, j, Integer.parseInt(String.valueOf(input[i].charAt(j))));
                this.grid[i][j] = p;
                maxY = j;
            }
            maxX = i;
        }
    }
    
    public int getTotalDangerLevel() {
        int sumDanger = 0;

        for (gridPoint[] line : grid) {
            for (gridPoint p : line) {
                if (isLowerThanSurrounding(p)) {
                    sumDanger += p.getDangerLevel();
                }
            }
        }
        return sumDanger;
    }

    private boolean isLowerThanSurrounding(gridPoint point) {
        if (point.yCoord > 0) {
            if(point.value >= grid[point.xCoord][point.yCoord - 1].value) {
                return false;
            }
        }

        if (point.yCoord < maxY) {
            if(point.value >= grid[point.xCoord][point.yCoord + 1].value) {
                return false;
            }
        }

        if( point.xCoord > 0) {
            if(point.value >= grid[point.xCoord - 1][point.yCoord].value) {
                return false;
            }
        }

        if (point.xCoord < maxX) {
            if(point.value >= grid[point.xCoord + 1][point.yCoord].value) {
                return false;
            }
        }

        point.setLowPoint(true);

        basin b = new basin();
        b.addToBasin(point);
        basinList.add(b);


        return true;
    }

    public void calculateBasins() {
        for (basin b : this.basinList) {
            boolean pointAddedToList = false;
            boolean initial = true;

            while (initial || pointAddedToList) {
                initial = false;
                pointAddedToList = false;
                ArrayList<gridPoint> curList = (ArrayList<gridPoint>) b.getBasinPoints().clone();
                for (gridPoint point : curList) {
                    if (point.yCoord > 0) {
                        if (point.value < grid[point.xCoord][point.yCoord - 1].value && grid[point.xCoord][point.yCoord - 1].value < 9) {
                            if (!b.getBasinPoints().contains(grid[point.xCoord][point.yCoord - 1])) {
                                b.addToBasin(grid[point.xCoord][point.yCoord - 1]);
                                pointAddedToList = true;
                            }
                        }
                    }

                    if (point.yCoord < maxY) {
                        if (point.value < grid[point.xCoord][point.yCoord + 1].value && grid[point.xCoord][point.yCoord + 1].value < 9) {
                            if (!b.getBasinPoints().contains(grid[point.xCoord][point.yCoord + 1])) {
                                b.addToBasin(grid[point.xCoord][point.yCoord + 1]);
                                pointAddedToList = true;
                            }
                        }
                    }

                    if (point.xCoord > 0) {
                        if (point.value < grid[point.xCoord - 1][point.yCoord].value && grid[point.xCoord - 1][point.yCoord].value < 9) {
                            if (!b.getBasinPoints().contains(grid[point.xCoord - 1][point.yCoord])) {
                                b.addToBasin(grid[point.xCoord - 1][point.yCoord]);
                                pointAddedToList = true;
                            }
                        }
                    }

                    if (point.xCoord < maxX) {
                        if (point.value < grid[point.xCoord + 1][point.yCoord].value && grid[point.xCoord + 1][point.yCoord].value < 9) {
                            if (!b.getBasinPoints().contains(grid[point.xCoord + 1][point.yCoord])) {
                                b.addToBasin(grid[point.xCoord + 1][point.yCoord]);
                                pointAddedToList = true;
                            }
                        }
                    }

                }

            }
        }
    }


}
