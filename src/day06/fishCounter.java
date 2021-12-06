package day06;

import java.math.BigInteger;

public class fishCounter {
    BigInteger[] fish = new BigInteger[9];

    public fishCounter(int[] inputFish) {
        for (int i = 0; i < 9; i++) {
            this.fish[i] = new BigInteger("0");
        }
        for (int fish : inputFish) {
            this.fish[fish] = this.fish[fish].add(new BigInteger("1"));
        }
    }


    public void progressFishOneDay() {
        BigInteger newfish = new BigInteger(this.fish[0].toString());

        for (int i = 1; i < this.fish.length; i++) {
            this.fish[i - 1] = new BigInteger(this.fish[i].toString());
        }
        this.fish[8] = newfish;
        this.fish[6] = this.fish[6].add(newfish);
    }

    public void progressXDays(int days) {
        for (int i = 0; i < days; i++) {
            progressFishOneDay();
        }
    }

    public BigInteger countCurrentFish() {
        BigInteger totalFish = BigInteger.ZERO;
        for (BigInteger fish : this.fish) {
            totalFish = totalFish.add(fish);
        }
        return totalFish;
    }




}
