package exercises.chapter4;

import textio.TextIO;

/**
 * CapitalizeString
 * This class asks user to type a string each word of which will be capitalized
 */
public class CapitalizeString {

    /**
     * There is 2 methods `printCapitalized` existing:
     * the one that receive only String parameter uses array
     * the one that receive one String and one boolean uses iterarion over each character
     **/
    public static void main(String[] args) {
        String userInput;
        System.out.println("Please type a sentence:");
        userInput = TextIO.getln();
        System.out.println();
        System.out.println("Your capitalized sentence using word iteration:");
        printCapitalized(userInput);
        System.out.println();
        System.out.println("Your capitalized sentence using character iteration:");
        printCapitalized(userInput, true);
    }

    /**
     * Take the string and print a new string with each word capitalized
     * This method uses String.split function and then iterates over the array
     * and replaces the first letter of each word with capitalized version of it
     **/
    private static void printCapitalized(String userInput) {
        String[] splittedString = userInput.split("\\W"); //regex that match any whitespace character

        for (int i = 0; i < splittedString.length; i++) {
            char firstChar = splittedString[i].charAt(0);
            if ( Character.isLetter(firstChar) ) {
                splittedString[i] = Character.toUpperCase(firstChar) + splittedString[i].substring(1);
            }
        }

        System.out.println(String.join(" ", splittedString));

    }

    /**
     * Take the string and print a new string with each word capitalized
     * This method iterates over every character of a string and prints
     * every character if its the first letter of a word and a letter
     **/
    private static void printCapitalized(String userInput, boolean flag) {
        for (int i = 0; i < userInput.length(); i++) {
            char currentChar = userInput.charAt(i);
            if ( Character.isLetter(currentChar) && (i == 0 || !Character.isLetter(userInput.charAt(i - 1))) ) {
                currentChar = Character.toUpperCase(currentChar);
            }
            System.out.print(currentChar);
        }
        System.out.println();
    }
}
