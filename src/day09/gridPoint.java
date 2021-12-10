package day09;

public class gridPoint {
    public int xCoord;
    public int yCoord;
    public int value;

    public boolean isLowPoint = false;


    public gridPoint(int x, int y, int value) {
        this.xCoord = x;
        this.yCoord = y;
        this.value = value;
    }

    public int getDangerLevel(){
        return this.value + 1;
    }

    public void setLowPoint(boolean lowPoint) {
        isLowPoint = lowPoint;
    }
}
