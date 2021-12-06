package day05;

public class ventLine {
    public int x1;
    public int y1;
    public int x2;
    public int y2;

    public ventLine(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public boolean isHorizontal(){
        return this.y1 == this.y2;
    }

    public boolean isVertical(){
        return this.x1 == this.x2;
    }

    public boolean isDiagonal() {
        return !isHorizontal() && !isVertical() && Math.abs(x1 - x2) == Math.abs(y1 - y2);
    }


}
