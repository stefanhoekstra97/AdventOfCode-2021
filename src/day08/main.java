package day08;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class main {
    // Length of known digit reprs.
    final int ONE = 2;
    final int FOUR = 4;
    final int SEVEN = 3;
    final int EIGHT = 7;

    String[] fullInput;

    public main() throws IOException {
        this.fullInput =  Files.readString(Paths.get("F://AdventOfCode-2021/Inputfiles/dayEight.txt")).split("\r\n");
    }

    public static void main(String[] args) throws IOException {
        main m = new main();
        System.out.println(m.part1());
        System.out.println(m.part2());
    }

    private int part2() {
        int sum = 0;
        for (String inputLine: this.fullInput) {
            String[] inputSplit = Arrays.stream(inputLine.split("\\|")).filter(s -> !s.isEmpty()).toArray(String[]::new);
            sum += calcRepr(inputSplit[0].split(" "), inputSplit[1].split(" "));
        }

        return sum;
    }

    public int calcRepr(String[] entries, String[] repr) {
        String[] digits = new String[10];
        // Find known values:
        for (String t : entries) {
            if(t.length() == ONE ) {
                digits[1] = t;
            } else if (t.length() == SEVEN ) {
                digits[7] = t;
            } else if( t.length() == FOUR ) {
                digits[4] = t;
            } else if ( t.length() == EIGHT) {
                digits[8] = t;
            }
        }

        for (String t : entries) {
            if(t.length() == 6) {
// Digit repr is either zero, 6 or 9
                if(amtCharDiff(t, digits[1]) == 1){
                    digits[6] = t;
                } else if(amtCharDiff(t, digits[4]) == 1) {
                    digits[0] = t;
                } else {
                    digits[9] = t;
                }

            } else if(t.length() == 5) {
// Digit repr is either 2, 3 or 5
                if(amtCharDiff(t, digits[1]) == 0) {
                    // Number must be 6
                    digits[3] = t;
                } else if(amtCharDiff(t, digits[4]) == 1) {
                    // Must be 0
                    digits[5] = t;
                } else {
                    digits[2] = t;
                }
            }
        }

        StringBuilder b = new StringBuilder();


        for (String num : repr) {
            if(!num.isEmpty()) {
                b.append(getDigitFromRepr(num, digits));
            }
        }
        return Integer.parseInt(b.toString());
    }

    private int getDigitFromRepr(String repr, String[] digits) {
        for (int i = 0; i < digits.length; i++) {
            if(isReprEqual(repr, digits[i])) {
                return i;
            }
        }
        return -1;
    }

    private boolean isReprEqual(String repr1, String repr2) {
        for (char a : repr1.toCharArray()) {
            if(repr2.indexOf(a) == -1) {
                return false;
            }
        }

        for (char a : repr2.toCharArray()){
            if(repr1.indexOf(a) == -1) {
                return false;
            }
        }
        return true;
    }

    // Is Characters in Chars all present in input?
    private int amtCharDiff(String input, String chars) {
        int charDiff = 0;
        for (char a : chars.toCharArray()) {
            if(input.indexOf(a) == -1) {
                charDiff += 1;
            }
        }
        return charDiff;
    }

    private int part1() {
        ArrayList<String> secondPartOfInput = new ArrayList<>();
        for (String s :  this.fullInput) {
            secondPartOfInput.addAll(Arrays.stream(s.split("\\|")[1].split(" ")).collect(Collectors.toList()));
        }
        int result = (int) secondPartOfInput.stream().filter(s -> s.length() == 2 || s.length() == 3 || s.length() == 7 || s.length() == 4).count();
        return result;
    }
}
