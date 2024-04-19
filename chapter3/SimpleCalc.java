package exercises.chapter3;

import textio.TextIO;

/**
 * SimpleCalc
    - Outline of a program:
        - Get input from user
        - Split input to operands and operator
        - Compute the result (Want to use `switch` statement)
        - Show the result
        - Repeat until user enters 0 as the first operand
 */
public class SimpleCalc {

    public static void main(String[] args) {

        double firstNumber;
        double secondNumber;
        double result;
        char operator;

        while (true) {
            System.out.println("Please type an expression, such as '3.2 * 4.5'");
            System.out.println("Type either full expression or the first number (0 for exit program)");
            firstNumber = TextIO.getDouble();
            if ( firstNumber == 0.0 ) {
                break;
            }
            if ( TextIO.peek() == '\n' ) {
                System.out.println("Type opperator (+, -, *, /):");
            }
            operator = TextIO.getChar();
            if ( TextIO.peek() == '\n' ) {
                System.out.println("Type second number:");
            }
            secondNumber = TextIO.getlnDouble();

            switch (operator) {
                case '+' -> {
                    result = firstNumber + secondNumber;
                }
                case '-' -> {
                    result = firstNumber - secondNumber;
                }
                case '*' -> {
                    result = firstNumber * secondNumber;
                }
                case '/' -> {
                    if ( secondNumber != 0.0 ){
                        result = firstNumber - secondNumber;
                    } else {
                        System.out.println("Cannot divide by 0, dummy! Try again");
                        continue;
                    }
                }
                default -> {
                    System.out.printf("Cannot understand '%s' operator, try again%n", operator);
                    continue;
                }
            }

            System.out.printf("Result is %1.2f%nCommencing anoter round!%n%n", result);
        }
        System.out.println("Exiting program...");
    }
}
