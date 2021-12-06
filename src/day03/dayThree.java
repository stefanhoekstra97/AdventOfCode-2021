package day03;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class dayThree {

    public static void main(String[] args) {
        File input = new File("F://AdventOfCode/Inputfiles/dayThree.txt");
        Scanner scnr = null;
        try {
            scnr = new Scanner(input);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ArrayList<Integer> listOfNumbers = new ArrayList<>();

        int lengthOfBits = -1;
        while (scnr.hasNext()){
            String item = scnr.next();
            if(lengthOfBits == -1){
                lengthOfBits = item.length();
            }
            listOfNumbers.add(Integer.valueOf(item, 2));
        }

        dayThree testObj = new dayThree(listOfNumbers, lengthOfBits);
        testObj.calculateGammaEpsilon();

        testObj.calculateOxygen();
        testObj.calculateCO2Scrubber();

        System.out.println(testObj.oxygenList);
        System.out.println(testObj.co2List);

        System.out.println("Result part 1 = " + ( testObj.epsilonRate * testObj.gammaRate));
        System.out.println("Life rating = " + testObj.oxygenList.get(0) * testObj.co2List.get(0));
    }

    private void calculateGammaEpsilon() {
        this.gammaRate = 0;
        this.epsilonRate = 0;

        for(int i = 0; i < baseBits; i++) {
            if (getAmountOfKthBitsSet(i, this.list) > (this.list.size() / 2)){
                this.gammaRate += Math.pow(2, i);
            } else {
                this.epsilonRate += Math.pow(2, i);
            }
        }

    }


    public ArrayList<Integer> list;
    public int baseBits;
    public ArrayList<Integer> oxygenList;
    public ArrayList<Integer> co2List;
    public int gammaRate;
    public int epsilonRate;


    public dayThree(ArrayList<Integer> list, int baseBits) {
        this.list = list;
        this.baseBits = baseBits;
        this.oxygenList = (ArrayList<Integer>) this.list.clone();
        this.co2List = (ArrayList<Integer>) this.list.clone();
    }


    public int getAmountOfKthBitsSet(int k, ArrayList<Integer> inputList){
        int amountOne = 0;
        for (Integer item : inputList) {
            if(((item>>k) & 1) == 1) {
                amountOne += 1;
            }
        }
        System.out.println("bits set: " + amountOne);
        return amountOne;
    }




    private void calculateOxygen() {

        for(int i = 0; i < baseBits; i++){
            int bitToHandle = baseBits - i -1;

            int amountOfBitsSet = getAmountOfKthBitsSet(bitToHandle, oxygenList);
            System.out.println("Size of remaining list: " + oxygenList.size());
            int mostDominantBit;
            if(oxygenList.size() % 2 == 0 && 2* amountOfBitsSet == oxygenList.size() ){
                mostDominantBit = 1;
            } else {
                mostDominantBit =  amountOfBitsSet > (oxygenList.size() / 2) ? 1 : 0;
            }
            oxygenList.removeIf(item -> ((item >> bitToHandle) & 1) != mostDominantBit);
            if(oxygenList.size() == 2){
                System.out.println(Integer.toBinaryString(oxygenList.get(0)));
                System.out.println(Integer.toBinaryString(oxygenList.get(1)));
            }
            if(oxygenList.size() == 1){ return; }
        }
    }

    private void calculateCO2Scrubber() {
        for(int i = 0; i < baseBits; i++){
            int bitToHandle = baseBits - i -1;

            int amountOfBitsSet = getAmountOfKthBitsSet(bitToHandle, co2List);
            System.out.println("Size of remaining list CO2: " + co2List.size());
            int leastDominantBit;
            if(co2List.size() % 2 == 0 && 2* amountOfBitsSet == co2List.size() ){
                leastDominantBit = 0;
            } else {
                leastDominantBit =  amountOfBitsSet > (co2List.size() / 2) ? 0 : 1;
            }
            co2List.removeIf(item -> ((item >> bitToHandle) & 1) != leastDominantBit);
            if(co2List.size() == 2){
                System.out.println(Integer.toBinaryString(co2List.get(0)));
                System.out.println(Integer.toBinaryString(co2List.get(1)));
            }
            if(co2List.size() == 1){ return; }
        }
    }
}
