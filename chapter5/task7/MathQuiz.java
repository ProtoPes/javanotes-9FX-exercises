package exercises.chapter5.task7;


import java.util.Random;

import textio.TextIO;
/**
 * Counduct an math quiz with 10 questions.
 * Numbers are chosen at random between 0 and 100.
 * Questions will be like "24 + 53 ?" or "30 - 21 ?".
 * Apart from this there will be two interesting non-math questions.
 * @author Nikolai Pavlov
 */
public class MathQuiz {

    private static int[] userAnswers = new int[10];
    private static IntQuestion[] questions = new IntQuestion[10];

    public static void main(String[] args) {
        create();
        System.out.println("The quiz is starting!");
        conduct();
        System.out.println();
        System.out.println("Thank tou for the answers! Lets find out if you were right!");
        evaluate();
    }

    /**
     * Initialize the questions and store it in the array.
     * Addition and Division questions are randomly placed,
     * There will be 2 non-math questions
     */
    private static void create() {

        IntQuestion bigQuestion = new IntQuestion() {
            public String getQuestion() {
                return "What is the answer to the ultimate question " +
                        " of life, the universe, and everything?";
            }
            public int getCorrectAnswer() {
                return 42;
            }
        };

        IntQuestion randomQuestion = new IntQuestion() {
            public String getQuestion() {
                return "What is the most random number between 1 and 100?";
            }
            public int getCorrectAnswer() {
                return 37;
            }
        };

        Random random = new Random();
        int bigQIndex;
        int randQIndex;
        do {
            bigQIndex = random.nextInt(10);
            randQIndex = random.nextInt(10);
        } while (bigQIndex == randQIndex);
        for (int i = 0; i < 10; i++) {
            double randomNum = random.nextDouble();
            if ( i ==  bigQIndex ) {
                questions[i] = bigQuestion;
                continue;
            }
            if ( i ==  randQIndex ) {
                questions[i] = randomQuestion;
                continue;
            }
            if ( randomNum < 0.5 ) {
                questions[i] = new AdditionQuestion();
            } else {
                questions[i] = new SubtractionQuestion();
            }
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
