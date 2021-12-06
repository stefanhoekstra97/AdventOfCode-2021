package day05;

import java.util.ArrayList;

public class grid {
    ArrayList<ventLine> lines;
    int maxX1 = Integer.MIN_VALUE;
    int maxY1 = Integer.MIN_VALUE;
    int maxX2 = Integer.MIN_VALUE;
    int maxY2 = Integer.MIN_VALUE;

    int[][] countingGrid;


    public grid(){
        this.lines = new ArrayList<ventLine>();
    }


    public void addLine(int x1, int y1, int x2, int y2) {
        ventLine newline = new ventLine(x1, y1, x2, y2);
        this.lines.add(newline);


        if(x1 > this.maxX1) {
            this.maxX1 = x1 + 1;
        }

        if(y1 > this.maxY1) {
            this.maxY1 = y1 + 1;
        }

        if(x2 > this.maxX2) {
            this.maxX2 = x2 + 1;
        }

        if(y2 > this.maxY2) {
            this.maxY2 = y2 + 1;
        }
    }

    public void initializeGrid() {
        int maxX = Integer.max(this.maxX1, this.maxX2);
        int maxY = Integer.max(this.maxY1, this.maxY2);
        this.countingGrid = new int[maxX+1][maxY+1];
    }

    public void countAllLines() {
        for (ventLine line : this.lines) {
            if (line.isHorizontal() ) {
                for (int i = 0; i <= Math.abs(line.x1 - line.x2); i++) {
                    this.countingGrid[line.y1][Math.min(line.x1, line.x2) + i] += 1;
                }

            } else if(line.isVertical()) {
                for (int i = 0; i <= Math.abs(line.y1 - line.y2); i++) {
                    this.countingGrid[Math.min(line.y1, line.y2) + i][line.x1] += 1;
                }
            }
        }
    }

    public int getOverlappingInGrid() {
        int amtOverlap = 0;
        for (int i = 0; i < countingGrid.length; i++) {
            for (int j = 0; j < countingGrid[i].length; j++) {
                if(countingGrid[i][j] >= 2) {
                    amtOverlap += 1;
                }
            }
        }
        return amtOverlap;
    }


    public void getMissingLines() {
        for (ventLine line :
                this.lines) {
            if (!line.isVertical() && !line.isHorizontal() && !line.isDiagonal()) {
                System.out.println(line.x1 + ", " + line.y1 + " -> " + line.x2 + ", " + line.y2);
            }
        }
    }

    public void CountIncludingDiagonals() {
        countAllLines();

        for (ventLine line : lines) {
            int modX = 0;
            int modY = 0;
            if (line.isDiagonal()) {
                if(line.x1 < line.x2) {
                    modX = 1;
                } else {
                    modX = -1;
                }
                if (line.y1 < line.y2) {
                    modY = 1;
                } else {
                    modY = -1;
                }
                for (int i = 0; i <= Math.abs(line.x1 - line.x2) ; i++) {
                    countingGrid[line.y1 + modY*i][line.x1 + modX*i] += 1;
                }

            }

        }
    }
}
