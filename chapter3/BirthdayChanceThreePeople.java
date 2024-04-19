package exercises.chapter3;

/**
 * BirthdayChanceThreePeople
 * This program is simulating the BirthdayProblem for three people.
 *
 * Outline:
 *
 * create an array of integers
 * in infinite loop
 *      add + 1 to count
 *      
 *      randomly access array
 *      add 1 to value
 *      check if value equals 3
 *          break the loop
 *
 */
public class BirthdayChanceThreePeople {

    public static void main(String[] args) {
        int[] used = new int[365];
        int count = 0;

        while (true) {
            int birthday = (int)(Math.random()*365);
            count++;

            System.out.printf("Person %d has birthday number %d%n", count, birthday);

            if ( used[birthday] == 2 ) {
                break;
            }

            used[birthday]++;
        }

        System.out.println();
        System.out.println("A truplicate birthday was found after " + count + " tries.");

    }
}
