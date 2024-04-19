package exercises.chapter4;

import javax.swing.SpringLayout;

import textio.TextIO;
/**
 * Counduct an addition quiz with 10 questions.
 * Numbers are chosen at random between 0 and 100.
 * Questions will be like "24 + 53 ?".
 */
public class AdditionQuiz {

    private static int[] leftNumbers = new int[10];
    private static int[] rightNumbers = new int[10];
    private static int[] userAnswers = new int[10];

    public static void main(String[] args) {
        create();
        System.out.println("The quiz is starting!");
        conduct();
        System.out.println();
        System.out.println("Thank tou for the answers! Lets find out if you were right!");
        evaluate();
    }

    /**
     * Fill two global arrays with random numbers between 0 and 100.
     */
    private static void create() {
        for (int i = 0; i < 10; i++) {
            leftNumbers[i] = (int)(Math.random()*101);
            rightNumbers[i] = (int)(Math.random()*101);
        }
    }

    /**
     * Administer the quiz: ask questions and store users answers.
     */
    private static void conduct() {
        for (int i = 0; i < 10; i++) {
            System.out.printf("%3d + %3d = ? ", leftNumbers[i], rightNumbers[i]);
            userAnswers[i] = TextIO.getlnInt();
        }
    }

    /**
     * Grade the quiz: show each question again with the user's answers
     * and tell if user was correct.
     * Show the total grade in points whereas 1 correct answer will give 10 points.
     */
    private static void evaluate() {
        int correctAnswers = 0;
        for (int i = 0; i < 10; i++) {
            int rightAnswer = leftNumbers[i] + rightNumbers[i];
            System.out.printf("%3d + %3d ", leftNumbers[i], rightNumbers[i]);
            System.out.printf("Your answer: %3d ", userAnswers[i]);
            if (userAnswers[i] == rightAnswer) {
                System.out.println("Correct!");
                correctAnswers++;
            } else {
                System.out.printf("Sorry, but it's %3d%n", rightAnswer);
            }
        }
        System.out.println();
        System.out.println("Your total score: " + (correctAnswers * 10));
    }
}
