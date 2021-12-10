package day09;

public class main {

    public static void main(String[] args) {

        grid G =  new grid();
        System.out.println(G.getTotalDangerLevel());
        G.calculateBasins();


        int top = 0;
        int top2 = 0;
        int top3 = 0;
        for (basin b : G.basinList) {
            int basinsize = b.getBasinSize();
            if (basinsize > top3) {
                if(basinsize > top2) {
                    if(basinsize > top) {
                        top3 = top2;
                        top2 = top;
                        top = basinsize;
                    } else {
                        top3 = top2;
                        top2 = basinsize;
                    }
                } else {
                    top3 = basinsize;
                }
            }
        }

        System.out.println(top * top2 * top3);
    }

}
