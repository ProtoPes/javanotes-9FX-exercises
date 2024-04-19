import textio.TextIO;
/**
 * NameParser
 */
public class NameParser {

    public static void main(String[] args) {

        System.out.println("Please, enter your Name and Last name, separated by a space:");
        String userInput = TextIO.getln();
        int indexOfSpace = userInput.indexOf(' ');
        String firstName = userInput.substring(0, indexOfSpace);
        String lastName = userInput.substring(indexOfSpace + 1);
        System.out.printf("Your first name is %s, which has %d charachters%n", firstName, firstName.length());
        System.out.printf("Your last name is %s, which has %d charachters%n", lastName, lastName.length());
        System.out.printf("Your initials are %s%s%n", firstName.charAt(0), lastName.charAt(0));

    }

}
