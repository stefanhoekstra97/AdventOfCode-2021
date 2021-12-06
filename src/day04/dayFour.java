package day04;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class dayFour {
    public ArrayList<dayFourBingocard> puzzles;
    public int[] rolls;

    public dayFour() {
        this.puzzles = new ArrayList<>();
        readPuzzles();
//        day04.dayFourBingocard winningCard = doBingo();
//        System.out.println(winningCard.getSumOfUnhitNumbers() * winningCard.lastHitNumber);

        dayFourBingocard losingCard = findTerminalCard();
        System.out.println(losingCard.getSumOfUnhitNumbers() * losingCard.lastHitNumber);
        System.out.println(losingCard.lastHitNumber);

        ArrayList<dayFourBingocard> singeltonPuzzlelist = new ArrayList<dayFourBingocard>();
        singeltonPuzzlelist.add(losingCard);
        dayFourBingocard losing =  doBingo(singeltonPuzzlelist);
        System.out.println(losing.lastHitNumber);
        System.out.println(losing.getSumOfUnhitNumbers() * losing.lastHitNumber);
    }

    private dayFourBingocard findTerminalCard() {
        ArrayList<Integer> bingoCardWhichWillWinFirst = doTerminalBingo();
        for (dayFourBingocard card : this.puzzles) {
            if (!bingoCardWhichWillWinFirst.contains(this.puzzles.indexOf(card))) {
                return card;
            }

        }
        throw new NoSuchElementException();
    }

    private ArrayList<Integer> doTerminalBingo() {
        ArrayList<Integer> winningboards = new ArrayList();
        boolean weNeedToStopNow = false;
        for (int i = 0; i < rolls.length; i++) {

            for (dayFourBingocard card : puzzles) {
             card.hitNumber(rolls[i]);
                if (winningboards.size() < puzzles.size()) {
                    if (card.checkForBingo()) {
                        if ( !weNeedToStopNow) {
                            if (winningboards.size() + 1 < puzzles.size()) {
                                if (!winningboards.contains(puzzles.indexOf(card))) {
                                    System.out.println("Added " + puzzles.indexOf(card));
                                    winningboards.add(puzzles.indexOf(card));
                                }
                            } else {
                                weNeedToStopNow = true;
                            }
                        } else {
                            return winningboards;
                        }

                    }
                } else {
                    System.out.println("Puzzles: " + puzzles.size() + " winning boards determined: " + winningboards.size());
                    return winningboards;
                }
            }
        }
        System.out.println("Puzzles: " + puzzles.size() + " winning boards determined: " + winningboards.size());
        return winningboards;
//        throw new NoSuchElementException();
    }


    private dayFourBingocard doBingo(ArrayList<dayFourBingocard> puzzles) {
        for (int roll : rolls) {
            for (dayFourBingocard card : puzzles) {
                card.hitNumber(roll);
                if (card.checkForBingo()) {
                    return card;
                }
            }
        }

        throw new NoSuchElementException();
    }


    public void readPuzzles() {
        File input = new File("F://AdventOfCode/Inputfiles/dayFour.txt");

        Scanner scnr = null;
        try {
            scnr = new Scanner(input);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        assert scnr != null;
        String rolls = scnr.next();
        this.rolls = Arrays.stream(rolls.split(",")).mapToInt(Integer::parseInt).toArray();

        while (scnr.hasNext()) {


            int[][] cols = new int[5][5];

            int digit;
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    digit = Integer.parseInt(scnr.next());
                    cols[i][j] = digit;
                }
            }
            dayFourBingocard card = new dayFourBingocard(cols);
            this.puzzles.add(card);
        }
    }

    public static void main(String[] args) {
        dayFour bingo = new dayFour();
    }
}
