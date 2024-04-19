package exercises.chapter4;

import textio.TextIO;

/**
 * ConvertToBase10
 * Reads input user and assumes its a hexadecimal number
 * converts it in base-10 number
 */
public class ConvertToBase10 {

    public static void main(String[] args) {
        int value = 0;
        String userInput;
        System.out.println("Please input a hexadecimal number:");
        userInput = TextIO.getln();
        for (int i = 0; i < userInput.length(); i++) {
            int curDigit = hexValue(userInput.charAt(i));
            if (curDigit == -1) {
                System.out.println("Your input is not a hexadecimal number! Aborting program...");
                System.exit(1);
            }
            value = value*16 + curDigit;
        }
        System.out.println("Your number in base-10 is " + value);
    }

    private static int hexValue(char ch) {
        switch (ch) {
            case '1','2','3','4','5','6','7','8','9' -> {
                return ch - 48;
            }
            case 'a','b','c','d','e','f' -> {
                return ch - 87;
            }
            case 'A','B','C','D','E','F' -> {
                return ch - 55;
            }
            default -> {
                return -1;
            }
        }
    }
}
