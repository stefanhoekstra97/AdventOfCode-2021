package day04;

import java.util.ArrayList;

public class dayFourBingocard {
    int[][] board;
    int[][] numbersHit;
    int lastHitNumber;

    public dayFourBingocard(int[][] board) {
        this.board = board;
        this.numbersHit = new int[5][5];
    }

    public void hitNumber(int number){
        this.lastHitNumber = number;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == number) {
                    numbersHit[i][j] = 1;
                }
            }
        }
    }

    public boolean checkForBingo() {
        boolean rows = checkRows();
        boolean columns = checkCols();

        return rows | columns;
    }

    public boolean checkCols() {
        for (int i = 0; i < board.length; i++){
            int count = 0;
            for (int j = 0; j < board.length; j++) {
                count += numbersHit[i][j];
            }
            if(count == 5){
                return true;
            }
        }
        return false;
    }


    public boolean checkRows() {
        for (int i = 0; i < board.length; i++){
            int count = 0;
            for (int j = 0; j < board.length; j++) {
                count += numbersHit[j][i];
            }
            if(count == 5){
                return true;
            }
        }
        return false;
    }

    public int getSumOfUnhitNumbers() {
        int sum = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if(numbersHit[i][j] != 1) {
                    sum += board[i][j];
                }
            }
        }
        return sum;
    }
}
