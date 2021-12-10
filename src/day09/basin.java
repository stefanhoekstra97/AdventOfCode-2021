package day09;

import java.util.ArrayList;

public class basin {
    private ArrayList<gridPoint> basinPoints;

    public basin(){
        basinPoints = new ArrayList<>();
    }

    public void addToBasin(gridPoint p) {
        basinPoints.add(p);
    }

    public int getBasinSize() {
        return basinPoints.size();
    }

    public ArrayList<gridPoint> getBasinPoints() {
        return this.basinPoints;
    }

    public void changeList(ArrayList<gridPoint> newlist) {
        this.basinPoints = newlist;
    }
}
