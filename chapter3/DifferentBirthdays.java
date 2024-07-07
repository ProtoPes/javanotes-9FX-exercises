package chapter3;
/**
 * DifferentBirthdays
 *
 * Outline:
 * We need to ask 365 random people when they have a birthday
 * Then we need to count how many different days we have got
 *
 * Pseudocode:
 * Array of boolean
 * integer counter
 * loop from 1 to 365
 *      compute random number between 1 and 365
 *      if value by index == false
 *          counter++
 *          value == true
 *  print counter
 */
public class DifferentBirthdays {

    public static void main(String[] args) {

        boolean[] people = new boolean[365];
        int counter = 0;

        for (int i = 0; i < people.length; i++) {
            int randomNumber = (int)(Math.random()*365);
            if (people[randomNumber] == false) {
                counter++;
            }
            people[randomNumber] = true;
        }

        System.out.println();
        System.out.printf("We found %d different birthdays%n", counter);
        System.out.println();

        /* After this line is a solution from the book 
         * Just wanted to compare
         * */

        boolean used[];  // used[i] will be true if a person is found
                 // whose birthday is the i-th day of the year.

        used = new boolean[365];  // Initially, all entries are false!

        for (int i = 0; i < 365; i++) {
        // Select a random birthday and record it.
            int birthday;  // The selected birthday.
            birthday = (int)(Math.random()*365);
            used[birthday] = true;
        }

        int count = 0;

        for (int day = 0; day < 365; day++) {
        // If this day occurred as a birthday, count it.
            if (used[day] == true)
                count++;
        }

        System.out.println("Among 365 people, there were " + count
                                + " different birthdays.");
    }
}
