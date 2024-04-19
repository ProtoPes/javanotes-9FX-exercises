package exercises.chapter5.task7;

/**
 * SubtractionQuestion
 *
 */
public class SubtractionQuestion implements IntQuestion{

    private int a, b;
        // The numbers in the problem.
        public SubtractionQuestion() { // constructor
            a = (int)(Math.random() * 50 + 1);
            b = (int)(Math.random() * 50);
            if ( b > a ) {
                int temp = a;
                a = b;
                b = temp;
            }
        }

        public String getQuestion() {
            return "What is " + a + " - " + b + " ?";
        }

        public int getCorrectAnswer() {
            return a - b;
        }
}
