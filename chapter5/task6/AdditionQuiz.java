package exercises.chapter5.task6;

import textio.TextIO;
/**
 * Counduct an addition quiz with 10 questions.
 * Numbers are chosen at random between 0 and 100.
 * Questions will be like "24 + 53 ?".
 * @author Nikolai Pavlov
 */
public class AdditionQuiz {

    private static int[] userAnswers = new int[10];
    private static AdditionQuestion[] questions = new AdditionQuestion[10];

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
            questions[i] = new AdditionQuestion();
        }
    }

    /**
     * Administer the quiz: ask questions and store users answers.
     */
    private static void conduct() {
        for (int i = 0; i < 10; i++) {
            System.out.print(questions[i].getQuestion() + " ");
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
            int rightAnswer = questions[i].getCorrectAnswer();
            System.out.print(questions[i].getQuestion() + " ");
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
