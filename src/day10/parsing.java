package day10;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class parsing {
    String[] input;
    HashMap<String, String> counterparts = new HashMap<>();
    ArrayList<String> toBeCompleted = new ArrayList<>();
    ArrayList<Long> autocompleteScores = new ArrayList<>();

    public parsing() {
        try {
            this.input = Files.readString(Paths.get("F://AdventOfCode-2021/Inputfiles/dayTen.txt")).lines().toArray(String[]::new);
        } catch (IOException e) {
            e.printStackTrace();
        }
        counterparts.put(">", "<");
        counterparts.put(")", "(");
        counterparts.put("}", "{");
        counterparts.put("]", "[");
    }

    public void part2() {

        for (String toComplete: toBeCompleted) {
            long sum = 0;
            for (int i = 0; i < toComplete.length(); i++) {
                int ind = toComplete.length() - i - 1;
                sum *= 5;
                switch (toComplete.charAt(ind)) {
                    case '(' -> sum += 1;
                    case '[' -> sum += 2;
                    case '{' -> sum += 3;
                    case '<' -> sum += 4;
                }
            }
            this.autocompleteScores.add(sum);
        }

        Collections.sort(autocompleteScores);

        if(autocompleteScores.size()% 2 == 0) {
            System.out.println("AutoComplete score = " + autocompleteScores.get(autocompleteScores.size() / 2));
        } else {
            System.out.println("AutoComplete score = " + autocompleteScores.get(autocompleteScores.size() / 2));
            System.out.println("AutoComplete score = " + autocompleteScores.get((autocompleteScores.size() + 1) / 2));

        }
    }

    public void part1() {
        int sum = 0;
        for (String input : this.input) {
            sum += parseLine(input);
        }
        System.out.println("Total Syntax error score: " + sum);
    }

    private int parseLine(String input) {
        String opened = "";
        char errorChar = '0';

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if(c == '(' || c == '{' || c == '[' || c == '<') {
                opened = opened + c;
            } else {
                if( i > 0 && counterparts.get(String.valueOf(c)).toCharArray()[0] == (opened.charAt(opened.length() - 1))) {
                    opened = opened.substring(0, opened.length() - 1);
                } else if (opened.length() > 0){
                    errorChar = c;
                    break;
                }
            }
        }

        if(!opened.equals("") && errorChar == '0') {
            System.out.println(opened);
            this.toBeCompleted.add(opened);
        }

        int errorValue = switch (errorChar) {
            case ')' -> 3;
            case '}' -> 1197;
            case ']' -> 57;
            case '>' -> 25137;
            default -> 0;
        };

        return errorValue;
    }

    public static void main(String... args){
        parsing p = new parsing();
        p.part1();
        p.part2();
    }

}
